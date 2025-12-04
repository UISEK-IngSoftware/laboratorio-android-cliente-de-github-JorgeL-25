package ec.edu.uisek.githubclient.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import ec.edu.uisek.githubclient.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHost.navController

        val topBar = findViewById<MaterialToolbar>(R.id.topAppBar)
        topBar.setupWithNavController(navController)

        val fab = findViewById<FloatingActionButton>(R.id.fabNewProject)
        fab.setOnClickListener {
            navController.navigate(R.id.formularioFragment)
        }
    }
}
