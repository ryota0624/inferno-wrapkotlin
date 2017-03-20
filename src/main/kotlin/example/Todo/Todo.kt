package example.Todo

import example.ParamError
import example.foldError

/**
 * Created by ryota on 2017/03/19.
 */

class Todo(val id: Id, val title: Title, val description: Description) {
    constructor(id: String, title: String, description: String) : this(Id(id), Title(title), Description(description))

    fun validate(): Error? {
        return foldError(
                "Todo",
                id.validate(),
                title.validate(),
                description.validate()
        )
    }

    data class Id(val value: String) {
        fun validate(): ParamError? = if (value.isEmpty()) null else ParamError("id")

    }

    data class Title(val value: String) {
        fun validate(): ParamError? = if (value.isEmpty()) null else ParamError("title")

    }

    data class Description(val value: String) {
        fun validate(): ParamError? = if (value.isEmpty()) null else ParamError("description")

    }
}
