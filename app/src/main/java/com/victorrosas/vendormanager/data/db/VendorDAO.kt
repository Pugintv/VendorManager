package com.victorrosas.vendormanager.data.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.victorrosas.vendormanager.data.model.VendorModel

@Dao
interface VendorDAO{

    //Get all
    @Query("SELECT * FROM VendorModel ORDER BY id DESC")
    fun getAll(): LiveData<List<VendorModel>>

    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vendor: VendorModel)

    //Delete
    @Query("DELETE FROM VendorModel")
    fun deleteAll()

    //Select by id
    @Query("SELECT * FROM VendorModel WHERE id = :id")
    fun find(id: Int): LiveData<VendorModel>

    //Find by name
    @Query("SELECT * FROM VendorModel WHERE name LIKE '%' || :name || '%' ")
    fun findBy(name:String): LiveData<List<VendorModel>>


}
