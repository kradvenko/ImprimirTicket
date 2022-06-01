package com.example.imprimirticket.data

import android.content.res.Resources
import com.example.imprimirticket.R

/* Returns initial list of flowers. */
fun articulosTicketList(resources: Resources): List<ArticuloTicket> {
    return listOf(
        ArticuloTicket(
            cantidad = "1",
            articulo = "NUEZ",
            total = "100"
        ),
        ArticuloTicket(
            cantidad = "1",
            articulo = "NUEZ",
            total = "100"
        ),
        ArticuloTicket(
            cantidad = "1",
            articulo = "NUEZ",
            total = "100"
        )
    )
}