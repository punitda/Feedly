package dev.punitd.rss.parser.inject

import com.prof.rssparser.Parser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RssParserModule {

    @Provides
    @Singleton
    fun provideRssParser(): Parser {
        return Parser.Builder().build()
    }
}
