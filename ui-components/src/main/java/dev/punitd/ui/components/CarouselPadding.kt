package dev.punitd.ui.components

import com.airbnb.epoxy.Carousel

fun defaultCarouselPadding(): Carousel.Padding = Carousel.Padding.resource(
    R.dimen.grid_4, // left
    R.dimen.grid_0, // top
    R.dimen.grid_4, // right
    R.dimen.grid_2, // bottom
    R.dimen.grid_2 // inset spacing
)

fun insetOnlyCarouselPadding(): Carousel.Padding = Carousel.Padding.resource(
    R.dimen.grid_0, // left
    R.dimen.grid_0, // top
    R.dimen.grid_0, // right
    R.dimen.grid_0, // bottom
    R.dimen.grid_2 // inset spacing
)
