package com.schoolwork.crashanalytics

import com.crashlytics.android.Crashlytics

fun dropBreadCrumb(className: String, methodName: String, id: Long, generalData: String){
    val breadcrumb = "$className - $methodName - $generalData"
    Crashlytics.log(breadcrumb)
}