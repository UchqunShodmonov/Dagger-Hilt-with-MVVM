package uz.uchqun.hilt_tutorial2.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BlogCacheEntity::class], version = 1)
abstract class BlogDatabase : RoomDatabase() {
    abstract fun blogDao(): BlogDao

    companion object {
        val DATABASE_NAME: String = "blog.db"
    }
}