package com.ahmedkenawy.cfhtest.data.local

import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import com.ahmedkenawy.cfhtest.domain.model.User
import com.ahmedkenawy.cfhtest.utils.Constants.AGE
import com.ahmedkenawy.cfhtest.utils.Constants.EMAIL
import com.ahmedkenawy.cfhtest.utils.Constants.FIRST_NAME
import com.ahmedkenawy.cfhtest.utils.Constants.LAST_NAME
import com.ahmedkenawy.cfhtest.utils.Constants.PASSWORD
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreference @Inject constructor(private val preferences: DataStore<Preferences>) {


    suspend fun saveUser(user: User) {
        preferences.edit {
            it[preferencesKey<String>(FIRST_NAME)] = user.firstName
            it[preferencesKey<String>(LAST_NAME)] = user.lastName
            it[preferencesKey<Int>(AGE)] = user.age
            it[preferencesKey<String>(EMAIL)] = user.email
            it[preferencesKey<String>(PASSWORD)] = user.password
        }
    }

    val userInfo = preferences.data.map {
        val firstName = it[preferencesKey<String>(FIRST_NAME)] ?: ""
        val lastName = it[preferencesKey<String>(LAST_NAME)] ?: ""
        val age = it[preferencesKey<Int>(AGE)] ?: -1
        val email = it[preferencesKey<String>(EMAIL)] ?: ""
        val password = it[preferencesKey<String>(PASSWORD)] ?: ""

        User(firstName, lastName, age, email, password)
    }


}