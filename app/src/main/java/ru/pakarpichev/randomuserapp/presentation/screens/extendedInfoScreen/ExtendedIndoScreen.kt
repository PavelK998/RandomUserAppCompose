package ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.pakarpichev.randomuserapp.ui.theme.Purple40

@Composable
fun ExtendedIndoScreen(
    navController: NavController,
    modelId: String?,
    viewModel: ExtendedInfoScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value
    if (!state.isLoading && state.userInfo != null) {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Purple40
                ){
                    Icon(
                        modifier = Modifier
                            .clickable {
                            navController.popBackStack()
                        }
                            .padding(start = 5.dp),
                        imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null
                    )
                }
                     },
            modifier = Modifier
                .fillMaxSize()
        ) { padding ->
            ExtendedInfoElement(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                model = state.userInfo,
                onPhoneTextClick = viewModel::onEvent,
                onEmailTextClick = viewModel::onEvent,
                onLocationTextClick = viewModel::onEvent,
            )
        }
    }
}
