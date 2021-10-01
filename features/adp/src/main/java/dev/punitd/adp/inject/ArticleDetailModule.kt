package dev.punitd.adp.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.adp.domain.usecase.BookmarkArticleUseCase
import dev.punitd.adp.domain.usecase.BookmarkArticleUseCaseImpl
import dev.punitd.adp.domain.usecase.IsArticleBookmarkedUseCase
import dev.punitd.adp.domain.usecase.IsArticleBookmarkedUseCaseImpl
import dev.punitd.adp.domain.usecase.RemoveBookmarkedArticleUseCase
import dev.punitd.adp.domain.usecase.RemoveBookmarkedArticleUseCaseImpl

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
