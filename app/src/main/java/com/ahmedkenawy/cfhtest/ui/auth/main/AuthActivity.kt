package com.ahmedkenawy.cfhtest.ui.auth.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.ahmedkenawy.cfhtest.R
import com.ahmedkenawy.cfhtest.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity for handling authentication-related screens.
 *
 * This activity serves as the entry point for authentication features in the app. It sets up navigation and handles the
 * action bar configuration to support up navigation.
 *
 * The activity uses [Jetpack Navigation Component](https://developer.android.com/guide/navigation) to manage fragments
 * within a navigation host.
 */
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout and set the content view
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar as the action bar
        setSupportActionBar(binding.toolbar)

        // Get the navigation controller and set up the action bar with it
        val navController = findNavController(R.id.nav_host_fragment_content_auth)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    /**
     * Handles the up navigation.
     *
     * This method ensures that the user can navigate up within the navigation hierarchy of the app.
     *
     * @return True if the navigation was successful, otherwise false.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_auth)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
