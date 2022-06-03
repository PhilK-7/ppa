package com.philk7.ppaprojectapp.enums


/**
 * Use a channel id depending on what kind of notification is sent through it.
 *  For most notifications, including all privacy decision notifications, use the normal
 *  channel. Only for special notifications, e.g. the daily overview reminder, or the survey
 *  invitation, use the special channel.
 * Pay attention to what channels must be created, or are already existing.
 * @param nameString: channel's name
 * ATTENTION: when building notifications, note that the field "name" of the enum is the actual id
 */
enum class NotificationChannelEnum(val nameString: String) {
    NORMAL_NOTF_CHANNEL_ID("PPA_NOTF_CHANNEL"),
    SPECIAL_NOTF_CHANNEL_ID("PPA_SPECIAL_NOTF_CHANNEL")
}