package com.wioletamwrobel.meetme

import java.io.Serializable

data class Invitation(
    val guestName: String,
    val guestNumber: String,
    val place: String,
    val date: String?,
    val confirmation: Boolean?,
    val confirmationDate: String,
    val dressCode: Boolean?,
    val themeCheckBox: Boolean?,
    val theme: String,
    val occasion: String,
) : Serializable


