package com.example.canvart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.canvart.data.Converters
import com.example.canvart.data.dao.*
import com.example.canvart.data.entity.*
import com.example.canvart.data.enums.Difficulty
import com.example.canvart.data.enums.Material

@Database(
    entities = [
        Drawing::class,
        Challenge::class,
        ImageChallenge::class,
        PortraitChallenge::class,
        DescriptionChallenge::class,
        ComponentHead::class,
        ComponentCharacter::class,
        ImageURL::class,
        PC_CH::class,
        DC_CC::class
    ],
    version = 1,
        exportSchema = true
)
@TypeConverters(
    value = [
        Converters::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract val challengeDao: ChallengeDao
    abstract val drawingDao: DrawingDao
    abstract val componentHeadDao: ComponentHeadDao
    abstract val componentCharacterDao: ComponentCharacterDao
    abstract val imageURLDao : ImageURLDAO

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
                        ).addTypeConverter(Converters()).addCallback(object: Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                db.execSQL("INSERT INTO `image_url` (`id`, `url`, `difficulty`) VALUES\n" +
                                        "(1, 'https://source.unsplash.com/lDokXi6_YLA/1000x1000', '0'),\n" +
                                        "(2, 'https://source.unsplash.com/ATgfRqpFfFI/1000x1000', '0'),\n" +
                                        "(3, 'https://source.unsplash.com/L-2p8fapOA8/1000x1000', '0'),\n" +
                                        "(4, 'https://source.unsplash.com/GQRQecNoNAE/1000x1000', '0'),\n" +
                                        "(5, 'https://source.unsplash.com/lZ8onQ1wuY8/1000x1000', '0'),\n" +
                                        "(6, 'https://source.unsplash.com/BlMj6RYy3c0/1000x1000', '0'),\n" +
                                        "(7, 'https://source.unsplash.com/ypVS3PmwPR8/1000x1000', '0'),\n" +
                                        "(8, 'https://source.unsplash.com/QsBTYwxjzUU/1000x1000', '0');")

                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 1)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 2)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 3)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 4)")

                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ' con cara alargada', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ' con cabeza redonda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y el pelo largo y liso.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el cabello rizado corto.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son almendrados', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Tiene los ojos grandes y redondos', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', la nariz puntiaguda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es redonda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ' y los labios gruesos.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ' y su boca es diminuta.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' Su cuello es de grosor estándar.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' Tiene un cuello fino.', 0)")

                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Hombre sobre los 40', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Hombre sobre los 40', 0)")

                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}