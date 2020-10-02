package io.audioshinigami.core.mapper

/**
 *A base helper to transform an input to a desired output.
 */
interface Mapper<F,T> {

    /**
     * @param from Object to be transformed.
     * @return The desired output.
     */
    suspend fun transform(from: F): T
}