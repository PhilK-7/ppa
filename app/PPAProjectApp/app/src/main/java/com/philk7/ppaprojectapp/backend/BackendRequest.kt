package com.philk7.ppaprojectapp.backend

import android.content.Context
import com.philk7.ppaprojectapp.R
import org.apache.commons.io.IOUtils
import org.apache.http.conn.ssl.AllowAllHostnameVerifier
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.StringWriter
import java.net.URL
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory

// uses an external dependency
class BackendRequest(
    // IP and port of the running backend that connects to the DB
    private val ipport: String,
    // which query function to prompt
    private val backendQueryFunction: String,
    // which HTTP method must be used for the query function
    private val queryMethod: String,
    // the parameters to provide in the HTTP request
    private var params: Map<String, String>,
    // needed for building trust store
    private val context: Context
) {

    /**
     * Performs a HTTP request to the backend, given by the class fields.
     * @return A JSON result, or an error message.
     */
    fun performRequest(): String {

        // build request URL
        val requestUrlBuilder = StringBuilder()
        requestUrlBuilder.append("https://")
        requestUrlBuilder.append(ipport)
        requestUrlBuilder.append("/")
        requestUrlBuilder.append("dbqueries")
        requestUrlBuilder.append("/")
        requestUrlBuilder.append(backendQueryFunction)
        var firstParam = true // flag for first parameter
        if (params.isNotEmpty()) {  // add parameters
            requestUrlBuilder.append("?")
            for ((key, value) in params) {
                if (!firstParam) requestUrlBuilder.append("&") // use ampersand for 2nd param & up
                else firstParam = false
                requestUrlBuilder.append(key)
                requestUrlBuilder.append("=")
                requestUrlBuilder.append(value)
            }
        }

        val code: Int

        return try {
            // do HTTP request

            val myURL = URL(requestUrlBuilder.toString())

            // set SSL certificate and key
            val cf = CertificateFactory.getInstance("X.509")
            val caInput: InputStream =
                BufferedInputStream(context.resources.openRawResource(R.raw.ppa))
            val cert = cf.generateCertificate(caInput)
            val keyStoreType = KeyStore.getDefaultType()
            val ks = KeyStore.getInstance(keyStoreType)
            ks.load(null, null)
            ks.setCertificateEntry("ppa", cert)
            val tmfa = TrustManagerFactory.getDefaultAlgorithm()
            val tmf = TrustManagerFactory.getInstance(tmfa)
            tmf.init(ks)
            val sslContext = SSLContext.getInstance("TLS")
            sslContext.init(null, tmf.trustManagers, null)
            val connection = myURL.openConnection() as HttpsURLConnection  // open connection
            /* Next line means the Android app will trust all host names, but only with the exact
            certificate here. For production, get a static IP / or domain name, and use a
             new certificate here instead (it needs to be swapped in Flask backend and Android app).
             */
            connection.hostnameVerifier = AllowAllHostnameVerifier()
            connection.sslSocketFactory = sslContext.socketFactory
            connection.requestMethod = queryMethod
            connection.setRequestProperty("User-Agent", "Mozilla/5.0")

            try {  // get response input
                code = connection.responseCode
                println(code)

                // read input and convert to string
                val responseStream = connection.inputStream
                val writer = StringWriter()
                IOUtils.copy(responseStream, writer, "UTF-8")
                writer.toString()
            } catch (e: IOException) {
                e.toString()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            if (e is KeyStoreException) "Certificate invalid?" else "Request failed."
        } catch (e: CertificateException) {
            e.printStackTrace()
            if (e is KeyStoreException) "Certificate invalid?" else "Request failed."
        } catch (e: KeyStoreException) {
            e.printStackTrace()
            "Certificate invalid?"
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            if (e is KeyStoreException) "Certificate invalid?" else "Request failed."
        } catch (e: KeyManagementException) {
            e.printStackTrace()
            if (e is KeyStoreException) "Certificate invalid?" else "Request failed."
        }
    }


}