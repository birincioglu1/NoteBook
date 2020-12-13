package com.example.notebook.fragments

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.notebook.R
import com.example.notebook.databinding.FragmentNoteDetailBinding
import com.example.notebook.model.NoteItem
import com.example.notebook.viewmodel.NoteDetailViewModel
import kotlinx.android.synthetic.main.fragment_note_detail.*
import kotlinx.coroutines.CoroutineScope
import java.text.SimpleDateFormat
import java.util.*


class NoteDetailFragment : Fragment() {
    val RESIM_SEC=120
    var photoURI: Uri?=null
    var photoPath: String?=null
    private  lateinit var viewModel: NoteDetailViewModel
    private var noteUUID=0
    private lateinit var dataBinding:FragmentNoteDetailBinding
    private lateinit var updatedNote:NoteItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         dataBinding=DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_note_detail,container,false)

        dataBinding.btnBackToList.setOnClickListener {
            val sdf = SimpleDateFormat("dd/M/yyyy")
            val currentDate = sdf.format(Date())
            updatedNote.title=dataBinding.etNoteDetailTitle.editText!!.text.toString()
            updatedNote.description=dataBinding.etNoteDetailDescription.editText!!.text.toString()
            updatedNote.photo=dataBinding.noteItemText?.photo
            updatedNote.updated=true
            updatedNote.updateDate=currentDate
            viewModel.updateNote(updatedNote)

            requireActivity().onBackPressed()

        }
        dataBinding.btnDetailDelete.setOnClickListener {
            viewModel.deleteNote(noteUUID)
            requireActivity().onBackPressed()
        }

        dataBinding.detailImage.setOnClickListener {
            var intent= Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_PICK)
            startActivityForResult(intent,RESIM_SEC)
            viewModel.updateNote(updatedNote)
        }

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
             noteUUID=NoteDetailFragmentArgs.fromBundle(it).noteId
            NoteDetailFragmentArgs.fromBundle(it)
        }



        viewModel= ViewModelProviders.of(this).get(NoteDetailViewModel::class.java)
        viewModel.getNoteById(noteUUID)


       observeleLiveData()


    }

    private fun observeleLiveData() {

        viewModel.note.observe(viewLifecycleOwner, Observer {note->
            note?.let {
                dataBinding.noteItemText=note
                updatedNote=note

            }

        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RESIM_SEC&& resultCode== AppCompatActivity.RESULT_OK && data!!.data!=null)
        {
            photoURI=data!!.data
            photoPath=photoURI.toString()
            dataBinding.detailImage.setImageURI(photoURI)
            updatedNote.photo=photoPath
        }
    }



}