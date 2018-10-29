package com.victorrosas.vendormanager.data

import android.app.Application
import android.arch.lifecycle.LiveData
import com.victorrosas.vendormanager.data.db.VendorDAO
import com.victorrosas.vendormanager.data.db.VendorDatabase
import com.victorrosas.vendormanager.data.model.VendorModel

class VendorRepository(application: Application){

    private val vendorDao: VendorDAO

    init{
        val vendorDatabase = VendorDatabase.getInstance(application)
        vendorDao = vendorDatabase.vendorDAO()
    }

    fun getAllVendors(): LiveData<List<VendorModel>>{
        return vendorDao.getAll()
    }

    fun insertVendor(vendor: VendorModel){
        vendorDao.insert(vendor)
    }

    fun findVendor(id: Int): LiveData<VendorModel>{
      return vendorDao.find(id)
    }

    fun findVendor(name: String): LiveData<List<VendorModel>>{
        return vendorDao.findBy(name)
    }


}