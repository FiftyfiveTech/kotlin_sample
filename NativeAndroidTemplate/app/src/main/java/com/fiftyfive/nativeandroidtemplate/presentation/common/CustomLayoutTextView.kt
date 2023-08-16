package com.fiftyfive.nativeandroidtemplate.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CustomLayoutTextView(text1:String, text2: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight()
            .height(60.dp)
    ) {
        Text(
            text = text1,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)
                .padding(8.dp),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text2,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(8.dp)
        )
    }

}