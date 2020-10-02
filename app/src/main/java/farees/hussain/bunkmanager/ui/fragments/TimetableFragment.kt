package farees.hussain.bunkmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import farees.hussain.bunkmanager.databinding.FragmentSettingsBinding
import farees.hussain.bunkmanager.databinding.FragmentTimetableBinding
import farees.hussain.bunkmanager.db.model.TimeTableItem
import farees.hussain.bunkmanager.other.Constants
import java.sql.Time
import java.time.DayOfWeek

class TimetableFragment : Fragment() {

    private lateinit var b : FragmentTimetableBinding
    val timetableItems = mutableListOf<TimeTableItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentTimetableBinding.inflate(layoutInflater,container,false)
        timetableItems.add(TimeTableItem(Constants.timeTableDay.MONDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.TUESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.TUESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.WEDNESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.FRIDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.MONDAY,"Math",Time(4,15,0),"AM" ))



        return b.root
    }
}