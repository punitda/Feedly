package dev.punitd.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val guid: String,
    val title: String,
    val author: String,
    val link: String,
    val pubDate: String,
    val description: String,
    val content: String,
    val imageUrl: String? = null,
    val categories: List<String> = emptyList(),
) : Parcelable
