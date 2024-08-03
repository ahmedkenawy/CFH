package com.ahmedkenawy.cfhtest.ui.main.terms_conditions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TermsConditionsViewModel @Inject constructor() : ViewModel() {

    private val _termsConditions = MutableLiveData<List<String>>().apply {
        value = listOf(
            "Welcome to our application. By accessing and using our services, you agree to comply with and be bound by the following terms and conditions of use, which together with our privacy policy govern our relationship with you in relation to this application.",
            "The content of the pages of this application is for your general information and use only. It is subject to change without notice.",
            "This application uses cookies to monitor browsing preferences. If you do allow cookies to be used, your personal information may be stored by us for use by third parties.",
            "Neither we nor any third parties provide any warranty or guarantee as to the accuracy, timeliness, performance, completeness or suitability of the information and materials found or offered on this application for any particular purpose.",
            "Your use of any information or materials on this application is entirely at your own risk, for which we shall not be liable. It shall be your own responsibility to ensure that any products, services or information available through this application meet your specific requirements."
        )
    }
    val termsConditions: LiveData<List<String>> = _termsConditions
}
