package life.alissonescorcio.listadecontatoscomapi.features.listContacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import life.alissonescorcio.listadecontatoscomapi.R
import life.alissonescorcio.listadecontatoscomapi.model.Contact

class ContactListAdapter(var listener: ClickItemContactListener)
    : RecyclerView.Adapter<ContactListAdapter.ContactAdapterViewHolder>() {

    private var list: List<Contact> = listOf()

    class ContactAdapterViewHolder(itemView: View, var list: List<Contact>, var listener: ClickItemContactListener) :
        RecyclerView.ViewHolder(itemView) {

        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)

        init {
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: Contact){
            tvName.text = contact.name
            tvPhone.text = contact.phone
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size


    fun updateList(listOfContacts: List<Contact>) {
        list = listOfContacts
        notifyDataSetChanged()
    }

}