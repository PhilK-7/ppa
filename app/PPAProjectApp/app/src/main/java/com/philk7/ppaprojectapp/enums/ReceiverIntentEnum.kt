package com.philk7.ppaprojectapp.enums


/**
 * These actions represent events received by one of the broadcast receivers (see HomeActivity).
 */
enum class ReceiverIntentEnum(val msg: String) {
    ACTION_NEST("notf_nested"),  // deliver notifications as intent extra
    ACTION_BUILD_NTF("notf_build_now"),  // building privacy decision notifications
    ACTION_ALLOW("allow"),  // single request allow
    ACTION_DENY("deny"),  // single request deny
    ACTION_ALL_ALLOW("allow_all"),  // all requests allow
    ACTION_ALL_DENY("deny_all"),  // all requests deny
    ACTION_DAILY_REMINDER("daily_overview_remind"),  // build daily reminder notification
    ACTION_SURVEY_REMINDER("midterm_survey_remind"), // build one-time survey reminder notification
    ACTION_SCHEDULE_SERVICE("service_alarm_check_velocity"),  // schedule service alarm

    // those actions are not used anymore (old system)
    ACTION_WINDOW_SCHEDULE("schedule_supplement_window"),  // supplement window alarms
    ACTION_LAST_WINDOW_SCHEDULE("schedule_last_supplement_window"),  // last window of a day
    ACTION_PRIVACY_QUERY_NOW("do_privacy_query"),  // privacy query initiated by window
}