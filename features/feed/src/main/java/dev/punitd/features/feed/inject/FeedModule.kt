package dev.punitd.features.feed.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.rss.parser.domain.usecase.GetChannelUseCase
import dev.punitd.rss.parser.domain.usecase.GetChannelUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class FeedModule {

    @Binds
    abstract fun provideGetChannelUseCase(
        getChannelUseCase: GetChannelUseCaseImpl
    ): GetChannelUseCase
}
