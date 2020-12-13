package com.example.notebook.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.notebook.R
import com.example.notebook.databinding.FragmentNoteAddBinding
import com.example.notebook.model.NoteItem
import com.example.notebook.viewmodel.NoteAddViewModel
import java.text.SimpleDateFormat
import java.util.*


class NoteAddFragment : Fragment() {
    val RESIM_SEC=100
    var photoURI: Uri?=null
    var photoPath: String?=""
    private lateinit var mNoteViewAddViewModel: NoteAddViewModel
    private lateinit var dataBinding: FragmentNoteAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding= DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.fragment_note_add,container,false)
        mNoteViewAddViewModel=  ViewModelProviders.of(this).get(NoteAddViewModel::class.java)

        dataBinding.btnSave.setOnClickListener {
           insertDataToDatabase()
        }
        dataBinding.btnBackToList.setOnClickListener {
            requireActivity().onBackPressed()
        }
        dataBinding.imageView2.setOnClickListener {
            var intent= Intent()
            intent.setType("image/*")
            intent.setAction(Intent.ACTION_PICK)
            startActivityForResult(intent,RESIM_SEC)
        }


        return dataBinding.root
    }

    private fun insertDataToDatabase() {
        val title=dataBinding.etNoteTitle.editText!!.text.toString()
        val description=dataBinding.etNoteDescription.editText!!.text.toString()

       if( inputCheck(title,description))
       {
           val sdf = SimpleDateFormat("dd/M/yyyy")
           val currentDate = sdf.format(Date())

           var note=NoteItem(title,description,photoPath!!,currentDate,currentDate,false)
           mNoteViewAddViewModel.addNoteSQLite(note)
           Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
           findNavController().navigate(R.id.action_noteAddFragment_to_noteListFragment)
       }else{
               Toast.makeText(requireContext(),"Please fill out all fields.",Toast.LENGTH_SHORT).show()
       }
    }

    private fun inputCheck(title: String, description: String):Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(description))
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RESIM_SEC&& resultCode==AppCompatActivity.RESULT_OK && data!!.data!=null)
        {
            photoURI=data!!.data
            photoPath=photoURI.toString()
            dataBinding.imageView2.setImageURI(photoURI)
        }
    }


}