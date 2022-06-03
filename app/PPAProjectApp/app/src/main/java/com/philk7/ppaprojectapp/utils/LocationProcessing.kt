package com.philk7.ppaprojectapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.philk7.ppaprojectapp.R
import kotlin.math.abs

object LocationProcessing {

    /**
     * Reads the last indicated place string.
     * @param context: caller context
     * @return the place string stored in sharedPref
     */
    fun readLastPlace(context: Context): String {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            context.resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val place: String
        with(sharedPref.edit()) {
            place = sharedPref.getString(LOCATION_KEY, "").toString()
            this.apply()
        }

        return place
    }


    /**
     * Writes the currently indicated place string.
     *  This also shifts the previous indicated location to the previous location cache.
     * @param context: caller context
     * @param place: place (see R.array.place_examples) just indicated (or custom)
     */
    fun writeLastPlace(context: Context, place: String) {
        val previousLastPlace = readLastPlace(context)  // save current value to previous

        val sharedPref: SharedPreferences = context.getSharedPreferences(
            context.resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            this.putString(LOCATION_KEY, place)
            this.putString(PREV_LOCATION_KEY, previousLastPlace)
            this.apply()
        }
    }


    /**
     * Checks whether the user's (theoretical) location has changed,
     *  according to given thresholds and the observed step counts.
     *  Also updates the steps counter cache (steps count last location change)
     *  if a location change was detected.
     * @param context: caller context
     * @return whether the location seems to have changed since the last steps cache update
     */
    fun checkStepsLocationChanged(context: Context): Boolean {
        var placeChanged: Boolean
        val sharedPref = context.getSharedPreferences(
            context
                .resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )

        with(sharedPref.edit()) {
            val stepsNow = sharedPref.getInt(STEPS_KEY, 0)
            val stepsLastLocationChange = sharedPref.getInt(STEPS_CACHE_KEY, 0)
            val difference = abs(stepsNow - stepsLastLocationChange)

            // considers location changed when threshold surpassed
            placeChanged = difference > STEPS_PLACE_CHANGED_THRESHOLD
            if (placeChanged) this.putInt(
                STEPS_CACHE_KEY,
                stepsNow
            )  // update cache when place changed

            this.apply()
        }

        return placeChanged
    }


    /**
     * Checks whether the user's indicated location has changed, based on the string values
     *  in sharedPref. Might be called after asking to indicate the place.
     * @param context: caller context
     * @return whether two successively indicated locations are different
     */
    fun checkIndicatedLocationChanged(context: Context): Boolean {
        val sharedPref: SharedPreferences = context.getSharedPreferences(
            context.resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val place: String
        val previousPlace: String

        with(sharedPref.edit()) {
            place = sharedPref.getString(LOCATION_KEY, "") ?: ""
            previousPlace = sharedPref.getString(PREV_LOCATION_KEY, "") ?: ""
            this.apply()
        }

        return place != previousPlace
    }


    const val STEPS_KEY = "step_counter"  // written to upon step counter sensor firing
    const val STEPS_CACHE_KEY = "steps_location_last_changed"
    const val LOCATION_KEY = "last_location_indicated"
    const val PREV_LOCATION_KEY = "previous_location_indicated"

    /*
    On average, about 135 steps equal 100 m of walking.
    Configure the next thresholds based on this fact.
     */
    private const val HUNDRED_METERS_PLACE_CHANGED_THRESHOLD: Float = 1.0F  // 100 m
    const val STEPS_PLACE_CHANGED_THRESHOLD = 135 * HUNDRED_METERS_PLACE_CHANGED_THRESHOLD


}