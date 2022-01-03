package com.orinab.shoporinab.utils.extention

import android.os.Build
import android.text.Html
import android.widget.TextView

fun initTextHtml(textView: TextView, html:String){
    textView.text = when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        else -> Html.fromHtml(html)
    }
}