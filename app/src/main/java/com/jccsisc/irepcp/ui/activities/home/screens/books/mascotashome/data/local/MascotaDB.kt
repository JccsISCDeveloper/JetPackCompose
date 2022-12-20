package com.jccsisc.irepcp.ui.activities.home.screens.books.mascotashome.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_MASCOTAS_VERSION
import com.jccsisc.irepcp.ui.activities.home.screens.books.mascotashome.domain.model.Mascota

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.data.local
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Database(entities = [Mascota::class], version = DB_MASCOTAS_VERSION, exportSchema = false)
abstract class MascotaDB: RoomDatabase() {
    abstract fun mascotaDao(): MascotaDao

}