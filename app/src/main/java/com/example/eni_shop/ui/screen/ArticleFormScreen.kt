package com.example.eni_shop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eni_shop.ui.common.EniShopTextField
import com.example.eni_shop.ui.common.EniShopTopBar

@Composable
fun ArticleFormScreen(
    navigationIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Scaffold(
        topBar = { EniShopTopBar(navigationIcon = navigationIcon) }) {
        ArticleForm(modifier = Modifier.padding(it))
    }
}

@Composable
fun ArticleForm(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    var title by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EniShopTextField(
            label = "Titre", value = title, onValueChange = {
                title = it
            })
        EniShopTextField(
            label = "Description", value = description, onValueChange = {
                description = it
            })
        EniShopTextField(
            label = "Prix", value = price, onValueChange = {
                price = it
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        DropDownCategories()
        OutlinedButton(onClick = {
            Toast.makeText(context, "$title est enregistré !", Toast.LENGTH_SHORT).show()
        }) {
            Text(text = "Enregistrer")
        }
    }
}

@Composable
fun DropDownCategories(modifier: Modifier = Modifier) {

    val categories = listOf("electronics", "jewelery", "men's clothing", "women's clothing")
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedCategory by rememberSaveable { mutableStateOf("") }

    Column {
        EniShopTextField(
            label = "Catégorie",
            enabled = false,
            value = selectedCategory,
            modifier = Modifier.clickable {
                expanded = !expanded
            },
            placeholder = { Text(text = "Choisir une catégorie") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Drop Down // null"
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                DropdownMenuItem(text = {
                    Text(
                        text = category.replaceFirstChar {
                            it.uppercase()
                        }

                    )
                }, onClick = {
                    selectedCategory = category.replaceFirstChar {
                        it.uppercase()
                    }
                    expanded = false
                })
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    //ArticleFormScreen()
}


