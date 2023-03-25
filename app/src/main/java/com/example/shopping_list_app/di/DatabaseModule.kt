package com.example.shopping_list_app.di

import android.content.Context
import androidx.room.Room
import com.example.shopping_list_app.data.db.item.ItemListDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        ItemListDatabase::class.java,
        "itemList_database"
    ).build()

    @Singleton
    @Provides
    fun provideDao(database: ItemListDatabase) = database.itemDao()

}