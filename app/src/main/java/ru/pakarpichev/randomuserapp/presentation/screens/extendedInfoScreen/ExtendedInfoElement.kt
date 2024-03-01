package ru.pakarpichev.randomuserapp.presentation.screens.extendedInfoScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Map
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList
import ru.pakarpichev.randomuserapp.R
import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExtendedInfoElement(
    modifier: Modifier,
    model: RandomUserModel,
    onPhoneTextClick: (ExtendedScreenEvent.OpenPhone) -> Unit,
    onEmailTextClick: (ExtendedScreenEvent.OpenEmail) -> Unit,
    onLocationTextClick: (ExtendedScreenEvent.OpenMaps) -> Unit
) {
    val listTabItems = listOf(
        TabItem(Icons.Filled.Phone, Icons.Outlined.Phone),
        TabItem(Icons.Filled.Email, Icons.Outlined.Email),
        TabItem(Icons.Filled.CalendarMonth, Icons.Outlined.CalendarMonth),
        TabItem(Icons.Filled.Map, Icons.Outlined.Map),
    ).toImmutableList()
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { listTabItems.size }
    )
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp),
                    model = model.picture,
                    contentDescription = "",
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    text = "${model.nameFirst}  ${model.nameLast}",
                )
            }
        }
        TabRow(
            selectedTabIndex = pagerState.currentPage
        ) {
            listTabItems.forEachIndexed { index, tabItem ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    icon = {
                        if (index == pagerState.currentPage) {
                            Icon(
                                imageVector = tabItem.iconSelected,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = tabItem.iconUnselected,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { index ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (index) {
                    0 -> {
                        Text(
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            text = stringResource(id = R.string.phone_number)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                onPhoneTextClick(ExtendedScreenEvent.OpenPhone(model.phoneNumber))
                            },
                            text = (model.phoneNumber)
                        )
                    }
                    1 -> {
                        Text(
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            text = stringResource(id = R.string.email)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            fontSize = 18.sp,
                            modifier = Modifier.clickable {
                                onEmailTextClick(ExtendedScreenEvent.OpenEmail(model.email))
                            },
                            text = model.email
                        )
                    }
                    2 -> {
                        Text(
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            text = stringResource(id = R.string.date_of_birth)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = model.dateOfBirth
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(text = stringResource(id = R.string.age) + " ${model.age}")
                    }
                    3 -> {
                        Text(
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            text = stringResource(id = R.string.location)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            modifier = Modifier.clickable {
                                onLocationTextClick(
                                    ExtendedScreenEvent.OpenMaps(
                                        model.latitude,
                                        model.longtitude
                                    )
                                )
                            },
                            text = model.country + "\n${model.city}, ${model.nameOfStreet}\n" +
                                    stringResource(id = R.string.postIndex) + " ${model.postcode}",
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

