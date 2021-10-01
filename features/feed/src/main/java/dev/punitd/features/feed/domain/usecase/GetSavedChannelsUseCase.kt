package dev.punitd.features.feed.domain.usecase

import dev.punitd.data.Channel

interface GetSavedChannelsUseCase {
    suspend fun execute(): List<Channel>
}
