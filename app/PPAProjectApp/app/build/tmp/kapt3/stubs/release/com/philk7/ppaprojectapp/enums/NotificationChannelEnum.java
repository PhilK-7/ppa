package com.philk7.ppaprojectapp.enums;

import java.lang.System;

/**
 * Use a channel id depending on what kind of notification is sent through it.
 * For most notifications, including all privacy decision notifications, use the normal
 * channel. Only for special notifications, e.g. the daily overview reminder, or the survey
 * invitation, use the special channel.
 * Pay attention to what channels must be created, or are already existing.
 * @param nameString: channel's name
 * ATTENTION: when building notifications, note that the field "name" of the enum is the actual id
 */
@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/philk7/ppaprojectapp/enums/NotificationChannelEnum;", "", "nameString", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getNameString", "()Ljava/lang/String;", "NORMAL_NOTF_CHANNEL_ID", "SPECIAL_NOTF_CHANNEL_ID", "app_release"})
public enum NotificationChannelEnum {
    /*public static final*/ NORMAL_NOTF_CHANNEL_ID /* = new NORMAL_NOTF_CHANNEL_ID(null) */,
    /*public static final*/ SPECIAL_NOTF_CHANNEL_ID /* = new SPECIAL_NOTF_CHANNEL_ID(null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String nameString = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getNameString() {
        return null;
    }
    
    NotificationChannelEnum(java.lang.String nameString) {
    }
}