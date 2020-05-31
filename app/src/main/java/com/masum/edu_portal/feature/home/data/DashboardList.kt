package com.masum.edu_portal.feature.home.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.masum.edu_portal.R
import java.util.ArrayList

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class DashboardList constructor(val context: Context?) {
    var list: ArrayList<Dashboard>? = null

    init {
        list = ArrayList<Dashboard>()


        list!!.add(
            Dashboard(
                "Class",
                context!!.resources.getDrawable(R.drawable.clas, context.theme)
            )
        )
        list!!.add(
            Dashboard(
                "Attendance",
                context.resources.getDrawable(R.drawable.attendance, context.theme)
            )
        )
        list!!.add(
            Dashboard(
                "Exam/\nResult",
                context.resources.getDrawable(R.drawable.exam, context.theme)
            )
        )
        list!!.add(
            Dashboard(
                "Homework",
                context.resources.getDrawable(R.drawable.home_work, context.theme)
            )
        )
        list!!.add(
            Dashboard(
                "Study",
                context.resources.getDrawable(R.drawable.study, context.theme)
            )
        )

        list!!.add(
            Dashboard(
                "Others",
                context.resources.getDrawable(R.drawable.others, context.theme)
            )
        )
    }


}