package br.marlon.mesainc.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.core.view.ViewCompat
import br.marlon.mesainc.R
import br.marlon.mesainc.databinding.ActivitySplashBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Animação layout
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        binding.TopImage.startAnimation(topAnim)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, LoginActivity::class.java)

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                        this,
                        binding.TopImage, ViewCompat.getTransitionName(binding.TopImage)
                )
                startActivity(intent, options.toBundle())
                finish()
            } else {
                TODO("VERSION.SDK_INT < LOLLIPOP")
            }

        }, 2000)

    }
}