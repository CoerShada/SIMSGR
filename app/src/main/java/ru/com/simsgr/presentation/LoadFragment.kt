package ru.com.simsgr.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.com.simsgr.R
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.viewmodels.VMFLoad
import ru.com.simsgr.domain.viewmodels.factories.VMFLoadFactory


class LoadFragment : Fragment() {

    lateinit var viewmodel: VMFLoad

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_load, container, false)

        val user: CurrentUser = (activity as MainActivity).viewmodel.user.value!!
        val factory = VMFLoadFactory(requireContext(), user)
        viewmodel = ViewModelProvider(this, factory)[VMFLoad::class.java]
        setObserver()
        viewmodel.syncronize(user.token)
        return view
    }


    fun setObserver(){
        viewmodel.complete.observe(viewLifecycleOwner){
            (activity as MainActivity).navController.navigate(R.id.action_loadFragment_to_menuFragment)
        }
    }

}