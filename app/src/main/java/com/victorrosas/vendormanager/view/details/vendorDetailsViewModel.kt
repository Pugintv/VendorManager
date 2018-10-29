package com.victorrosas.vendormanager.view.details

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import com.victorrosas.vendormanager.VendorApp
import com.victorrosas.vendormanager.data.model.VendorModel

class vendorDetailsViewModel(application: Application) : AndroidViewModel(application){

    private val vendorRepository = getApplication<VendorApp>().getVendorRepo()
    private val vendorId = MutableLiveData<Int>()

    fun getVendorDetails(id: Int): LiveData<VendorModel>{
        vendorId.value = id
        val vendorDetails = Transformations.switchMap<Int,VendorModel>(vendorId){id ->
            vendorRepository.findVendor(id)
        }

        return vendorDetails
    }

}