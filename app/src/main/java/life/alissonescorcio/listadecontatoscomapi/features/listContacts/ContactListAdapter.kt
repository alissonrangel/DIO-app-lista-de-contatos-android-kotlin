package life.alissonescorcio.listadecontatoscomapi.features.listContacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import life.alissonescorcio.listadecontatoscomapi.R
import life.alissonescorcio.listadecontatoscomapi.databinding.ContactItemBinding
import life.alissonescorcio.listadecontatoscomapi.model.Contact

class ContactListAdapter(
    //var listener: ClickItemContactListener
    private val onClick: (Contact, Int) -> Unit
    )
    : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    private var list: List<Contact> = listOf()

    inner class ViewHolder(private val binding: ContactItemBinding, val onClick: (Contact, Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact, position: Int){
            binding.root.setOnClickListener {
                onClick(contact, position)
            }

            binding.apply {
                tvName.text = contact.name
                tvPhone.text = contact.phone
            }
        }
    }
    /*
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
    */
    /*
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ContactAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: ContactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
    */

    fun updateList(listOfContacts: List<Contact>) {
        list = listOfContacts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = list[position]
        holder.bind(contact, position)
    }

    override fun getItemCount() = list.size


}