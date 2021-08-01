package daniel.chatmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val host = supportFragmentManager.findFragmentById(R.id.main_container) as NavHostFragment
        navController = host.navController
        appBarConfig = AppBarConfiguration(navController.graph, drawerLayout)

        nav_view.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfig)

        if (userNotSignedIn()){
            navController.setGraph(R.navigation.login_graph)
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, host).setPrimaryNavigationFragment(host).commit()
    }

    private fun userNotSignedIn(): Boolean {
        return Firebase.auth.uid == null
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}