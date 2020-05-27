package com.example.minishop.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.minishop.Model.Destination
import com.example.minishop.R
import com.example.minishop.Services.DestinationService
import com.example.minishop.Services.SampleData
import com.example.minishop.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_create.*
import kotlinx.android.synthetic.main.activity_destination_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_create)

        setSupportActionBar(toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall : Call<Destination> = destinationService.addDestination(newDestination)

            requestCall.enqueue(object: Callback<Destination> {

                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if(response.isSuccessful) {
                        finish()
                        Toast.makeText(this@DestinationCreateActivity, "Successfully added", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@DestinationCreateActivity, "Failed to add item", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(this@DestinationCreateActivity, "Failed to add item", Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}

