package com.philk7.ppaprojectapp.assistants

interface IPrivacyAssistant {

    /**
     * ActionEnum.INIT
     * This function is called to setup the (chosen) privacy assistant for the first time.
     *  Some values / entries are manipulated, and notifications/intents scheduled.
     */
    fun initializePrivacyAssistant()


    /**
     * ActionEnum.LOCATION_QUERY
     * This function is called to explicitly gather information on (indicated) location change.
     */
    fun queryLocation()


    /**
     * ActionEnum.PRIVACY_QUERY
     * This function is called when a new privacy decision should be made.
     *  That means, depending on the specific assistant, either a privacy notification is sent,
     *  or a decision is made directly.
     */
    fun doPrivacyDecisionQuery()



}
