package app.adham.com.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.View
import app.adham.com.AdhamApp.mPreference
import app.adham.com.R
import app.adham.com.fragment.history.HistoryFragment
import app.adham.com.fragment.home.HomeFragment
import app.adham.com.fragment.MoreFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var homeFragment: HomeFragment
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setDefaultToolbar()
                tvToolbarTitle.text = resources.getString(R.string.discover)
                ivToolbarSearch.visibility = View.VISIBLE
                homeFragment = HomeFragment.newInstance(intent.getStringExtra("BrandID"))
                initializeFragment(homeFragment, R.id.main_container)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_history -> {
                if (mPreference.getBoolean(mPreference.isLogin)) {
                    setDefaultToolbar()
                    tvToolbarTitle.text = resources.getString(R.string.title_history)
                    ivToolbarSearch.visibility = View.GONE
                    val fragment = HistoryFragment()
                    initializeFragment(fragment, R.id.main_container)
                    return@OnNavigationItemSelectedListener true
                }
            }
            R.id.navigation_more -> {
                setDefaultToolbar()
                tvToolbarTitle.text = resources.getString(R.string.title_more)
                ivToolbarSearch.visibility = View.GONE
                val fragment = MoreFragment()
                initializeFragment(fragment, R.id.main_container)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        homeFragment = HomeFragment.newInstance(intent.getStringExtra("BrandID"))
        initializeFragment(homeFragment, R.id.main_container)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setUI()

    }

    private fun setUI() {
        ivToolbarArrow.setOnClickListener(this)
        ivToolbarSearch.setOnClickListener(this)
        svToolbar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (homeFragment != null) {
                    homeFragment.search(newText)
                }
                return false
            }
        })
        svToolbar.setOnCloseListener {
            setDefaultToolbar()
            false
        }
    }

    private fun initializeFragment(fragment: Fragment, id: Int) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(id, fragment, "HELLO")
        fragmentTransaction.commit()
    }

    override fun onClick(view: View) {
        val id = view.id
        when (id) {
            R.id.ivToolbarArrow -> {
                startActivity(Intent(this, CarTypeActivity::class.java))
                finish()
            }
            R.id.ivToolbarSearch -> {
                ivToolbarSearch.visibility = View.GONE
                tvToolbarTitle.visibility = View.GONE
                svToolbar.visibility = View.VISIBLE
                svToolbar.isIconified = false
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, CarTypeActivity::class.java))
        finish()
    }

    fun setDefaultToolbar() {
        svToolbar.visibility = View.GONE
        ivToolbarSearch.visibility = View.VISIBLE
        tvToolbarTitle.visibility = View.VISIBLE
    }

}
