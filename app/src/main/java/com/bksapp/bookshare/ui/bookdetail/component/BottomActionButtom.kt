package com.bksapp.bookshare.ui.bookdetail.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bksapp.bookshare.ui.theme.BackgroundDark

@Composable
fun BottomButton(addToCart: () -> Unit){
    Button("Add to cart",
        btColor = BackgroundDark,
        addToCart)

    Button("Buy Now",
        btColor = MaterialTheme.colorScheme.primary,
        {})

}

@Composable
fun Button(text:String,
           btColor: Color = MaterialTheme.colorScheme.primary,
           clickAction: ()->Unit){

    FilledTonalButton(onClick = clickAction,
        modifier = Modifier
            .width(140.dp)
            .height(42.dp),
        contentPadding = PaddingValues(4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = btColor
        ),
        shape = RoundedCornerShape(8.dp),
        elevation = ButtonDefaults.buttonElevation(20.dp)
    ){
        Text(text = text)
    }
}