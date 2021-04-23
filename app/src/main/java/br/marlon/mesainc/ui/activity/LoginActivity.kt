package br.marlon.mesainc.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.animation.AnimationUtils
import br.marlon.mesainc.R
import br.marlon.mesainc.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Animação layout
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        binding.emailTextInputLayout.startAnimation(topAnim)
        binding.passwordTextInputLayout.startAnimation(topAnim)
        binding.tvNovoUsuario.startAnimation(topAnim)
        binding.tvInscrevaSe.startAnimation(topAnim)


        binding.tvInscrevaSe.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)

            //Animação layout
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this,
                    Pair.create(binding.emailTextInputLayout, "email_tran"),
                    Pair.create(binding.passwordTextInputLayout, "password_tran"),
                    Pair.create(binding.tvBemVindo, "logo_text"),
                    Pair.create(binding.tvNovoUsuario, "novousuario_tran"),
                    Pair.create(binding.tvInscrevaSe, "cadastro_tran"),
                    Pair.create(binding.btnLogin, "button_tran")
                )
                startActivity(intent, options.toBundle())
            }
        }
    }
}