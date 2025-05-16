package com.example.rickandmortyapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.rickandmortyapi.data.dao.CharacterDao
import com.example.rickandmortyapi.data.model.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1, exportSchema = false)
abstract class DatabaseRickAndMorty : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var Instance: DatabaseRickAndMorty? = null

        fun getDatabase(context: Context) : DatabaseRickAndMorty {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DatabaseRickAndMorty::class.java, "rick_morty_database")
                    .fallbackToDestructiveMigration(false)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}