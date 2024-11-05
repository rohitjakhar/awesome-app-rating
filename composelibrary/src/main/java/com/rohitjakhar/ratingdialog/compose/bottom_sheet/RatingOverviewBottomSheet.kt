package com.rohitjakhar.ratingdialog.compose.bottom_sheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatingOverviewBottomSheet(modifier: Modifier = Modifier, onDismissRequest: () -> Unit, onSelect: () -> Unit) {
    val context = LocalContext.current
    ModalBottomSheet(onDismissRequest = onDismissRequest, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 4.dp),
        ) {
            Text("Please Give Rating on Store", modifier = Modifier.fillMaxWidth(), style = Typography().headlineMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 6.dp),
                    onClick = onDismissRequest,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("No", modifier = Modifier.padding(4.dp), fontSize = 20.sp)
                }
                Button(
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(horizontal = 6.dp),
                    onClick = onSelect,
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Text("Yes", modifier = Modifier.padding(4.dp), fontSize = 20.sp)
                }

            }
        }
    }

}
