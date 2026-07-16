package com.markicob.project_anmp_uas.view
import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.markicob.project_anmp_uas.databinding.FragmentNewHabitBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.markicob.project_anmp_uas.viewmodel.ListViewModel

class EditHabitFragment : Fragment() {

    private lateinit var binding: FragmentNewHabitBinding
    private lateinit var viewModel: ListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewHabitBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        val icons = listOf("Water","Exercise","Read","Walk","Meditation","Diet","Sleep","Medicine","Study","Other")
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, icons)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spinnerIcon.adapter = spinnerAdapter
        binding.tvTitle.text = "Edit Habit"
        binding.btnCreateHabit.text = "Submit"

        val habitld = EditHabitFragmentArgs.fromBundle(requireArguments()).habitld

        viewModel.fetch(habitld)

        viewModel.habitDetailLD.observe(viewLifecycleOwner, Observer { habits ->
            binding.habit = habits
            val iconPosition = icons.indexOf(habits.icon)
            if (iconPosition >= 0) {
                binding.spinnerIcon.setSelection(iconPosition)
            }
        })

        binding.btnCreateHabit.setOnClickListener { v ->
            val selectedIcon = icons[binding.spinnerIcon.selectedItemPosition]
            val habit = binding.habit!!.copy(
                description = binding.etDescription.text.toString(),
                goal = binding.etGoal.text.toString().toIntOrNull() ?: 0,
                unit = binding.etUnit.text.toString(),
                icon = selectedIcon
            )
            viewModel.updateHabit(habit)
            Toast.makeText(v.context, "Habit Updated", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
    override fun onResume() {
        super.onResume()
        viewModel.refresh()
    }
}