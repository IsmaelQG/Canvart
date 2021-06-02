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

                                //Url images

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES\n" +
                                        "('https://source.unsplash.com/lDokXi6_YLA/1000x1000', '0'),\n" +
                                        "('https://source.unsplash.com/ATgfRqpFfFI/1000x1000', '0'),\n" +
                                        "('https://source.unsplash.com/L-2p8fapOA8/1000x1000', '0'),\n" +
                                        "('https://source.unsplash.com/GQRQecNoNAE/1000x1000', '1'),\n" +
                                        "('https://source.unsplash.com/lZ8onQ1wuY8/1000x1000', '1'),\n" +
                                        "('https://source.unsplash.com/BlMj6RYy3c0/1000x1000', '2'),\n" +
                                        "('https://source.unsplash.com/ypVS3PmwPR8/1000x1000', '3'),\n" +
                                        "('https://source.unsplash.com/QsBTYwxjzUU/1000x1000', '3');")

                                //Components head

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

                                //Components character

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

                                //Adventure challenges

                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 1)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 2)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 3)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 4)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 5)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 6)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 7)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 8)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 9)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 10)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 11)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 12)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 13)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 14)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 15)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 16)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 4', 1, 17)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 5', 1, 18)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 6', 1, 19)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (3, NULL, 0, 0, 0, 'Lorem ipsum 7', 1, 20)")

                                db.execSQL("INSERT INTO image_challenges VALUES (1, 1)")
                                db.execSQL("INSERT INTO image_challenges VALUES (2, 2)")
                                db.execSQL("INSERT INTO image_challenges VALUES (3, 3)")
                                db.execSQL("INSERT INTO image_challenges VALUES (4, 4)")
                                db.execSQL("INSERT INTO image_challenges VALUES (5, 5)")
                                db.execSQL("INSERT INTO image_challenges VALUES (6, 6)")
                                db.execSQL("INSERT INTO image_challenges VALUES (7, 7)")
                                db.execSQL("INSERT INTO image_challenges VALUES (8, 8)")

                                //Tutorial challenges

                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 4', 2, 1)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 5', 2, 2)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 6', 2, 3)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 7', 2, 4)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 4', 2, 5)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 5', 2, 6)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 6', 2, 7)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 7', 2, 8)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 4', 2, 9)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, attempts, state, title, type, 'index') VALUES (4, NULL, 0, 0, 0, 'Lorem ipsum 5', 2, 10)")

                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}