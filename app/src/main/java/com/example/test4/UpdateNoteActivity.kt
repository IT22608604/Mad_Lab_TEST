package com.example.test4

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.test4.databinding.ActivityUpdateBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: NoteDataBaseHelper
    private var noteId: Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1) {
            finish()
            return
        }
        val note = db.getNoteByID(noteId)
        binding.UpdatetitleEditText.setText(note.title)
        binding.UpdatecontentEditText.setText(note.content)

        binding.UpdatesaveButton.setOnClickListener {
            val newTitle = binding.UpdatetitleEditText.text.toString()
            val newContent = binding.UpdatecontentEditText.text.toString()
            val updatedNote = Note(noteId,newTitle, newContent)
            db.updateNote(updatedNote)
            finish()
            Toast.makeText(this,"Changes Saved", Toast.LENGTH_SHORT).show()
        }

        }
    }
