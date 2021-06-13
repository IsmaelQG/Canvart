package com.example.canvart.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.canvart.R
import com.example.canvart.data.Converters
import com.example.canvart.data.dao.*
import com.example.canvart.data.entity.*

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

    abstract val challengeDrawingDao: ChallengeDrawingDao
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

                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 4', 1, 'Lorem ipsum', 1)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 5', 1, 'Lorem ipsum', 2)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 6', 1, 'Lorem ipsum', 3)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 7', 1, 'Lorem ipsum', 4)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 4', 1, 'Lorem ipsum', 5)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 5', 1, 'Lorem ipsum', 6)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 6', 1, 'Lorem ipsum', 7)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 7', 1, 'Lorem ipsum', 8)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 4', 1, 'Lorem ipsum', 9)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 5', 1, 'Lorem ipsum', 10)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 6', 1, 'Lorem ipsum', 11)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 7', 1, 'Lorem ipsum', 12)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 4', 1, 'Lorem ipsum', 13)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 5', 1, 'Lorem ipsum', 14)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (3, 3, 0, 'Lorem ipsum 6', 1, 'Lorem ipsum', 15)")

                                //1
                                db.execSQL("INSERT INTO image_challenges VALUES (1, 1)")
                                //2
                                db.execSQL("INSERT INTO image_challenges VALUES (2, 2)")
                                //3
                                db.execSQL("INSERT INTO image_challenges VALUES (3, 3)")
                                //4
                                db.execSQL("INSERT INTO image_challenges VALUES (4, 4)")
                                //5
                                db.execSQL("INSERT INTO image_challenges VALUES (5, 5)")
                                //6
                                db.execSQL("INSERT INTO portrait_challenges VALUES (6)")

                                db.execSQL("INSERT INTO portrait_component_head VALUES (1, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (2, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (3, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (4, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (5, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (6, 6)")
                                db.execSQL("INSERT INTO portrait_component_head VALUES (7, 6)")

                                //7
                                db.execSQL("INSERT INTO image_challenges VALUES (7, 7)")
                                db.execSQL("INSERT INTO image_challenges VALUES (8, 8)")
                                db.execSQL("INSERT INTO image_challenges VALUES (9, 1)")
                                db.execSQL("INSERT INTO image_challenges VALUES (10, 2)")
                                db.execSQL("INSERT INTO image_challenges VALUES (11, 3)")
                                db.execSQL("INSERT INTO image_challenges VALUES (12, 4)")
                                db.execSQL("INSERT INTO image_challenges VALUES (13, 5)")
                                db.execSQL("INSERT INTO image_challenges VALUES (14, 6)")
                                db.execSQL("INSERT INTO image_challenges VALUES (15, 7)")

                                //Tutorial challenges

                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (4, 3, 3, 'Bienvenido a Canvart', 2, '¡Te damos la bienvenida a nuestra aplicación!\nEn este tutorial te inicaremos en nuestra mecánica de retos, como por ejemplo en el que te encuentras ahora mismo.\nEn cada reto tienes que dibujar la referencia que te ofrecemos en un tiempo limitado, ¡empecemos con algo sencillo!\n\nPulsa en el botón de abajo a tu derecha para comenzar.', 1)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (4, 3, 3, 'Formas circulares', 2, 'Los círculos son las formas más básicas en lo que a composición nos referimos y se pueden encontrar en todos lados.\nEn el siguiente reto ten en cuenta lo anterior y dibuja teniendo en mente el centro de la figura y las lineas que convergen de él.\n\n¡Recuerda que siempre puedes repetir los retos accediendo de nuevo a ellos!', 2)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (4, 3, 3, 'Más formas', 2, 'Acuérdate de que dibujar fruta puede ser un buen ejercicio para situar formas simples en tu mente e interpretarlas en tus ilustraciones.\n\n¡No olvides que el tutorial siempre será accesible independientemente de tus avances!\nAdemás no olvides que cuando termines un dibujo puedes esribir tu opinión sobre él, ¡la autocrítica es importante para avanzar!', 3)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (4, 3, 3, '¡Ojo con éste!', 2, 'Las personas que se introducen en el mundo de la ilustración suelen empezar por dibujar ojos, ¿sabes por qué?\nPara las personas ojos expresan, muestran mucho sentimiento y es normal querer plasmarlos en tu lienzo. Curióso, ¿no?\n\nFíjate que un ojo es un óvalo, compuesto de un círculo interior que dependiendo del espacio que le rodee y la forma de los párpados muestren una expresión u otra.', 4)")
                                db.execSQL("INSERT INTO challenges (difficulty, material, timer, title, type, description, 'index') VALUES (4, 3, 5, 'Un retrato', 2, 'Al principio puese resultar un poco abrumador dibujar un rostro pero debes saber que es más facil de lo que piensas.\nTómate tu tiempo en componerlo y tomar referencia de la imagen.\n\nY no te preocupes por el tiempo, ¡en este reto haremos la excepción ofrecíendote tiempo infinito!\n\n¡Gracias por terminar el tutorial, esperamos que hayas comprendido que no hay que ser un gran experto para ser un gran atrista!', 5)")


                                db.execSQL("INSERT INTO image_challenges VALUES (16, 7)")
                                db.execSQL("INSERT INTO image_challenges VALUES (17, 8)")
                                db.execSQL("INSERT INTO image_challenges VALUES (18, 9)")
                                db.execSQL("INSERT INTO image_challenges VALUES (19, 10)")
                                db.execSQL("INSERT INTO image_challenges VALUES (20, 11)")

                                //Tips

                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Aprende a gestionar tu tiempo'," +
                                        "'Puede que te haya pasado que cuando dibujas te cuesta reunir las fuerzas para mantenerte horas dibujando.\nNo te preocupes, ¡eso es muy normal y le pasa a los mejores! Un dibujo bien realizado y esquematizado suele llevar horas pero tambien hay que aprender a realizar descansos, hacer ejercicios de muñeca y aprender a parar de imprimir detalles donde no hacen falta.\nUn ejercicio muy bueno que lleva poco tiempo es hacer sketches, realizar dibujos muy rápidos.\nA eso se le llama Ejercicio de Gesto y los profesionales lo usan para tener bases desde donde trabajar, además de los artistas conceptuales.\n¡Deberías probarlo!' , " +
                                        R.drawable.apple_gesture +
                                        ", 0," +
                                        "2)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Pose de personaje'," +
                                        "'Dibujar un personaje no es muy distinto a jugar un Juego de Rol. Piensa que cuando estas creando a tu personaje sobre el papel debes plasmar bien en tu mente cómo es él:\n\nSu personalidad se refleja mucho en su pose. Si se encuentra encogido en sí mismo mirando hacia uno de sus lados quiere decir que probablemente sea inseguro e intovertido mientras que una pose recta y firme nos muestra todo lo contrario' , " +
                                        R.drawable.pose +
                                        ", 0," +
                                        "4)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Perspectiva'," +
                                        "'A la hora de dibujar fondos o personajes en diversos necesarios debes tener en cuenta la perspectiva.\nTe interesaría saber acerca de lo que es el punto de fuga, es decir, el punto encontrado en la linea de horizonte donde convergen todas las lineas en perspectiva.\n\nAhora el resto saldrá solo: Una vez que dispongas de la linea de horizonte y el punto de fuga solo debes trazar lineas que convergan en ambos elementos, punto y linea, y ya trazar demás lineas, como por ejemplo, perpendiculares si queremos dibujar edificios.' , " +
                                        R.drawable.perspective +
                                        ", 0," +
                                        "6)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Dibujar ojos'," +
                                        "'Dibujar ojos es muy sencillo. La forma de éstos es un sencillo óvalo en cuyo uno de sus extremos horizontales encontramos el lagrimal, donde sobresale un poco de la figura original.\nRecuerda que el ojo es una esfera situada en una cuenca y los párpados cubren casi toda su superficie solo dejando mostrar el iris y la pupila\n\nHablando de pupilas, debes saber que ésta no se sitúa en el borde del ojo sino dentro de la cornea, habendo espacio entre ellos como si estuviese introducida un poco más adentro.' , " +
                                        R.drawable.eye +
                                        ", 0," +
                                        "8)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Estructura de las manos'," +
                                        "'Algunas veces escucharás de compañeros dibujantes que las manos son la parte más difícil de dibujar del cuerpo.\nEs un dicho muy común en esta comunidad y por ello no debes temer, ¡te explico cómo dibujarlas paso a paso!\n\nLa base principal de una mano está en su palma, que se compone de un sencillo pentágono y un pequeño triángulo del que sobresale el pulgar, cuya altura sería aproximadamente la mitad de la palma\nLas falanges realmente se componen de cilindros articulados y te daré un truco que pocas personas usan: pillar referencia de tus propias manos. ¡Parecerá una tontería pero realmente es muy útil!' , " +
                                        R.drawable.hand_sketch_final +
                                        ", 0," +
                                        "11)")
                                db.execSQL("INSERT INTO tips (title, description_first, resid_image_first, visibility, unlock_level) VALUES (" +
                                        "'Proporciones faciales'," +
                                        "'El rostro realmente es una figura muy compleja si nos fijamos con detalle. Sin embargo podemos hacer uso de proporciones básicas que nos ayudarán a ilustrarlos:\n\nLas orejas poseen la misma altura que la nariz\nLas comisuras de los labios normalmente están situadas horizontalmente al mismo nivel que el medio de los ojos\nEnte ojo y ojo suele haber de distancia el espacio equivalente a otro ojo, musma anchura que comparte la naríz.\nLa cara suele componerse de un círculo y un triángulo que compone la barbilla.' , " +
                                        R.drawable.face_proportions +
                                        ", 0," +
                                        "13)")

                                //Url images

                                    //Adventure

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/6crKuOOSpzM/1000x1000', 3)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/90v3asMhhL0/1000x1000', 3)")//Jumping Girl
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/6crKuOOSpzM/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lDokXi6_YLA/1000x1000', 3)")

                                    //Tutorial

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/t2ZIt-WNXrk/1000x1000', 4)")//Apple
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/hMMc7mvb34A/1000x1000', 4)")//Sunflower
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/U1iYwZ8Dx7k/1000x1000', 4)")//Orange
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/fuBj4vkp4-g/1000x1000', 4)")//Eye
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/rDEOVtE7vOs/1000x1000', 4)")//Girl

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lZ8onQ1wuY8/1000x1000', 0)")//Tulipan
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/ATgfRqpFfFI/1000x1000', 0)")//Cereza
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/hR0jlOh-mHc/1000x1000', 0)")//Eye
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/PFzy4N0_R3M/1000x1000', 0)")//Doughnut
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/GJY1eAw6tn8/1000x1000', 0)")//Key

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

                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/GQRQecNoNAE/1000x1000', 2)")//Pajaro
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/uDpPaR14ENg/1000x1000', 2)")//E
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/qGAQ5b8dt88/1000x1000', 2)")//E
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/j4HF-tMscQQ/1000x1000', 2)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/AHBvAIVqk64/1000x1000', 2)")//R
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/5RBXc7R-YWs/1000x1000', 2)")//Tigre
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/DEYu5meHHCM/1000x1000', 2)")//Paisaje nevado
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/ZxNKxnR32Ng/1000x1000', 2)")//Leon
                                db.execSQL("INSERT INTO `image_url` (`url`, `difficulty`) VALUES ('https://source.unsplash.com/lylCw4zcA7I/1000x1000', 2)")//Birb

                                //Components head

                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre de expresión dura ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, 'y de cabeza cuadrada, ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, 'portando un peinado mohawk. ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, 'Porta un par de gafas de sol, ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, 'su nariz es ancha, ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, 'sus labios finos ', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, 'y su cuello es muy ancho, adornado con tatuajes. Tiene barba de varios días', 0)")


                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño que porta una sonrisa', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña sonriente', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven con una expresión feliz', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre joven de expresión alegre', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cara alargada', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cabeza redonda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y el pelo largo y liso.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el cabello rizado corto.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el pelo liso, algo corto.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y su pelo es rizado y largo.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son almendrados', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Tiene los ojos grandes y redondos', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Porta un par de ojos rasgados', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son pequeños', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', la nariz la tiene puntiaguda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es redonda', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', tiene una nariz fina y pequeña', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es chata', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', sus labios gruesos', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es diminuta', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es amplia, con labios finos.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es ancha', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es de grosor estándar.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y tiene un cuello fino.', 0)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es muy corto.', 0)")

                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño que porta una sonrisa', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña sonriente', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven con una expresión feliz', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre joven de expresión alegre', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cara alargada', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cabeza redonda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y el pelo largo y liso.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el cabello rizado corto.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el pelo liso, algo corto.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y su pelo es rizado y largo.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son almendrados', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Tiene los ojos grandes y redondos', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Porta un par de ojos rasgados', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son pequeños', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', la nariz la tiene puntiaguda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es redonda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', tiene una nariz fina y pequeña', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es chata', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', sus labios gruesos', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es diminuta', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es amplia, con labios finos.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es ancha', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es de grosor estándar.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y tiene un cuello fino.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es muy corto.', 1)")
                                //
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño con su cara algo seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño con una expresión de duda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño con una expresión algo triste', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña con una expresión seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña con una expresión de duda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña con una expresión de tristeza', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven con una expresión seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven de expresión triste', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer adulta con una expresión seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer adulta con una expresión sonriente', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre joven de expresión seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre joven que porta una expresión triste', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre adulto con una cara algo seria', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre adulto sonriente', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cara cuadrada', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con una cara ovalada', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', de cabeza grande y muy redonda', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con mucha frente', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y el pelo muy corto, rapado.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el pelo rapado a los lados y recogido en un moño.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con su pelo afro muy rizado.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el pelo muy largo y liso, con flequillo.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son almendrados', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Tiene los ojos grandes y redondos', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Porta un par de ojos rasgados', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son pequeños', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', la nariz la tiene grande y ancha', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es muy pequeña', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', tiene una nariz torcida', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es recta', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', tiene una boca muy diminuta con labios gruesos.', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es muy gruesa', 1)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es grande y de labios carnosos.', 1)")

                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niño que porta una sonrisa', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Niña sonriente', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Mujer joven con una expresión feliz', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (0, 'Hombre joven de expresión alegre', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cara alargada', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con cabeza redonda', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', de facciones joviales', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (1, ', con la cabeza cuadrada', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y el pelo largo y liso.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el cabello rizado corto.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y con el pelo liso, algo corto.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (2, ' y su pelo es rizado y largo.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son almendrados', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Tiene los ojos grandes y redondos', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Porta un par de ojos rasgados', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (3, ' Sus ojos son pequeños', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', la nariz la tiene puntiaguda', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es redonda', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', tiene una nariz fina y pequeña', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (4, ', su nariz es chata', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', sus labios gruesos.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es diminuta', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es amplia, con labios finos.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (5, ', su boca es ancha', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es de grosor estándar.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y tiene un cuello fino.', 2)")
                                db.execSQL("INSERT INTO components_head (part_head, text, difficulty) VALUES (6, ' y su cuello es muy corto.', 2)")

                                //Components character

                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humano ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humana ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Humano adulto ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Mujer adulta ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de caracter visiblemente amable ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'bastante impaciente y de caracter inquieto ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'con personalidad muy calmada y tranquila ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de comportamiento introvertido y nervioso ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la herrería en su pueblo y su cuerpo está lleno de cenizas y cicatrices. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la magia de profesión, teniendo control sobre la luz que le rodea. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'vive cerca de las aguas, siendo el elemento que controla a voluntad con sus gestos. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'le gusta la ganadería y le rodean numerosos animales. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene un pelo normal, siendo una cabellera corta nada fuera de lo común ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo muy largo y liso, adornado con un par de orejas puntiagudas ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo rizado y corto con una cornamenta similar a la de un ciervo ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene una gran capucha puesta con detalles rúnicos ensombreciendo su cara ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara tiene rasgos elegantes y finos de expresión altiva. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara porta un semblante duro y serio. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara de rasgos redondos la adorna una gran sonrisa. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara la adornan algunos tatuajes místicos. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es normal, algo delgado ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es muy ancho con brazos musculosos ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es delgado y largo ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es redondo y esférico ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas largas y finas. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son anchas. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son cortas, con grandes pies. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son muy largas y fuertes. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva puesto un chaleco de tela con una camisa sencilla ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Porta en la parte de arriba una armadura sencilla con algunas placas de hierro ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva en la parte de arriba una capa que cubre su parte superior con hombreras puntiagudas ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Arriba lleva una camiseta sencilla con su logo favorito ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y pantalones cortos de tela, ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y una armadura inferior que protege sus piernas, ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón largo con pociones colgando de su cinturón, ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón remendado con numerosos trapos, ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatillas sencillas. \n\n', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatos de metal que terminan en punta. \n\n', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'va descalzo. \n\n', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'En una de sus manos tiene una espada de doble filo y en la otra un gran escudo de madera. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Porta un gran bastón de madera con un orbe brillante en uno de sus extremos. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un gran martillo de metal que porta con ambas manos. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un arco sencillo y un carcaj en su espalda lleno de flechas. ', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'En mitad de su aventura decide comprar en una sencilla tienda en un pueblo.', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra caminando por el bosque rodeado de numerosos árboles.', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Está hablando con sus pequeños súbditos en su alta torre sobre cómo realizar sus nuevos planes.', 0)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra practicando sus habilidades para entrar en el gremio de lucha de su ciudad natal.', 0)")


                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humano ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humana ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Humano adulto ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Mujer adulta ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de caracter visiblemente amable ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'bastante impaciente y de caracter inquieto ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'con personalidad muy calmada y tranquila ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de comportamiento introvertido y nervioso ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la herrería en su pueblo y su cuerpo está lleno de cenizas y cicatrices. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la magia de profesión, teniendo control sobre la luz que le rodea. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'vive cerca de las aguas, siendo el elemento que controla a voluntad con sus gestos. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'es un granjero y le rodean numerosos animales. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene un pelo normal, siendo una cabellera corta nada fuera de lo común ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo muy largo y liso, adornado con un par de orejas puntiagudas ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo rizado y corto con una cornamenta similar a la de un ciervo ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene una gran capucha puesta con detalles rúnicos ensombreciendo su cara ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara tiene rasgos elegantes y finos de expresión altiva. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara porta un semblante duro y serio. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara de rasgos redondos la adorna una gran sonrisa. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara la adornan algunos tatuajes místicos. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es normal, algo delgado ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es muy ancho con brazos musculosos ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es delgado y largo ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es redondo y esférico ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas largas y finas. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son anchas. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son cortas, con grandes pies. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son muy largas y fuertes. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva puesto un chaleco de tela con una camisa sencilla ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Porta en la parte de arriba una armadura sencilla con algunas placas de hierro ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva en la parte de arriba una capa que cubre su parte superior con hombreras puntiagudas ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Arriba lleva una camiseta sencilla con su logo favorito ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y pantalones cortos de tela, ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y una armadura inferior que protege sus piernas, ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón largo con pociones colgando de su cinturón, ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón remendado con numerosos trapos, ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatillas sencillas. \n\n', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatos de metal que terminan en punta. \n\n', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'va descalzo. \n\n', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'En una de sus manos tiene una espada de doble filo y en la otra un gran escudo de madera. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Porta un gran bastón de madera con un orbe brillante en uno de sus extremos. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un gran martillo de metal que porta con ambas manos. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un arco sencillo y un carcaj en su espalda lleno de flechas. ', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'En mitad de su aventura decide comprar en una sencilla tienda en un pueblo.', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra caminando por el bosque rodeado de numerosos árboles.', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Está hablando con sus pequeños súbditos en su alta torre sobre cómo realizar sus nuevos planes.', 1)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra practicando sus habilidades para entrar en el gremio de lucha de su ciudad natal.', 1)")


                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humano ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Adolescente humana ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Humano adulto ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (0, 'Mujer adulta ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de caracter visiblemente amable ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'bastante impaciente y de caracter inquieto ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'con personalidad muy calmada y tranquila ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (1, 'de comportamiento introvertido y nervioso ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la herrería en su pueblo y su cuerpo está lleno de cenizas y cicatrices. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'se dedica a la magia de profesión, teniendo control sobre la luz que le rodea. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'vive cerca de las aguas, siendo el elemento que controla a voluntad con sus gestos. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (2, 'es un granjero y le rodean numerosos animales. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene un pelo normal, siendo una cabellera corta nada fuera de lo común ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo muy largo y liso, adornado con un par de orejas puntiagudas ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene el pelo rizado y corto con una cornamenta similar a la de un ciervo ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (3, 'Tiene una gran capucha puesta con detalles rúnicos ensombreciendo su cara ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara tiene rasgos elegantes y finos de expresión altiva. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara porta un semblante duro y serio. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara de rasgos redondos la adorna una gran sonrisa. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (4, 'y su cara la adornan algunos tatuajes místicos. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es normal, algo delgado ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es muy ancho con brazos musculosos ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es delgado y largo ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (5, 'Su torso es redondo y esférico ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas largas y finas. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son anchas. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son cortas, con grandes pies. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (6, 'y sus piernas son muy largas y fuertes. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva puesto un chaleco de tela con una camisa sencilla ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Porta en la parte de arriba una armadura sencilla con algunas placas de hierro ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Lleva en la parte de arriba una capa que cubre su parte superior con hombreras puntiagudas ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (7, 'Arriba lleva una camiseta sencilla con su logo favorito ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y pantalones cortos de tela, ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y una armadura inferior que protege sus piernas, ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón largo con pociones colgando de su cinturón, ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (8, 'y un pantalón remendado con numerosos trapos, ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatillas sencillas. \n\n', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'con zapatos de metal que terminan en punta. \n\n', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (9, 'va descalzo. \n\n', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'En una de sus manos tiene una espada de doble filo y en la otra un gran escudo de madera. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Porta un gran bastón de madera con un orbe brillante en uno de sus extremos. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un gran martillo de metal que porta con ambas manos. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (10, 'Tiene un arco sencillo y un carcaj en su espalda lleno de flechas. ', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'En mitad de su aventura decide comprar en una sencilla tienda en un pueblo.', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra caminando por el bosque rodeado de numerosos árboles.', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Está hablando con sus pequeños súbditos en su alta torre sobre cómo realizar sus nuevos planes.', 2)")
                                db.execSQL("INSERT INTO components_character (character_feature, text, difficulty) VALUES (11, 'Se encuentra practicando sus habilidades para entrar en el gremio de lucha de su ciudad natal.', 2)")


                            }
                        }).build()
                    }
                }
            }
            return INSTANCE!!
        }

    }

}