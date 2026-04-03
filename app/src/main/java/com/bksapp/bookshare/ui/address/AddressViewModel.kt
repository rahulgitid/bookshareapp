package com.bksapp.bookshare.ui.address

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bksapp.bookshare.data.repository.AddressRepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ValidAddressMessage(val msg: String){
    Name("Name is not valid"),
    Address("Address is not valid"),
    Country("Country is not valid"),
    State("State is not valid"),
    City("City is not valid"),
    ZipCode("ZipCode is not valid"),
    LandMark("LandMark is not valid")
}
@Immutable
data class AddressUIState(
    val validName: Boolean = false,
    val validAddress: Boolean = false,
    val validCountry: Boolean = false,
    val validState: Boolean = false,
    val validCity: Boolean = false,
    val validZipCode: Boolean = false,
    val validLandMark: Boolean = false,

    val nameMessage: String = "",
    val addressMessage: String = "",
    val countryMessage: String = "",
    val stateMessage: String = "",
    val cityMessage: String = "",
    val zipCodeMessage: String = "",
    val landMarkMessage: String = "",
)



data class Address(
    val name: String ="",
    val address: String="",
    val country: String="",
    val state: String="",
    val city: String="",
    val zipCode: String="",
    val landMark: String="",
    val isDefault:Boolean = false
)
@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class AddressViewModel @Inject constructor(
    private val addressRepo: AddressRepoImpl
) : ViewModel() {
    private val _addressValidation = MutableStateFlow(AddressUIState())
    val addressValidation = _addressValidation.asStateFlow()
    private val _hideSheet = MutableStateFlow(false)
    val hideSheet = _hideSheet.asStateFlow()


val addressState = addressRepo.getNewAddress()
    fun setAddress(
         newAddress:Address
    ): Boolean{
        val addressUIState = AddressUIState(
                validName = newAddress.name.isNotEmpty(),
                nameMessage = if(newAddress.name.isNotEmpty()) "" else ValidAddressMessage.Name.msg,
                validAddress = newAddress.address.isNotEmpty(),
                addressMessage = if(newAddress.address.isNotEmpty()) "" else ValidAddressMessage.Address.msg,
                validCountry = newAddress.country.isNotEmpty(),
                countryMessage = if(newAddress.country.isNotEmpty()) "" else ValidAddressMessage.Country.msg,
                validState = newAddress.state.isNotEmpty(),
                stateMessage = if(newAddress.state.isNotEmpty()) "" else ValidAddressMessage.State.msg,
                validCity = newAddress.city.isNotEmpty(),
                cityMessage = if(newAddress.city.isNotEmpty()) "" else ValidAddressMessage.City.msg,
                validZipCode = newAddress.zipCode.isNotEmpty(),
                zipCodeMessage = if(newAddress.zipCode.isNotEmpty()) "" else ValidAddressMessage.ZipCode.msg,
                validLandMark = newAddress.landMark.isNotEmpty(),
                landMarkMessage = if(newAddress.landMark.isNotEmpty()) "" else ValidAddressMessage.LandMark.msg
            )

        _addressValidation.update {addressUIState}

        if(addressUIState.validName
            && addressUIState.validAddress
            && addressUIState.validCountry
            && addressUIState.validState
            && addressUIState.validCity
            && addressUIState.validZipCode
            && addressUIState.validLandMark){
            viewModelScope.launch {
                if(addressRepo.getNewAddress().value.isEmpty()){
                    addressRepo.setAddress(newAddress.copy(isDefault = true))
                }
                else {
                    addressRepo.setAddress(newAddress)
                }
            }
            return true
        }

    return false
 }

    fun resetAddress(){
        _addressValidation.update {
            AddressUIState()
        }
    }



}