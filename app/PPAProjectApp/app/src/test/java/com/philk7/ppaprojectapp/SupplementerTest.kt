package com.philk7.ppaprojectapp

import com.philk7.ppaprojectapp.utils.Supplementer
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class SupplementerTest {

    @Test
    fun testComputeSchedulePointsForWindow(){
        // window -p -> 10h + p, so that padded window is 0 -> 10h
        val spl = Supplementer.computeSchedulePointsForWindow(10,
            -Supplementer.WINDOW_SCHEDULE_PADDING.toLong(),
            36000000+ Supplementer.WINDOW_SCHEDULE_PADDING.toLong())
        println(spl)
        for(i in 0..9){
            assert(spl[i] >= 3600000*i && spl[i] <= 3600000*(i+1))
        }
    }


    @Test
    fun testWriteTimeWindowStartsInner() {
        val sps = listOf(0, 60000, 120000, 180000, 240000)
        val outs = mutableListOf<String>()
        for(sp in sps){
            val cal = Calendar.getInstance().apply {
                timeInMillis = sp.toLong()
            }
            val sdf = SimpleDateFormat("hh:mm:ss", Locale.GERMANY)
            val clockTime = sdf.format(cal.time)
            outs.add(clockTime)
        }

        println(outs)  // depends also on summer time
        assert(outs[0].contains(":00"))
        assert(outs[1].contains(":01"))
        assert(outs[2].contains(":02"))
        assert(outs[3].contains(":03"))
        assert(outs[4].contains(":04"))
    }

}