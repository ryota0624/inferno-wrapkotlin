package example.view

import Inferno.InfernoComponentSpec
import Inferno.InfernoElement
import Inferno.htmlProps
import Inferno.infernoCreateClass
import createElement
import example.Services.RegisterUser

/**
 * Created by ryota on 2017/03/20.
 */

sealed class Page {
    object SignUp: Page()
    object Login: Page()
    object TodoList: Page()
    object TodoDetail: Page()
}

data class ContainerState(val page: Page)
class Container(val registerUser: RegisterUser): InfernoComponentSpec<Unit, ContainerState> {
    override fun getInitialState() = ContainerState(Page.SignUp)
    override val props = Unit
    override val defaultProps = Unit
    override val state = ContainerState(Page.SignUp)
    override fun render(): InfernoElement {
        return switchPage()
    }

    fun switchPage(): InfernoElement {
        return when (state.page) {
            is Page.SignUp -> createElement(component = signUpComponent, props = SignUpComponentProps { changePage(Page.TodoList) })
            else -> createElement(type = "span", props = htmlProps(),text = "no match page")
        }
    }

    fun changePage(page: Page) {
        this.setState(state.copy(page))
    }
    val signUpComponent = infernoCreateClass(SignUpComponentSpec(registerUser))
}