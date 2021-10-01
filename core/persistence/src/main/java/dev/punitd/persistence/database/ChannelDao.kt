package dev.punitd.persistence.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ChannelDao {
    @Update
    suspend fun selectChannel(channel: Channel)

    @Update
    suspend fun unselectChannel(channel: Channel)

    @Query("SELECT * from channel")
    fun getAllChannels(): Flow<List<Channel>>

    @Query("SELECT * from channel")
    suspend fun getAllChannelsOnce(): List<Channel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChannel(channel: Channel)
}
