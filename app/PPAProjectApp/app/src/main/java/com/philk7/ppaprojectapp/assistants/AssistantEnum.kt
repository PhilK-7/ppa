package com.philk7.ppaprojectapp.assistants

/**
 * Every type of assistant (except NONE) implements the interface IPrivacyAssistant.
 */
enum class AssistantEnum(val assistantId: Int) {
    NONE(0),
    NOTIFICATION_ASSISTANT(1),
    RECOMMENDATION_ASSISTANT(2),
    AUTOMATIC_ASSISTANT(3)
}