package dev.punitd.data

data class Channel(
    val title: String,
    val link: String,
    val description: String? = null,
    val image: Image? = null,
) {
    data class Image(
        val title: String,
        val url: String,
        val link: String,
    )
}
