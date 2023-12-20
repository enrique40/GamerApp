package com.example.gamerapp.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gamerapp.R
import com.example.gamerapp.presentation.MainActivity
import com.example.gamerapp.presentation.components.DefaultButtom
import com.example.gamerapp.presentation.navigation.DetailsScreen
import com.example.gamerapp.presentation.screens.profile.ProfileViewModel
import com.example.gamerapp.presentation.screens.profile.UserPreferencesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel(), darkMode: Boolean) {

    val activity = LocalContext.current as? Activity
    var isDarkThemeIcon = remember { mutableStateOf(false) }
   /* LaunchedEffect(Unit) {
        userPreferencesRepository.dataStore.data.map { settings ->
            settings[booleanPreferencesKey("themaa")] ?: false
        }.collect { dataFromDataStore ->
           // darkMode = dataFromDataStore
            // Usa el valor de dataFromDataStore aquí
            Log.e("TAG", "onCreate -$dataFromDataStore")
        }
    }*/

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "imagen controles",
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Bienvenido",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(55.dp))
                if (viewModel.userData.image != "") {
                    AsyncImage(
                        modifier =
                        Modifier
                            .size(115.dp)
                            .clip(CircleShape),
                        model = viewModel.userData.image,
                        contentDescription = "User image",
                        contentScale = ContentScale.Crop
                    )
                }else {
                    Image(
                        modifier = Modifier.size(115.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "imagen de usario"
                    )
                }
            }
        }
        if (isDarkThemeIcon.value){
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.End)
                    .padding(end = 10.dp, top = 10.dp)
                    .clickable {
                        Log.e("TAG", "ProfileContent 1 ${isDarkThemeIcon.value} ")
                        isDarkThemeIcon.value = !isDarkThemeIcon.value
                       // darkMode = !darkMode
                        CoroutineScope(IO).launch {
                            viewModel.saveToDataStore(isDarkThemeIcon.value)
                        }

                    }
                    .rotate(46f),
                painter = painterResource(id = R.drawable.baseline_mode_night_24),
                contentDescription = "",
            )
        }else {
            Image(modifier = Modifier
                .size(40.dp)
                .align(Alignment.End)
                .padding(end = 10.dp, top = 10.dp)
                .clickable {
                    isDarkThemeIcon.value = !isDarkThemeIcon.value
                    //darkMode = !darkMode
                    CoroutineScope(IO).launch {
                        viewModel.saveToDataStore(isDarkThemeIcon.value)
                    }
                },
                painter = painterResource(id = R.drawable.baseline_light_mode_24),
                contentDescription = "",
            )
        }

        Text(
            text = viewModel.userData.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(20.dp))
        DefaultButtom(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            color = Color.White,
            icon = Icons.Default.Edit,
            textColor = Color.Black,
            colorIcon = Color.Black,
            onClick = {
                navController.navigate(
                    route = DetailsScreen.ProfileUpdate.passUser(viewModel.userData.toJson())
                )
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        DefaultButtom(
            modifier = Modifier.width(250.dp),
            text = "Cerrrar Seccion",
            icon = Icons.Default.Close,
            onClick = {
                viewModel.logout()
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            }
        )
    }
}