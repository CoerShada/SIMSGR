package ru.com.simsgr.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.com.simsgr.R
import ru.com.simsgr.domain.viewmodels.VMFUsers
import ru.com.simsgr.domain.viewmodels.factories.VMFUsersFactory
import ru.com.simsgr.presentation.adapters.UsersRVAdapter


class UsersFragment : Fragment() {

    lateinit var viewModel: VMFUsers



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_users, container, false)
        viewModel = ViewModelProvider(this, VMFUsersFactory())[VMFUsers::class.java]
        init(fragment = view)
        setListeners(fragment = view)
        onViewModelChanged(fragment = view)
        return view
    }


    private fun init(fragment: View){
        val rv: RecyclerView = fragment.findViewById(R.id.fUsersRVUsers)
        val manager = LinearLayoutManager(this.requireContext())
        rv.adapter = UsersRVAdapter()
        rv.layoutManager = manager
        updateUsersData(fragment)
    }

    private fun updateUsersData(view: View){
        val tv = view.findViewById<TextView>(R.id.fUsersTVSearch)
        viewModel.getUsers((activity as MainActivity).viewmodel.user.value!!.token, tv.text.trim().toString())

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onViewModelChanged(fragment: View){
        viewModel.users.observe(viewLifecycleOwner){
            if (it!=null) {
                var rv: RecyclerView = fragment.findViewById(R.id.fUsersRVUsers)

                val adapter = (rv.adapter as UsersRVAdapter)
                adapter.users = it
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setListeners(fragment: View){
        val bSearch = fragment.findViewById<ImageButton>(R.id.fUsersIBSerach)
        bSearch.setOnClickListener {
            updateUsersData(view = fragment)
        }
    }
}