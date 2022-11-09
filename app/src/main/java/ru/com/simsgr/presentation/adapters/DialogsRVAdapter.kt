package ru.com.simsgr.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.findFragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.com.simsgr.R
import ru.com.simsgr.databinding.CardDialogBinding
import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.presentation.AllDialogsFragment
import ru.com.simsgr.presentation.DialogFragment
import ru.com.simsgr.presentation.MainActivity
import ru.com.simsgr.presentation.UsersFragment

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
            val fragment = v.findFragment<AllDialogsFragment>()
            val dialogs = fragment.viewModel.dialogs.value
            val activity: MainActivity = v.context as MainActivity
            activity.navController.navigate(R.id.action_allDialogsFragment_to_dialogFragment,
                bundleOf(DialogFragment.OTHER_USER_KEY to Gson().toJson(dialogs!![adapterPosition].otherUser) )
            )
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