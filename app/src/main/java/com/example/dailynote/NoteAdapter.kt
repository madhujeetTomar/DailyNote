package com.example.dailynote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynote.model.Note
import java.lang.reflect.Constructor

class NoteAdapter internal constructor(val context: Context):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var notes = emptyList<Note>() // Cached copy of words


    inner class NoteViewHolder(view: View) :RecyclerView.ViewHolder(view) {
val title=view.findViewById<TextView>(R.id.txt_title)
        val timestamp=view.findViewById<TextView>(R.id.timestamp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = inflater.inflate(R.layout.item_note,parent,false)
    return NoteViewHolder(itemView)

    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note=notes[position]

        holder.title.text=note.title
        holder.timestamp.text=note.timeStamp

    }

    internal fun setNote(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }
}