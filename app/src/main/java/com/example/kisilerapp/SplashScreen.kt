package com.example.kisilerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreen : AppCompatActivity() {

    private val splashScreen = 4500

    lateinit var img_gif : ImageView
    lateinit var txtPersonApp : TextView
    lateinit var txtDesc : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        img_gif = findViewById(R.id.img_gif)
        txtPersonApp = findViewById(R.id.txtNoteApp)
        txtDesc = findViewById(R.id.txtDesc)

        showGIF()
        supportActionBar?.hide()

        val animation1 = AnimationUtils.loadAnimation(this,R.anim.animation1)
        val animation2 = AnimationUtils.loadAnimation(this,R.anim.animation2)

        img_gif.animation = animation1
        txtPersonApp.animation = animation2

        // Splash Screen

        GlobalScope.launch(Dispatchers.Main) {
            delay(splashScreen.toLong())
            val intent = Intent(this@SplashScreen,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
    fun showGIF(){
        val img_gif :ImageView = findViewById(R.id.img_gif)
        Glide.with(this).load(R.drawable.splash_screen).into(img_gif)
    }
}