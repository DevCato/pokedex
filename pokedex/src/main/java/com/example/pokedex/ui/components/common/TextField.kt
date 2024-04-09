package com.example.pokedex.ui.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.pokedex.ui.theme.circularShapeBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustom(
    modifier: Modifier = Modifier,
    placeholder: String = "Buscar",
    trailingIcon: ImageVector = Icons.Filled.Search,
    onValueChange: (TextFieldValue) -> Unit = {}
) {

    var textState by remember { mutableStateOf(TextFieldValue()) }

    TextField(
        modifier = modifier.fillMaxWidth(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        value = textState,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = circularShapeBackground
        ),
        trailingIcon = { Icon(trailingIcon, "") },
        label = null,
        singleLine = true,
        placeholder = { Text(text = placeholder) },
        onValueChange = {
            textState = it
            onValueChange(it)
        })
}