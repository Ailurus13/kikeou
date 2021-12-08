package fr.enssat.kikeou.couturier_morizur.main.screens.maincontact

import android.content.Context
import android.view.View
import android.widget.Toast
import fr.enssat.kikeou.couturier_morizur.R

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
