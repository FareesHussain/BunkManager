package farees.hussain.bunkmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.adapter.ClassAdapter
import farees.hussain.bunkmanager.databinding.FragmentClassesBinding
import farees.hussain.bunkmanager.databinding.FragmentSettingsBinding
import farees.hussain.bunkmanager.db.model.Subject

class SettingsFragment : Fragment() {

    private lateinit var b : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentSettingsBinding.inflate(layoutInflater,container,false)


        return b.root
    }
}