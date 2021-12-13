package com.example.bukupr3.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BukuPr(
    var idBukuPr : String,
    var uid : String,
    var namaBukuPr : String
) : Parcelable
