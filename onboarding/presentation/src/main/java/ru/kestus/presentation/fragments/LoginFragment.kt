package ru.kestus.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.kestus.core.domain.preferences.PreferencesStorage
import ru.kestus.presentation.R
import ru.kestus.presentation.databinding.FragmentLoginBinding
import ru.kestus.presentation.viewModel.LoginViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var preferencesStorage: PreferencesStorage

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("FragmentLoginBinding == null")

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setNavigationClickListeners()
        setInputListeners()
        setSocialButtonsClickListeners()
        observeInputErrors()
        observeSubmitButtonState()
        setSubmitButtonClickListener()
        observeFinished()
    }

    private fun observeFinished() {
        viewModel.finished.observe(viewLifecycleOwner) {
            it?.let { finished ->
                if (finished) {
                    activity?.finish()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setNavigationClickListeners() {
        binding.btnRegister.setOnClickListener {
            // TODO: fix launchSingleTop
            findNavController().navigate(
                R.id.action_loginFragment_to_registerFragment
            )
        }
    }

    private fun setSocialButtonsClickListeners() {
        binding.btnLoginVk.setOnClickListener {
            openBrowser(Uri.parse("https://vk.com"))
        }
        binding.btnLoginOk.setOnClickListener {
            openBrowser(Uri.parse("https://ok.ru"))
        }
    }

    private fun setInputListeners() {
        binding.apply {
            etEmail.addTextChangedListener {
                it?.let {
                    viewModel.setInputEmail(it)
                }
            }
            etPassword.addTextChangedListener {
                it?.let {
                    viewModel.setInputPassword(it)
                }
            }
        }
    }

    private fun observeInputErrors() {
        viewModel.errorEmail.observe(viewLifecycleOwner) {
            binding.etEmail.error = null
            it?.let {
                binding.etEmail.error = it
            }
        }
        viewModel.errorPassword.observe(viewLifecycleOwner) {
            binding.etPassword.error = null
            it?.let {
                binding.etPassword.error = it
            }
        }
    }

    private fun observeSubmitButtonState() {
        viewModel.submitButtonEnabled.observe(viewLifecycleOwner) {
            binding.btnSubmit.isEnabled = false
            if (it == true) {
                binding.btnSubmit.isEnabled = true
            }
        }
    }

    private fun setSubmitButtonClickListener() {
        binding.btnSubmit.setOnClickListener {
            if (it.isEnabled) {
                viewModel.login()
            }
        }
    }

    private fun openBrowser(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}