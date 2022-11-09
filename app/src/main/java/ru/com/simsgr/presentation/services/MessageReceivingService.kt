package ru.com.simsgr.presentation.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*


import ru.com.simsgr.data.repositories.MessagesRepository
import ru.com.simsgr.data.repositories.SessionRepository
import ru.com.simsgr.data.storage.local.LocalMessageStorage
import ru.com.simsgr.data.storage.local.LocalSessionStorage
import ru.com.simsgr.data.storage.remote.RemoteMessageStorage
import ru.com.simsgr.domain.models.CurrentUser
import ru.com.simsgr.domain.usecases.*


class MessageReceivingService : Service() {

    private lateinit var user : CurrentUser

    private lateinit var uCGetCurrentUser : UCGetCurrentUser
    private lateinit var uCSyncNewMessages: UCSyncNewMessages

    private val viewModelJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + viewModelJob)



    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.i(this::class.java.name, "Service has been started")

        Thread {
            while (true) {
                reInit()
                Thread.sleep(600000)
            }
        }.start()

        Thread {
            while (true) {
                Thread.sleep(500)
                syncData()
            }
        }.start()

        return START_STICKY
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i(this::class.java.name, "Service has been destroyed")

    }



    private fun syncData() = serviceScope.launch{
        uCSyncNewMessages.execute()
        Log.i(this::class.java.name, "Service sync new messages")

    }

    private fun reInit() = serviceScope.launch{

        val sessionRepository = SessionRepository(LocalSessionStorage(this@MessageReceivingService))
        uCGetCurrentUser = UCGetCurrentUser(sessionRepository)
        user = uCGetCurrentUser.execute()!!


        val messageRepository =  MessagesRepository(
            remoteMessageStorage = RemoteMessageStorage(uCGetCurrentUser.execute()!!),
            localMessageStorage = LocalMessageStorage(this@MessageReceivingService, user)
        )
        uCSyncNewMessages = UCSyncNewMessages(messageRepository)


        Log.i(this::class.java.name, "Service init use-cases")

    }

}