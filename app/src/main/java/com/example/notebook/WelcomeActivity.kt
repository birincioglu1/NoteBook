package com.example.notebook


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat


class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val motionLayout = findViewById<MotionLayout>( R.id.montion)
        motionLayout.transitionToEnd()

        Handler().postDelayed({
            //start main activity
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            //finish this activity
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            this@WelcomeActivity.overridePendingTransition(0,0)
            finish()
        },1500)
    }
}