package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import fr.enssat.kikeou.couturier_morizur.R
import fr.enssat.kikeou.couturier_morizur.database.entity.Location

class LocationAdapter(var mainContactViewModel: MainContactViewModel, override var context: Context?): ReadOnlyLocationAdapter(context) {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var item = data[position]

        holder.deleteLocationButton.visibility = View.VISIBLE

        holder.deleteLocationButton.setOnClickListener {
            mainContactViewModel.deleteLocation(item)
            Toast.makeText(context, context?.getString(R.string.toast_delete_location), Toast.LENGTH_SHORT).show()
        }
    }
}
