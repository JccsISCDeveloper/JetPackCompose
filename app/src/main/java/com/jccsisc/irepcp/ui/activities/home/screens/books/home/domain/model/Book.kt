package com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_BOOKS

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Entity(tableName = TBL_BOOKS)
data class Book(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    val image: String = "",
    val read: Boolean = false,
    val favorite: Boolean = false
)