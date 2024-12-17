package com.example.assignment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.adapter.ImageAdapter
import com.example.assignment.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding
    lateinit var recyclerView: RecyclerView
    lateinit var imageAdapter: ImageAdapter
    var imagelist = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Safely getting the data passed via Intent
        val username: String = intent.getStringExtra("username").toString()
        val email: String = intent.getStringExtra("email").toString()
        val gender: String = intent.getStringExtra("gender").toString()
        val country: String = intent.getStringExtra("country").toString()
        val city: String = intent.getStringExtra("city").toString()

        // Display the received data
        binding.usernameDisplay.text = "Username: ${username}"
        binding.emailDisplay.text = "Email: ${email}"
        binding.genderDisplay.text = "Gender: ${gender}"
        binding.countryDisplay.text = "Country: ${country}"
        binding.cityDisplay.text = "City: ${city}"

        // Initialize image list
        imagelist.add(R.drawable.flowerboy)
        imagelist.add(R.drawable.random)
        imagelist.add(R.drawable.hands)
        imagelist.add(R.drawable.starrynight)
        imagelist.add(R.drawable.mylogo)
        imagelist.add(R.drawable.crane)
        imagelist.add(R.drawable.house)
        imagelist.add(R.drawable.light)
        imagelist.add(R.drawable.mountain)
        imagelist.add(R.drawable.temple)
        imagelist.add(R.drawable.woman)
        imagelist.add(R.drawable.mountaincloud)

       recyclerView = binding.recyclerView // Make sure this points to your RecyclerView
        imageAdapter = ImageAdapter(this@DashboardActivity, imagelist)
        recyclerView.adapter = imageAdapter

        recyclerView.layoutManager = GridLayoutManager(this@DashboardActivity,3)


        // Handle window insets (for edge-to-edge UI)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
