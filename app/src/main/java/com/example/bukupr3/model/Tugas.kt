package com.example.bukupr3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tugas (
    var idTugas : String,
    var idBukuPr : String,
    var namaTugas : String,
    var deskripsi : String,
    var subject : String,
    var startDate : String,
    var dueDate : String,
    var status : String? = null
        ): Parcelable