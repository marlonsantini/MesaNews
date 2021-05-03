package br.marlon.mesainc.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.marlon.mesainc.databinding.ActivityCadastroBinding
import br.marlon.mesainc.model.DefaultResponse
import br.marlon.mesainc.retrofit.service.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnReg.setOnClickListener {

            val email = binding.RegEmail.text.toString()
            val password = binding.RegSenha.text.toString()
            val nome = binding.RegNome.text.toString()

            if (email.isEmpty()) {
                binding.RegEmail.error = "Email requerido"
                binding.RegEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                binding.RegSenha.error = "Senha requerida"
                binding.RegSenha.requestFocus()
                return@setOnClickListener
            }

            if (nome.isEmpty()) {
                binding.RegNome.error = "Nome requerido"
                binding.RegNome.requestFocus()
                return@setOnClickListener
            }

            binding.pd.visibility = View.VISIBLE

            RetrofitInitializer.instance.cadastro(nome, email, password)
                    .enqueue(object : Callback<DefaultResponse> {
                        override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                            if (response.isSuccessful) {

                                //val token = response.body()
                                val intent = Intent(applicationContext, FeedActivity::class.java)
                                Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                                startActivity(intent)
                                finish()

                            } else {
                                Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
                                binding.pd.visibility = View.GONE
                            }
                        }

                        override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                            Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                            binding.pd.visibility = View.GONE
                        }

                    })
        }
    }
}