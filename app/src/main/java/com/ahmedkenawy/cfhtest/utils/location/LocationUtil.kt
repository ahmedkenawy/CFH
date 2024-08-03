package com.ahmedkenawy.cfhtest.utils.location

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.ahmedkenawy.cfhtest.R
import com.ahmedkenawy.cfhtest.utils.Constants.LOCATION_PERMISSION_REQUEST_CODE
import com.google.android.gms.location.*
import com.google.android.gms.common.api.ResolvableApiException

class LocationUtil(private val fragment: Fragment) {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(fragment.requireContext())

    private val settingsClient: SettingsClient =
        LocationServices.getSettingsClient(fragment.requireContext())

    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = 10000 // 10 seconds
        fastestInterval = 5000 // 5 seconds
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val locationSettingsRequest: LocationSettingsRequest = LocationSettingsRequest.Builder()
        .addLocationRequest(locationRequest)
        .build()

    fun checkAndRequestLocationSettings(
        onLocationSettingsSatisfied: () -> Unit,
        onLocationSettingsNotSatisfied: (String) -> Unit
    ) {
        settingsClient.checkLocationSettings(locationSettingsRequest)
            .addOnSuccessListener {
                onLocationSettingsSatisfied()
            }
            .addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        exception.startResolutionForResult(
                            fragment.requireActivity() as Activity,
                            LOCATION_PERMISSION_REQUEST_CODE
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                        onLocationSettingsNotSatisfied("Failed to resolve location settings")
                    }
                } else {
                    onLocationSettingsNotSatisfied("Location settings cannot be fixed by showing a dialog")
                }
            }
    }

    fun askUserForLocationPermission(
        onPermissionGranted: () -> Unit,
        onPermissionDenied: () -> Unit
    ) {
        when {
            ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                onPermissionGranted()
            }

            fragment.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) -> {
                showPermissionRationaleDialog()
            }

            else -> {
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    fun getCurrentLocation(
        onLocationReceived: (Double, Double) -> Unit,
        onError: (String) -> Unit
    ) {
        if (ContextCompat.checkSelfPermission(
                fragment.requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, null)
                .addOnSuccessListener { location ->
                    location?.let {
                        onLocationReceived(it.latitude, it.longitude)
                    } ?: run {
                        getLastKnownLocation(onLocationReceived, onError)
                    }
                }
                .addOnFailureListener {
                    onError("Failed to get location")
                }
        } else {
            onError("Location permission not granted")
        }
    }

    private fun getLastKnownLocation(
        onLocationReceived: (Double, Double) -> Unit,
        onError: (String) -> Unit
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    onLocationReceived(it.latitude, it.longitude)
                } ?: run {
                    onError("Location not available")
                }
            }
            .addOnFailureListener {
                onError("Failed to get location")
            }
    }

    private fun showPermissionRationaleDialog() {
        AlertDialog.Builder(fragment.requireContext())
            .setTitle(fragment.getString(R.string.location_permission_required))
            .setMessage(fragment.getString(R.string.location_permission_is_required_to_use_this_feature_please_grant_the_permission_in_the_app_settings))
            .setPositiveButton(fragment.getString(R.string.ok)) { _, _ ->
                fragment.requestPermissions(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE
                )
            }
            .setNegativeButton(fragment.getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun handleLocationSettingsResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    // User agreed to change location settings
                    // You might want to check location settings again or proceed with your logic
                }

                Activity.RESULT_CANCELED -> {
                    // User did not agree to change location settings
                    // Handle accordingly
                }
            }
        }
    }
}
