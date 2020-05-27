package com.example.minishop.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.minishop.Adapters.DestinationAdapter
import com.example.minishop.Model.Destination
import com.example.minishop.R
import com.example.minishop.Services.DestinationService
import com.example.minishop.Services.SampleData
import com.example.minishop.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    private fun loadDestinations() {
        val destinationService : DestinationService = ServiceBuilder.buildService(DestinationService::class.java)

        val requestCall : Call<List<Destination>> = destinationService.getDestinationList()

        requestCall.enqueue(object: Callback<List<Destination>> {

            override fun onResponse(call: Call<List<Destination>>,response: Response<List<Destination>>) {
                if (response.isSuccessful) {
                    val destinationList: List<Destination> = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                } else if (response.code() == 401) {
                    Toast.makeText(
                        this@DestinationListActivity,
                        "Your session has expired. Please log in again",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@DestinationListActivity, "Failed to retrive items", Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@DestinationListActivity, "Error occured" + t.toString(),
                    Toast.LENGTH_LONG).show()
            }
        })
//        destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)
    }

}
