package com.example.assignnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignnotes.adapter.NoteAdapter
import com.example.assignnotes.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)

        val adapter = NoteAdapter { note ->
            val bundle = Bundle().apply {
                putInt("note_id", note.id)
                putString("note_title", note.title)
                putString("note_content", note.content)
            }
            findNavController().navigate(R.id.action_noteListFragment_to_addEditNoteFragment, bundle)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]
        viewModel.allNotes.observe(viewLifecycleOwner) { notes ->
            adapter.submitList(notes)
        }

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_noteListFragment_to_addEditNoteFragment)
        }

        return view
    }
}