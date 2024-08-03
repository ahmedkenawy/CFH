package com.ahmedkenawy.cfhtest.ui.auth.sign_up

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedkenawy.cfhtest.R
import com.ahmedkenawy.cfhtest.databinding.FragmentSingUpBinding
import com.ahmedkenawy.cfhtest.ui.main.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment responsible for handling user sign-up functionality.
 *
 * This fragment manages user input, interacts with the SignUpViewModel for user registration, and navigates to the MainActivity upon successful registration.
 */
@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSingUpBinding? = null
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingUpBinding.inflate(inflater, container, false)
        binding.viewModel = signUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe the registration success state
        signUpViewModel.isRegistrationSuccessful.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                // Navigate to MainActivity on successful registration
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            }
        }

        // Handle navigation to SignInFragment
        binding.tvSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_SingUpFragment_to_SingInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
