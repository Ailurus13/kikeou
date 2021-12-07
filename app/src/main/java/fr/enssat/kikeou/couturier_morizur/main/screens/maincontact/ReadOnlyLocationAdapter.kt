package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

open class ReadOnlyLocationAdapter(open var context: Context?): RecyclerView.Adapter<ReadOnlyLocationAdapter.ViewHolder>() {
    var data =  listOf<Location>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.location_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]

        holder.locationValue.text = item.value
        holder.locationDay.text = item.day.toString()

        // Read only so it means button delete is not there
        holder.deleteLocationButton.visibility = View.INVISIBLE
    }

    override fun getItemCount(): Int {
        return data.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val locationValue: TextView = itemView.findViewById(R.id.locationValue)
        val locationDay: TextView = itemView.findViewById(R.id.locationDay)
        val deleteLocationButton: Button = itemView.findViewById(R.id.deleteLocationButton)
    }
}
