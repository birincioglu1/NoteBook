package com.example.notebook.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R

import com.example.notebook.databinding.ItemNoteTextBinding
import com.example.notebook.fragments.NoteListFragmentDirections
import com.example.notebook.model.NoteItem
import kotlinx.android.synthetic.main.item_note_text.view.*

class NoteAdapter(
    var noteList: ArrayList<NoteItem>
):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(),NoteClickListener {

    var lastPosition= -1
    var context:Context?=null
    class NoteViewHolder(var view:ItemNoteTextBinding):RecyclerView.ViewHolder(view.root) {

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var inflater=LayoutInflater.from(parent.context)
                context=parent.context
            val view=DataBindingUtil.inflate<ItemNoteTextBinding>(inflater,R.layout.item_note_text,parent,false)
            return NoteViewHolder(view)


    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        if (holder.adapterPosition>lastPosition)
        {
            var animation:Animation=AnimationUtils.loadAnimation(context,R.anim.slide_in_row)
            holder.itemView.startAnimation(animation)

            lastPosition=holder.adapterPosition
        }
        holder.view.noteItemText=noteList[position]
        holder.view.listener=this


    }

    override fun onNoteClicked(v: View) {
        val noteId=v.noteId.text.toString().toInt()
        var  action=NoteListFragmentDirections.actionNoteListFragmentToNoteDetailFragment(noteId)
         Navigation.findNavController(v).navigate(action)


    }


    fun updateNotes(notes: List<NoteItem>)
    {
        noteList.clear()
        noteList.addAll(notes)
        notifyDataSetChanged()
    }
}