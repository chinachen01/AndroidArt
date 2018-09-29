package com.slyser.androidart

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_activity)
        if (savedInstanceState == null) {
            val fragment = NavHostFragment.create(R.navigation.nav_graph)
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .setPrimaryNavigationFragment(fragment)
                    .commitNow()
        }
    }
}
