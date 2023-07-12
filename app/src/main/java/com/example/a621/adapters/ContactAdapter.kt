package com.example.a621.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a621.databinding.ItemContactBinding
import com.example.a621.models.Contact

class ContactAdapter(var contactlist:ArrayList<Contact>, var onClik: OnClik): RecyclerView.Adapter<ContactAdapter.rvadapterholder>() {
    inner class rvadapterholder(var itemview : ItemContactBinding):RecyclerView.ViewHolder(itemview.root){

        fun onbain(contact:Contact,position: Int){
            itemview.name.text = contact.name
            itemview.number.text = contact.number
            itemview.btnContact.setOnClickListener {
                onClik.click(contact,position)
            }
//            itemview.image_item.setImageResource(contact.image!!)
//            Picasso.with(context).load(contact.image).into(itemview.image_item)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): rvadapterholder {
        return rvadapterholder(ItemContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: rvadapterholder, position: Int) {
        var contact =contactlist[position]
        holder.onbain(contact,position)

    }

    override fun getItemCount(): Int {
        return contactlist.size

    }
    interface OnClik{
        fun click(contact: Contact,position: Int){

        }
    }

}
