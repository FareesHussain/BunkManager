package farees.hussain.bunkmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import farees.hussain.bunkmanager.adapter.TimetablePagerAdapter
import farees.hussain.bunkmanager.databinding.FragmentSettingsBinding
import farees.hussain.bunkmanager.databinding.FragmentTimetableBinding
import farees.hussain.bunkmanager.db.model.TimeTableItem
import farees.hussain.bunkmanager.other.Constants
import kotlinx.android.synthetic.main.fragment_timetable.*
import kotlinx.android.synthetic.main.fragment_timetable.view.*
import kotlinx.android.synthetic.main.timetable_pageritem.*
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
        timetableItems.add(TimeTableItem(Constants.timeTableDay.SATURDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.SATURDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.WEDNESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.WEDNESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.WEDNESDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.FRIDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.THURSDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.SUNDAY,"Math",Time(4,15,0),"AM" ))
        timetableItems.add(TimeTableItem(Constants.timeTableDay.MONDAY,"Math",Time(4,15,0),"AM" ))
        var days = mutableListOf<Constants.timeTableDay>()
        days.apply {
            add(Constants.timeTableDay.SUNDAY)
            add(Constants.timeTableDay.MONDAY)
            add(Constants.timeTableDay.TUESDAY)
            add(Constants.timeTableDay.WEDNESDAY)
            add(Constants.timeTableDay.THURSDAY)
            add(Constants.timeTableDay.FRIDAY)
            add(Constants.timeTableDay.SATURDAY)
        }
        val adapter = TimetablePagerAdapter(days, timetableItems)

        b.viewpager.adapter = adapter
        TabLayoutMediator(b.viewpagertab,b.viewpager){ tab, position ->
            tab.text = days[position].toString()
        }.attach()


        return b.root
    }
}