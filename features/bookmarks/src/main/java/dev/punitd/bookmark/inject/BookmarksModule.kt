package dev.punitd.bookmark.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.bookmark.domain.usecase.GetBookmarksUseCase
import dev.punitd.bookmark.domain.usecase.GetBookmarksUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class BookmarksModule {

    @Binds
    abstract fun provideGetBookmarksUseCase(
        getBookmarksUseCase: GetBookmarksUseCaseImpl
    ): GetBookmarksUseCase
}
