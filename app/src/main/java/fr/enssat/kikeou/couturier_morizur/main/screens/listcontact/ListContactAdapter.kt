package fr.enssat.kikeou.couturier_morizur.main.screens.listcontact

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.dao.ContactDAO

class ListContactAdapter(
    var cellClickListener: CellClickListener
) : RecyclerView.Adapter<ListContactAdapter.ViewHolder>() {
    var data = listOf<ContactDAO.ContactListInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)

        Log.e("aloha", item.locationValue)

        holder.itemView.setOnClickListener{
            this.cellClickListener.onCellClickListener(item)
        }
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
                val view = layoutInflater.inflate(R.layout.contact_list_item, parent, false)
                return ViewHolder(view)
            }
        }
    }

    interface CellClickListener {
        fun onCellClickListener(data: ContactDAO.ContactListInfo)
    }
}
