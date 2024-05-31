package com.valoy.events.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.valoy.events.domain.model.User

@Composable
fun UsersScreen(viewModel: UsersViewModel = UsersViewModel(), modifier: Modifier = Modifier) {
    val uiState by viewModel.state.collectAsStateWithLifecycle(lifecycleOwner = androidx.compose.ui.platform.LocalLifecycleOwner.current)

    LazyColumn(modifier = modifier) {
        items(uiState.users.size) { index ->
            UserItem(uiState.users[index])
        }
    }
}

@Composable
fun UserItem(user: User) {
    Text(text = user.name)
}