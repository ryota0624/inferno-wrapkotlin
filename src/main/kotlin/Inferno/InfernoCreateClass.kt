package Inferno

/**
 * Created by ryota on 2017/03/19.
 */
external fun <P> infernoCreateClass(spec: InfernoComponentSpec<P, *>): InfernoComponentClass<P>
external fun <P> infernoCreateClass(spec: InfernoStatelessComponentSpec<P>): InfernoComponentClass<P>


interface InfernoComponentSpec<out P, S> {
    val state: S
    val props: P
    fun getInitialState(): S
    val defaultProps: P
    fun render(): InfernoElement
    fun setState(state: S): Unit {
        js("console.log(this)")
        js("this.setState(state)")
    }
}

interface InfernoStatelessComponentSpec<out P> {
    val props: P
    val defaultProps: P
    fun render(): InfernoElement
}

interface InfernoComponentClass<P>