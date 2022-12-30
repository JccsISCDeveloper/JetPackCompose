package com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_BOOKS
import javax.annotation.Nonnull

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.dashboard.home.domain.model
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Entity(tableName = TBL_BOOKS)
data class Book(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = -1,
    @ColumnInfo(name = "image") @Nonnull var image: String = "",
    @ColumnInfo(name = "read") var read: Int = 0,
    @ColumnInfo(name = "favorite") var favorite: Boolean = false,
    @ColumnInfo(name = "image_name") @Nonnull var imageName: String = ""
)