package com.example.imprimirticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imprimirticket.data.Ticket
import com.example.imprimirticket.ticketList.TicketAdapter
import com.example.imprimirticket.ticketList.TicketsListViewModel
import com.example.imprimirticket.ticketList.TicketsListViewModelFactory

class TicketsActivity : AppCompatActivity() {
    private val newTicketActivityRequestCode = 1
    private val ticketsListViewModel by viewModels<TicketsListViewModel> {
        TicketsListViewModelFactory(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        val ticketAdapter = TicketAdapter { ticket -> adapterOnClick(ticket) }

        val concatAdapter = ConcatAdapter(ticketAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)

        recyclerView.adapter = concatAdapter

        ticketsListViewModel.ticketsLiveData.observe(this) {
            it?.let {
                ticketAdapter.submitList(it as MutableList<Ticket>)
            }
        }

    }

    private fun adapterOnClick(ticket: Ticket) {
        /*
        val intent = Intent(this, FlowerDetailActivity()::class.java)
        intent.putExtra(FLOWER_ID, flower.id)
        startActivity(intent)
        */
    }
}