package life.alissonescorcio.listadecontatoscomapi.features.addContact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_contact.*
import life.alissonescorcio.listadecontatoscomapi.R
import life.alissonescorcio.listadecontatoscomapi.data.ContactCallback
import life.alissonescorcio.listadecontatoscomapi.data.DataSourceRemote
import life.alissonescorcio.listadecontatoscomapi.model.Contact
import life.alissonescorcio.listadecontatoscomapi.utils.Constants
import java.util.*

class CreateContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_contact)

        toolbar.title = "Criar Contato"
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        btnCreate.setOnClickListener {
            val name = tietName.text.toString()
            val phone = tietPhone.text.toString()

            //Tratamento para mostrar um erro quando nenhum campo foi preenchido
            setErrorIfFieldsNotFill(name, phone).apply {
                if (this) {
                    createNewContact(name, phone)
                }
            }
        }
    }

    private fun setErrorIfFieldsNotFill(name: String, phone: String): Boolean {
        if (name.isBlank()) {
            tilName.error = "Campo Obrigatório"
            return false
        } else {
            tilName.error = null
        }

        if (phone.isBlank()) {
            tilPhone.error = "Campo Obrigatório"
            return false
        } else {
            tilPhone.error = null
        }

        return true
    }

    private fun createNewContact(name: String, phone: String) {
        val contact = Contact(Random(100L).nextInt(), name, phone)
//        Local
//        DataSourceLocal.createTodo(contact)

//        Remoto
        DataSourceRemote().create(contact, object : ContactCallback {
            override fun onSucesso(todos: Contact?) {
                setResult(Constants.CODE_RESULT_CREATE_SUCCESS)
                finish()
            }

            override fun onFalha(t: Throwable) {
                Toast.makeText(this@CreateContactActivity, "Falha na busca", Toast.LENGTH_SHORT).show()
            }
        })
    }



}