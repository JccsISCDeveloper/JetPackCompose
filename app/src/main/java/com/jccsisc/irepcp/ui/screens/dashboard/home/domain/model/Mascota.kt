package com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jccsisc.irepcp.core.ConstantsRoom.TBL_MASCOTA

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Entity(tableName = TBL_MASCOTA)
data class Mascota(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val animal: String? = "",
    val raza: String? = ""
)