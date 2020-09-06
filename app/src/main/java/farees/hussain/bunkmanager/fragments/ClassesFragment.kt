package farees.hussain.bunkmanager.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import farees.hussain.bunkmanager.adapter.ClassAdapter
import farees.hussain.bunkmanager.databinding.FragmentClassesBinding
import farees.hussain.bunkmanager.model.Class

class ClassesFragment : Fragment() {

    private lateinit var b : FragmentClassesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentClassesBinding.inflate(layoutInflater,container,false)

        var subjects = ArrayList<Class>()
        b.rvClasses.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        subjects.add(Class("math",0,"0/0","Nothing Added", 0,0,0))
        b.rvClasses.adapter = ClassAdapter(subjects)

        return b.root
    }
}