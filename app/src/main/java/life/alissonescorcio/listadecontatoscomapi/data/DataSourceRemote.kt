package life.alissonescorcio.listadecontatoscomapi.data

import life.alissonescorcio.listadecontatoscomapi.data.network.NetworkClient
import life.alissonescorcio.listadecontatoscomapi.model.Contact
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataSourceRemote {

    private val service = NetworkClient().service()

    fun getAll(callback: ContactsCallback) {

        val call: Call<List<Contact>> = service.getAllContacts()
        call.enqueue(object : Callback<List<Contact>> {
            override fun onResponse(call: Call<List<Contact>>, response: Response<List<Contact>>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<List<Contact>>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun getById(id: Int, callback: ContactCallback) {
        val call: Call<Contact> = service.getContactById(id)

        call.enqueue(object : Callback<Contact> {
            override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Contact>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun create(contact: Contact, callback: ContactCallback) {
        val call = service.createContact(contact)
        call.enqueue(object : Callback<Contact> {
            override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Contact>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }

    fun remove(id: Int, callback: ContactCallback) {
        val call = service.deleteContactById(id)
        call.enqueue(object : Callback<Contact> {
            override fun onResponse(call: Call<Contact>, response: Response<Contact>) {
                callback.onSucesso(response.body())
            }

            override fun onFailure(call: Call<Contact>, t: Throwable) {
                callback.onFalha(t)
            }
        })
    }
}