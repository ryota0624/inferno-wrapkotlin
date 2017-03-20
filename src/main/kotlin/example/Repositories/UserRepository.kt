package example.Repositories

import example.Callback
import example.User.User

/**
 * Created by ryota on 2017/03/19.
 */

interface UserRepository {
    fun findById(id: User.Id, callback: Callback<Error, User>): Unit
    fun store(user: User, callback: Callback<Error, Unit>): Unit
}