package com.wioletamwrobel.meetme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText

//if you come back from invotation preview save inputs ???

class MainActivity : AppCompatActivity() {

    private var textInputGuestName: TextInputEditText? = null
    private var textInputGuestNumber: TextInputEditText? = null
    private var textInputPlace: TextInputEditText? = null
    private var datePickerEventDate: DatePicker? = null
    private var checkBoxConfirmation: CheckBox? = null
    private var datePickerConfirmationDate: DatePicker? = null
    private var spinnerOccasion: Spinner? = null
    private var checkBoxDressCode: CheckBox? = null
    private var checkBoxTheme: CheckBox? = null
    private var textInputTheme: TextInputEditText? = null
    private lateinit var buttonInvitationPreview: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        setSpinnerValues()
        setConfirmationDatePickerVisibility()
        setThemeTextViewVisibility()
        onClickInvitationPreviewButton()
    }

    private fun setThemeTextViewVisibility() {
        checkBoxTheme?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                textInputTheme?.visibility = View.VISIBLE
            } else {
                textInputTheme?.visibility = View.GONE
            }
        }
    }

    private fun setConfirmationDatePickerVisibility() {
        checkBoxConfirmation?.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked) {
                datePickerConfirmationDate?.visibility = View.VISIBLE
            } else {
                datePickerConfirmationDate?.visibility = View.GONE
            }
        }
    }

    private fun onClickInvitationPreviewButton() {
        buttonInvitationPreview.setOnClickListener {
            val invitationText = Invitation(
                textInputGuestName?.text.toString(),
                textInputGuestNumber?.text.toString(),
                textInputPlace?.text.toString(),
                setDateInEventDatePicker(),
                checkBoxConfirmation?.isChecked,
                setDateInConfirmationDatePicker(),
                checkBoxDressCode?.isChecked,
                checkBoxTheme?.isChecked,
                textInputTheme?.text.toString(),
                spinnerOccasion?.selectedItem.toString()
            )

            val intent = Intent(this, InvitationPreview::class.java)
            intent.putExtra("Invitation", invitationText)
            startActivity(intent)
        }
    }

    private fun findViews() {
        textInputGuestName = findViewById(R.id.text_input_guest_name)
        textInputGuestNumber = findViewById(R.id.text_input_guest_number)
        textInputPlace = findViewById(R.id.text_input_place)
        datePickerEventDate = findViewById(R.id.date_picker_event_date)
        checkBoxConfirmation = findViewById(R.id.check_box_confirmation)
        this.datePickerConfirmationDate = findViewById(R.id.date_picker_confirmation_date)
        spinnerOccasion = findViewById(R.id.spinner_occasion)
        checkBoxDressCode = findViewById(R.id.check_box_dress_code)
        checkBoxTheme = findViewById(R.id.check_box_theme)
        textInputTheme = findViewById(R.id.text_input_theme)
        buttonInvitationPreview = findViewById(R.id.button_invitation_preview)
    }

    private fun setSpinnerValues() {
        val spinnerValues = arrayOf("Birthday", "Party", "Meeting")
        val spinnerAdapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues)
        spinnerOccasion?.adapter = spinnerAdapter
    }

    private fun setDateInEventDatePicker(): String {
        val day = datePickerEventDate?.dayOfMonth
        val month = (datePickerEventDate?.month?.plus(1)).toString()
        val year = datePickerEventDate?.year
        return "$month/$day/$year"
    }

    private fun setDateInConfirmationDatePicker(): String {
        val day = datePickerConfirmationDate?.dayOfMonth
        val month = (datePickerConfirmationDate?.month?.plus(1)).toString()
        val year = datePickerConfirmationDate?.year
        return "$month/$day/$year"
    }
}