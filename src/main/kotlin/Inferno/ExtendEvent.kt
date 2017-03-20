package Inferno
/**
 * Created by ryota on 2017/03/18.
 */
import org.w3c.dom.*
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventTarget

/**
 * Created by ryota on 2017/03/18.
 */

abstract class FormEventTarget : EventTarget() {
    abstract var value: String
}

abstract class InputTextEvent(type: String, eventInitDict: EventInit) : Event(type = type, eventInitDict = eventInitDict) {
    override abstract var target: FormEventTarget
}

sealed class EventProp<T>(val eventName: String, val handler: (T) -> dynamic) : InfernoProp<(T) -> dynamic>(name = eventName, value = handler) {
    class onClick(handler: (Event) -> dynamic) : EventProp<Event>(eventName = "onclick", handler = handler)
    class onInput(handler: (Event) -> dynamic) : EventProp<Event>(eventName = "onInput", handler = handler)
    class onInputText(textHandler: (String) -> dynamic) : EventProp<InputTextEvent>(eventName = "onInput", handler = { event ->
        textHandler(event.target.value)
    })

    class onChange(handler: (Event) -> dynamic) : EventProp<Event>(eventName = "onChange", handler = handler)
}

/**
 * Created by ryota on 2017/03/20.
 */
