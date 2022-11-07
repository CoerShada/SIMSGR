package ru.com.simsgr.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.com.simsgr.R
import ru.com.simsgr.databinding.UserCardBinding
import ru.com.simsgr.domain.models.OtherUser

class UsersRVAdapter(): RecyclerView.Adapter<UsersRVAdapter.UserHolder>() {

    var users: List<OtherUser> = listOf()

    class UserHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        private val binding = UserCardBinding.bind(view)

        init {
            view.setOnClickListener(this)
        }

        fun bind(user: OtherUser){

            binding.cUserTVName.text = user.login
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int {
        return users.size
    }
}