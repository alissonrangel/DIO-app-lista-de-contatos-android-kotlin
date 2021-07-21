package life.alissonescorcio.listadecontatoscomapi.model

import com.google.gson.annotations.SerializedName

data class Contact(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("phone") val phone: String,
        //@SerializedName("done") val done: Boolean
)