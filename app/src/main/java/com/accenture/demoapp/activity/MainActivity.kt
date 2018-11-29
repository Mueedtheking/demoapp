package com.accenture.demoapp.view.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.accenture.demoapp.DemoApplication
import com.accenture.demoapp.DemoDatabase
import com.accenture.demoapp.database.DaoOperations
import com.accenture.demoapp.network.Restapi
import com.accenture.demoapp.view.fragments.AlbumListFragment
import com.accenture.demoappapp.R

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    var retrofit: Restapi? = null
        @Inject set
    var demoDb: DemoDatabase? = null
        @Inject set

    private lateinit var mDrawerLayout: DrawerLayout
    var fragment = Fragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDrawerLayout = findViewById(R.id.drawer_layout)
        setSupportActionBar(toolBars as Toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        var mDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, toolBars as Toolbar, R.string.app_name, R.string.app_name) {
        }
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
        nav_view.setNavigationItemSelectedListener { menuItem ->
            if (fragment !is AlbumListFragment) {
                fragment = AlbumListFragment()
                replaceFragment(fragment, false)
            }
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }
        (application as DemoApplication).getNetComponent()?.injectMain(this)
        fragment = AlbumListFragment()
        replaceFragment(fragment, false)

    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        if (addToBackStack) {
            transaction.addToBackStack(null)
        }
        transaction.replace(R.id.realtabcontent, fragment)
        transaction.commit()
        supportFragmentManager.executePendingTransactions()
    }

    override fun onBackPressed() {
        if (!returnBackStackImmediate(supportFragmentManager)) {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
    }

    fun getRestClient(): Restapi? {
        return retrofit
    }

    fun getDaoOperations(): DaoOperations? {
        return demoDb!!.daoOperations()
    }


    fun hideSplash() {
        ivSplash.visibility = View.GONE
    }



    private fun returnBackStackImmediate(fm: FragmentManager): Boolean {
        val fragments = fm.fragments
        if (fragments != null && fragments.size > 0) {
            for (fragment in fragments) {
                if (fragment.childFragmentManager.backStackEntryCount > 0) {
                    return if (fragment.childFragmentManager.popBackStackImmediate()) {
                        true
                    } else {
                        returnBackStackImmediate(fragment.childFragmentManager)
                    }
                }
            }
        }
        return false
    }

}