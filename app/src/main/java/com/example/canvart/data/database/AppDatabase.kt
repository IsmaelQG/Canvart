package com.example.canvart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.canvart.data.dao.ChallengeDao
import com.example.canvart.data.dao.ComponentCharacterDao
import com.example.canvart.data.dao.ComponentHeadDao
import com.example.canvart.data.dao.DrawingDao
import com.example.canvart.data.entity.*

@Database(
    entities = [
        Drawing::class,
        Challenge::class,
        ImageChallenge::class,
        PortraitChallenge::class,
        DescriptionChallenge::class,
        ComponentHead::class,
        ComponentCharacter::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val challengeDao: ChallengeDao
    abstract val drawingDao: DrawingDao
    abstract val componentHeadDao: ComponentHeadDao
    abstract val componentCharacterDao: ComponentCharacterDao

    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if(INSTANCE == null){
                synchronized(this){
                    if(INSTANCE == null){
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "app_database"
                        ).addCallback(object: Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                db.compileStatement("INSERT INTO challenges VALUES (0, 1, 1, true, 'lorem ipsum')")
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}