package com.example.notebook.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notebook.R
import com.example.notebook.adapter.NoteAdapter
import com.example.notebook.databinding.FragmentNoteListBinding
import com.example.notebook.viewmodel.NoteViewModel


class NoteListFragment : Fragment() {


    private lateinit var viewModel:NoteViewModel
    private  var noteAdapter=NoteAdapter(arrayListOf())
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataBinding:FragmentNoteListBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = FragmentNoteListBinding.inflate(inflater, container, false)

        dataBinding.btnAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_noteAddFragment)
        }
        dataBinding.etSearch.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var aranan=s.toString()
                if (aranan.length>1)
                {
                    viewModel.searchNode("%$aranan%")
                    dataBinding.tvNoteStatus.visibility=View.INVISIBLE
                }else{
                    dataBinding.tvSearchStatus.visibility=View.INVISIBLE
                    viewModel.getDataFromSQLite()
                }
            }

        })
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.rv_list_note)
       viewModel= ViewModelProviders.of(this).get(NoteViewModel::class.java)
       viewModel.getDataFromSQLite()
        recyclerView.setHasFixedSize(true);
        recyclerView.layoutManager=LinearLayoutManager(context)
        recyclerView.adapter=noteAdapter





        observerLiveData()
    }

  private  fun observerLiveData()
    {
        viewModel.notes.observe(viewLifecycleOwner, Observer {notes->
                notes?.let {
                    recyclerView.visibility=View.VISIBLE
                    noteAdapter.updateNotes(notes)
                    if (notes.isNullOrEmpty())
                    {
                        dataBinding.tvNoteStatus.visibility=View.VISIBLE
                    }else{
                        dataBinding.tvNoteStatus.visibility=View.INVISIBLE
                        dataBinding.tvNoteStatus.visibility=View.INVISIBLE
                    }



                }

        })
        viewModel.searchNotes.observe(viewLifecycleOwner, Observer {  notes->
            notes?.let {
                recyclerView.visibility=View.VISIBLE
                noteAdapter.updateNotes(notes)
                if (notes.isNullOrEmpty())
                {
                    dataBinding.tvSearchStatus.visibility=View.VISIBLE
                }else{
                    dataBinding.tvSearchStatus.visibility=View.INVISIBLE
                }
            }
        })




    }


}