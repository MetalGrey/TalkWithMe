package com.example.talkwithme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        register_button_reg.setOnClickListener() {

            val email = email_edittext_reg.text.toString()
            val password = password_edittext_reg.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "please enter text in email or password", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            Log.d("MainActivity", "Password is: $password")
            Log.d("MainActivity", "Email is: $email")

            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(){
                    if (!it.isSuccessful) return@addOnCompleteListener

                    Log.d("Activity", "Use: ${it.result}")
                    //else
                }
                    .addOnFailureListener() {
                        Log.d("MainActivity", "Failed create user ${it.message}")
                        Toast.makeText(this, "Failed to create user: ${it.message}", Toast.LENGTH_LONG).show()
                    }


        }

        already_have_account_reg.setOnClickListener(){
            Log.d("MainActivity", "Already have Account")

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}