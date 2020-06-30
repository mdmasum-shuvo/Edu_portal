package com.masum.edu_portal.feature.home.view

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.navigation.NavigationView
import com.masum.edu_portal.BuildConfig
import com.masum.edu_portal.R
import com.masum.edu_portal.common.BaseActivity
import com.masum.edu_portal.databinding.ActivityHomeBinding
import com.masum.edu_portal.feature.member.view.MyProfileActivity
import com.masum.edu_portal.feature.member.view.ProfileActivity
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.colupsing_toolbar_layout.*
import kotlinx.android.synthetic.main.content_home.*
import java.util.*

class HomeActivity : BaseActivity() {
    private val VERSION = "Version : " + BuildConfig.VERSION_NAME
    private val GOOGLE_PLAY = "http://play.google.com/store/apps/details?id="
    private var drawer: DrawerLayout? = null
    private var toggle: ActionBarDrawerToggle? = null
    private lateinit var navController: NavController
    private lateinit var navControllerNested: NavController
    private lateinit var binding: ActivityHomeBinding
    private lateinit var mActivity: Activity
    override fun getLayoutResourceFile(): Int {
        return R.layout.activity_home
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initComponent() {
        binding = getBinding() as ActivityHomeBinding
        mActivity = this
        setNotificationBarBlackNWhite()
        setToolbar()
        setDrawerNavigation()
        setBottomNavigation()
       // listener = HomeRepository()
        //viewModel = ViewModelProviders.of(mActivity as HomeActivity).get(MyViewModel::class.java)
        //loadFragment(HomeDashboardFragment())
    }


    override fun initFunctionality() {
       // callData()
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initListener() {
       // callLiveDataListener();

        notification.setOnClickListener {
            val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            startActivity(NotificationActivity::class.java,false,bundle)

        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun setToolbar() {
        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        val myProfile: FrameLayout = findViewById(R.id.profile)
        myProfile.setOnClickListener {
            launchMyProfile()
        }
        val notification: FrameLayout = findViewById(R.id.notification)
        notification.setOnClickListener { }
        val notificationBadge: TextView = findViewById(R.id.badge_notification)
        drawer = findViewById(R.id.drawer_layout)
        notificationBadge.text = "2"
        toggle = ActionBarDrawerToggle(
            this, drawer, toolbar
            ,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle!!)
        Objects.requireNonNull(supportActionBar)
            ?.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun launchMyProfile() {
        val bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
        val profileIntent = Intent(this, MyProfileActivity::class.java)
        //findNavController(R.id.fragNavHost).navigate(R.id.memberDetailFragment)
        startActivity(profileIntent, bundle)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        toggle?.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle!!.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun setDrawerNavigation() {
        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            val id = menuItem.itemId
            drawer!!.closeDrawers()
            when (id) {
                R.id.nav_item_my_profile -> {
                    // launchMyProfile()
                    true
                }

                else -> false
            }
        }
        val tvVersion =
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.tv_version)
        tvVersion.setText(VERSION)
    }


    private fun setBottomNavigation() {

        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragNavHost) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(home_bottom_nav_menu, navHostFragment.navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
        //var appBarConfiguration = AppBarConfiguration(navHostFragment.navController.graph)

        var appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.HomeDashboardFragment,
                R.id.postFragment,
                R.id.GalleryFragment,
                R.id.NotificationFragment
            )
        )
        //setOf(R.id.HomeDashboardFragment,R.id.GalleryFragment,R.id.PaymentFragment,R.id.aboutOcdFragment,R.id.profileFragment)
        setupActionBarWithNavController(navHostFragment.navController, appBarConfiguration)
        /*       val bottomMenu: BottomNavigationView = findViewById(R.id.home_bottom_nav_menu)
               bottomMenu.setOnNavigationItemSelectedListener { menuItem ->
                   var fragment: Fragment? = null
                   when (menuItem.itemId) {
       *//*                R.id.action_home -> fragment = HomeFragment()
                R.id.action_blood -> fragment = BloodFragment()
                R.id.action_ambulance -> fragment = AmbulanceFragment()
                R.id.action_diagnostic -> fragment = DiagnosticCenterFragment()
                R.id.action_hospital -> fragment = HospitalFragment()*//*
            }
            loadFragment(fragment)
        }*/
    }

    fun showBottomNavigation() {
        home_bottom_nav_menu.visibility = View.VISIBLE
    }

    fun hideBottomNavigation() {
        home_bottom_nav_menu.visibility = View.GONE
    }


    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return super.onSupportNavigateUp()
    }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_home_activity, fragment)
                .commit()
            return true
        }
        return false
    }


/*    private fun gotToPlayStore() {
        val uri =
            Uri.parse("market://details?id=" + this.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        goToMarket.addFlags(
            Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        )
        try {
            startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(HomeActivity.GOOGLE_PLAY + this.packageName)
                )
            )
        }
    }*/

    override fun onInternetConnectivityChanged(isConnected: Boolean) {


    }

    override fun onRefresh() {
    }
/*
    override fun memberList(pageNumber: Int) {
        when (pageNumber) {
            1 -> {
                homeFragment.callMemberList(memberList)

            }
            else -> {
                viewModel.getMemberList(pageNumber, listener)
            }
        }
    }

    override fun aboutDataList() {
    }

    override fun memberStatus(pageNumber: Int) {
        when (pageNumber) {
            *//*     1 -> {

                 }
                 else -> {
                     viewModel.getAboutUsDataList(listener)
                 }*//*

        }
    }

    override fun error() {

    }*/

  /*  override fun progress() {
    }
*/
}
