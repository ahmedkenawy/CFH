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

/**
 * A Fragment that displays nearby venues based on the user's current location.
 * It uses Hilt for dependency injection and Kotlin Coroutines for background operations.
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {

    // ViewModel for the HomeFragment
    private val homeViewModel: HomeViewModel by viewModels()

    // Utility class for handling location-related tasks
    private val locationUtil: LocationUtil by lazy { LocationUtil(this) }

    // Binding object to access the views in the fragment_home.xml layout
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    /**
     * Called to have the fragment instantiate its user interface view.
     * @return The View for the fragment's UI.
     */
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

    /**
     * Called when the fragment is visible to the user and actively running.
     */
    override fun onResume() {
        super.onResume()
        checkLocationSettingsAndFetchVenues()
    }

    /**
     * Observes the ViewModel LiveData to update the UI based on the data changes.
     */
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

    /**
     * Sets the adapter for the RecyclerView to display the list of venues.
     * @param resource The successful response containing the list of venues.
     */
    private fun setListAdapter(resource: Resource.Success<VenuesResponse>) {
        val venues = resource.data
        val adapter = VenueAdapter { venue ->
            showPopupMenu(binding.root, venue)
        }
        binding.recyclerView.adapter = adapter
        adapter.submitList(venues?.response?.venues)
    }

    /**
     * Checks location settings and requests location permissions if needed.
     * If permissions are granted, fetches the user's current location and updates the ViewModel.
     */
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

    /**
     * Called when the user grants or denies the location permission.
     * @param requestCode The request code passed in requestPermissions.
     * @param permissions The requested permissions.
     * @param grantResults The grant results for the corresponding permissions.
     */
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

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional data from it.
     * @param requestCode The integer request code originally supplied to startActivityForResult().
     * @param resultCode The integer result code returned by the child activity through its setResult().
     * @param data An Intent, which can return result data to the caller.
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        locationUtil.handleLocationSettingsResult(requestCode, resultCode, data)
    }

    /**
     * Displays a Toast message.
     * @param message The message to display.
     */
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a PopupMenu with venue details.
     * @param view The view to anchor the PopupMenu.
     * @param venue The venue whose details are to be displayed.
     */
    private fun showPopupMenu(view: View, venue: Venue) {
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menu.apply {
            add("Name: ${venue.name}")
            add("Address: ${venue.location?.address}")
            add("Category: ${venue.categories?.get(0)?.name}")
        }
        popupMenu.show()
    }

    /**
     * Called when the view created by this fragment is destroyed.
     * Cleans up the binding reference to prevent memory leaks.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
