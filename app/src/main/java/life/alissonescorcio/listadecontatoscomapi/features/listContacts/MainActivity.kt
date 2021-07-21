package life.alissonescorcio.listadecontatoscomapi.features.listContacts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import life.alissonescorcio.listadecontatoscomapi.data.DataSourceRemote
import life.alissonescorcio.listadecontatoscomapi.data.ContactsCallback
import life.alissonescorcio.listadecontatoscomapi.R
import life.alissonescorcio.listadecontatoscomapi.features.addContact.CreateContactActivity
import life.alissonescorcio.listadecontatoscomapi.model.Contact
import life.alissonescorcio.listadecontatoscomapi.utils.Constants

class MainActivity : AppCompatActivity(), ClickItemContactListener {

    private val rvList: RecyclerView by lazy {
        findViewById<RecyclerView>(R.id.rv_list)
    }

    private val adapter = ContactListAdapter(this)

    //private lateinit var contactListAdapter: ContactListAdapter

    private val createActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) { result ->

        when(result.resultCode) {
            Constants.CODE_RESULT_CREATE_SUCCESS -> {
                updateList()
            }
            Constants.CODE_RESULT_REMOVE_SUCCESS -> {
                result.data?.getIntExtra(Constants.KEY_EXTRA_TODO_INDEX, 0)?.let {
                    updateList()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateList()
        bindViews()

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateContactActivity::class.java)
            createActivityLauncher.launch(intent)
        }

//        rvList.apply {
//            adapter = contactListAdapter
//            layoutManager = LinearLayoutManager(this@MainActivity)
//
//            updateList()
//        }
    }

    private fun fetchListContact(){
        //val list = arrayListOf()

    }

    private fun updateList() {
//            Local
//            todoListAdapter.updateList(DataSourceLocal.getAllTodos())

//            Remoto
        DataSourceRemote().getAll(object : ContactsCallback {
            override fun onSucesso(contacts: List<Contact>?) {
                contacts?.let {
                    adapter.updateList(it)
                }
            }
            override fun onFalha(t: Throwable) {
                Log.e("Errou -> ", t.message!!)
                Toast.makeText(this@MainActivity, "Falha na criação", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun bindViews(){
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)

        //val list = getListContacts()
        //adapter.updateList(list)
    }

    private fun detailTodo(todoId: Int, position: Int){
//        val intent = Intent(this, DetailTodoActivity::class.java)
//        intent.putExtra(Constants.KEY_EXTRA_TODO_ID, todoId)
//        intent.putExtra(Constants.KEY_EXTRA_TODO_INDEX, position)
//        createActivityLauncher.launch(intent)
    }


    override fun clickItemContact(contact: Contact) {
//        val intent = Intent(this, ContactDetail::class.java)
//
//        intent.putExtra(ContactDetail.EXTRA_CONTACT, contact)
//        startActivity(intent)
        Toast.makeText(this, "Clicou", Toast.LENGTH_SHORT).show()
    }
}