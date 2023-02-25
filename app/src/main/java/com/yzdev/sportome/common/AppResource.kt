package com.yzdev.sportome.common

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.BoolRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import com.yzdev.sportome.App
import com.yzdev.sportome.R
import java.io.File

object AppResource {
    fun getString(@StringRes stringRes: Int): String {
        return App.instance.resources.getString(stringRes)
    }

    fun getBoolean(@BoolRes boolRes: Int): Boolean {
        return App.instance.resources.getBoolean(boolRes)
    }

    fun getInt(@IntegerRes intRes: Int): Int {
        return App.instance.resources.getInteger(intRes)
    }

    fun getAppContext(): Context {
        return App.instance.applicationContext
    }

    fun getAppInstance(): App {
        return App.instance
    }

    //Store the capture image
    fun getDirectory(): File {
        val mediaDir = App.instance.externalMediaDirs.firstOrNull()?.let {
            File(it, App.instance.resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else App.instance.filesDir
    }

    fun getStringArray(@ArrayRes arrayRes: Int): Array<out String> {
        return App.instance.resources.getStringArray(arrayRes)
    }
}