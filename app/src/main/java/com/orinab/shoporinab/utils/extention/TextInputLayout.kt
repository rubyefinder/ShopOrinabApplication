package com.orinab.shoporinab.utils.extention

import android.view.View
import com.google.android.material.internal.CheckableImageButton

import android.view.ViewGroup


fun findTogglePasswordButton(viewGroup: ViewGroup): View? {
    val childCount = viewGroup.childCount
    for (ind in 0 until childCount) {
        val child: View = viewGroup.getChildAt(ind)
        if (child is ViewGroup) {
            val togglePasswordButton: View? = findTogglePasswordButton(child as ViewGroup)
            if (togglePasswordButton != null) {
                return togglePasswordButton
            }
        } else if (child is CheckableImageButton) {
            return child
        }
    }
    return null
}