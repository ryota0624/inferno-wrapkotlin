import Inferno.*
import kotlin.js.Json
import kotlin.js.json
import kotlin.reflect.KClass

private fun propsToJson(props: InfernoProperties): Json {
    return json(*props.map { prop ->
        Pair(prop.name, prop.value)
    }.toTypedArray())
}

fun <P> createElement(component: InfernoComponentClass<P>, props: P, children: Array<out InfernoElement>): InfernoElement {
    return infernoCreateElement(component, props as Any, *children)
}

fun <P> createElement(component: InfernoComponentClass<P>, props: P): InfernoElement {
    return infernoCreateElement(component, props as Any)
}

fun <P> createElement(component: InfernoComponentClass<P>): InfernoElement {
    return infernoCreateElement(component, json())
}


fun createElement(type: String, props: InfernoProperties, children: Array<out InfernoElement>): InfernoElement {
    return infernoCreateElement(type, propsToJson(props), *children)
}

fun createElement(type: String, props: InfernoProperties, text: String): InfernoElement {
    return infernoCreateElement(type, propsToJson(props), text)
}

fun createElement(type: String, props: InfernoProperties): InfernoElement {
    return infernoCreateElement(type, propsToJson(props))
}

fun createTextElement(text: String): InfernoElement {
    return infernoTextElement(text)
}

fun <P>createElement(jsClass: KClass<*>, props: P): InfernoElement {
    return infernoCreateElement(jsClass.js, props)
}
fun <P>createElement(jsClass: KClass<*>, props: P, children: Array<out InfernoElement>): InfernoElement {
    return infernoCreateElement(jsClass.js, props, *children)
}



external fun infernoCreateElement(spec: Any, props: Any): InfernoElement
external fun infernoCreateElement(spec: Any, props: Any, vararg children: InfernoElement): InfernoElement
external fun infernoCreateElement(type: String, props: Any, vararg children: InfernoElement): InfernoElement
external fun infernoCreateElement(type: String, props: Any, text: String): InfernoElement
external fun infernoCreateElement(type: String, props: Any): InfernoElement
external fun <P>infernoCreateElement(type: JsClass<*>, props: P): InfernoElement
external fun <P>infernoCreateElement(type: JsClass<*>, props: P, vararg children: InfernoElement): InfernoElement

external fun infernoTextElement(text: String): InfernoElement
