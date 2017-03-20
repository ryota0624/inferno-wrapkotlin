package example

/**
 * Created by ryota on 2017/03/19.
 */


class ParamError(message: String) : Error("invalid param ${message}")
typealias Callback<E, Result> = (E?, Result?) -> Unit

fun foldError(contextMessage: String, vararg errors: Error?): Error? {
    val result = errors.filter {
        result ->
        !(result == null)
    }.map {
        it!!.message
    }.reduce {
        acc, pre ->
        acc + pre
    }

    return if (result == null) return null else ParamError("${contextMessage}: result")
}