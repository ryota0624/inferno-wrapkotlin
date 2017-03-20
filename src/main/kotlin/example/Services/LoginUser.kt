package example.Services

import example.Callback
import example.Repositories.UserRepository
import example.User.User

/**
 * Created by ryota on 2017/03/19.
 */
class LoginUser(val repository: UserRepository) {
    fun execute(id: String, name: String, callback: Callback<Error, User>) {
        repository.findById(id = User.Id(id)) { error, user ->
            if (error != null) {
                callback(error, null)
            } else {
                user?.let {
                    matchName(name, it, callback)
                }
            }
        }
    }

    fun matchName(name: String, user: User, callback: Callback<Error, User>) {
        if (user.name == User.Name(name)) {
            callback(null, user)
        } else {
            callback(Error("no match id and name"), null)
        }
    }
}