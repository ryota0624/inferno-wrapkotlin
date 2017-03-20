package Inferno

import createElement

/**
 * Created by ryota on 2017/03/22.
 */

//external open class InfernoComponent<S>(props: Any, context: Any) {
//    fun setState(state: S)
//    fun setState(state: S, callback: () -> Unit)
//}

external abstract class InfernoComponent<out P, S, C>(props: P, context: C) {
    abstract var state: S
    val props: P
    var context: C
    fun setState(state: S)
    fun setState(state: S, callback: () -> Unit)
    abstract fun render() :InfernoElement
}
abstract class Component<P, S>(props: P): InfernoComponent<P, S, Unit>(props, context = Unit)
abstract class StatelessComponent<P>(props: P): Component<P, Unit>(props) {
    override var state = Unit
}