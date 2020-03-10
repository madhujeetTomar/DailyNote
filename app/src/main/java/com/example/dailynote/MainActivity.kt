package com.example.dailynote

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailynote.model.Note
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    lateinit var note: Note
    lateinit var noteViewModel: NoteViewModel

    lateinit var recyclerView: RecyclerView
    lateinit var addNewNotes: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()



        val adapter = NoteAdapter(this)

        recyclerView.adapter = adapter

        recyclerView.layoutManager= LinearLayoutManager(this)

        noteViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(MyApplication.instance).create(NoteViewModel::class.java)

        noteViewModel.allNotes.observe(this, Observer {list_notes-> list_notes?.let { adapter.setNote(it) }  })




        //add the value in the database


        addNewNotes.setOnClickListener{
            openNewDialog()
        }



    }

    private  fun openNewDialog() {

        val dialog = Dialog(this)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)
        dialog .setContentView(R.layout.layout_add_newe_note)
        val title = dialog .findViewById(R.id.et_new_title) as EditText
        val content = dialog .findViewById(R.id.et_new_content) as EditText
        val timeStamp = dialog .findViewById(R.id.txt_timestamp) as TextView

        val yesBtn = dialog .findViewById(R.id.submit) as Button
        val noBtn = dialog .findViewById(R.id.cancel) as TextView
        yesBtn.setOnClickListener {

            timeStamp.text = System.currentTimeMillis().toString()
            note =Note(System.currentTimeMillis(),title = title.text.toString(),
                content = content.text.toString(), timeStamp = timeStamp.text.toString())

            noteViewModel.run { insert(note = note) }
            dialog .dismiss()
        }
        noBtn.setOnClickListener { dialog .dismiss() }
        dialog .show()
    }



    private fun initView() {
        recyclerView=findViewById(R.id.list_notes) as RecyclerView
        addNewNotes=findViewById(R.id.floatingActionButton) as FloatingActionButton
    }
}
