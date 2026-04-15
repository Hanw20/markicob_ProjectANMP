package com.markicob.Project_ANMP.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.markicob.Project_ANMP.R
import com.markicob.Project_ANMP.databinding.FragmentHabitListBinding
import com.markicob.Project_ANMP.viewmodel.ListViewModel

class HabitListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val studentListAdapter  = HabitListAdapter(arrayListOf())
    private lateinit var binding: FragmentHabitListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHabitListBinding.inflate(inflater,container, false)
        return binding.root

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.recViewHabit.layoutManager = LinearLayoutManager(context)
        binding.recViewHabit.adapter = studentListAdapter

        observeViewModel()


    }

    fun observeViewModel() {
        viewModel.habitsLD.observe(viewLifecycleOwner, Observer {
            studentListAdapter.updateHabitList(it)
        })
        viewModel.habitLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.txtError?.visibility = View.VISIBLE
            } else {
                binding.txtError?.visibility = View.GONE
            }
        })
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                binding.recViewHabit.visibility = View.GONE
                binding.progressLoad.visibility = View.VISIBLE
            } else {
                binding.recViewHabit.visibility = View.VISIBLE
                binding.progressLoad.visibility = View.GONE
            }
        })


    }


}