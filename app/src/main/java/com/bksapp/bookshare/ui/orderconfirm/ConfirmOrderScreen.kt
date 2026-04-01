package com.bksapp.bookshare.ui.orderconfirm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.ui.cart.CartViewModel
import com.bksapp.bookshare.ui.orderconfirm.component.DeliveryTo
import com.bksapp.bookshare.ui.orderconfirm.component.ItemSpacer
import com.bksapp.bookshare.ui.orderconfirm.component.OrderConfirmBottom
import com.bksapp.bookshare.ui.orderconfirm.component.OrderItem
import com.bksapp.bookshare.ui.orderconfirm.component.PaymentMethod
import com.bksapp.bookshare.ui.orderconfirm.component.PriceDetails
import com.bksapp.bookshare.utils.BookTopBar

@Composable
fun ConfirmOrderScreen(cartViewModel: CartViewModel = hiltViewModel(),
        goToAddress: ()->Unit,
        goback:()->Unit){
    val cartUIState by cartViewModel.cartState.collectAsStateWithLifecycle()
    Column {
        BookTopBar(
            title = "Confirm Order",
            backButton = true,
            backAction = goback
        )
        LazyColumn(
            modifier = Modifier
                .padding(start = 14.dp, end = 14.dp)
        ) {
            item { DeliveryTo(goToAddress) }
            item { ItemSpacer() }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                ) {

                    Text(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                        text = "Order Summary",
                        style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold
                    )
                }
            }
            itemsIndexed(
                cartUIState.itemsList,
                key = { _, book -> book.id },
                contentType = { _, _ -> "order_summary_item" }) { index, book ->
                OrderItem(book)
                if (index < cartUIState.itemsList.lastIndex) {
                    HorizontalDivider(modifier = Modifier.padding(start = 10.dp, end = 10.dp))
                }
            }
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                ) {}

            }
            item { ItemSpacer() }
            item { PaymentMethod() }
            item { ItemSpacer() }
            item { PriceDetails({cartUIState.total},{cartUIState.shipping}) }
            item { ItemSpacer() }
            item { OrderConfirmBottom({cartUIState.total},{cartUIState.shipping}) }
        }
    }

}