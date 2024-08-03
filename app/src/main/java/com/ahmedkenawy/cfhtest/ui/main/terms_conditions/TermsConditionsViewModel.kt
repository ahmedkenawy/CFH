package com.ahmedkenawy.cfhtest.ui.main.terms_conditions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * ViewModel for managing and providing terms and conditions data in the TermsConditionsFragment.
 *
 * This ViewModel initializes and holds a list of terms and conditions that are displayed in the
 * TermsConditionsFragment. It uses LiveData to expose this data to the UI, allowing the fragment
 * to observe changes and update the view accordingly.
 *
 * @constructor Injects dependencies required by this ViewModel.
 */
@HiltViewModel
class TermsConditionsViewModel @Inject constructor() : ViewModel() {

    // LiveData containing the list of terms and conditions
    private val _termsConditions = MutableLiveData<List<String>>().apply {
        value = listOf(
            "Welcome to our application. By accessing and using our services, you agree to comply with and be bound by the following terms and conditions of use, which together with our privacy policy govern our relationship with you in relation to this application.",
            "The content of the pages of this application is for your general information and use only. It is subject to change without notice.",
            "This application uses cookies to monitor browsing preferences. If you do allow cookies to be used, your personal information may be stored by us for use by third parties.",
            "Neither we nor any third parties provide any warranty or guarantee as to the accuracy, timeliness, performance, completeness or suitability of the information and materials found or offered on this application for any particular purpose.",
            "Your use of any information or materials on this application is entirely at your own risk, for which we shall not be liable. It shall be your own responsibility to ensure that any products, services or information available through this application meet your specific requirements."
        )
    }

    /**
     * LiveData object exposing the list of terms and conditions to the UI.
     *
     * This LiveData provides the terms and conditions data to the fragment, allowing it to observe
     * changes and update the UI when the data changes.
     */
    val termsConditions: LiveData<List<String>> = _termsConditions
}
