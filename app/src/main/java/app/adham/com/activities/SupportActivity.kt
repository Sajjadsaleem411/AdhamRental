package app.adham.com.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import app.adham.com.R
import kotlinx.android.synthetic.main.activity_support.*

class SupportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_support)

        ivSupportToolbarArrow.setOnClickListener { finish() }
        rlBtnFeedback.setOnClickListener { finish() }
    }
}
