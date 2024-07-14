package com.example.notes.Mydatabase

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


//Converts for the Room database
//Date to String for Store in Room DB
class DatetoStringConverter {


    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun DateToString(cdate:LocalDate):String{
        val formater = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)
        val currentDate = cdate.format(formater)

        return currentDate.toString()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun StringToDate(value:String):LocalDate{

        val formater = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)

        return LocalDate.parse(value,formater)
    }
}