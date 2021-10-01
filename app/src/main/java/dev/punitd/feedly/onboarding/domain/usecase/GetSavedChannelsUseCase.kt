package dev.punitd.feedly.onboarding.domain.usecase

import dev.punitd.data.Channel

interface GetSavedChannelsUseCase {
    suspend fun execute(): List<Channel>
}
