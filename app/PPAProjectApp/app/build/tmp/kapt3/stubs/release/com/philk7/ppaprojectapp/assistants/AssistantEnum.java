package com.philk7.ppaprojectapp.assistants;

import java.lang.System;

/**
 * Every type of assistant (except NONE) implements the interface IPrivacyAssistant.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/philk7/ppaprojectapp/assistants/AssistantEnum;", "", "assistantId", "", "(Ljava/lang/String;II)V", "getAssistantId", "()I", "NONE", "NOTIFICATION_ASSISTANT", "RECOMMENDATION_ASSISTANT", "AUTOMATIC_ASSISTANT", "app_release"})
public enum AssistantEnum {
    /*public static final*/ NONE /* = new NONE(0) */,
    /*public static final*/ NOTIFICATION_ASSISTANT /* = new NOTIFICATION_ASSISTANT(0) */,
    /*public static final*/ RECOMMENDATION_ASSISTANT /* = new RECOMMENDATION_ASSISTANT(0) */,
    /*public static final*/ AUTOMATIC_ASSISTANT /* = new AUTOMATIC_ASSISTANT(0) */;
    private final int assistantId = 0;
    
    public final int getAssistantId() {
        return 0;
    }
    
    AssistantEnum(int assistantId) {
    }
}