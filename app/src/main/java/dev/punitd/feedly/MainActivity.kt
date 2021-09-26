package dev.punitd.feedly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.punitd.base.android.extensions.viewBinding
import dev.punitd.feedly.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Setup Bottom Nav with NavController
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_container
        ) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        // This is to avoid reloading same fragment again when selected item is reselected.
        binding.bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId != binding.bottomNavigationView.selectedItemId)
                NavigationUI.onNavDestinationSelected(it, navController)
            true
        }
    }
}
