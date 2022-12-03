package com.jccsisc.irepcp.core.di

import android.content.Context
import androidx.room.Room
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_MASCOTAS
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_TASKS
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.data.local.MascotaDB
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.data.local.MascotaDao
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.data.local.MascotaRepositoryImpl
import com.jccsisc.irepcp.ui.screens.mascotas.mascotashome.domain.repository.MascotaRepository
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local.TaskDao
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local.TasksDB
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.data.local.TasksRepository
import com.jccsisc.irepcp.ui.screens.todomodule.addtasks.domain.usecase.UseCaseTasks
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Project: IREPCP
 * FROM: com.jccsisc.irepcp.core.di
 * Created by Julio Cesar Camacho Silva on 01/12/22
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    /**
     * DB MASCOTAS
     * */
    @Provides
    fun provideMascotaDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MascotaDB::class.java, DB_MASCOTAS).build()

    @Provides
    fun provideMascotaDao(mascotaDB: MascotaDB) = mascotaDB.mascotaDao()

    @Provides
    fun provideMascotaRepository(mascotaDao: MascotaDao): MascotaRepository =
        MascotaRepositoryImpl(mascotaDao = mascotaDao)


    /**
     * DB TAREAS
     * */
    @Provides
    fun provideTasksDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TasksDB::class.java, DB_TASKS).build()

    @Provides
    fun provideTaskDao(tasksDB: TasksDB) = tasksDB.taskDao()

    @Provides
    fun provideTaskUseCaseTasks(taskDao: TaskDao): UseCaseTasks =
        TasksRepository(taskDao = taskDao)
}