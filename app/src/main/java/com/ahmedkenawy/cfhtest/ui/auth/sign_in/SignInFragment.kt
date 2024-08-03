package com.ahmedkenawy.cfhtest.ui.auth.sign_in

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedkenawy.cfhtest.R
import com.ahmedkenawy.cfhtest.databinding.FragmentSingInBinding
import com.ahmedkenawy.cfhtest.ui.main.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment for handling user sign-in functionality.
 *
 * This fragment provides the user interface for signing in. It observes the sign-in state from the [SignInViewModel]
 * and navigates to the main activity upon successful sign-in. It also provides navigation to the sign-up fragment.
 */
@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private val singInViewModel: SignInViewModel by viewModels()

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    /**
     * Inflates the layout and initializes the binding.
     *
     * @param inflater LayoutInflater to inflate the layout.
     * @param container The parent view group.
     * @param savedInstanceState Saved instance state.
     * @return The root view of the fragment's layout.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSingInBinding.inflate(inflater, container, false)
        binding.viewModel = singInViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    /**
     * Sets up observers and click listeners.
     *
     * Observes the [isSignInSuccessful] LiveData from [SignInViewModel] to handle sign-in success and navigate to
     * the [MainActivity]. Also sets up navigation to the sign-up fragment.
     *
     * @param view The root view of the fragment's layout.
     * @param savedInstanceState Saved instance state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singInViewModel.isSignInSuccessful.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
                Toast.makeText(requireContext(), "Sign in successful", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvSignUp.setOnClickListener {
            findNavController().navigate(R.id.action_SingInFragment_to_SingUpFragment)
        }
    }

    /**
     * Cleans up the binding reference.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
