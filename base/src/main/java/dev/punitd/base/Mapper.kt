package dev.punitd.base

interface Mapper<T, R> {
    suspend fun map(from: T): R
}
