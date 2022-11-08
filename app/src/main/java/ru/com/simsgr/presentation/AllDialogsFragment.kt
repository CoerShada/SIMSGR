package ru.com.simsgr.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.com.simsgr.R
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.viewmodels.VMFAllDialogs
import ru.com.simsgr.domain.viewmodels.factories.VMFAllDialogsFactory
import ru.com.simsgr.presentation.adapters.DialogsRVAdapter


class AllDialogsFragment : Fragment() {

    lateinit var viewModel: VMFAllDialogs

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragment: View = inflater.inflate(
            R.layout.fragment_all_dialogs,
            container,
            false
        )
        val user: CurrentUser = (activity as MainActivity).viewmodel.user.value!!
        viewModel = ViewModelProvider(this,
            VMFAllDialogsFactory(user, requireContext()))[VMFAllDialogs::class.java]

        init(fragment = fragment)
        setListeners(fragment = fragment)
        onViewModelChanged(fragment = fragment)

        return fragment
    }


    private fun init(fragment: View){
        val rv: RecyclerView = fragment.findViewById(R.id.fAllDialogsRVDialogs)
        val manager = LinearLayoutManager(this.requireContext())
        rv.adapter = DialogsRVAdapter()
        rv.layoutManager = manager

    }

    private fun updateData(){
        viewModel.getUsersDialogs((activity as MainActivity).viewmodel.user.value!!)

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun onViewModelChanged(fragment: View){
        viewModel.dialogs.observe(viewLifecycleOwner){
            if (it!=null) {
                val rv: RecyclerView = fragment.findViewById(R.id.fAllDialogsRVDialogs)

                val adapter = (rv.adapter as DialogsRVAdapter)
                adapter.dialogs = it.toList()
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun setListeners(fragment: View){
        val bSearch = fragment.findViewById<ImageButton>(R.id.fAllDialogsIBSearch)
        bSearch.setOnClickListener {
            updateData()
        }
    }
}