package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var binding: ActivityLoginBinding
    var countries = arrayOf("Select Country","Nepal", "China", "Bhutan", "Pakistan", "Canada", "Korea")
    var cities = arrayOf("Kathmandu", "Kritipur", "Lalitpur", "Kanchanpur", "Bhaktapur")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize AutoCompleteTextView for Cities
        val cityAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            cities
        )
        binding.city.setAdapter(cityAdapter)
        binding.city.threshold = 1

        // Initialize Spinner for Countries
        val countryAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            countries
        )
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.country.adapter = countryAdapter
        binding.country.onItemSelectedListener = this

        // Login button click listener
        binding.login.setOnClickListener {
            val username = binding.username.text.toString()
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val country = binding.country.selectedItem?.toString() ?: ""
            val city = binding.city.text.toString()
            val conditions = binding.conditions.isChecked

            // Validate inputs

            val selectGender = binding.radioGroup.checkedRadioButtonId
            val gender = when(selectGender){
                R.id.male -> "Male"
                R.id.female -> "Female"
                R.id.other -> "Other"
                else -> null
            }

                if (username.isEmpty()){
                    binding.username.error = "Username cannot be empty"
                }else if(email.isEmpty()) {
                    binding.email.error = "Email cannot be empty"
                }else if (password.isEmpty()) {
                    binding.password.error = "Password cannot be empty"
                }else if (gender == null){
                    Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
                }else if(country == "Select Country"){
                    Toast.makeText(this,"Select a country",Toast.LENGTH_SHORT).show()
                }
                else if (country.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Please select a country.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else if(city.isEmpty()) {
                    binding.city.error = null
                    Toast.makeText(this,
                        "Please select a city.",
                        Toast.LENGTH_SHORT)
                        .show()
                }else if(!conditions) {
                    Toast.makeText(
                        this,
                        "Please agree to the terms & conditions.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    val intent = Intent(
                        this@LoginActivity,
                        DashboardActivity::class.java
                    )
                    intent.putExtra("username",username)
                    intent.putExtra("email",email)
                    intent.putExtra("gender",gender)
                    intent.putExtra("country",country)
                    intent.putExtra("city",city)
                    startActivity(intent)
                    finish()
                }
            }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v: View, insets: WindowInsetsCompat ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }
}

override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    // Handle country selection if needed
}

override fun onNothingSelected(parent: AdapterView<*>?) {
    // Handle case when no item is selected in the spinner
}
}