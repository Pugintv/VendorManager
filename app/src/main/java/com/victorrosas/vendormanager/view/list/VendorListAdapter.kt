package com.victorrosas.vendormanager.view.list

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.victorrosas.vendormanager.R
import com.victorrosas.vendormanager.data.model.VendorModel
import kotlinx.android.synthetic.main.layout_list_item.view.*

class VendorListAdapter(

    private val items: List<VendorModel>,
    private val clickListener: OnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    interface OnItemClickListener{

        fun onItemClick(vendor:VendorModel, itemView: View)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(items[position],clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(vendor: VendorModel,listener: OnItemClickListener) = with(itemView){
            textViewName.text = vendor.name
            textViewCompany.text = vendor.company
            buttonContact.text = vendor.phoneNumber
            buttonContact.setOnClickListener{
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:${vendor.phoneNumber}")
                itemView.context.startActivity(dialIntent)
            }

            setOnClickListener{
                listener.onItemClick(vendor,it)
            }

        }

    }

}