package history

/**
 * Created by ryota on 2017/03/22.
 */
typealias HistoryModule = Any
external object History {
    fun createHashHistory(): HistoryModule
}