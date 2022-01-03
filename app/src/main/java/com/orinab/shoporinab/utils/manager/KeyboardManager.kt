package com.orinab.shoporinab.utils.manager

import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.miguelcatalan.materialsearchview.MaterialSearchView
import com.orinab.shoporinab.utils.application.AppShopOrinab


class KeyboardManager {

    fun hideKeyboard(editText: EditText) {
        val imm =AppShopOrinab.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(editText.windowToken, 0);
    }

    fun hideKeyboard(materialSearchView: MaterialSearchView) {
        val imm =AppShopOrinab.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(materialSearchView.windowToken, 0);
    }

    fun hideKeyboard() {
        val v = AppShopOrinab.activity.currentFocus
        val imm = AppShopOrinab.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (v != null)
            imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun showKeyboard() {
        val v = AppShopOrinab.activity.currentFocus
        val imm = AppShopOrinab.activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        if (v != null)
            imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT)
    }

}