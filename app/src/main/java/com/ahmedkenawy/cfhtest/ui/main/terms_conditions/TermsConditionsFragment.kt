package com.ahmedkenawy.cfhtest.ui.main.terms_conditions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.ahmedkenawy.cfhtest.databinding.FragmentTermConditionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TermsConditionsFragment : Fragment() {

    private var _binding: FragmentTermConditionsBinding? = null
    private val termsConditionsViewModel: TermsConditionsViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermConditionsBinding.inflate(inflater, container, false)
        binding.termsConditionsViewModel = termsConditionsViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
