package com.victorrosas.vendormanager.view.add

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import androidx.navigation.Navigation
import com.victorrosas.vendormanager.R
import com.victorrosas.vendormanager.data.model.VendorModel
import kotlinx.android.synthetic.main.fragment_add_vendors.*

class addVendorFragment: Fragment(){

    private lateinit var viewModel: addVendorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(addVendorViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_vendors,container,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_add_vendor,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_add ->{
                saveVendorInfo()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun saveVendorInfo(){
        val vendor = VendorModel(
            textInputName.editText?.text.toString(),
            textInputMetAt.editText?.text.toString(),
            textInputContact.editText?.text.toString(),
            textInputEmail.editText?.text.toString(),
            textInputTwitter.editText?.text.toString()
        )

        viewModel.addVendors(vendor)

        Navigation.findNavController(view!!).navigateUp()
    }

}