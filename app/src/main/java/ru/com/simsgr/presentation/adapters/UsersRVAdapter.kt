package ru.com.simsgr.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.com.simsgr.R
import ru.com.simsgr.databinding.CardUserBinding
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.presentation.DialogFragment
import ru.com.simsgr.presentation.MainActivity
import ru.com.simsgr.presentation.UsersFragment

class UsersRVAdapter(): RecyclerView.Adapter<UsersRVAdapter.UserHolder>() {

    var users: List<User> = listOf()

    class UserHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = CardUserBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        fun bind(user: User){

            binding.cUserTVUserName.text = user.login
        }

        override fun onClick(v: View) {
            val fragment = v.findFragment<UsersFragment>()
            val users = fragment.viewModel.users.value
            val activity: MainActivity = v.context as MainActivity
            activity.navController.navigate(R.id.action_usersFragment_to_dialogFragment,
            bundleOf(DialogFragment.OTHER_USER_KEY to Gson().toJson(users!![adapterPosition]) ))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_user, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}