package farees.hussain.bunkmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import farees.hussain.bunkmanager.databinding.FragmentSettingsBinding
import farees.hussain.bunkmanager.databinding.FragmentTimetableBinding

class TimetableFragment : Fragment() {

    private lateinit var b : FragmentTimetableBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentTimetableBinding.inflate(layoutInflater,container,false)


        return b.root
    }
}