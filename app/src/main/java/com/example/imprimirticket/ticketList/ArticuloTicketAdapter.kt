package com.example.imprimirticket.ticketList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.imprimirticket.R
import com.example.imprimirticket.data.ArticuloTicket

class ArticuloTicketAdapter(private val onClick: (ArticuloTicket) -> Unit) :
    ListAdapter<ArticuloTicket, ArticuloTicketAdapter.ArticuloTicketHolder>(ArticuloTicketDiffCallback) {

    /* ViewHolder for Flower, takes in the inflated view and the onClick behavior. */
    class ArticuloTicketHolder(itemView: View, val onClick: (ArticuloTicket) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val cantidadTextView: TextView = itemView.findViewById(R.id.tvCantidad)

        private var currentArticulo: ArticuloTicket? = null
/*
        private var currentArticulo: Flower? = null

        init {
            itemView.setOnClickListener {
                currentFlower?.let {
                    onClick(it)
                }
            }
        }
*/
        /* Bind flower name and image. */
        fun bind(articuloTicket: ArticuloTicket) {
            currentArticulo = articuloTicket

            cantidadTextView.text = articuloTicket.cantidad
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticuloTicketHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.articulo_ticket_item, parent, false)
        return ArticuloTicketHolder(view, onClick)
    }

    /* Gets current flower and uses it to bind view. */
    override fun onBindViewHolder(holder: ArticuloTicketHolder, position: Int) {
        val flower = getItem(position)
        holder.bind(flower)

    }
}

object ArticuloTicketDiffCallback : DiffUtil.ItemCallback<ArticuloTicket>() {
    override fun areItemsTheSame(oldItem: ArticuloTicket, newItem: ArticuloTicket): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ArticuloTicket, newItem: ArticuloTicket): Boolean {
        return oldItem.articulo == newItem.articulo
    }
}