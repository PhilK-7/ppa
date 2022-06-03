package com.philk7.ppaprojectapp.utils

import android.content.Context
import com.philk7.ppaprojectapp.R
import kotlin.random.Random

/**
 * Generates sets of decision requests, for any kind of privacy assistant.
 */
class DecisionRequestGenerator(context: Context) {
    private val deviceOptions: Array<String> = context
        .resources.getStringArray(R.array.device_examples)
    private val dataOptions: Array<String> = context
        .resources.getStringArray(R.array.data_types)


    /**
     * Generates a random decision request, based on the available string arrays.
     * @return a random DecisionRequest object
     */
    fun generateDecisionRequest(): DecisionRequest {
        val deviceIndex = Random.nextInt(this.deviceOptions.size)
        val dataIndex = Random.nextInt(this.dataOptions.size)

        return DecisionRequest(this.deviceOptions[deviceIndex], this.dataOptions[dataIndex])
    }


    /**
     * Generates multiple random decision requests.
     * @param amount: how many random request object
     * @return a list containing all randomly generated decision requests
     */
    fun generateMultipleDecisionRequests(amount: Int): List<DecisionRequest> {
        val drl = mutableListOf<DecisionRequest>()
        var counter = 0

        while(counter < amount){
            val drCandidate = this.generateDecisionRequest()

            // look whether generated sample is duplicate
            var isDuplicate = false
            for(i in drl.indices){  // seek through previously generated
                if(drCandidate.deviceType == drl[i].deviceType
                    && drCandidate.dataType == drl[i].dataType){
                    isDuplicate = true
                    break
                }
            }
            if(!isDuplicate)  // add only new samples (and the increment counter)
                drl.add(counter++, drCandidate)
        }

        return drl
    }

}