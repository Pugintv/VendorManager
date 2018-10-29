package com.victorrosas.vendormanager.data

import com.victorrosas.vendormanager.data.model.VendorModel

class VendorProvider{

    companion object {
        var VendorList = initVendorList()

        private fun initVendorList(): MutableList<VendorModel>{
            var vendors = mutableListOf<VendorModel>()
            vendors.add(
                VendorModel(
                    "Sunil Kumar", "AllianceIT",
                    "1234567890", "s.kumar@allianceIT.com", "AllianceIT",
                    1
                )
            )
            vendors.add(
                VendorModel(
                    "Raj", "Concursoft",
                    "2234567890", "raj@concursoft.com", "AllianceIT",
                    2
                )
            )

            return vendors
        }


    }

}