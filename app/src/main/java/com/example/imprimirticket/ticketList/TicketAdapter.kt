package com.example.imprimirticket.ticketList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imprimirticket.R
import com.example.imprimirticket.data.Ticket

class TicketAdapter(private val onClick: (Ticket) -> Unit) :
    ListAdapter<Ticket, TicketAdapter.TicketHolder>(TicketDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class TicketHolder(itemView: View, val onClick: (Ticket) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val ventaNumeroTextView: TextView = itemView.findViewById(R.id.tvVentaNumero)

        private var currentTicket: Ticket? = null

        init {
            itemView.setOnClickListener {
                currentTicket?.let {
                    onClick(it)
                }
            }
        }

        fun bind(ticket: Ticket) {
            currentTicket = ticket

            ventaNumeroTextView.text = ticket.id
            /*
            if (flower.image != null) {
                flowerImageView.setImageResource(flower.image)
            } else {
                flowerImageView.setImageResource(R.drawable.rose)
            }
             */
        }
    }

    /* Creates and inflates view and return FlowerViewHolder. */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ticket_item, parent, false)
        return TicketHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: TicketHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}

object TicketDiffCallback : DiffUtil.ItemCallback<Ticket>() {
    override fun areItemsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Ticket, newItem: Ticket): Boolean {
        return oldItem.id == newItem.id
    }
}