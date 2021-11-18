package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO
import fr.enssat.kikeou.couturier_morizur.main.Util

class ListContactAdapter: RecyclerView.Adapter<ListContactAdapter.ViewHolder>() {
    var data = listOf<ContactDAO.ContactListInfo>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        Log.v("enodebug", "Nombre item: $itemCount")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val firstname: TextView = itemView.findViewById(R.id.contact_firstname)

        fun bind(item: ContactDAO.ContactListInfo){
            firstname.text = item.firstname
        }

        companion object{
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_contact, parent, false)
                return ViewHolder(view)
            }
        }
    }
}