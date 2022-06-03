package com.philk7.ppaprojectapp

import android.os.SystemClock
import com.philk7.ppaprojectapp.utils.NotificationHandler
import org.junit.Assert
import org.junit.Test
import org.junit.Assert.*

class NotificationHandlerTest {

    @Test
    // not mocked anymore
    fun testCalculateSchedulingPoint(){
        val tolerance = 1500f
        val now = SystemClock.elapsedRealtime().toFloat()
        val result = NotificationHandler.calculateSchedulingPoint(10).toFloat()
        assertEquals(now + 10000, result, tolerance)
    }
}