package example.User

import example.ParamError
import example.Todo.Todo
import example.foldError

/**
 * Created by ryota on 2017/03/19.
 */

class User(val id: Id, val name: Name, val todos: Todos) {
    fun validate() = foldError("User", id.validate(), name.validate())
    data class Id(val value: String) {
        fun validate(): ParamError? = if (value.isEmpty()) ParamError("Id") else null

    }
    data class Name(val value: String) {
        fun validate(): ParamError? = if (value.isEmpty()) ParamError("Name") else null
    }
    data class Todos(val value: Map<Todo.Id, Todo>) {
        companion object {
            val empty = Todos(emptyMap<Todo.Id, Todo>())
        }
    }
}
