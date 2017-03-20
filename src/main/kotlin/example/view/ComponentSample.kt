package example.view

import Inferno.*
import InfernoRouter.ConnectRouterComponent
import InfernoRouter.ConnectRouterContext
import InfernoRouter.ConnectRouterParam
import createElement

/**
 * Created by ryota on 2017/03/22.
 */

class ParentComponentSample(props: ConnectRouterParam, context: ConnectRouterContext) :
        ConnectRouterComponent<Unit>(props = props, context = context) {
    override var state = Unit
    override fun render(): InfernoElement {
        return createElement(ComponentSample::class, Props(200))
    }
}

class ParentComponentSample2(props: ConnectRouterParam, context: ConnectRouterContext) :
        ConnectRouterComponent<Unit>(props = props, context = context) {
    override var state = Unit
    override fun render(): InfernoElement {
        console.log(this)
        return createElement(ComponentSample::class, Props(20000000))
    }
}

data class State(val name: String)
data class Props(val age: Int)
class ComponentSample(props: Props) : Component<Props, State>(props) {
    val onChangeName: (String) -> Unit = { text -> this.setState(this.state.copy(name = text)) }
    fun onChangeNameForm(name: String) {
        this.setState(this.state.copy(name = name))
    }

    override var state = State("default")
    override fun render(): InfernoElement {
        return createElement("div", props = htmlProps(), children = childrenElement(
                createElement("input", props = htmlProps(EventProp.onInputText(this::onChangeNameForm)), text = "hello component"),
                createElement("div", props = htmlProps(), text = "name: " + state.name),
                createElement("div", props = htmlProps(), text = "age: " + props.age.toString()),
                createElement(SampleChildComponent::class, ChildProps(state))
        ))
    }
}

data class ChildProps(val parentState: State)
class SampleChildComponent(props: ChildProps) : StatelessComponent<ChildProps>(props) {
    override fun render(): InfernoElement {
        return createElement("div", props = htmlProps(), children = childrenElement(
                createElement("div", props = htmlProps(), text = "parent name: " + props.parentState.name)
        ))
    }
}