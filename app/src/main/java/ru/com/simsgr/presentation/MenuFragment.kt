package ru.com.simsgr.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ru.com.simsgr.R


class MenuFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragment =  inflater.inflate(R.layout.fragment_menu, container, false)
        setListeners(fragment)


        return fragment
    }




    private fun setListeners(fragment: View){

        val bUsers = fragment.findViewById<Button>(R.id.fMenuBUsers)
        val bDialogs = fragment.findViewById<Button>(R.id.fMenuBDialogs)

        bUsers.setOnClickListener {
            (this.activity as MainActivity).navController.navigate(R.id.action_menuFragment_to_usersFragment)
        }
        bDialogs.setOnClickListener {
            (this.activity as MainActivity).navController.navigate(R.id.action_menuFragment_to_allDialogsFragment)
        }

    }


}