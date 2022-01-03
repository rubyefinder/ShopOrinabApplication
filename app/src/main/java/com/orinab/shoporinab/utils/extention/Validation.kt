package com.orinab.shoporinab.utils.extention

import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import com.orinab.shoporinab.R
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.START_PHONE_NUMBER_VALIDATION


fun isValidPhoneNumber(textInputLayout: TextInputLayout):Boolean {
    val strPhoneNumber= textInputLayout.editText!!.text.toString()
    if (strPhoneNumber.isEmpty()){
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = AppShopOrinab.context.resources.getString(R.string.empty_phone_number)
        return false
    }else{
        return when (strPhoneNumber.substring(0,2)) {
            START_PHONE_NUMBER_VALIDATION -> {
                textInputLayout.isErrorEnabled = false
                true
            }
            else -> {
                textInputLayout.isErrorEnabled = true
                textInputLayout.error = AppShopOrinab.context.resources.getString(R.string.not_validate_phone_number)
                false
            }
        }
    }
}

fun isValidPhoneNumberTextWatcher(textInputLayout: TextInputLayout):Boolean {
    val strPhoneNumber= textInputLayout.editText!!.text.toString()
    if (strPhoneNumber.isEmpty()){

        return false
    }else{
        if (strPhoneNumber.length>3)
        return when (strPhoneNumber.substring(0,2)) {
            START_PHONE_NUMBER_VALIDATION -> {
                true
            }
            else -> {
                false
            }
        }
    }
    return false
}

fun isValidPay(textInputLayout: TextInputLayout):Boolean {
    val strPhoneNumber= textInputLayout.editText!!.text.toString()
    if (strPhoneNumber.isEmpty()){
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = AppShopOrinab.context.resources.getString(R.string.not_validate_pay)
    }else{
        return true
    }
    return false
}



fun isValidPasswordTextWatcher(textInputLayout: TextInputLayout):Boolean {
    val strPassword= textInputLayout.editText!!.text.toString().length
    val maxLength= 6
    return strPassword >= maxLength
}

fun isValidNationalCodeTextWatcher(textInputLayout: TextInputLayout):Boolean {
    val strNationalCode= textInputLayout.editText!!.text.toString().length
    val maxLength= 10
    return strNationalCode == maxLength
}

fun isValidEmptyTextWatcher(textInputLayout: TextInputLayout):Boolean {
    val strTextInputLayout= textInputLayout.editText!!.text.toString()
    return strTextInputLayout.isNotEmpty()
}

fun isValidEmptyTextWatcher(editText: EditText):Boolean {
    val strTextInputLayout= editText.text.toString()
    return strTextInputLayout.isNotEmpty()
}