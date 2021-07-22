package life.alissonescorcio.listadecontatoscomapi.features.detailContact

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detail_contact.*
import life.alissonescorcio.listadecontatoscomapi.R
import life.alissonescorcio.listadecontatoscomapi.data.ContactCallback
import life.alissonescorcio.listadecontatoscomapi.data.DataSourceRemote
import life.alissonescorcio.listadecontatoscomapi.databinding.ActivityDetailContactBinding
import life.alissonescorcio.listadecontatoscomapi.model.Contact
import life.alissonescorcio.listadecontatoscomapi.utils.Constants

class DetailContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailContactBinding

    private lateinit var contact: Contact

    private var positionOfContact: Int? = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailContactBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val toolbar = binding.toolbar
        toolbar.title = "Detalhes do Contato"
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        positionOfContact = intent.extras?.getInt(Constants.KEY_EXTRA_TODO_INDEX)
        intent.extras?.getInt(Constants.KEY_EXTRA_TODO_ID)?.let { todoId ->
            showDataFromDataSource(todoId)
        }

        binding.btRemove.setOnClickListener {
            removeContact(contact, positionOfContact)
        }
    }

    private fun removeContact(contact: Contact, position: Int?) {
//        Local
//        DataSourceLocal.removeTodo(todo)

//        Remoto
        DataSourceRemote().remove(contact.id, object : ContactCallback {
            override fun onSucesso(todos: Contact?) {
                val data = Intent()
                data.putExtra(Constants.KEY_EXTRA_TODO_INDEX, position)
                setResult(Constants.CODE_RESULT_REMOVE_SUCCESS, data)
                finish()
            }

            override fun onFalha(t: Throwable) {
                Toast.makeText(this@DetailContactActivity, "Falha na remoção", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun showDataFromDataSource(idContact: Int) {
//        Local
//        todo = DataSourceLocal.getTodoById(idTodo)

//        Remoto
        DataSourceRemote().getById(idContact, object : ContactCallback {
            override fun onSucesso(contacts: Contact?) {
                contacts?.let {
                    contact = contacts

                    binding.apply {
                        tvHeader.text =
                            String.format(getString(R.string.detail_contact_tv_header_text, contact.name, contact.id))
                        tvNameValue.text = contact.name
                        tvPhoneValue.text = contact.phone
                    }
                }
            }

            override fun onFalha(t: Throwable) {
                Log.w("Falha", "Falhou na busca")
                Toast.makeText(this@DetailContactActivity, "Falha na busca", Toast.LENGTH_SHORT).show()
            }
        })
    }
}