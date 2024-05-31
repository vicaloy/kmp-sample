package com.valoy.events.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valoy.events.domain.model.User
import com.valoy.events.domain.repository.UserRepository
import com.valoy.events.infra.repository.UserNetworkRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel(private val userRepository: UserRepository = UserNetworkRepository()) :
    ViewModel() {

    private val _state = MutableStateFlow(UiState())
    val state = _state.asStateFlow()

    init {
        getAllUsers()
    }

   private fun getAllUsers() {
        viewModelScope.launch {
            try {
                userRepository.getAll().collect { users ->
                    _state.update { it.copy(users = users) }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.d("UsersViewModel", "Error: ${e.message}")
            }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val users: List<User> = emptyList()
    )
}