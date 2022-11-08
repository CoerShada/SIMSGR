package ru.com.simsgr.presentation.adapters
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.com.simsgr.R
import ru.com.simsgr.databinding.CardMessageCurrentUserBinding
import ru.com.simsgr.databinding.CardMessageOtherUserBinding
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.User


class MessagesRVAdapter(val otherUser: User): RecyclerView.Adapter<MessagesRVAdapter.MessageHolder>() {

    var messages: List<Message> = listOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value){
        field = value
        notifyDataSetChanged()
    }


    class MessageHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(message: Message, user: User){

           if (message.from==user.id){
               val binding = CardMessageOtherUserBinding.bind(itemView)
               binding.cMessageOtherUserName.text = user.login
               binding.cMessageOtherUserMessage.text = message.message
            }
            else{
               val binding = CardMessageCurrentUserBinding.bind(itemView)
               binding.cMessageCurrentUserMessage.text = message.message

            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder {
        val view = if(viewType==0)
            LayoutInflater.from(parent.context).inflate(R.layout.card_message_current_user, parent, false)
        else
            LayoutInflater.from(parent.context).inflate(R.layout.card_message_other_user, parent, false)
        return MessageHolder(view)
    }

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.bind(messages[position], otherUser)
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].from!=otherUser.id) {
            0
        }
        else {
            1
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }
}