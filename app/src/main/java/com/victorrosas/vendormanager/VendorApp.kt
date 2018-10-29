package com.victorrosas.vendormanager

import android.app.Application
import com.victorrosas.vendormanager.data.VendorRepository

class VendorApp: Application() {

    //Centralized functions

    fun getVendorRepo() = VendorRepository(this)

}