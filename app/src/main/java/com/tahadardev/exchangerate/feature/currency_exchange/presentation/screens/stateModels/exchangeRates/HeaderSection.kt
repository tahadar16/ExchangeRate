package com.tahadardev.exchangerate.feature.currency_exchange.presentation.screens.stateModels.exchangeRates

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tahadardev.exchangerate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderSection(currencies: MutableList<String>) {
    val currValue by remember { mutableStateOf("") }
    var selectedCurrency by remember { mutableStateOf("USD") }
    var isDropDownExpanded by remember {
        mutableStateOf(true)
    }

    val dropdownIcon = if (isDropDownExpanded) {
        Icons.Default.KeyboardArrowUp
    } else {
        Icons.Default.KeyboardArrowDown
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            placeholder = { Text(stringResource(id = R.string.enter_value)) },
            value = currValue,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .align(Alignment.End)
                .fillMaxWidth(.3f)
        ) {
            OutlinedTextField(
                value = selectedCurrency,
                onValueChange = {},
                enabled = false,
                trailingIcon = {
                    Icon(
                        imageVector = dropdownIcon,
                        contentDescription = ""
                    )
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTrailingIconColor = Color.Black,
                    disabledTextColor = Color.Black,
                    disabledBorderColor = MaterialTheme.colorScheme.outline
                ),
                modifier = Modifier
                    .clickable {
                        isDropDownExpanded = !isDropDownExpanded
                    }
            )
            DropdownMenu(
                expanded = isDropDownExpanded,
                onDismissRequest = { isDropDownExpanded = false }) {
                for (currency in currencies) {
                    DropdownMenuItem(
                        text = {
                            Text(
                                currency,
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        },
                        onClick = {
                            selectedCurrency = currency
                            isDropDownExpanded = false
                        }, modifier = Modifier.background(Color.White)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderSectionPrev() {
    HeaderSection(mutableListOf("USD", "RS", "YN", "SRY"))
}