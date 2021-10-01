package dev.punitd.feedly.onboarding.domain.usecase

import dev.punitd.data.Channel

interface GetPrefilledChannelsUseCase {
    suspend fun execute(): List<Channel>
}
