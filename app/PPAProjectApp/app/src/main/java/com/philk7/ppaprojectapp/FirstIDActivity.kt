package com.philk7.ppaprojectapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.philk7.ppaprojectapp.backend.BackendRequest
import com.philk7.ppaprojectapp.utils.ProlificIDTextHandler
import org.json.JSONException
import org.json.JSONObject


class FirstIDActivity : AppCompatActivity() {

    private var isFullscreen: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        setContentView(R.layout.activity_firstid)
        isFullscreen = true

        // animations
        findViewById<TextView>(R.id.initial_thank_you_text).startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.show_delay_special)
        )
        findViewById<ImageView>(R.id.maskImage).startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.img_grow_bigger)
        )

        // add Prolific ID text handler
        val pidET = findViewById<EditText>(R.id.editTextId)
        pidET.addTextChangedListener(
            ProlificIDTextHandler(
                pidET,
                pidET.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            )
        )

        // continue: checks length, validity, inserts PID, and goes to consent form activity
        findViewById<Button>(R.id.id_enter_continue_button).setOnClickListener {
            val i = Intent(this, ConsentFormActivity::class.java)

            // validate and process PID
            val enteredID = pidET.text.toString()
            val idParts = enteredID.split("-")
            val finalID = idParts.reduce { agg, element -> agg + element }

            if (finalID.length == 24 || ALLOW_ANY_LENGTH) {  // must not be empty

                // currently disabled by standard, as login should be possible with yet unknown PID
                // assumes PID exists in pids table instead of inserting entered id
                if (CHECK_PID_EXISTS) {

                    // check that PID is correct and exists (backend interaction)
                    val params: MutableMap<String, String> = HashMap()
                    params["pid"] = finalID

                    val br = BackendRequest(
                        this.applicationContext.resources.getString(R.string.backend_address),
                        "check_user_exists", "GET",
                        params, applicationContext
                    )
                    // run request on separate thread (mandatory)

                    var response = "norun"
                    val runnable = Runnable {
                        response = br.performRequest()
                    }
                    val sepThread = Thread(runnable)
                    sepThread.start()
                    sepThread.join(this.applicationContext.resources
                        .getInteger(R.integer.server_request_timeout_std)
                        .toLong())  // try to join thread within timeout ms

                    // react to response
                    if (response == "norun") {  // response norun or some other error
                        val ts = "An error occurred on the backend side. Is the server down?"
                        // display warning
                        val wt = Toast.makeText(
                            this,
                            ts, Toast.LENGTH_LONG
                        )
                        wt.show()
                    } else {  // got response from backend
                        val result =
                            try {  // normal check
                                val rj = JSONObject(response)   // response is always JSON
                                rj["result"]  // result object, is array / boolean / number ...
                            } catch (e: JSONException) {
                                "No JSON"
                            }

                        // handle result
                        if (result is Boolean) {
                            if (result) {  // continue to next activity (consent form)
                                i.putExtra(PID_KEY, finalID)
                                startActivity(i)
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            } else {  // display info message
                                val errMsg = "The Prolific ID does not exist (yet)!"
                                val mt = Toast.makeText(
                                    this,
                                    errMsg, Toast.LENGTH_LONG
                                )
                                mt.show()
                            }
                        } else {
                            val toast = Toast.makeText(
                                this,
                                "Failure: incorrect server response!", Toast.LENGTH_SHORT
                            )
                            toast.show()
                        }
                    }

                } else {  // current standard: PID not on server yet, register now

                    if(!IGNORE_REGISTRATION){  // the NORMAL CASE: correct length, register

                        // save entered ID on server
                        val param = HashMap<String, String>()
                        param["pid"] = finalID
                        val saveBr = BackendRequest(
                            this.applicationContext.resources.getString(R.string.backend_address),
                            "pids_insert",
                            "POST",
                            param,
                            this.applicationContext
                        )


                        var response = "norun"
                        val run = Runnable {
                            response = saveBr.performRequest()
                        }
                        val sepThread = Thread(run)
                        sepThread.start()
                        sepThread.join(this.applicationContext.resources
                            .getInteger(R.integer.server_request_timeout_std)
                            .toLong())

                        try{
                            val result = JSONObject(response)["result"].toString()  // parse response

                            if (!result.startsWith("SUCCESS")) {
                                val failToast = Toast.makeText(
                                    this.applicationContext,
                                    "Error: ID could not be saved. Is the server down?",
                                    Toast.LENGTH_SHORT
                                )
                                failToast.show()

                                return@setOnClickListener  // do not continue then

                            } else {  // successful insert

                                val registeredToast = Toast.makeText(this.applicationContext,
                                    "Registration successful!", Toast.LENGTH_LONG)
                                registeredToast.show()

                                // proceed to next activity
                                i.putExtra(PID_KEY, finalID)
                                startActivity(i)
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                            }
                        }
                        catch(e: JSONException){
                            /* Attempting to insert a primary key already existing in the database
                            lets the backend return code 500, which throws an exception, e.g.
                            FileNotFoundException, in BackendRequest. The returned JSON may be valid,
                            but is not returned by the class. Therefore, a JSONException occurs.
                             */
                            val errorToast = Toast.makeText(this.applicationContext,
                                "Error: Bad server response, or PID already registered.",
                                Toast.LENGTH_SHORT)
                            errorToast.show()
                        }
                    }

                    else {  // no registration (for showcasing / server offline)
                        Toast.makeText(this.applicationContext,
                            "No registration.", Toast.LENGTH_SHORT).show()

                        // proceed to next activity anyway
                        i.putExtra(PID_KEY, finalID)
                        startActivity(i)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }


                }


            } else {  // only continue when ID there (and correct), else display warning
                val toast = Toast.makeText(
                    this,
                    "Please enter your correct Prolific ID!" +
                            " Check also for length (24 characters).", Toast.LENGTH_SHORT
                )
                toast.show()

            }

        }
    }


    companion object {

        const val PID_KEY = "pid"  // use in intent extra, sharedpref etc.

        // standard false for ff. values
        const val CHECK_PID_EXISTS = false  // set to true if login check should be performed
        const val ALLOW_ANY_LENGTH = false  // set to true to ignore PID length check
        const val IGNORE_REGISTRATION = false  // set to true to not register user, for showcasing
    }
}