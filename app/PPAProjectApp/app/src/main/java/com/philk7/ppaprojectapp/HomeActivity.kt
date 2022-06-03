package com.philk7.ppaprojectapp

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.navigation.NavDeepLinkBuilder
import com.philk7.ppaprojectapp.assistants.*
import com.philk7.ppaprojectapp.backend.DecisionBackendHandling
import com.philk7.ppaprojectapp.enums.NotificationChannelEnum
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import com.philk7.ppaprojectapp.utils.LocationProcessing
import com.philk7.ppaprojectapp.utils.NotificationHandler
import com.philk7.ppaprojectapp.utils.Supplementer
import kotlin.random.Random

class HomeActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbar))

        // ask/check for activity detection permission
        this.checkMovePermission()

        // set sensor manager and sensor type (step counter)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)

        if (sensor == null) Toast.makeText(
            this.applicationContext,
            "No step sensor found.", Toast.LENGTH_SHORT
        ).show()
        else sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)


        // call app initializer - ESSENTIAL MOMENT
        this.initializeAppState()

        // back navigation from other fragments of activity to home activity
        findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            onBackPressed()
        }
    }


    override fun onResume() {
        super.onResume()
        // re-register sensor
        if (sensor != null)
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        // re-start alarm
        Supplementer.startServiceAlarm(this.applicationContext)
    }

    override fun onStop() {
        super.onStop()
        // re-start alarm
        Supplementer.startServiceAlarm(this.applicationContext)
    }


    override fun onBackPressed() {
        // pop back stack and go back to home menu from other connected fragment
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStackImmediate()
            supportActionBar?.setTitle(R.string.title_activity_home)
        } else super.onBackPressed()
    }


    /**
     * Checks whether the permission ACTIVITY_RECOGNITION is granted, and asks for it if not.
     * NOTE: This permission needs to be granted in order for the step counter to be usable
     *  for all Android 10+.
     */
    private fun checkMovePermission() {
        if (checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACTIVITY_RECOGNITION
            ) == PermissionChecker.PERMISSION_DENIED
            && Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
        )
            requestPermissions(arrayOf(Manifest.permission.ACTIVITY_RECOGNITION), 0)

    }


    // NOTE: NEEDS TO BE ONLY CALLED ONCE - FOR NORMAL USAGE, CALL THIS METHOD EXACTLY ONCE
    // as taken similarly from the official Android documentation
    /**
     * Creates the notification channel needed for displaying notifications of the app.
     * @param channel: the notification channel, depends on the caller / type of notification
     */
    private fun createNotificationChannel(channel: NotificationChannelEnum) {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chanId = channel.name
            val name = channel.nameString
            val descriptionText: String
            val importance: Int
            var doVibrate = false
            // differentiate values depending on channel
            when (channel) {
                NotificationChannelEnum.NORMAL_NOTF_CHANNEL_ID -> {
                    importance = NotificationManagerCompat.IMPORTANCE_HIGH
                    descriptionText = resources
                        .getString(R.string.normal_channel_description)
                    doVibrate = true
                }
                NotificationChannelEnum.SPECIAL_NOTF_CHANNEL_ID -> {
                    importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
                    descriptionText = resources
                        .getString(R.string.special_channel_description)
                }
            }
            val builtChannel =
                NotificationChannel(chanId, name, importance).apply {
                    description = descriptionText
                    enableVibration(doVibrate)
                }
            // Register the channel with the system
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(builtChannel)
        }
    }


    /**
     * Initializes the app state when the home screen is opened for the first time.
     * The following things are done:
     *      - (re)set some sharedPref values
     *      - create the notification channels
     *      - initialize the privacy assistant (e.g. weights, supplementer)
     *      - schedule mid-term survey reminder
     *      - confirm initialization
     */
    private fun initializeAppState() {
        // read shared preferences
        val sharedPref = this.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
        )
        val initialized: Boolean = sharedPref.getBoolean(INITIALIZED_KEY, false)
        println("Checking for app state initialization...")

        if (initialized && !IGNORE_ALREADY_SETUP) return  // already set up

        else {

            println("Performing app initialization...")

            // (re)set sharedPref entries used for further operation
            LocationProcessing.writeLastPlace(this.applicationContext, "")
            with(sharedPref.edit()) {
                putInt(LocationProcessing.STEPS_KEY, 0)
                this.apply()
            }

            // create the notification channels (no effect when already existing)
            for (type in NotificationChannelEnum.values())
                this.createNotificationChannel(type)


            // initialize privacy assistant
            // if not readable / defined, standards to Recommendation Assistant
            val theContext = this.applicationContext
            val assistant = when (determineAssistant(this.applicationContext)) {
                AssistantEnum.NOTIFICATION_ASSISTANT -> NotificationAssistant(theContext)
                AssistantEnum.RECOMMENDATION_ASSISTANT -> RecommendationAssistant(theContext)
                AssistantEnum.AUTOMATIC_ASSISTANT -> AutomaticAssistant(theContext)
                else -> NoAssistant()
            }
            assistant.initializePrivacyAssistant()  // INITIALIZE ASSISTANT


            // location permissions checked on init / every home screen startup


            // schedule special notification for mid-term survey
            val scheduledTime = NotificationHandler.calculateSchedulingPoint(INTERVAL_WEEK)
            NotificationHandler.scheduleActionAlarmToMainReceiver(
                this.applicationContext,
                scheduledTime, ReceiverIntentEnum.ACTION_SURVEY_REMINDER.msg, false
            )


            // finally, to mark setup state, write initialize key
            with(sharedPref.edit()) {
                putBoolean(INITIALIZED_KEY, true)
                this?.apply()
            }
            val cstr = "App initialization complete."
            println(cstr)
            val confirmToast = Toast.makeText(this.applicationContext, cstr, Toast.LENGTH_LONG)
            confirmToast.show()
        }
    }


    // sensor interface methods


    /**
     * When the device's step counter sensor fires (not every step!), this method
     *  writes the total step counter (e.g. since system reboot) to sharedPref,
     *  and might trigger a location/privacy query.
     * @param p0: the fired event object
     */
    override fun onSensorChanged(p0: SensorEvent?) {
        var eventVal = -1.0f
        with(
            this.getSharedPreferences(
                resources.getString(R.string.main_sharedpref_name),
                Context.MODE_PRIVATE
            ).edit()
        ) {
            if (p0 != null) {
                eventVal = p0.values[0]  // step counter value
                println("========== Event value: $eventVal")
                println(System.currentTimeMillis())
                this.putInt(LocationProcessing.STEPS_KEY, eventVal.toInt())  // write step count
            }
            this.apply()
        }

        // if location seems to have changed according to step sensors, trigger this event
        if (eventVal >= 0 && LocationProcessing.checkStepsLocationChanged(this.applicationContext)) {
            /* Send either a notification that lets the user choose the current location,
                or directly send a privacy notification.
                If the first one happens, and the indicated location changed,
                another privacy query will be initiated. */

            // determine next request (location / privacy (direct)) randomly
            val randNum = Random.nextInt(100)
            if (randNum * 0.01 < LOCATION_QUERY_TRIGGER_PROB) {
                passHandleToAssistant(
                    this.applicationContext, ActionEnum.LOCATION_QUERY
                )
                // privacy query might get triggered in opened activity
            } else
                passHandleToAssistant(
                    this.applicationContext,
                    ActionEnum.PRIVACY_QUERY
                )
        }
    }


    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
        println("========== Sensor name: " + p0?.name)
        println("Accuracy changed.")
    }


    // start of static area

    companion object {
        const val INITIALIZED_KEY = "app_init_done"
        const val INTERVAL_WEEK = 604800  // how many seconds in a week

        // config
        const val IGNORE_ALREADY_SETUP =
            false

        // probability of location query before privacy query when enough steps
        const val LOCATION_QUERY_TRIGGER_PROB = 0.5


        /**
         * ESSENTIAL FUNCTION.
         * Passes generic actions to privacy assistants. This is based on the assistant hierarchy
         *  pattern. Determines the current user's assistant, and calls the appropriate method.
         * @param context: caller context
         * @param action: which generic action (also see IPrivacyAssistant methods)
         */
        fun passHandleToAssistant(context: Context, action: ActionEnum) {
            val assistant = when (determineAssistant(context)) {
                AssistantEnum.NOTIFICATION_ASSISTANT -> NotificationAssistant(context)
                AssistantEnum.RECOMMENDATION_ASSISTANT -> RecommendationAssistant(context)
                AssistantEnum.AUTOMATIC_ASSISTANT -> AutomaticAssistant(context)
                else -> NoAssistant()
            }

            when (action) {
                ActionEnum.INIT -> assistant.initializePrivacyAssistant()
                ActionEnum.LOCATION_QUERY -> assistant.queryLocation()
                ActionEnum.PRIVACY_QUERY -> {

                    // only do the privacy decision query if daily limit is not exceeded
                    if (NotificationHandler.checkWithinNotificationDayBudget(context)) {
                        assistant.doPrivacyDecisionQuery()

                        // also update daily counter
                        val sharedPref = context.getSharedPreferences(
                            context.resources
                                .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
                        )
                        with(sharedPref.edit()) {
                            var dayNtfCount = sharedPref
                                .getInt(NotificationHandler.NOTIFICATION_DAY_COUNT_KEY, 0)
                            this.putInt(
                                NotificationHandler.NOTIFICATION_DAY_COUNT_KEY, ++dayNtfCount
                            )
                            this.apply()
                        }
                    }


                }
            }

        }


        /**
         * Checks what the user's privacy assistant is.
         * @param context: caller context
         * @return the user's chosen assistant as AssistantEnum
         */
        fun determineAssistant(context: Context): AssistantEnum {
            val assistantId: Int
            val sharedPref = context.getSharedPreferences(
                context.resources
                    .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                assistantId = sharedPref.getInt(AssistantChoiceFragment.ASSISTANT_KEY, 2)
                this.apply()
            }

            return when (assistantId) {
                1 -> AssistantEnum.NOTIFICATION_ASSISTANT
                2 -> AssistantEnum.RECOMMENDATION_ASSISTANT
                3 -> AssistantEnum.AUTOMATIC_ASSISTANT
                else -> AssistantEnum.NONE
            }

        }


        /**
         * Overwrites the user's assigned privacy assistant.
         * WARNING: This changes the app's behavior. Overwrite only for testing purposes.
         * @param context: caller context
         * @param assistant: the assistant to change to
         */
        fun overwriteAssistant(context: Context, assistant: AssistantEnum) {
            val sharedPref = context.getSharedPreferences(
                context.resources
                    .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                this.putInt(AssistantChoiceFragment.ASSISTANT_KEY, assistant.assistantId)
                this.apply()
            }
        }


        /**
         * Reads the current user ID from shared preferences.
         * @param context: caller context
         * @return the user PID
         */
        fun readUserPid(context: Context): String {
            val uid: String
            val sharedPref = context.getSharedPreferences(
                context.resources
                    .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                uid = sharedPref.getString(FirstIDActivity.PID_KEY, "") ?: ""
                this.apply()
            }

            return uid
        }


        // start of receivers
        // possible intent actions -> see ReceiverIntentEnum


        class MainReceiver : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                val iaction = p1?.action ?: "NONE_SET"

                // nested notification
                if (iaction == ReceiverIntentEnum.ACTION_NEST.msg) {
                    val notf = p1?.getParcelableExtra<Notification>("notf")
                    val nid = p1?.getIntExtra("nid", 0) ?: 0
                    if (notf != null && p0 != null)
                        NotificationHandler.showNotification(p0, notf, nid)
                } // privacy query
                else if (iaction == ReceiverIntentEnum.ACTION_BUILD_NTF.msg) {
                    // do appropriate action (notification / auto) depending on assistant
                    if (p0 != null) {
                        passHandleToAssistant(p0, ActionEnum.PRIVACY_QUERY)
                    }
                } // survey reminder notification
                else if (iaction == ReceiverIntentEnum.ACTION_SURVEY_REMINDER.msg && p0 != null) {
                    val intent = Intent(p0, MidtermSurveyActivity::class.java)
                    val pintent = PendingIntent.getActivity(
                        p0, 0,
                        intent, PendingIntent.FLAG_ONE_SHOT
                    )
                    val ntf = NotificationHandler.buildReminderNotification(
                        p0, pintent,
                        "Midterm Survey",
                        "Please take some time to fill out this mid-term survey."
                    )
                    NotificationHandler.showNotification(
                        p0, ntf,
                        NotificationIdEnum.ONETIME_SURVEY__REMINDER__NTF_ID.id
                    )
                } // daily overview reminder
                else if (iaction == ReceiverIntentEnum.ACTION_DAILY_REMINDER.msg && p0 != null) {
                    val pintent = NavDeepLinkBuilder(p0)
                        .setComponentName(HomeActivity::class.java)
                        .setGraph(R.navigation.home_navigation)
                        .setDestination(R.id.dailyDecisionsFragment)
                        .createPendingIntent()
                    val ntf = NotificationHandler.buildReminderNotification(
                        p0, pintent,
                        "Daily Decisions",
                        "Have a look at the privacy decisions made today."
                    )
                    NotificationHandler.showNotification(
                        p0, ntf,
                        NotificationIdEnum.DAILY_DECISIONS_PANEL__REMINDER__NTF_ID.id
                    )
                }

                // action for schedule service alarm
                else if (iaction == ReceiverIntentEnum.ACTION_SCHEDULE_SERVICE.msg && p0 != null) {
                    // compute timing; check if inside time window, do nothing if not

                    val now = System.currentTimeMillis()
                    val todayTwStart =
                        Supplementer.translateTimeStringToUnixTime(Supplementer.TIME_WINDOW_START)
                    val todayTwEnd =
                        Supplementer.translateTimeStringToUnixTime(Supplementer.TIME_WINDOW_END)
                    val intervalLength = Supplementer.getRepeatInterval(p0)
                    val exactStart = now - (now % intervalLength)

                    // also write trigger times to sharedPref
                    val sharedPref = p0.getSharedPreferences(
                        p0.resources
                            .getString(R.string.main_sharedpref_name), Context.MODE_PRIVATE
                    )
                    with(sharedPref.edit()) {
                        val nowAsTs = DecisionBackendHandling.getCurrentUnixTimeAsTimestamp()
                        this.putString(Supplementer.SERVICE_ALARM_LAST_KEY, nowAsTs)
                        val nextAsTs = DecisionBackendHandling
                            .getCurrentUnixTimeAsTimestamp(offset = intervalLength)
                        this.putString(Supplementer.SERVICE_ALARM_NEXT_KEY, nextAsTs)
                        this.apply()
                    }

                    /* NOTE: Alarms are not exact. They are often delayed by several minutes.
                    Remind this when checking whether now is included in the time window.
                    For this service alarm specifically, use (about) exact alarms.
                     */
                    // schedule next service alarm
                    NotificationHandler.scheduleActionAlarmToMainReceiver(
                        p0,
                        exactStart + intervalLength,
                        ReceiverIntentEnum.ACTION_SCHEDULE_SERVICE.msg,
                        false, exact = true
                    )
                    if (now < todayTwStart || exactStart > todayTwEnd)
                        return  // nothing left to do if not inside time window

                    // inside time window -> might need to do additional privacy query
                    NotificationHandler
                        .checkWithinNotificationDayBudget(p0)  // reset counter, if needed
                    val dayTimeProgess = (now - todayTwStart).toFloat() /
                            (todayTwEnd - todayTwStart).toFloat()
                    val velDif = Supplementer.computeVelocityDifference(p0, dayTimeProgess)
                    // if behind "quota", do a privacy query
                    if (velDif < 0)
                        passHandleToAssistant(p0, ActionEnum.PRIVACY_QUERY)
                }

                // other kinds of nested notifications
                else {
                    // receive broadcasted intent and display wrapped notification
                    val ntf = p1?.getParcelableExtra<Notification>("innerNotf")
                    val nid = p1?.getIntExtra("nid", 0) ?: 0
                    if (p0 != null && ntf != null) {
                        NotificationHandler.showNotification(p0, ntf, nid)
                    }
                }

            }


        }


        // The broadcast receiver that handles privacy decisions specifically.
        class DecisionRequestReceiver : BroadcastReceiver() {

            override fun onReceive(p0: Context?, p1: Intent?) {
                val actionStr = p1?.action
                actionStr.let {
                    val pid: String = p1?.getStringExtra("pid") ?: ""
                    val place = p1?.getStringExtra("place") ?: ""

                    // insert single decision
                    if (actionStr == ReceiverIntentEnum.ACTION_ALLOW.msg ||
                        actionStr == ReceiverIntentEnum.ACTION_DENY.msg
                    ) {

                        val accessBool = actionStr == ReceiverIntentEnum.ACTION_ALLOW.msg
                        val device = p1.getStringExtra("device") ?: ""
                        val data = p1.getStringExtra("data") ?: ""
                        if (p0 != null) {
                            val success = interactWithBackend(
                                p0, pid, place, device, data, accessBool
                            )
                            if (!success) {
                                val t = Toast.makeText(
                                    p0,
                                    "Could not insert decision. Is the server offline?",
                                    Toast.LENGTH_SHORT
                                )
                                t.show()
                            }
                        }

                    } else {  // batch decision action

                        val rd = p1?.getStringArrayExtra("rd")
                        val rt = p1?.getStringArrayExtra("rt")
                        if (p0 != null && rd != null && rt != null) {
                            val s: Boolean
                            when (actionStr) {
                                ReceiverIntentEnum.ACTION_ALL_ALLOW.msg -> {
                                    s = DecisionBackendHandling().executeBatchDecision(
                                        p0,
                                        true,
                                        pid, place,
                                        rd, rt
                                    )
                                }
                                ReceiverIntentEnum.ACTION_ALL_DENY.msg -> {
                                    s = DecisionBackendHandling().executeBatchDecision(
                                        p0,
                                        false,
                                        pid, place,
                                        rd, rt
                                    )
                                }
                                else -> {
                                    return
                                }
                            }

                            if (!s) Toast.makeText(
                                p0,
                                "Could not insert (all) decisions. Is the server offline?",
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }

                    if (p0 != null && p1 != null) {
                        handleSystemState(p0, p1)  // close
                    }
                }

            }

            // insert a single privacy decision
            private fun interactWithBackend(vararg params: Any): Boolean {
                return DecisionBackendHandling().insertUserDecision(
                    params[0] as Context,
                    params[1] as String, params[2] as String, params[3] as String,
                    params[4] as String, params[5] as Boolean, false
                )
            }

            // close notification and system panel
            private fun handleSystemState(vararg params: Any) {
                val p0 = params[0] as Context
                val p1 = params[1] as Intent
                val nid = p1.getIntExtra("nid", 0)
                NotificationManagerCompat.from(p0).cancel(nid)
                val closeIntent = Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)
                p0.sendBroadcast(closeIntent)
            }

        }

    }


}
