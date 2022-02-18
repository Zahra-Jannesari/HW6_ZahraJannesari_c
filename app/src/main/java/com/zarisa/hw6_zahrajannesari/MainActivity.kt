package com.zarisa.hw6_zahrajannesari

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import com.zarisa.hw6_zahrajannesari.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() ,RadioGroup.OnCheckedChangeListener {
    lateinit var binding: ActivityMainBinding
    var sharedpreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        sharedpreferences = getSharedPreferences("Information", Context.MODE_PRIVATE)
        binding.RadioGroupGender.setOnCheckedChangeListener(this)
        setListeners()
    }

    private fun setListeners() {
        binding.buttonRegister.setOnClickListener { registerButtonClick() }
        binding.buttonShowInfo.setOnClickListener { showInfoButtonClick() }
        binding.buttonHideInfo.setOnClickListener { hideInfoButtonClick() }
    }

    override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
        val editor = sharedpreferences!!.edit()
        when(p1){
            R.id.radioButton_Female -> {
                editor.putString("Gender", "Female")
                editor.apply()
                return
            }
            R.id.radioButton_Male ->{
                editor.putString("Gender","Male")
                editor.apply()
                return
            }
        }
    }
    private fun registerButtonClick(){
        if (binding.textFieldPassword.text.toString()==binding.textFieldRetypePassword.text.toString()) {
        Toast.makeText(this,"Registered!", Toast.LENGTH_LONG).show()

            val editor = sharedpreferences!!.edit()
            editor.putString("fullName", binding.textFieldFullName.text.toString())
            editor.apply()
            editor.putString("Username", binding.textFieldUsername.text.toString())
            editor.apply()
            editor.putString("Email", binding.textFieldEmail.text.toString())
            editor.apply()
            editor.putString("Password", binding.textFieldPassword.text.toString())
            editor.apply()
        }
        else{
            binding.textFieldRetypePassword.error="Passwords do not match"
        }
    }
    private fun showInfoButtonClick() {
        binding.textViewFullName.text= sharedpreferences?.getString("fullName", "")
        binding.textViewUserName.text=sharedpreferences?.getString("Username", "")
        binding.textViewEmail.text=sharedpreferences?.getString("Email", "")
        binding.textViewPassword.text=sharedpreferences?.getString("Password", "")
        binding.textViewGenderInfo.text=sharedpreferences?.getString("Gender", "")
        binding.LinearLayoutInfo.visibility= View.VISIBLE
    }
    private fun hideInfoButtonClick(){
        binding.LinearLayoutInfo.visibility= View.GONE
    }
}