package life.alissonescorcio.listadecontatoscomapi.data.network

import life.alissonescorcio.listadecontatoscomapi.model.Contact
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @POST("contacts")
    fun createContact(@Body todo: Contact) : Call<Contact>

    @GET("contacts")
    fun getAllContacts() : Call<List<Contact>>

    @GET("contacts/{id}")
    fun getContactById(@Path("id") id: Int) : Call<Contact>

    @PUT("contacts/{id}")
    fun updateContactById(@Path("id") id: Int, @Body novoTodo: Contact) : Call<Contact>

    @DELETE("contacts/{id}")
    fun deleteContactById(@Path("id") id: Int) : Call<Contact>

}