package com.ahmedkenawy.cfhtest.ui.main.home

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ahmedkenawy.cfhtest.databinding.FragmentHomeBinding
import com.ahmedkenawy.cfhtest.domain.model.response.Venue
import com.ahmedkenawy.cfhtest.domain.model.response.VenuesResponse
import com.ahmedkenawy.cfhtest.ui.main.home.adapter.VenueAdapter
import com.ahmedkenawy.cfhtest.utils.Constants.LOCATION_PERMISSION_REQUEST_CODE
import com.ahmedkenawy.cfhtest.utils.Resource
import com.ahmedkenawy.cfhtest.utils.location.LocationUtil
import dagger.hilt.android.AndroidEntryPoint
import es.dmoral.toasty.Toasty


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private val locationUtil: LocationUtil by lazy { LocationUtil(this) }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = homeViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        checkLocationSettingsAndFetchVenues()
    }

    private fun observeViewModel() {
        homeViewModel.venuesLiveData.observe(viewLifecycleOwner) { resource ->
            when (resource) {
                is Resource.Loading -> {
                    // Handle loading state (e.g., show a loading indicator)
                }

                is Resource.Success -> {
                    setListAdapter(resource)
                }

                is Resource.Error -> {
                    val errorMessage = resource.message
                    Toasty.error(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                }

                else -> {
                    // Handle other cases if necessary
                }
            }
        }
    }

    private fun setListAdapter(resource: Resource.Success<VenuesResponse>) {
        val venues = resource.data
        val adapter = VenueAdapter { venue ->
            showPopupMenu(binding.root, venue)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(venues?.response?.venues)
    }

    private fun checkLocationSettingsAndFetchVenues() {
        locationUtil.checkAndRequestLocationSettings(
            onLocationSettingsSatisfied = {
                locationUtil.askUserForLocationPermission(
                    onPermissionGranted = {
                        locationUtil.getCurrentLocation(
                            onLocationReceived = { latitude, longitude ->
                                homeViewModel.getVenues("$latitude,$longitude")
                            },
                            onError = { error ->
                                showToast(error)
                            }
                        )
                    },
                    onPermissionDenied = {
                        showToast("Location permission denied")
                    }
                )
            },
            onLocationSettingsNotSatisfied = { error ->
                showToast(error)
            }
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                locationUtil.getCurrentLocation(
                    onLocationReceived = { latitude, longitude ->
                        homeViewModel.getVenues("$latitude,$longitude")
                    },
                    onError = { error ->
                        showToast(error)
                    }
                )
            } else {
                showToast("Location permission denied")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationUtil.handleLocationSettingsResult(requestCode, resultCode, data)
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun showPopupMenu(view: View, venue: Venue) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menu.apply {
            add("Name: ${venue.name}")
            add("Address: ${venue.location?.address}")
            add("Category: ${venue.categories?.get(0)?.name}")
        }
        popupMenu.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
