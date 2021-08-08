package daniel.chatmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import daniel.chatmodel.upcoming.dagger.ExampleScope
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

        // should ask about it.
        // todo what is the best way to make the login screen?
        // todo and what's the best way of mixing bottom nav bar and drawer layout together?
        if (userNotSignedIn()){
            navController.setGraph(R.navigation.login_graph)
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, host).setPrimaryNavigationFragment(host).commit()
    }

    private fun userNotSignedIn(): Boolean {
        //todo should be taken away from activity to some kinda ViewModel or something
        return Firebase.auth.uid == null
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfig) || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}