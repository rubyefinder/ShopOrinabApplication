package com.orinab.shoporinab.utils.extention

import android.content.Context
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.local.date.DateModel
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.MIN_YEAR_DATE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.PERSIAN_DATE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.PERSIAN_DAY
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.PERSIAN_MONT
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener


fun initFromPersonDatePicker(context: Context,onClickListenerAny: OnClickListenerAny){
    val typeface = ResourcesCompat.getFont(context, R.font.iran_sans)
    val picker = PersianDatePickerDialog(context)
        .setPositiveButtonString(context.resources.getString(R.string.ok_date))
        .setNegativeButton(context.resources.getString(R.string.no_date))
        .setTodayButton(context.resources.getString(R.string.today_date))
        .setTodayButtonVisible(true)
        .setMinYear(MIN_YEAR_DATE)
        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
        .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
        .setMaxDay(PersianDatePickerDialog.THIS_DAY)
        .setInitDate(PERSIAN_DATE, PERSIAN_MONT, PERSIAN_DAY)
        .setActionTextColor(Color.GRAY)
        .setTypeFace(typeface)
        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
        .setShowInBottomSheet(true)
        .setListener(object : PersianPickerListener {
            override fun onDateSelected(persianPickerDate: PersianPickerDate) {
              val result=persianPickerDate.persianYear.toString() + "/" + persianPickerDate.persianMonth + "/" + persianPickerDate.persianDay
                onClickListenerAny.onClickListener(DateModel(persianPickerDate.persianYear,persianPickerDate.persianDay,persianPickerDate.persianMonth,result))
            }

            override fun onDismissed() {}
        })
    picker.show()
}

fun initUpToDatePersonDatePicker(context: Context,dateModel: DateModel,onClickListenerAny: OnClickListenerAny){
    val typeface = ResourcesCompat.getFont(context, R.font.iran_sans)
    val picker = PersianDatePickerDialog(context)
        .setPositiveButtonString(context.resources.getString(R.string.ok_date))
        .setNegativeButton(context.resources.getString(R.string.no_date))
        .setTodayButton(context.resources.getString(R.string.today_date))
        .setTodayButtonVisible(true)
        .setMinYear(dateModel.date)
        .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
        .setMaxMonth(PersianDatePickerDialog.THIS_MONTH)
        .setMaxDay(PersianDatePickerDialog.THIS_DAY)
        .setInitDate(dateModel.date, dateModel.mont, dateModel.day)
        .setActionTextColor(Color.GRAY)
        .setTypeFace(typeface)
        .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
        .setShowInBottomSheet(true)
        .setListener(object : PersianPickerListener {
            override fun onDateSelected(persianPickerDate: PersianPickerDate) {
                val result=persianPickerDate.persianYear.toString() + "/" + persianPickerDate.persianMonth + "/" + persianPickerDate.persianDay
                onClickListenerAny.onClickListener(DateModel(persianPickerDate.persianYear,persianPickerDate.persianDay,persianPickerDate.persianMonth,result))
            }

            override fun onDismissed() {}
        })
    picker.show()
}