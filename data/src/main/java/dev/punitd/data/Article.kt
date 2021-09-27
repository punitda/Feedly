package dev.punitd.data

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
)
