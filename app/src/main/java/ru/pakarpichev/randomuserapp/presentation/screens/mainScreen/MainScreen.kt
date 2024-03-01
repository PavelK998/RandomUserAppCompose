package ru.pakarpichev.randomuserapp.presentation.screens.mainScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TopAppBar
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import ru.pakarpichev.randomuserapp.R
import ru.pakarpichev.randomuserapp.ui.theme.Purple40


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.collectAsState().value
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = {
            viewModel.onEvent(MainScreenEvent.RefreshResults)
        },
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar (
                backgroundColor = Purple40
            ){
                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    ) { padding ->
        if (state.usersList.isNullOrEmpty()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                if (state.error != null) {
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(onClick = {
                        viewModel.onEvent(MainScreenEvent.RefreshResults)
                    }) {
                        Text(text = stringResource(id = R.string.refresh))
                    }
                    Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .pullRefresh(pullRefreshState)
                    .padding(padding)
            ) {
                LazyColumn() {
                    itemsIndexed(state.usersList) { _, model ->
                        UserInfoElement(model = model, navController)
                    }
                }
                PullRefreshIndicator(
                    refreshing = state.isLoading,
                    state = pullRefreshState,
                    Modifier.align(Alignment.TopCenter)
                )
            }
        }
    }
}





