package life.alissonescorcio.listadecontatoscomapi.data

import life.alissonescorcio.listadecontatoscomapi.model.Contact

interface ContactsCallback {
    fun onSucesso(contacts: List<Contact>?)
    fun onFalha(t: Throwable)
}