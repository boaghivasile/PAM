package com.example.minishop.Controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.minishop.Model.Destination
import com.example.minishop.R
import com.example.minishop.Services.DestinationService
import com.example.minishop.Services.SampleData
import com.example.minishop.Services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destination_create.et_city
import kotlinx.android.synthetic.main.activity_destination_create.et_country
import kotlinx.android.synthetic.main.activity_destination_create.et_description
import kotlinx.android.synthetic.main.activity_destination_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination_detail)

        setSupportActionBar(detail_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {

            val id = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadDetails(id)

            initUpdateButton(id)

            initDeleteButton(id)
        }
    }

    private fun loadDetails(id: Int) {

        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall : Call<Destination> = destinationService.getDestination(id)

        requestCall.enqueue(object: Callback<Destination> {

            override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                if(response.isSuccessful) {
                    val destination : Destination? = response.body()
                    destination?.let {
                        et_city.setText(destination.city)
                        et_description.setText(destination.description)
                        et_country.setText(destination.country)

                        collapsing_toolbar.title = destination.city
                    }
                } else {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to retrive data", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Destination>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun initUpdateButton(id: Int) {

        btn_update.setOnClickListener {

            val city = et_city.text.toString()
            val description = et_description.text.toString()
            val country = et_country.text.toString()

            val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall : Call<Destination> = destinationService.updateDestination(id, city, description, country)

            requestCall.enqueue(object: Callback<Destination> {
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if(response.isSuccessful) {
                        finish()
                        Toast.makeText(this@DestinationDetailActivity,"Item updated successfully", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to update item",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to update item",Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    private fun initDeleteButton(id: Int) {

        btn_delete.setOnClickListener {

            val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall : Call<Unit> = destinationService.deleteDestination(id)

            requestCall.enqueue(object: Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    if(response.isSuccessful) {
                        finish()
                        Toast.makeText(this@DestinationDetailActivity, "Successfully deleted", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to delete", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to delete", Toast.LENGTH_LONG).show()
                }
            })

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}

