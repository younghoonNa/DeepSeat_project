package com.deepseat.ds.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.deepseat.ds.GlobalData
import com.deepseat.ds.R
import com.deepseat.ds.api.UserServiceImpl
import com.deepseat.ds.databinding.ActivityLoginBinding
import com.deepseat.ds.vo.ResponseBody
import com.deepseat.ds.vo.UserVO
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.btnLogin.setOnClickListener {
            val userID = binding.edtLoginId.text.toString().trim()
            val userPW = binding.edtLoginPw.text.toString().trim()

            val call: Call<String> = UserServiceImpl.service.loginUser(userID, userPW)

            call.enqueue(object : Callback<String> {
                override fun onResponse(
                    call: Call<String>,
                    response: Response<String>
                ) {
                    val responseBody = Gson().fromJson(response.body(), ResponseBody::class.java)

                    if (responseBody == null || responseBody.responseCode != 200 || responseBody.data as? String == null) {
                        Snackbar.make(binding.root, R.string.login_id, Snackbar.LENGTH_LONG).show()
                    } else {
                        GlobalData.sessionId = responseBody.data as? String
                        finish()
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Snackbar.make(binding.root, R.string.login_id, Snackbar.LENGTH_LONG).show()
                }

            })
        }
    }

}