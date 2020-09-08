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

        var subjects = ArrayList<Subject>()
        b.rvClasses.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        subjects.add(Subject("math","Not Yet Started","0/0",0, 0,0,0,false,0))
        b.rvClasses.adapter = ClassAdapter(subjects)

        return b.root
    }
}