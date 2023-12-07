package com.example.navigationdrawerproject_gr2

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.navigationdrawerproject_gr2.databinding.ActivityMainBinding
import com.example.navigationdrawerproject_gr2.fragment.AboutUsFragment
import com.example.navigationdrawerproject_gr2.fragment.HomeFragment
import com.example.navigationdrawerproject_gr2.fragment.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addIconToAppBar()
        initToggleAndDrawerLayout()
        setFragment(HomeFragment())
        handleNavigationItems()
    }

    private fun handleNavigationItems() {
        binding.navigation.setNavigationItemSelectedListener { menuItem ->
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawers()
            }
            when (menuItem.itemId) {
                R.id.homeItem -> setFragment(HomeFragment())
                R.id.settingsItem -> setFragment(SettingsFragment())
                R.id.AboutUsItem -> setFragment(AboutUsFragment())
            }
            true
        }
    }

    private fun initToggleAndDrawerLayout() {
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun addIconToAppBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) return true
        return super.onOptionsItemSelected(item)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
            commit()
        }
    }


}