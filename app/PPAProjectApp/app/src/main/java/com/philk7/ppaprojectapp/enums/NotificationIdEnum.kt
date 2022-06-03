package com.philk7.ppaprojectapp.enums


/**
 * Use a unique notification id for every specific kind of notification that can only occur
 *  once at a time within its type. This enables correct handling (e.g. by broadcast receivers),
 *  and also correct cancelling, without compromising other notifications.
 */
enum class NotificationIdEnum(val id: Int) {
    // ids for normal privacy decision notifications sent by the different assistants
    NOW__PRIVACY_DECISION__NTF_ID(1),  // onetime, now

    DAILY_DECISIONS_PANEL__REMINDER__NTF_ID(5),  // daily regular
    ONETIME_SURVEY__REMINDER__NTF_ID(6),  // survey reminder
    LOCATION_QUERY__NTF_ID(7)  // location queries
}