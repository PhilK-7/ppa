package com.philk7.ppaprojectapp.enums;

import java.lang.System;

/**
 * Use a unique notification id for every specific kind of notification that can only occur
 * once at a time within its type. This enables correct handling (e.g. by broadcast receivers),
 * and also correct cancelling, without compromising other notifications.
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n\u00a8\u0006\u000b"}, d2 = {"Lcom/philk7/ppaprojectapp/enums/NotificationIdEnum;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "NOW__PRIVACY_DECISION__NTF_ID", "DAILY_DECISIONS_PANEL__REMINDER__NTF_ID", "ONETIME_SURVEY__REMINDER__NTF_ID", "LOCATION_QUERY__NTF_ID", "app_debug"})
public enum NotificationIdEnum {
    /*public static final*/ NOW__PRIVACY_DECISION__NTF_ID /* = new NOW__PRIVACY_DECISION__NTF_ID(0) */,
    /*public static final*/ DAILY_DECISIONS_PANEL__REMINDER__NTF_ID /* = new DAILY_DECISIONS_PANEL__REMINDER__NTF_ID(0) */,
    /*public static final*/ ONETIME_SURVEY__REMINDER__NTF_ID /* = new ONETIME_SURVEY__REMINDER__NTF_ID(0) */,
    /*public static final*/ LOCATION_QUERY__NTF_ID /* = new LOCATION_QUERY__NTF_ID(0) */;
    private final int id = 0;
    
    public final int getId() {
        return 0;
    }
    
    NotificationIdEnum(int id) {
    }
}