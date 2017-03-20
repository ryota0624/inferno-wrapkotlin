package example.view

import Inferno.*
import createElement
import createTextElement
import example.Services.RegisterUser

/**
 * Created by ryota on 2017/03/20.
 */
data class SignUpComponentState(val username: String, val password: String, val errorMessage: String?)
data class SignUpComponentProps(val onSignUp: (() -> Unit)?)
class SignUpComponentSpec(val service: RegisterUser) : InfernoComponentSpec<SignUpComponentProps, SignUpComponentState> {
    override fun getInitialState() = SignUpComponentState("", "", null)
    override val props = SignUpComponentProps(null)
    override val state = SignUpComponentState("", "", null)
    override val defaultProps = SignUpComponentProps(null)
    override fun render(): InfernoElement {
        return createElement("div", props = htmlProps(classNames("signUp")), children = childrenElement(
                createElement("div", props = htmlProps(classNames("sign-up-form")), text = "username"),
                createElement("input", props = htmlProps(
                        EventProp.onInputText { inputUsername(it) },
                        classNames("username-form"),
                        InfernoProp.TextValue(state.username))
                ),
                createElement("input", props = htmlProps(
                        EventProp.onInputText { inputPassword(it) },
                        InfernoProp.Type("password"),
                        classNames("password-form"),
                        InfernoProp.TextValue(state.password))
                ),
                createElement("button", props = htmlProps(
                        EventProp.onClick { registerUser() },
                        classNames("submit-button")),
                        text = "signUp"
                ),
                createElement("div", props = htmlProps(classNames("sign-up-form")), text = "username"),
                createTextElement(state.username)
        ))
    }

    fun registerUser() :Unit {
        service.execute(name = state.username) { error, _ ->
            if (error != null) {
                setState(state.copy(errorMessage = error.message))
            } else {
                props.onSignUp?. let {
                    it()
                }
            }
        }
    }
    fun inputPassword(pass: String):Unit {
        this.setState(state.copy(password = pass))
    }

    fun inputUsername(name: String):Unit {
        this.setState(state.copy(username = name))
    }
}