package com.example.assignnotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.assignnotes.model.Note
import com.example.assignnotes.viewmodel.NoteViewModel

class AddEditNoteFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_edit_note, container, false)
        val titleEditText = view.findViewById<EditText>(R.id.titleEditText)
        val contentEditText = view.findViewById<EditText>(R.id.contentEditText)
        val saveButton = view.findViewById<Button>(R.id.saveButton)

        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

        arguments?.let { bundle ->
            val id = bundle.getInt("note_id", 0)
            val title = bundle.getString("note_title")
            val content = bundle.getString("note_content")
            if (id != 0 && title != null && content != null) {
                note = Note(id, title, content)
                titleEditText.setText(title)
                contentEditText.setText(content)
            }
        }

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                val newNote = if (note != null) {
                    Note(note!!.id, title, content)
                } else {
                    Note(title = title, content = content)
                }
                if (note == null) {
                    viewModel.insert(newNote)
                } else {
                    viewModel.update(newNote)
                }
                findNavController().popBackStack()
            }
        }

        return view
    }
}