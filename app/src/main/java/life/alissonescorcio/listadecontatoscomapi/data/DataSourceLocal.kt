package life.alissonescorcio.listadecontatoscomapi.data

import life.alissonescorcio.listadecontatoscomapi.model.Contact


//import bootcamp.snt.bootcampsantandertodo.model.Todo

object DataSourceLocal {
    private val todoList = initialList()

    fun getAllTodos() : List<Contact> {
        return todoList
    }

    fun removeTodo(contact: Contact) {
        todoList.remove(contact)
    }

    fun getTodoById(id: Int): Contact {
        return todoList.single { it.id == id }
    }

    fun createTodo(newContact: Contact){
        todoList.add(0, newContact)
    }

    private fun initialList() : MutableList<Contact> {
        return mutableListOf(
            Contact(1, "Titulo 1", "Conteúdo 1"),
            Contact(2, "Titulo 2", "Conteúdo 2"),
            Contact(3, "Titulo 3", "Conteúdo 3"),
            Contact(4, "Titulo 4", "Conteúdo 4"),
            Contact(5, "Titulo 5", "Conteúdo 5")
        )
    }
}