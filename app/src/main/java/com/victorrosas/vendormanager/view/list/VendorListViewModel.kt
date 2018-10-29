package com.victorrosas.vendormanager.view.list

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import com.victorrosas.vendormanager.VendorApp
import com.victorrosas.vendormanager.data.model.VendorModel

class VendorListViewModel(application: Application) : AndroidViewModel(application){

    private val vendorRepository = getApplication<VendorApp>().getVendorRepo()
    private val vendorList = MediatorLiveData<List<VendorModel>>()

    init{
        getAllVendors()
    }

    fun getVendorList(): LiveData<List<VendorModel>> {
        return vendorList
    }

    fun getAllVendors(){
        vendorList.addSource(vendorRepository.getAllVendors()){ vendors ->
            vendorList.postValue(vendors)
        }
    }

    fun searchVendors(name: String){
        vendorList.addSource(vendorRepository.findVendor(name)){ vendors ->
            vendorList.postValue(vendors)
        }
    }


}