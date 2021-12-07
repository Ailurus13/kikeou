package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO

class ListContactAdapter(var context : Context, var cellClickListener: CellClickListener) : RecyclerView.Adapter<ListContactAdapter.ViewHolder>() {
    var data = listOf<ContactDAO.ContactListInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        holder.itemView.setOnClickListener{
            this.cellClickListener.onCellClickListener(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(context, parent)
    }

    class ViewHolder private constructor(var context: Context, itemView: View): RecyclerView.ViewHolder(itemView){
        val firstname: TextView = itemView.findViewById(R.id.contact_firstname)
        val lastname: TextView = itemView.findViewById(R.id.contact_lastname)
        val contactLocation: Button = itemView.findViewById(R.id.contact_location)

        fun bind(item: ContactDAO.ContactListInfo){
            firstname.text = item.firstname
            lastname.text = item.lastname
            if(item.locationValue != null){
                contactLocation.text = item.locationValue
            }else {
                contactLocation.text = context.getString(R.string.no_location)
            }
        }  

        companion object{
            fun from(context: Context, parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_contact, parent, false)
                return ViewHolder(context, view)
            }
        }
    }

    interface CellClickListener {
        fun onCellClickListener(data: ContactDAO.ContactListInfo)
    }
}
