package com.example.proton.utils


import android.os.Build
import androidx.annotation.RequiresApi
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

object DefaultFormat {

    fun formatRupiah(amount: Long): String {
        val format = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        return format.format(amount).replace("Rp", "Rp ")
    }

    fun getFormattedDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormat(date: String): String {
        val inputDate = "2023-12-01T00:00:00.000Z"

        // Parsing tanggal menjadi objek Instant
        val instant = Instant.parse(date)

        // Mengubah Instant menjadi LocalDateTime menggunakan zona waktu UTC
        val dateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

        // Format tanggal dalam format yang diinginkan (01-12-2023)
        val formattedDate = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

        println(formattedDate) // Output: 01-12-2023
        return formattedDate
    }

}