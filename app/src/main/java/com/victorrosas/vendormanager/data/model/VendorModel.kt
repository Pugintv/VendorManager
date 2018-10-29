package com.victorrosas.vendormanager.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class VendorModel(
    var name: String = "",
    var vendor: String = "",
    var phoneNumber: String = "",
    var email: String = "",
    var company: String = "",
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    )