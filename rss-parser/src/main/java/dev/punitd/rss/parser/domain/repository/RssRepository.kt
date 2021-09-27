package dev.punitd.rss.parser.domain.repository

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import dev.punitd.base.data.Error
import dev.punitd.base.data.Result
import dev.punitd.base.data.Success
import javax.inject.Inject

class RssRepository @Inject constructor(
    private val parser: Parser
) {
    suspend fun getChannel(url: String): Result<Channel> {
        return try {
            val channel = parser.getChannel(url)
            Success(data = channel)
        } catch (e: Exception) {
            Error(throwable = e)
        }
    }
}
