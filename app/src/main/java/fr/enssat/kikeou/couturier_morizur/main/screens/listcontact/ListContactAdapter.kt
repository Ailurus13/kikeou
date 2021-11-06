package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO

class ListContactAdapter : RecyclerView.Adapter<ListContactAdapter.ViewHolder>() {
    var data = listOf<ContactDAO.ContactListInfo>()

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
        val firstname: TextView = itemView.findViewById(R.id.contact_firstname)
        val lastname: TextView = itemView.findViewById(R.id.contact_lastname)

        fun bind(item: ContactDAO.ContactListInfo){
            val res = itemView.context.resources
            firstname.text = item.firstname
            lastname.text = item.lastname
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