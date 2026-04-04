package com.bksapp.bookshare.ui.address

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldLineLimits.Companion
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddLocation
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Man
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bksapp.bookshare.ui.address.components.AddressItem
import com.bksapp.bookshare.ui.bookdetail.component.Button
import com.bksapp.bookshare.ui.theme.Primary
import com.bksapp.bookshare.utils.TextFieldWithSpinner
import com.bksapp.bookshare.utils.countryList
import com.bksapp.bookshare.utils.indiaStates
import com.bksapp.bookshare.utils.mpCities
import dagger.hilt.android.lifecycle.HiltViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressScreen(addressViewModel: AddressViewModel = hiltViewModel(),onBack:()->Unit){

    var isSheetShow by remember { mutableStateOf(false) }
    val addressValidationState by addressViewModel.addressValidation.collectAsStateWithLifecycle()
    val hideSheet by addressViewModel.hideSheet.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()
    val state = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )

    val addressData by addressViewModel.addressState.collectAsStateWithLifecycle()

    val nameState = rememberTextFieldState()


    val windowInfo = LocalWindowInfo.current
    val sheetHeight = remember{
        val screenHeight = windowInfo.containerDpSize.height
        screenHeight-screenHeight/4
    }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)){
            LazyColumn {
                items(addressData,key={it.id}, contentType = {""}){address->
                    AddressItem({
                        addressViewModel.setCurrentAddress(it)
                        onBack()

                     },address)
                }
            }
            FloatingActionButton(modifier = Modifier
                .align(Alignment.BottomEnd),
                onClick = {
                    isSheetShow = isSheetShow.not()
                }
            ){
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add",tint = Primary)
            }

    }

        AddressBottomSheet({nameState},{scrollState},{state},{sheetHeight},{isSheetShow},addressValidationState,
            {
                addressViewModel.setAddress(it)
            }){
            addressViewModel.resetAddress()
            isSheetShow = isSheetShow.not()
        }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressBottomSheet(
    nameState:()-> TextFieldState,
    scrollState:()-> ScrollState,
    state:()-> SheetState,
    sheetHeight:()-> Dp,
    isSheetShow: ()->Boolean,
    addressValidationState: AddressUIState,
    sendAddress: (Address)-> Boolean,
    onDismiss:()->Unit){




if(isSheetShow()){

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = state(),

    ) {

        Column(
            modifier = Modifier
                .height(sheetHeight())
                .verticalScroll(scrollState())
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AddressUI(
                addressValidationState,
                sendAddress,
                onDismiss)
        }

    }
    }
}

@Composable
fun AddressSpacer(){
    Spacer(modifier = Modifier
        .height(10.dp))
}
@Composable
fun AddressUI(
    uiState: AddressUIState,
    sendAddress: (Address)-> Boolean,
    cancelSheet: ()->Unit){

    val name = rememberTextFieldState()
    val address = rememberTextFieldState()
    val selectCountry = rememberTextFieldState()
    val selectState = rememberTextFieldState()
    val selectCity = rememberTextFieldState()
    val zipCode = rememberTextFieldState()
    val landMark = rememberTextFieldState()
    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)) {
            Icon(imageVector = Icons.Filled.AddLocation, contentDescription = "", tint = Primary)
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Add New Address", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
        }
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            state = name,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = {Text(text = "Name")},
            isError = uiState.validName.not(),
            supportingText = {Text(text = uiState.nameMessage)},
            leadingIcon = {Icon(Icons.Outlined.Man, contentDescription = "Name", tint = Primary)}
        )
        AddressSpacer()
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            state = address,
            label = {Text(text = "Shipping Address")},
            isError = uiState.validAddress.not(),
            supportingText = {Text(text =uiState.addressMessage)},
            lineLimits = TextFieldLineLimits.MultiLine(1,3),
            leadingIcon = {Icon(Icons.Outlined.Home, contentDescription = "Address", tint = Primary)}
        )
        AddressSpacer()
        TextFieldWithSpinner(uiState.validCountry.not(),
            uiState.countryMessage,
            "Country",countryList,
            selectCountry)
        AddressSpacer()
        TextFieldWithSpinner(uiState.validState.not(),
            uiState.stateMessage,
            "State",indiaStates,selectState)
        AddressSpacer()
        TextFieldWithSpinner(uiState.validCity,
            uiState.cityMessage,
            "City",mpCities,selectCity)
        AddressSpacer()
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            state = zipCode,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            lineLimits = TextFieldLineLimits.SingleLine,
            isError = uiState.validZipCode.not(),
            supportingText = {Text(text =uiState.zipCodeMessage)},
            label = {Text(text = "ZIP Code")},
            leadingIcon = {Icon(Icons.Filled.Mail, contentDescription = "Name", tint = Primary)}
        )
        AddressSpacer()
        OutlinedTextField(modifier = Modifier
            .fillMaxWidth(),
            state = landMark,
            lineLimits = TextFieldLineLimits.SingleLine,
            label = {Text(text = "LandMark")},
            isError = uiState.validLandMark.not(),
            supportingText = {Text(text =uiState.landMarkMessage)},
            leadingIcon = {Icon(Icons.Outlined.LocationOn, contentDescription = "Name", tint = Primary)}
        )
        AddressSpacer()
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){
            Button(
                Modifier.weight(0.45f),
                text = "cancel",
               clickAction =  cancelSheet)
            Spacer(modifier = Modifier.weight(0.1f))
            Button(
                Modifier.weight(0.45f),
                text = "Save Address",
                clickAction =  {
                    if(sendAddress(Address(
                            name=name.text as String,
                            address = address.text as String,
                        country = selectCountry.text as String,
                        state = selectState.text as String,
                        city = selectCity.text as String,
                        zipCode = zipCode.text as String,
                        landMark = landMark.text as String
                    ))){
                        cancelSheet()
                    }
                })
        }
    }
}