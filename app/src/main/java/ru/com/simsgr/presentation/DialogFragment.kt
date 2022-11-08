package ru.com.simsgr.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ru.com.simsgr.R
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.models.Message
import ru.com.simsgr.domain.models.User
import ru.com.simsgr.domain.viewmodels.VMFDialog
import ru.com.simsgr.domain.viewmodels.factories.VMFDialogFactory
import ru.com.simsgr.presentation.adapters.MessagesRVAdapter
import java.util.*


class DialogFragment : Fragment() {

    lateinit var viewmodel: VMFDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment =  inflater.inflate(R.layout.fragment_dialog, container, false)
        val serializedUser = requireArguments().getString(OTHER_USER_KEY)
        val user: CurrentUser = (activity as MainActivity).viewmodel.user.value!!
        val factory = VMFDialogFactory(user,
            Gson().fromJson(serializedUser, User::class.java),
            requireContext())
        viewmodel = ViewModelProvider(this, factory)[VMFDialog::class.java]

        init(fragment = fragment)
        setListeners(fragment = fragment)
        onViewModelChanged(fragment = fragment)


        return fragment
    }

    companion object{
        const val OTHER_USER_KEY = "user"
    }

    private fun init(fragment: View){
        val rv: RecyclerView = fragment.findViewById(R.id.fDialogRVMessages)
        val adapterLayout = LinearLayoutManager(this.requireContext())
        adapterLayout.reverseLayout = true
        rv.adapter = MessagesRVAdapter(otherUser = viewmodel.user)
        rv.layoutManager = adapterLayout
        viewmodel.getActualDialog()
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun onViewModelChanged(fragment: View){
        viewmodel.messages.observe(viewLifecycleOwner){
            val rv: RecyclerView = fragment.findViewById(R.id.fDialogRVMessages)
            val adapter = (rv.adapter as MessagesRVAdapter)
            adapter.messages = it
        }

    }

    private fun setListeners(fragment: View){
        val bSend = fragment.findViewById<ImageButton>(R.id.fDialogIBSend)
        val currentUser = (activity as MainActivity).viewmodel.user.value!!
        bSend.setOnClickListener {
            val textView = fragment.findViewById<TextView>(R.id.fDialogETMessage)
            val text = textView.text.toString().trim()
            if (text.isEmpty()) return@setOnClickListener
            val message = Message(id = -1,
                                  message = text,
                                  from = currentUser.id,
                                  to = viewmodel.user.id,
                                  date = Date().time,
                                  delivered = false
            )
            viewmodel.sendMessage(message = message)
            textView.text = ""

        }
    }
}