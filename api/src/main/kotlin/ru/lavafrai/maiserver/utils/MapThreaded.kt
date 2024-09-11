package ru.lavafrai.maiserver.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun <A, B>List<A>.mapThreadedOld (f: suspend (A) -> B): List<B> = runBlocking {
    map {
        async(Dispatchers.Default)
        {
            f(it)
        }
    }.map { it.await() }
}

fun <T, R> Iterable<T>.mapThreaded(
    numThreads: Int = 24,
    exec: ExecutorService = Executors.newFixedThreadPool(numThreads),
    transform: (T) -> R): List<R> {

    // default size is just an inlined version of kotlin.collections.collectionSizeOrDefault
    val defaultSize = if (this is Collection<*>) this.size else 10
    val destination = Collections.synchronizedList(ArrayList<R>(defaultSize))

    for (item in this) {
        exec.submit { destination.add(transform(item)) }
    }

    exec.shutdown()
    exec.awaitTermination(1, TimeUnit.DAYS)

    return ArrayList<R>(destination)
}