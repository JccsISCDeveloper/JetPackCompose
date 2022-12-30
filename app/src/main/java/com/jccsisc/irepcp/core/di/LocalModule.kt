package com.jccsisc.irepcp.core.di

import android.content.Context
import androidx.room.Room
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_BOOKS
import com.jccsisc.irepcp.core.constants.ConstantsRoom.DB_TASKS
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local.BookRepositoryImpl
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local.BooksDB
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local.BooksDao
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.data.local.BooksDataBaseBuilder.BOOKS_MIGRATION_2_3
import com.jccsisc.irepcp.ui.activities.home.screens.books.home.domain.repository.BooksRepository
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local.TasksDataBaseBuilder.TASKS_MIGRATION_2_3
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local.TaskDao
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local.TasksDB
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.data.local.TasksRepository
import com.jccsisc.irepcp.ui.activities.home.screens.todomodule.domain.usecase.UseCaseTasks
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
     * DB BOOKS
     * */
    @Provides
    fun provideBooksDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BooksDB::class.java, DB_BOOKS)
            .addMigrations(BOOKS_MIGRATION_2_3)
            .build()

    @Provides
    fun provideBooksDao(booksDB: BooksDB) = booksDB.booksDao()

    @Provides
    fun provideBooksRepository(booksDao: BooksDao): BooksRepository =
        BookRepositoryImpl(booksDao = booksDao)


    /**
     * DB TAREAS
     * */
    @Provides
    fun provideTasksDB(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, TasksDB::class.java, DB_TASKS)
            .addMigrations(TASKS_MIGRATION_2_3)
            .build()

    @Provides
    fun provideTaskDao(tasksDB: TasksDB) = tasksDB.taskDao()

    @Provides
    fun provideTaskUseCaseTasks(taskDao: TaskDao): UseCaseTasks =
        TasksRepository(taskDao = taskDao)
}