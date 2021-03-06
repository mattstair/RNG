package com.example.matt.rng4

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.matt.rng4.R.layout.favorite_row
import kotlinx.android.synthetic.main.favorite_row.view.*


class FavoriteAdapter (var context: Context): RecyclerView.Adapter<CustomViewHolder>() {

    val db = DatabaseHandler(context)
    val data = db.readData()


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from((parent?.context))
        val cellForRow = layoutInflater.inflate(favorite_row,parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
        val name = data[position].name
        holder?.view?.textViewName?.text = name
        holder?.view?.btnShare?.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setType("text/plain")
            val shareSub = "Hilarious Name"
            intent.putExtra(Intent.EXTRA_SUBJECT,shareSub)
            intent.putExtra(Intent.EXTRA_TEXT,name)
            context.startActivity(Intent.createChooser(intent,"Share Using..."))
        })
        holder?.view?.btnDelete?.setOnClickListener(View.OnClickListener {
            db.deleteData(data.get(position).id)
            Toast.makeText(context,"Deleted!",Toast.LENGTH_SHORT).show()
            val intent = Intent(context,FavoritesActivity::class.java)
            context.startActivity(intent)
        })
    }


}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}