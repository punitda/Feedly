package dev.punitd.adp.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.adp.domain.usecase.*

@InstallIn(ViewModelComponent::class)
@Module
abstract class ArticleDetailModule {

    @Binds
    abstract fun provideIsArticleBookmarkedUseCase(
        isArticleBookmarkedUseCase: IsArticleBookmarkedUseCaseImpl
    ): IsArticleBookmarkedUseCase

    @Binds
    abstract fun provideBookmarkArticleUseCase(
        bookmarkArticleUseCase: BookmarkArticleUseCaseImpl
    ): BookmarkArticleUseCase

    @Binds
    abstract fun provideRemoveBookmarkedArticleUseCase(
        removeBookmarkedArticleUseCaseImpl: RemoveBookmarkedArticleUseCaseImpl
    ): RemoveBookmarkedArticleUseCase
}
