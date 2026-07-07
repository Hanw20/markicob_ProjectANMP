package com.markicob.project_anmp_uas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.markicob.project_anmp_uas.databinding.FragmentLoginBinding
import com.markicob.project_anmp_uas.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(
            inflater,
            container, false
        )
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.btnLogin.setOnClickListener {
//            val username = binding.txtUsername.text.toString()
//            val password = binding.txtPassword.text.toString()
//
//            if (username == "student" && password == "123") {
//                val action = LoginFragmentDirections.actionHabitListFragment()
//                it.findNavController().navigate(action)
//            } else {
//                binding.txtErrorLogin.visibility = View.VISIBLE
//                binding.txtErrorLogin.text = "Username atau password salah!"
//            }
//        }
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observeViewModel()
        viewModel.checkSession()

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(username, password)
        }
    }

    fun observeViewModel() {
        viewModel.autoLoginLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = LoginFragmentDirections.actionHabitListFragment()
                view?.findNavController()?.navigate(action)
            }
        })
        viewModel.loginResultLD.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                val action = LoginFragmentDirections.actionHabitListFragment()
                view?.findNavController()?.navigate(action)
            } else {
                binding.txtErrorLogin.visibility = View.VISIBLE
                binding.txtErrorLogin.text = "Username atau password salah!"
            }
        })
    }
}