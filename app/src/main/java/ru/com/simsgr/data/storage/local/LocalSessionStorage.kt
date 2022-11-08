package ru.com.simsgr.data.storage.local

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import ru.com.simsgr.data.storage.interfaces.ISessionStorage
import ru.com.simsgr.domain.models.CurrentUser

const val SHARED_PREFERENCES_NAME: String = "main_prefs"
const val SHARED_PREFERENCES_USER: String = "user"

class LocalSessionStorage(context: Context): ISessionStorage {

    private val LOG_TAG: String = javaClass.name


    private val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()


    override suspend fun getCurrentUser(): CurrentUser?{
        val json : String? = sharedPreferences.getString(SHARED_PREFERENCES_USER, "")
        if (json==null || json.isEmpty()){
            Log.d(LOG_TAG, "Authorised user not found [${json}]")

            return null
        }
        Log.d(LOG_TAG, "Authorised has been found [${json}]")

        return gson.fromJson(json, CurrentUser::class.java)
    }

    override suspend fun saveUser(user: CurrentUser): Boolean {
        val success = sharedPreferences.edit().putString(SHARED_PREFERENCES_USER, gson.toJson(user)).commit()
        Log.d(LOG_TAG, "Save user has been ${if (success) "successes" else "thrown"}")
        return success
    }

    @SuppressLint("CommitPrefEdits")
    override suspend fun delCurrentUser() {
        sharedPreferences.edit().remove(SHARED_PREFERENCES_USER).apply()
    }


}