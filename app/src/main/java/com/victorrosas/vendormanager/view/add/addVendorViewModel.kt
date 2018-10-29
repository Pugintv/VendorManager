package com.victorrosas.vendormanager.view.add

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.victorrosas.vendormanager.VendorApp
import com.victorrosas.vendormanager.data.model.VendorModel

class addVendorViewModel(application: Application) : AndroidViewModel(application){

    private val vendorRepository = getApplication<VendorApp>().getVendorRepo()

    fun addVendors(vendors: VendorModel){
        vendorRepository.insertVendor(vendors)
    }

}