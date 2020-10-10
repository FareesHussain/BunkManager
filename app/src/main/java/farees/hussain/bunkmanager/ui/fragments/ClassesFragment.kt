package farees.hussain.bunkmanager.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import farees.hussain.bunkmanager.adapter.SubjectItemAdapter
import farees.hussain.bunkmanager.databinding.FragmentClassesBinding
import farees.hussain.bunkmanager.db.model.Subject
import farees.hussain.bunkmanager.ui.SubjectViewModel

class ClassesFragment : Fragment() {

    private lateinit var b : FragmentClassesBinding
    private lateinit var viewModel : SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        b = FragmentClassesBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(SubjectViewModel::class.java)
        var subjects = ArrayList<Subject>().apply {
            add(Subject("math",75,"Not Yet Started","0/0",90, 117,0,0,false,id=2))
            add(Subject("math",75,"Not Yet Started","0/0",0, 0,0,0,false,id=3))
        }
        b.rvClasses.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        val subjectItemAdapter = SubjectItemAdapter()
        subjectItemAdapter.submitList(subjects)
        b.rvClasses.adapter = subjectItemAdapter
        var id = 4
        b.floatingActionButton.setOnClickListener {
            subjects.add(Subject("phy",75,"Not Yet Started","0/0",0, 0,0,0,false,id=id++))
            subjectItemAdapter.submitList(subjects)
        }

        return b.root
    }
}