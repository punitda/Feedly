package dev.punitd.persistence.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Channel(
    @PrimaryKey val title: String,
    val link: String,
    val isSelected: Boolean,
)
