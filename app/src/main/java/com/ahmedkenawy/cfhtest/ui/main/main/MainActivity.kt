package com.ahmedkenawy.cfhtest.ui.main.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import com.ahmedkenawy.cfhtest.R
import com.ahmedkenawy.cfhtest.databinding.ActivityMainBinding
import com.ahmedkenawy.cfhtest.ui.auth.main.AuthActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * MainActivity is the primary activity for the application, responsible for setting up the
 * navigation drawer and handling navigation actions within the app.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being
     * shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState.
     * Note: Otherwise it is null.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Set up logout menu item click listener
        binding.navView.menu.findItem(R.id.logout).setOnMenuItemClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
            Toast.makeText(this, "Logout Successfully", Toast.LENGTH_SHORT).show()
            true
        }

        // Define top-level destinations for the AppBarConfiguration
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.profile, R.id.terms_condition
            ), drawerLayout
        )

        // Set up ActionBar with NavController and AppBarConfiguration
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    /**
     * Handle support navigation up action.
     *
     * @return true if navigation was successful, false otherwise.
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
