package com.ahmedkenawy.cfhtest.ui.main.terms_conditions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedkenawy.cfhtest.databinding.FragmentTermConditionsBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * TermsConditionsFragment displays the terms and conditions of the application.
 *
 * This fragment uses View Binding to manage its UI and is dependent on a ViewModel for
 * providing data to the view. The ViewModel is injected using Hilt for dependency injection.
 */
@AndroidEntryPoint
class TermsConditionsFragment : Fragment() {

    // View Binding for this fragment's layout
    private var _binding: FragmentTermConditionsBinding? = null
    private val binding get() = _binding!!

    // ViewModel for managing the terms and conditions data
    private val termsConditionsViewModel: TermsConditionsViewModel by viewModels()

    /**
     * Called to create the view hierarchy associated with this fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return The root view of the fragment's layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout using View Binding
        _binding = FragmentTermConditionsBinding.inflate(inflater, container, false)
        // Set the ViewModel and lifecycle owner for data binding
        binding.termsConditionsViewModel = termsConditionsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    /**
     * Called when the view hierarchy associated with this fragment is being destroyed.
     * This is where you should clean up resources that were tied to the view.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        // Nullify the binding to prevent memory leaks
        _binding = null
    }
}
