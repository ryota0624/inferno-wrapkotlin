package Inferno

/**
 * Created by ryota on 2017/03/20.
 */

private class ClassName(value: String) : InfernoStringProp("class", value)

fun classNames(vararg values: String): InfernoStringProp {
    return ClassName(values.joinToString(" "))
}