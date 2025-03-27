package com.example.assignnotes.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.assignnotes.DAO.NoteDao
import com.example.assignnotes.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?= null

        fun getDatabase(context: Context):NoteDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}