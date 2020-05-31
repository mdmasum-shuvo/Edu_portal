/*
 * *
 *  * Created by Md Masum Talukder on 5/16/20 2:53 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 5/16/20 2:53 AM
 *
 */

package com.masum.edu_portal.utils

import com.masum.edu_portal.common.Constant
import java.text.SimpleDateFormat

object DateTimeUtils {


    fun textDateFormat(currentDate: String): String? {
        val currentFormatter =
            SimpleDateFormat(Constant.DATE_PATTERN)
        try {
            val date = currentFormatter.parse(currentDate)
            val formatter2 =
                SimpleDateFormat(Constant.TEXT_DATE_PATTERN)
            return formatter2.format(date)
        } catch (e: Exception) {
        }
        return null
    }
}