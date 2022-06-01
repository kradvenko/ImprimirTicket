package com.example.imprimirticket.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataSource(resources: Resources) {
    private val initialArticulosTicketList = articulosTicketList(resources)
    private val initialTicketsList = ticketsList(resources)
    private val articulosTicketLiveData = MutableLiveData(initialArticulosTicketList)
    private val ticketsLiveData = MutableLiveData(initialTicketsList)

    /* Adds flower to liveData and posts value.
    fun addFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList == null) {
            flowersLiveData.postValue(listOf(flower))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, flower)
            flowersLiveData.postValue(updatedList)
        }
    }
*/
    /* Removes flower from liveData and posts value.
    fun removeFlower(flower: Flower) {
        val currentList = flowersLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(flower)
            flowersLiveData.postValue(updatedList)
        }
    }
*/
    /* Returns flower given an ID.
    fun getFlowerForId(id: Long): Flower? {
        flowersLiveData.value?.let { flowers ->
            return flowers.firstOrNull{ it.id == id}
        }
        return null
    }
*/
    fun getTicketsList(): LiveData<List<Ticket>> {
        return ticketsLiveData
    }

    /* Returns a random flower asset for flowers that are added.
    fun getRandomFlowerImageAsset(): Int? {
        val randomNumber = (initialFlowerList.indices).random()
        return initialFlowerList[randomNumber].image
    }
*/
    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}