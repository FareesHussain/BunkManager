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
import farees.hussain.bunkmanager.db.model.Subject

class ClassesFragment : Fragment() {

    private lateinit var b : FragmentClassesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentClassesBinding.inflate(layoutInflater,container,false)
        var subjects = ArrayList<Subject>().apply {
            add(Subject("math","Not Yet Started","0/0",90, 117,0,0,false))
            add(Subject("math","Not Yet Started","0/0",0, 0,0,0,false))
        }
        b.rvClasses.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        b.rvClasses.adapter = ClassAdapter(subjects)

        return b.root
    }
}