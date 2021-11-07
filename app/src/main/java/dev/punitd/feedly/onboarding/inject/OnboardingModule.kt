package dev.punitd.feedly.onboarding.inject

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.punitd.feedly.onboarding.domain.usecase.GetPrefilledChannelsUseCase
import dev.punitd.feedly.onboarding.domain.usecase.GetPrefilledChannelsUseCaseImpl

@InstallIn(ViewModelComponent::class)
@Module
abstract class OnboardingModule {

    @Binds
    abstract fun provideGetPrefilledChannelsUseCase(
        getPrefilledChannelsUseCase: GetPrefilledChannelsUseCaseImpl
    ): GetPrefilledChannelsUseCase
}
