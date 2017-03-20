package example.Repositories

import example.Todo.Todo

/**
 * Created by ryota on 2017/03/19.
 */

interface TodoRepository {
    fun findById(id: Todo.Id): Todo?
    fun store(todo: Todo): Error?
}