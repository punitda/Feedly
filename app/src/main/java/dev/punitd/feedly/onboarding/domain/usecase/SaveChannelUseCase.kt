package dev.punitd.feedly.onboarding.domain.usecase

import dev.punitd.data.Channel

interface SaveChannelUseCase {
    suspend fun execute(channel: Channel): Unit
}
