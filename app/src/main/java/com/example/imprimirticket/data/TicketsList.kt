package com.example.imprimirticket.data

import android.content.res.Resources

fun ticketsList(resources: Resources): List<Ticket> {
    return listOf(
        Ticket(
            id = "1",
            fecha = "12/12/2022",
            atiende = "BERNA",
            total = "100"
        ),
        Ticket(
            id = "2",
            fecha = "11/11/2022",
            atiende = "BERNA",
            total = "50"
        ),
        Ticket(
            id = "3",
            fecha = "10/10/2022",
            atiende = "BERNA",
            total = "1020"
        )
    )
}