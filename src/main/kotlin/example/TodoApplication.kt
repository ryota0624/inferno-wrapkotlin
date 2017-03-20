package example


import Inferno.Inferno
import Inferno.infernoCreateClass
import createElement
import example.Repositories.UserRepository
import example.Services.RegisterUser
import example.User.User
import example.view.ComponentSample
import example.view.Container
import example.view.Props
import example.view.sample

import kotlin.browser.document

fun start() {
    val service = RegisterUser(object: UserRepository {
        var tmpStore: Map<User.Id, User> = emptyMap()
        override fun store(user: User, callback: Callback<Error, Unit>) {
            if (!tmpStore.containsKey(user.id)) {
                tmpStore = tmpStore.plus(Pair(user.id, user))
                callback(null, Unit)
            } else {
                callback(Error("duplication id"), null)
            }
        }

        override fun findById(id: User.Id, callback: Callback<Error, User>) {
            val user = tmpStore.get(id)
            if (user == null)
                callback(Error("not found user"), null)
            else
                callback(null, user)
        }
    })

    val appContainer = infernoCreateClass(Container(service))
//    val component = ComponentSample(props = "", context = "")
//    Inferno.render(createElement(jsClass = ComponentSample::class, props = Props(100)), document.getElementById("component-sample")!!)
      Inferno.render(sample(), document.getElementById("component-sample")!!)
//    Inferno.render(createElement(appContainer, props = Unit), document.getElementById("main")!!)
}
