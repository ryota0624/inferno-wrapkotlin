package example.Services

import example.Callback
import example.Repositories.UserRepository
import example.User.User

/**
 * Created by ryota on 2017/03/19.
 */

class RegisterUser(val repository: UserRepository) {
    fun execute(name: String, callback: Callback<Error, Unit>) {
        val id = User.Id("0000")
        val user = User(id ,User.Name(name), User.Todos.empty)
        repository.store(user, callback)
    }
}