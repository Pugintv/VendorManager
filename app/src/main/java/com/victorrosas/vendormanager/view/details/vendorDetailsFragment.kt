package com.victorrosas.vendormanager.view.details

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.victorrosas.vendormanager.R
import com.victorrosas.vendormanager.data.model.VendorModel
import kotlinx.android.synthetic.main.fragment_vendor_details.*

class vendorDetailsFragment: Fragment(){

    private lateinit var viewModel: vendorDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(vendorDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vendor_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Find vendor by id
        val vendorId = arguments?.getInt(getString(R.string.vendor_id))
        vendorId?.let {
            viewModel.getVendorDetails(vendorId).observe(this, Observer { vendorDetails ->
                populateVendorDetails(vendorDetails)
            })
        }

    }

    private fun populateVendorDetails(vendors: VendorModel?){
        textViewName.text = vendors?.name
        textViewMet.text = vendors?.company
        buttonContact.text = vendors?.phoneNumber
        textViewEmail.text = vendors?.email
    }


}