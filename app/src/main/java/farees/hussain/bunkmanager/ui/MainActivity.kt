package farees.hussain.bunkmanager.ui

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import farees.hussain.bunkmanager.R
import farees.hussain.bunkmanager.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var b : ActivityMainBinding
    private lateinit var viewModel: SubjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        //navHeader settings
        var navView = navView
        var navHeader = navView.getHeaderView(0)
        var canBunkCount = navHeader.tvCanBunk
        var mustAttend = navHeader.tvMustAttend
        canBunkCount.apply {
//            if(getSubjectsCount>0) text = "Can Bunk : $totalCanBunk" else visibility = View.GONE
        }
        mustAttend.apply {
//            if(getSubjectsCount>0) text = "Can Attend : $totalMustAttend" else visibility = View.GONE
        }

        //tool bar settings and navigation
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(navView, navController)
        toolbar.apply {
            setupWithNavController(navController,appBarConfiguration)
            title = null
        }
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            toolbar.title = null
            when(destination.id){
                R.id.settingsFragment -> b.title.text = "Settings"
                R.id.classesFragment -> b.title.text = "Bunk Manager"
                R.id.timetableFragment -> b.title.text = "Time Table"
            }
        }

        // for notifications
        createNotificationChannel()
        makeNotification()

        //
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        toolbar.title = null
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

    var CHANNEL_ID = "channelId"
    var CHANNEL_NAME = "makeAttendanceNow"

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
    private fun makeNotification(){
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run{
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Awesome Notification")
            .setContentText("This is content Text")
            .setSmallIcon(R.drawable.ic_app_icon)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        val notifcationManager = NotificationManagerCompat.from(this)
        notifcationManager.notify(0,  notification)
    }
}