package com.philk7.ppaprojectapp.utils

import android.content.Context
import com.philk7.ppaprojectapp.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

// represents the privacy prompt for an IoT device
/**
 * @param deviceType: which kind of device
 * @param dataType: which kind of data the device wants to collect
 */
class DecisionRequest(var deviceType: String, var dataType: String) {

    constructor() : this("", "")  // empty constructor when using just methods

    /**
     * Converts an array of decision requests into a JSON array,
     *  containing each request as JSON object.
     * @param requests: the decision requests
     * @return a JSON array, containing a JSON object for each request
     */
    private fun serializeRequests(requests: Array<DecisionRequest>): JSONArray {
        val jarray = JSONArray()
        for(i in requests.indices){
            val jobject = JSONObject()
            jobject.put("device", requests[i].deviceType)
            jobject.put("data", requests[i].dataType)
            jarray.put(i, jobject)
        }

        return jarray
    }


    /**
     * Stringifies a JSON array (of decision requests).
     * @param jarray: a JSON array
     * @return the JSON array as string
     */
    private fun serializedToString(jarray: JSONArray): String{
        return jarray.toString(4)
    }


    /**
     * Writes some decision requests to the cached value.
     * @param callerContext: the caller (activity, fragment) context
     * @param requests: list containing Decision Request objects
     */
    fun writeRequestCache(callerContext: Context, requests: List<DecisionRequest>) {
        val listAsArray = requests.toTypedArray()  // needed as array for indexing
        val toCacheString = DecisionRequest().serializedToString(
            DecisionRequest().serializeRequests(listAsArray))  // serialize as JSON string
        // write to cache
        val sharedPref = callerContext.getSharedPreferences(callerContext
            .resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            this.putString(REQUEST_CACHE_KEY, toCacheString)
            this.apply()
        }
    }


    /**
     * Reads the decision objects last written to the cache.
     *  Deserializes the saved string and parses JSON.
     * @param callerContext: the caller (activity, fragment) context
     * @return a list containing the decision requests encoded in the cached json string
     */
    fun readRequestCache(callerContext: Context): List<DecisionRequest> {
        val sharedPref = callerContext.getSharedPreferences(callerContext
            .resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE)
        val objStr: String
        with(sharedPref.edit()) {
            objStr = sharedPref.getString(REQUEST_CACHE_KEY, "").toString()
            this.apply()
        }

        if (objStr == "")  // empty list when no cache value available
            return listOf()

        else {
            return try{

                val jarray = JSONArray(objStr)
                val reqList = mutableListOf<DecisionRequest>()
                for(i in 0 until jarray.length()) {
                    val jobject = jarray.getJSONObject(i)
                    reqList.add(i, DecisionRequest(
                        jobject.getString("device"), jobject.getString("data")))
                }

                reqList

            } catch (e: JSONException){
                e.printStackTrace()
                listOf()
            }
        }
    }


    companion object {
        /*
        Under this key, a cache value in shared preferences can be used to share decision requests
        between different contexts. This cache is written anew every time, with overwriting the
        previous content. This way, decision requests can be passed around without requiring to
        put it as extras / bundles / parcels etc. to intents, and no special extra processing is
        needed. This makes it also more reusable. Using the cache is recommended for passing around
        multiple decision requests at a time.
         */
        const val REQUEST_CACHE_KEY = "request_cache_object"
    }
}

