package com.jccsisc.irepcp.ui.activities.home.screens.todomodule.addtasks.data.local

import android.database.sqlite.SQLiteException
import android.util.Log
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jccsisc.irepcp.core.constants.ConstantsRoom.TBL_TASK

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local
 * Created by Julio Cesar Camacho Silva on 09/12/22
 */
object DataBaseBuilder {

    val TASKS_MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            try {
                /**
                 * Creamos tabla temporal
                 * */
                database.execSQL(
                    "CREATE TABLE tasks_temporal (" +
                            "id INTEGER PRIMARY KEY NOT NULL, " +
                            "task TEXT NOT NULL, " +
                            "selected INTEGER NOT NULL, " +
                            "modification_date INTEGER NOT NULL DEFAULT 0" +
                            ")"
                )

                /**
                 * Copiamos los datos de TBL_TASKS a task_temporal
                 * */
                database.execSQL(
                    "INSERT INTO tasks_temporal " +
                            "(id, task, selected) " +
                            "SELECT id, task, selected FROM $TBL_TASK"
                )

                /**
                 * Eliminamos la tabla TBL_TASKS
                 * */
                database.execSQL("DROP TABLE $TBL_TASK")

                /**
                 * Renombramos nuestra tabla temporal
                 * */
                database.execSQL("ALTER TABLE tasks_temporal RENAME TO $TBL_TASK")

            } catch (sqlEx: SQLiteException) {
                sqlEx.message?.let { Log.e("ERROR_SQL ", it) }
            }
        }
    }

    val TASKS_MIGRATION_2_3 = object : Migration(2, 3) {
        override fun migrate(database: SupportSQLiteDatabase) {
            try {
                /**
                 * Creamos tabla temporal
                 * */
                database.execSQL(
                    "CREATE TABLE tasks_temporal (" +
                            "id INTEGER PRIMARY KEY NOT NULL, " +
                            "task TEXT NOT NULL, " +
                            "selected INTEGER NOT NULL, " +
                            "modification_date INTEGER NOT NULL DEFAULT 0, " +
                            "title TEXT NOT NULL DEFAULT '', " +
                            "priority_task INTEGER NOT NULL DEFAULT 0 " +
                            ")"
                )

                /**
                 * Copiamos los datos de TBL_TASKS a task_temporal
                 * */
                database.execSQL(
                    "INSERT INTO tasks_temporal " +
                            "(id, task, selected, modification_date) " +
                            "SELECT id, task, selected, modification_date FROM $TBL_TASK"
                )

                /**
                 * Eliminamos la tabla TBL_TASKS
                 * */
                database.execSQL("DROP TABLE $TBL_TASK")

                /**
                 * Renombramos nuestra tabla temporal
                 * */
                database.execSQL("ALTER TABLE tasks_temporal RENAME TO $TBL_TASK")

            } catch (sqlEx: SQLiteException) {
                sqlEx.message?.let { Log.e("ERROR_SQL ", it) }
            }
        }
    }
}