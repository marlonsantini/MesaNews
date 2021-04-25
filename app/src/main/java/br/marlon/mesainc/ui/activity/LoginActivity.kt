package br.marlon.mesainc.ui.activity

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.marlon.mesainc.R
import br.marlon.mesainc.databinding.ActivityLoginBinding
import br.marlon.mesainc.model.LoginResponse
import br.marlon.mesainc.retrofit.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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

        binding.btnLogin.setOnClickListener {

            val email = binding.RegEmail.text.toString()
            val password = binding.RegSenha.text.toString()

            if(email.isEmpty()){
                binding.RegEmail.error = "Email requerido"
                binding.RegEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()){
                binding.RegSenha.error = "Senha requerida"
                binding.RegSenha.requestFocus()
                return@setOnClickListener
            }

            RetrofitInitializer.instance.login(email, password)
                    .enqueue(object: Callback<LoginResponse>{
                        override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                            if(response.isSuccessful){

                                val token = response.body()
                                val intent = Intent(applicationContext, FeedActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()

                            }else{
                                Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                        }

                    })
        }
    }
}