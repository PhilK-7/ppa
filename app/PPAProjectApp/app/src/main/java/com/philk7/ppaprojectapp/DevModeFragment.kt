package com.philk7.ppaprojectapp

import android.app.Notification
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.philk7.ppaprojectapp.assistants.AssistantEnum
import com.philk7.ppaprojectapp.databinding.FragmentDevModeBinding
import com.philk7.ppaprojectapp.enums.NotificationIdEnum
import com.philk7.ppaprojectapp.enums.ReceiverIntentEnum
import com.philk7.ppaprojectapp.utils.DecisionRequestGenerator
import com.philk7.ppaprojectapp.utils.NotificationHandler
import kotlin.random.Random


class DevModeFragment : Fragment() {
    private lateinit var binding: FragmentDevModeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_dev_mode, container, false
        )

        // NOTE: normal notification channel needs to exist

        // set button on-click listeners
        binding.showNotfButton.setOnClickListener {
            displayExampleNotification()
        }
        binding.showSoonButton.setOnClickListener {
            displayENIn10Seconds()
        }
        binding.showIntervalButton.setOnClickListener {
            displayEveryMinute()
        }
        binding.showAtTimeButton.setOnClickListener {
            var h = 0
            var m = 0
            val dialog = TimePickerDialog(
                this.requireContext(), R.style.TimePickerCustom,
                { _, hour, minute ->
                    // get set values
                    h = hour
                    m = minute
                },
                0, 0, true
            )

            dialog.setOnDismissListener {
                this.displayOnSpecificTime(h, m)
            }

            dialog.show()
        }
        binding.showMultiButton.setOnClickListener {
            this.displayMulti()
        }
        binding.gotoLIAButton.setOnClickListener {
            val intent = Intent(this.requireContext(), LocationIndicationActivity::class.java)
            startActivity(intent)
        }
        // button to switch between assistants
        binding.assistantChangeButton.setOnClickListener {
            val assistant = HomeActivity.determineAssistant(this.requireContext())
            val toastText: String
            when(assistant){
                AssistantEnum.NOTIFICATION_ASSISTANT -> {
                    HomeActivity.overwriteAssistant(this.requireContext(),
                        AssistantEnum.RECOMMENDATION_ASSISTANT)
                    toastText = "Changed to RECOMMENDATION ASSISTANT."
                }
                AssistantEnum.RECOMMENDATION_ASSISTANT -> {
                    HomeActivity.overwriteAssistant(this.requireContext(),
                    AssistantEnum.AUTOMATIC_ASSISTANT)
                    toastText = "Changed to AUTOMATIC ASSISTANT."
                }
                AssistantEnum.AUTOMATIC_ASSISTANT -> {
                    HomeActivity.overwriteAssistant(this.requireContext(),
                    AssistantEnum.NOTIFICATION_ASSISTANT)
                    toastText = "Changed to NOTIFICATION ASSISTANT."
                }
                else -> {
                    toastText = ""
                }
            }

            Toast.makeText(this.requireContext(), toastText, Toast.LENGTH_SHORT).show()
        }
        binding.recButton.setOnClickListener {
            this.displayRecommendation()
        }
        binding.multiRecButton.setOnClickListener {
            this.displayMultiRecommendation()
        }

        // assimilate action bar
        val ab = (activity as AppCompatActivity).supportActionBar!!
        ab.setDisplayHomeAsUpEnabled(true)
        ab.title = "DEVELOPER PANEL"

        return binding.root
    }


    /**
     * Extracts the information given by the spinners.
     * @return a Map that contains the necessary information to build a decision notification
     */
    private fun extractNotificationInfo(): Map<String, String> {
        val device = binding.deviceSpinner .selectedItem.toString()
        val type = binding.dataSpinner.selectedItem.toString()

        // get data for decision
        val sharedPref = this.activity?.getSharedPreferences(
            resources.getString(R.string.main_sharedpref_name), AppCompatActivity.MODE_PRIVATE
        )
        val pid: String
        with(sharedPref?.edit()) {
            pid = sharedPref?.getString("pid", "").toString()
            this?.apply()
        }
        val place = binding.placeSpinner.selectedItem.toString()

        val infoMap = HashMap<String, String>()
        infoMap["device"] = device
        infoMap["data"] = type
        infoMap["pid"] = pid
        infoMap["place"] = place

        return infoMap
    }

    /**
     * Builds an example privacy decision notification based on the fragment's spinners.
     * @return the built notification
     */
    private fun buildExampleNotification(): Notification {
        val theMap = this.extractNotificationInfo()
        val pid = theMap["pid"] as String
        val place = theMap["place"] as String
        val device = theMap["device"] as String
        val data = theMap["data"] as String

        // build notification
        return NotificationHandler.buildNotificationAssistantNotification(
            this.requireContext(),
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id,
            pid, place, device, data)
    }


    /**
    Displays an example notification, including device and data type.
        A showcase for the real notification assistant.
        The artifical decision is inserted by the backend.
     NOTE: Does not increase the daily counter.
     */
    private fun displayExampleNotification() {

        val ntf = buildExampleNotification()
        NotificationHandler.showNotification(this.requireContext(),
            ntf, NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id
        )
    }


    /**
     * Displays an example notification with a delay of 10 seconds (or does decision automatically).
     * NOTE: Increases the daily counter!
     */
    private fun displayENIn10Seconds() {
        val time = NotificationHandler.calculateSchedulingPoint(10)

        NotificationHandler.scheduleActionAlarmToMainReceiver(this.requireContext(), time,
            ReceiverIntentEnum.ACTION_BUILD_NTF.msg, false)
    }


    /**
     * Displays an example notification about every minute.
     * NOTE: Increases the daily counter!
     */
    private fun displayEveryMinute() {

        NotificationHandler.scheduleActionAlarmToMainReceiver(this.requireContext(),
            System.currentTimeMillis() + 60000,
            ReceiverIntentEnum.ACTION_BUILD_NTF.msg, true, 60000)
    }

    /**
     * Displays an example notification on a specific time (of the same day), or does it
     *  autonomously if required.
     * NOTE: Increases the daily counter!
     * @param hour: the clock hour
     * @param minute: the clock minute
     */
    private fun displayOnSpecificTime(hour: Int, minute: Int) {
        val time = NotificationHandler.calculateSchedulingPointClock(hour, minute)

        NotificationHandler.scheduleActionAlarmToMainReceiver(this.requireContext(),
            time, ReceiverIntentEnum.ACTION_BUILD_NTF.msg, false)

    }


    /**
     * Displays a multi request notification.
     * NOTE: Does not increase the daily counter.
     */
    private fun displayMulti() {

        // get necessary data
        val theMap = this.extractNotificationInfo()
        val pid = theMap["pid"] as String
        val place = theMap["place"] as String

        // generate some decision requests
        val amount = Random.nextInt(2, 7)  // 2-6 requests
        val randomReqList = DecisionRequestGenerator(this.requireContext())
            .generateMultipleDecisionRequests(amount)

        // build notification
        val ntf = NotificationHandler.buildMultiRequestDecisionNotificationAssistantNotification(
            this.requireContext(),
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id,
            pid, place, randomReqList
        )

        // show
        NotificationHandler.showNotification(this.requireContext(), ntf,
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id
        )
    }


    /**
     * Displays a recommendation privacy notification.
     * Note: Does not increase the daily counter.
     */
    private fun displayRecommendation() {
        val theMap = this.extractNotificationInfo()
        val pid = theMap["pid"] as String
        val place = theMap["place"] as String
        val device = theMap["device"] as String
        val data = theMap["data"] as String

        val ntf = NotificationHandler.buildRecommendationAssistantNotification(
            this.requireContext(),
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id, pid, place, device, data)
        NotificationHandler.showNotification(this.requireContext(),
            ntf, NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id
        )
    }


    /**
     * Displays a multi request recommendation privacy notification.
     * NOTE: Does not increase the daily counter.
     */
    private fun displayMultiRecommendation() {
        // get necessary data
        val theMap = this.extractNotificationInfo()
        val pid = theMap["pid"] as String
        val place = theMap["place"] as String

        // generate some decision requests
        val amount = Random.nextInt(2, 5)  // 2-4 requests
        val randomReqList = DecisionRequestGenerator(this.requireContext())
            .generateMultipleDecisionRequests(amount)

        val ntf = NotificationHandler
            .buildMultiRequestDecisionRecommendationAssistantNotification(
                this.requireContext(),
                NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id, pid, place, randomReqList)
        NotificationHandler.showNotification(this.requireContext(), ntf,
            NotificationIdEnum.NOW__PRIVACY_DECISION__NTF_ID.id)
    }

}