package Inferno

/**
 * Created by ryota on 2017/03/20.
 */
/**
 * Created by ryota on 2017/03/18.
 */
import org.w3c.dom.Node
import org.w3c.dom.Element
import kotlin.js.Json
import kotlin.js.json

abstract class ComponentProps

abstract class InfernoProp<out T>(val name: String, val value: T) {
    class Ref(handler: (Node) -> Unit) : InfernoProp<(Node) -> Unit>(value = handler, name = "ref")
    class Key(value: String) : InfernoProp<String>(value = value, name = "key")
    class TextValue(value: String): InfernoProp<String>(value = value, name = "value")
    class Type(value: String): InfernoProp<String>(value = value, name = "type")
}

abstract class InfernoStringProp(name: String, value: String) : InfernoProp<String>(name, value)

interface InfernoElement

typealias InfernoProperties = Array<out InfernoProp<Any>>
fun htmlProps(vararg p: InfernoProp<Any>): InfernoProperties {
    return p
}

fun childrenElement(vararg children: InfernoElement): Array<out InfernoElement> {
    return children
}

external class Inferno {
    companion object {
        val version: dynamic
        fun render(node: InfernoElement, container: Element)
        fun cloneVNode(element: InfernoElement, props: Any): InfernoElement
    }
}

object InfernoHelper {
    private fun propsToJson(props: InfernoProperties): Json {
        return json(*props.filter { prop ->
            prop is InfernoStringProp
        }.map { prop ->
            Pair(prop.name, prop.value)
        }.toTypedArray())
    }

    private fun propsToEvents(props: InfernoProperties): Json {
        return json(*props.filter { prop ->
            prop is EventProp<*>
        }.map { prop ->
            Pair(prop.name, prop.value)
        }.toTypedArray())
    }

    private fun pickKeyFromProps(props: InfernoProperties): String? {
        val value = props.find {
            it.name == "key"
        }?.value

        return if (value is String) value else null
    }

    private fun pickRefFromProps(props: InfernoProperties): Function<*>? {
        val value = props.find {
            it.name == "ref"
        }?.value

        return if (value is Function<*>) value else null
    }
}