package ru.pakarpichev.randomuserapp.presentation.screens.mainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import ru.pakarpichev.randomuserapp.R
import ru.pakarpichev.randomuserapp.domain.model.RandomUserModel
import ru.pakarpichev.randomuserapp.presentation.navigation.Screens


@Composable
fun UserInfoElement(model: RandomUserModel, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable {
                navController.navigate(Screens.ExtendedInfoScreen.route + "/${model.id}")
            }
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.padding(start = 3.dp),
                model = model.smallPicture,
                contentDescription = "picture of user",
                contentScale = ContentScale.None,
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.width(15.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row (
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        text = "${model.nameFirst} ${model.nameLast}",
                    )
                }
                Text(text = model.country)
                Text(text = stringResource(id = R.string.email) + " " + model.email)
                Text(text = stringResource(id = R.string.phone_number) + " " + model.phoneNumber)
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}