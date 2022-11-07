package ru.com.simsgr.domain.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.com.simsgr.domain.models.Dialog
import ru.com.simsgr.domain.repositories.IMessagesRepository

class VMFAllDialogsFragment(messagesRepository : IMessagesRepository): ViewModel() {


    var dialogs = MutableLiveData<List<Dialog>>()

}