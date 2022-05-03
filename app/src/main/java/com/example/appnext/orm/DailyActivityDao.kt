package com.example.appnext.orm

import androidx.room.*
import com.example.appnext.models.database.DailyActivityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveData(entity: List<DailyActivityEntity>)

    @Query("SELECT * FROM DailyActivityTable")
    fun observeAll(): Flow<List<DailyActivityEntity>>

    @Query("DELETE FROM DailyActivityTable")
    fun clearAll()

}