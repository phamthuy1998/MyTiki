package com.thuypham.ptithcm.mytiki.help

import android.R
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.layout_input_birthday.*
import java.text.DateFormat
import java.util.*


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar: Calendar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        /*
            **** reference source developer.android.com ***

            DatePickerDialog(Context context)
                Creates a new date picker dialog for the current date using the
                parent context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId)
                Creates a new date picker dialog for the current date.

            DatePickerDialog(Context context, DatePickerDialog.OnDateSetListener listener,
            int year, int month, int dayOfMonth)
                Creates a new date picker dialog for the specified date using the parent
                context's default date picker dialog theme.

            DatePickerDialog(Context context, int themeResId, DatePickerDialog.OnDateSetListener
            listener, int year, int monthOfYear, int dayOfMonth)
                Creates a new date picker dialog for the specified date.
        */

        // Initialize a new date picker dialog and return it

        val datePickerDialog = DatePickerDialog(
                requireContext(), // Context
                // Put 0 to system default theme or remove this parameter
//                Theme_Holo_Light_Dialog_NoActionBar_MinWidth
                R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
                this, // DatePickerDialog.OnDateSetListener
                year, // Year
                month, // Month of year
                day // Day of month
        )
        calendar.add(Calendar.DATE, -1);
        datePickerDialog.datePicker.maxDate = calendar.getTimeInMillis()
        calendar.add(Calendar.YEAR, -100);
//        calendar.add(Calendar.DATE, -6);
        datePickerDialog.datePicker.minDate = calendar.getTimeInMillis()
        return datePickerDialog
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        // Display the selected date in text view
        val date = formatDate(year, month, day)
        activity?.edt_birthday_sign_up?.setText(date)
    }


    // Custom method to format date
    private fun formatDate(year: Int, month: Int, day: Int): String {
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time

        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate).toString()
    }
}