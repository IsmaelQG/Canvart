package com.example.canvart.data.database

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.canvart.R
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
        DC_CC::class,
        Tip::class
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
    abstract val componentHeadDao: ComponentHeadDao
    abstract val componentCharacterDao: ComponentCharacterDao
    abstract val imageURLDao : ImageURLDAO
    abstract val tipDao : TipDao

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


                                db.execSQL("INSERT INTO image_challenges VALUES (16, 1)")
                                db.execSQL("INSERT INTO image_challenges VALUES (17, 6)")

                                //Tips

                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, description_last, resid_image_last, visibility, unlock_level) VALUES (" +
                                        "'lorem ipsum'," +
                                        "'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam rhoncus, quam quis ullamcorper viverra, augue augue dignissim nisl, ac elementum quam dolor et lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.\nVivamus eu ipsum quis tortor semper facilisis sed quis nunc.\nAenean ullamcorper tincidunt scelerisque. Phasellus mi nibh, elementum in ultrices in, suscipit placerat diam.\nNulla hendrerit non lectus ut facilisis. Vivamus aliquam, nunc et pretium varius, quam diam hendrerit eros, et condimentum leo nibh et massa. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Aenean et feugiat lorem, eget sagittis massa. Sed eget venenatis turpis.' , " +
                                        R.drawable.ic_launcher_background +
                                        ", 'Lorem ipsum',"
                                        + R.drawable.ic_launcher_background +
                                        ", 0," +
                                        "2)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, description_last, resid_image_last, visibility, unlock_level) VALUES (" +
                                        "'lorem ipsum'," +
                                        "'lorem ipsum' , " +
                                        R.drawable.ic_launcher_background +
                                        ", 'Lorem ipsum',"
                                        + R.drawable.ic_launcher_background +
                                        ", 0," +
                                        "2)")

                                //Url images

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lZ8onQ1wuY8/1000x1000', 0)")//Tulipan
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/ATgfRqpFfFI/1000x1000', 0)")//Cereza
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/hR0jlOh-mHc/1000x1000', 0)")//Eye
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/PFzy4N0_R3M/1000x1000', 0)")//Doughnut
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/GJY1eAw6tn8/1000x1000', 0)")//Key
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ("+ R.drawable.ic_launcher_background +", 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 0)")

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/L-2p8fapOA8/1000x1000', 1)")//Turtle
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/BlMj6RYy3c0/1000x1000', 1)")//Flower
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/QyCH5jwrD_A/1000x1000', 1)")//Hand
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/eMzblc6JmXM/1000x1000', 1)")//Cat
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/UbJMy92p8wk/1000x1000', 1)")//Eye
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/3402kvtHhOo/1000x1000', 1)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/drw6RtOKDiA/1000x1000', 1)")//CarDoll
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/gjo0yv_2sNU/1000x1000', 1)")//Mug
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/NhrcL_C0sFA/1000x1000', 1)")//Ring
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/gcWd0ts4RCo/1000x1000', 1)")//Rose
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 1)")

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/GQRQecNoNAE/1000x1000', 2)")//Pajaro
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/uDpPaR14ENg/1000x1000', 2)")//E
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/qGAQ5b8dt88/1000x1000', 2)")//E
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/j4HF-tMscQQ/1000x1000', 2)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/AHBvAIVqk64/1000x1000', 2)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/5RBXc7R-YWs/1000x1000', 2)")//Tigre
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/DEYu5meHHCM/1000x1000', 2)")//Paisaje nevado
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/ZxNKxnR32Ng/1000x1000', 2)")//Leon
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lylCw4zcA7I/1000x1000', 2)")//Birb
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 2)")

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/6crKuOOSpzM/1000x1000', 3)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/90v3asMhhL0/1000x1000', 3)")//Jumping Girl
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/6crKuOOSpzM/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/t2ZIt-WNXrk/1000x1000', 4)")//Apple
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/fuBj4vkp4-g/1000x1000', 4)")//Eye
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/hMMc7mvb34A/1000x1000', 4)")//Sunflower

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

                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}