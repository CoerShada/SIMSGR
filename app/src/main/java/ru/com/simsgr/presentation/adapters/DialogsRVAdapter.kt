package ru.com.simsgr.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.com.simsgr.R
import ru.com.simsgr.databinding.CardDialogBinding
import ru.com.simsgr.domain.models.Dialog

class DialogsRVAdapter(): RecyclerView.Adapter<DialogsRVAdapter.DialogsHolder>() {

    var dialogs: List<Dialog> = listOf()

    class DialogsHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        val binding = CardDialogBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        fun bind(dialog: Dialog){
            binding.cDialogTVUserName.text = dialog.otherUser.login
            binding.cDialogTVLastMessage.text = dialog.messages.last().message
        }

        override fun onClick(v: View) {
            /*val activity: RisksActivity = v.context as RisksActivity
            val bundle = Bundle()
            bundle.putInt("index", activity.risks[adapterPosition].id)
            val fragment = SettingUpRiskFragment()
            fragment.arguments = bundle
            activity.replaceFragment(fragment)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DialogsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_dialog, parent, false)
        return DialogsHolder(view)
    }

    override fun onBindViewHolder(holder: DialogsHolder, position: Int) {
        holder.bind(dialogs[position])
    }

    override fun getItemCount(): Int {
        return dialogs.size
    }
}