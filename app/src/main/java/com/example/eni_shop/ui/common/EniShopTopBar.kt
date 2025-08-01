package com.example.eni_shop.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eni_shop.datastore.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EniShopTopBar(
    navigationIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { EniShopTopBarTitle() },
        navigationIcon = navigationIcon,
        actions = { BurgerSettingsMenu() }
    )
}

@Composable
fun NavigationBackIcon(
    onNavigateToBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onNavigateToBack
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Retour vers le passé"
        )
    }
}


@Composable
fun EniShopTopBarTitle(modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "ENI SHOP",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "ENI-SHOP",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 40.sp
        )
    }
}

@Composable
fun BurgerSettingsMenu(modifier: Modifier = Modifier) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    var enabled by rememberSaveable { mutableStateOf(false) }
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        DataStoreManager.isDarkModeEnabled(context).collect {
            enabled = it
        }
    }

    IconButton(
        onClick = {
            expanded = !expanded
        }
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Menu"
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text(text = "Dark Theme") },
            onClick = {},
            trailingIcon = {
                Switch(
                    checked = enabled,
                    onCheckedChange = {
                        coroutine.launch(Dispatchers.IO) {
                            DataStoreManager.setDarkMode(context, it)
                        }
                    }
                )
            }
        )
    }

}


@Preview
@Composable
private fun Preview() {
    EniShopTopBarTitle()
}