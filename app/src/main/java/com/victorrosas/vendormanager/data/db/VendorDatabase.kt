package com.victorrosas.vendormanager.data.db

import android.app.Application
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.os.AsyncTask
import com.victorrosas.vendormanager.data.VendorProvider
import com.victorrosas.vendormanager.data.model.VendorModel

@Database (entities = [VendorModel::class],version = 1)
abstract class VendorDatabase : RoomDatabase(){

    abstract fun vendorDAO(): VendorDAO

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Vendor.db"
        private var INSTANCE: VendorDatabase? = null


        fun getInstance(application: Application): VendorDatabase{
            synchronized(VendorDatabase.lock){
                if(VendorDatabase.INSTANCE == null){
                    VendorDatabase.INSTANCE = Room.databaseBuilder(application,VendorDatabase::class.java,VendorDatabase.DB_NAME)
                        .allowMainThreadQueries()
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                VendorDatabase.INSTANCE?.let{
                                    VendorDatabase.populate(it, VendorProvider.VendorList)
                                }
                            }
                        })
                        .build()
                }
                return INSTANCE!!
            }
        }

        fun populate(database: VendorDatabase,vendorList: List<VendorModel>){
            for(vendor in vendorList){
                AsyncTask.execute{
                    database.vendorDAO().insert(vendor)
                }
            }
        }

    }
}