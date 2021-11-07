package dev.punitd.domain.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.domain.repository.ChannelsRepository
import dev.punitd.domain.repository.ChannelsRepositoryImpl
import dev.punitd.domain.usecase.GetSavedChannelsUseCase
import dev.punitd.domain.usecase.GetSavedChannelsUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class DomainModule {

    @Binds
    abstract fun provideGetSavedChannelsUseCase(
        getSavedChannelsUseCase: GetSavedChannelsUseCaseImpl
    ): GetSavedChannelsUseCase

    @Binds
    abstract fun provideChannelsRepo(
        channelsRepository: ChannelsRepositoryImpl
    ): ChannelsRepository
}
