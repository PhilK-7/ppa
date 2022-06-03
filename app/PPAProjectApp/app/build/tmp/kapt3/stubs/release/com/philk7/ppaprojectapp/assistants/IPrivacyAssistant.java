package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&\u00a8\u0006\u0006"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/IPrivacyAssistant;", "", "doPrivacyDecisionQuery", "", "initializePrivacyAssistant", "queryLocation", "app_release"})
public abstract interface IPrivacyAssistant {
    
    /**
     * ActionEnum.INIT
     * This function is called to setup the (chosen) privacy assistant for the first time.
     * Some values / entries are manipulated, and notifications/intents scheduled.
     */
    public abstract void initializePrivacyAssistant();
    
    /**
     * ActionEnum.LOCATION_QUERY
     * This function is called to explicitly gather information on (indicated) location change.
     */
    public abstract void queryLocation();
    
    /**
     * ActionEnum.PRIVACY_QUERY
     * This function is called when a new privacy decision should be made.
     * That means, depending on the specific assistant, either a privacy notification is sent,
     * or a decision is made directly.
     */
    public abstract void doPrivacyDecisionQuery();
}