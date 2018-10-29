package com.victorrosas.vendormanager.view.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.navigation.findNavController
import com.victorrosas.vendormanager.R
import com.victorrosas.vendormanager.data.model.VendorModel
import kotlinx.android.synthetic.main.fragment_vendors_list.*

class VendorListFragment: android.support.v4.app.Fragment(),VendorListAdapter.OnItemClickListener,
    android.support.v7.widget.SearchView.OnQueryTextListener,android.support.v7.widget.SearchView.OnCloseListener{

    private lateinit var searchView: android.support.v7.widget.SearchView
    private lateinit var viewModel: VendorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(VendorListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vendors_list,container,false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_vendor_list,menu)

        searchView = menu?.findItem(R.id.menu_search)?.actionView as android.support.v7.widget.SearchView
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVendorList().observe(this,Observer<List<VendorModel>> { vendors ->
            vendors?.let {
                populateVendorList(vendors)
            }
        })

        addFab.setOnClickListener{
            view.findNavController().navigate(R.id.action_vendorsListFragment_to_addVendorFragment) //Navgraph
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        viewModel.searchVendors(query!!)
        return true
    }

    override fun onClose(): Boolean {
        viewModel.getAllVendors()
        searchView.onActionViewCollapsed()
        return true
    }

    fun populateVendorList(vendorList: List<VendorModel>){
        vendorRecyclerView.adapter = VendorListAdapter(vendorList,this)
    }

    override fun onItemClick(vendor: VendorModel, itemView: View) {
        val vendorBundle = Bundle().apply {
            putInt(getString(R.string.vendor_id),vendor.id)
        }
        view?.findNavController()?.navigate(R.id.action_vendorsListFragment_to_vendorDetailsFragment,vendorBundle)
    }

}