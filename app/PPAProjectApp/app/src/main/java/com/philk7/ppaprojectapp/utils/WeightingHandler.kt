package com.philk7.ppaprojectapp.utils

import android.content.Context
import android.widget.Toast
import com.philk7.ppaprojectapp.R
import org.json.JSONException
import org.json.JSONObject

object WeightingHandler {

    private const val DEVICE_WEIGHTS_CACHE_KEY = "device_weights_cache"
    private const val DATA_WEIGHTS_CACHE_KEY = "data_weights_cache"

    // two-power for higher machine accuracy without rounding
    private const val WEIGHT_STEP_SIZE: Double = 0.0625  // 1/16 -> 17 steps
    private const val RECOMMENDATION_THRESHOLD = 0.5


    /**
     * Computes the initial weights for a weights cache, depending on which one.
     *  For device weights, all weights are initialized equal.
     *  For data weights, the weights are chosen specifically and distinct.
     * @param forData: whether the weights for data types are calculated or not
     * @param context: caller context, only needed when computing device type weights
     */
    private fun computeInitialWeights(forData: Boolean, context: Context? = null): JSONObject {
        val jobject = JSONObject()
        if (forData) {
            jobject.put("Audio", 0.75)
            jobject.put("Video", 0.25)
            jobject.put("Other", 0.5)
        } else {

            // when computing weights for devices, a real context MUST be supplied
            val devices = context!!.resources.getStringArray(R.array.device_examples)
            for (device in devices)
                jobject.put(device, 0.5)  // equal init weights for all devices
        }

        return jobject
    }


    /**
     * Initializes a weight cache.
     * @param forData: whether to initialize the data weights cache (instead of device)
     * @param context: caller context, necessary for writing to sharedPref
     */
    private fun initializeWeightCache(forData: Boolean, context: Context) {
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            val weightsJson = computeInitialWeights(forData, context)  // the initial weights
            val serializedWeights = weightsJson.toString(2)
            if (forData)
                this.putString(DATA_WEIGHTS_CACHE_KEY, serializedWeights)
            else
                this.putString(DEVICE_WEIGHTS_CACHE_KEY, serializedWeights)
            this.apply()
        }
    }


    /**
     * Initializes all (both) weight caches using the initial weights.
     * @param context: caller context
     */
    fun initializeAllWeights(context: Context) {
        initializeWeightCache(true, context)
        initializeWeightCache(false, context)
    }


    /**
     * Reads a weight from a weight cache.
     * @param context: caller context
     * @param fromData: whether to read from the data weights cache (instead device)
     * @param key: the key the weight is mapped to
     * @return the weight associated with the key in the accessed cache
     */
    private fun readWeight(context: Context, fromData: Boolean, key: String): Double {
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        var readWeight = 0.5
        with(sharedPref.edit()) {
            val cacheKey = if (fromData) DATA_WEIGHTS_CACHE_KEY else DEVICE_WEIGHTS_CACHE_KEY
            val weightsString = sharedPref.getString(cacheKey, "") ?: ""

            try {
                val weightsObject = JSONObject(weightsString)
                readWeight = weightsObject.getDouble(key)
            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(
                    context,
                    "Could not read weight.", Toast.LENGTH_SHORT
                ).show()
            }
            this.apply()
        }

        return readWeight
    }


    /**
     * Updates a specific weight in one of the caches, depending on a privacy decision.
     * @param context: caller context
     * @param inData: whether the weight is in data cache (instead of device)
     * @param access: whether the initiating privacy decision allowed access for a request
     */
    private fun updateWeight(context: Context, inData: Boolean, key: String, access: Boolean) {
        val sharedPref = context.getSharedPreferences(
            context.resources
                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        // increment or decrement weight by one step, depending on privacy decision
        val updateStep = if (access) WEIGHT_STEP_SIZE else -WEIGHT_STEP_SIZE

        with(sharedPref.edit()) {
            val cacheKey = if (inData) DATA_WEIGHTS_CACHE_KEY else DEVICE_WEIGHTS_CACHE_KEY
            val weightsString = sharedPref.getString(cacheKey, "") ?: ""

            try {
                val weightsObject = JSONObject(weightsString)
                val currentWeight = weightsObject.getDouble(key)

                // if updated weight between 0-1, apply to cache
                if (currentWeight + updateStep in 0.0..1.0) {
                    weightsObject.put(key, currentWeight + updateStep)
                    val serializedBack = weightsObject.toString(2)
                    this.putString(cacheKey, serializedBack)
                }

            } catch (e: JSONException) {
                e.printStackTrace()
                Toast.makeText(
                    context,
                    "Could not update weight.", Toast.LENGTH_SHORT
                ).show()
            }

            this.apply()
        }
    }


    /**
     * Calculates the arithmetic mean value for a decision request. That means, the weights
     *  for its device and data are averaged.
     *  Can be used for making recommendations on privacy decisions.
     * @param context: caller context
     * @param req: the decision request object waiting to get a privacy decision answer
     */
    private fun getAverageForDecisionRequest(context: Context, req: DecisionRequest): Double {
        val deviceWeight = readWeight(context, false, req.deviceType)
        val dataWeight = readWeight(context, true, req.dataType)

        return (deviceWeight + dataWeight) / 2
    }


    /**
     * Updates the respective weights for a decision request, depending on the privacy decision.
     * @param context: caller context
     * @param req: the answered decision request that requires weight updating
     * @param access: whether the decision request was answered with "allow"
     */
    fun updateWeightsForRequest(context: Context, req: DecisionRequest, access: Boolean) {
        updateWeight(context, false, req.deviceType, access)
        updateWeight(context, true, req.dataType, access)
    }


    /**
     * Makes a decision request recommendation, based on weights and the threshold.
     * @param context: caller context
     * @param req: decision request to make a recommendation for
     * @return whether to recommend access for the decision request
     */
    fun getRequestRecommendation(context: Context, req: DecisionRequest): Boolean {
        val reqAvg = getAverageForDecisionRequest(context, req)

        return reqAvg >= RECOMMENDATION_THRESHOLD
    }


    /**
     * Makes a recommendation for a batch of decisions, based on some kind of majority vote.
     *
     *  This is done when a user can accept or deny all requests from a notification.
     *  Instead of batch deciding, a user could also decide for each single case.
     * @param context: caller context
     * @param reqs: list of decision requests to make a batch recommendation for
     * @return whether to recommend access for all requests (or deny all)
     */
    fun getBatchRecommendation(context: Context, reqs: List<DecisionRequest>): Boolean {

        // get all averages
        val reqAvgs = List(reqs.size) { getAverageForDecisionRequest(context, reqs[it]) }
        val avgSum = reqAvgs.reduce { acc, d -> acc + d }  // sum of averages

        return (avgSum / reqs.size) >= RECOMMENDATION_THRESHOLD
    }

}