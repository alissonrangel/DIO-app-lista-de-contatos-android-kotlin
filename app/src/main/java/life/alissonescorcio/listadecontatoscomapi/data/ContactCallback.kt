package life.alissonescorcio.listadecontatoscomapi.data

import life.alissonescorcio.listadecontatoscomapi.model.Contact

interface ContactCallback {
    fun onSucesso(todos: Contact?)
    fun onFalha(t: Throwable)
}