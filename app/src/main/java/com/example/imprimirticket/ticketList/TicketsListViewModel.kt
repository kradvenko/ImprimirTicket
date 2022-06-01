package com.example.imprimirticket.ticketList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imprimirticket.data.DataSource
import com.example.imprimirticket.data.Ticket
import kotlin.random.Random

class TicketsListViewModel(val dataSource: DataSource) : ViewModel() {

    val ticketsLiveData = dataSource.getTicketsList()

    /* If the name and description are present, create new Flower and add it to the datasource */
    /*
    fun insertFlower(flowerName: String?, flowerDescription: String?) {
        if (flowerName == null || flowerDescription == null) {
            return
        }

        val image = dataSource.getRandomFlowerImageAsset()
        val newFlower = Flower(
            Random.nextLong(),
            flowerName,
            image,
            flowerDescription
        )

        dataSource.addFlower(newFlower)
    }

     */
}

class TicketsListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TicketsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TicketsListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}