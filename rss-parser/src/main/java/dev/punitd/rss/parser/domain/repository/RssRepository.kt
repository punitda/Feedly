package dev.punitd.rss.parser.domain.repository

import com.prof.rssparser.Channel
import com.prof.rssparser.Parser
import dev.punitd.base.data.Error
import dev.punitd.base.data.Result
import dev.punitd.base.data.Success
import java.io.IOException
import javax.inject.Inject
import org.xmlpull.v1.XmlPullParserException

class RssRepository @Inject constructor(
    private val parser: Parser
) {
    @Suppress("TooGenericExceptionCaught")
    suspend fun getChannel(url: String): Result<Channel> {
        return try {
            val channel = parser.getChannel(url)
            Success(data = channel)
        } catch (e: IOException) {
            Error(throwable = e)
        } catch (e: XmlPullParserException) {
            Error(throwable = e)
        } catch (e: Exception) {
            Error(throwable = e)
        }
    }
}
