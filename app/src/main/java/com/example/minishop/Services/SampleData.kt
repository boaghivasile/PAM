package com.example.minishop.Services

import com.example.minishop.Model.Destination
import java.util.*

object SampleData {

    val DESTINATIONS = ArrayList<Destination>()

    private var COUNT = 5

    private var dummy_description = "everything is blue"

    init {
        // Add some sample items
        val newDestination1 = Destination()
        newDestination1.id = 1
        newDestination1.city = "Chisinau"
        newDestination1.description = dummy_description
        newDestination1.country = "Moldova"
        DESTINATIONS.add(newDestination1)

        val newDestination2 = Destination()
        newDestination2.id = 2
        newDestination2.city = "London"
        newDestination2.description = dummy_description
        newDestination2.country = "UK"
        DESTINATIONS.add(newDestination2)

        val newDestination3 = Destination()
        newDestination3.id = 3
        newDestination3.city = "New York"
        newDestination3.description = dummy_description
        newDestination3.country = "USA"
        DESTINATIONS.add(newDestination3)

        val newDestination4 = Destination()
        newDestination4.id = 4
        newDestination4.city = "Mardird"
        newDestination4.description = dummy_description
        newDestination4.country = "Spain"
        DESTINATIONS.add(newDestination4)

        val newDestination5 = Destination()
        newDestination5.id = 5
        newDestination5.city = "Sydney"
        newDestination5.description = dummy_description
        newDestination5.country = "Australia"
        DESTINATIONS.add(newDestination5)

    }

    fun addDestination(item: Destination) {
        item.id = COUNT
        DESTINATIONS.add(item)
        COUNT += 1
    }

    fun getDestinationById(id: Int): Destination? {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id) {
                return DESTINATIONS[i]
            }
        }

        return null
    }

    fun deleteDestination(id: Int) {
        var destinationToRemove: Destination? = null

        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == id) {
                destinationToRemove = DESTINATIONS[i]
            }
        }

        if (destinationToRemove != null) {
            DESTINATIONS.remove(destinationToRemove)
        }
    }

    fun updateDestination(destination: Destination) {
        for (i in DESTINATIONS.indices) {
            if (DESTINATIONS[i].id == destination.id) {
                val destinationToUpdate = DESTINATIONS[i]

                destinationToUpdate.city = destination.city
                destinationToUpdate.description = destination.description
                destinationToUpdate.country = destination.country
            }
        }
    }
}
