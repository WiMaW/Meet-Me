package com.wioletamwrobel.meetme

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class InvitationPreview : AppCompatActivity() {

    private lateinit var invitation: Invitation
    private lateinit var textViewInvitationPreview: TextView
    private lateinit var invitationPreview: String
    private lateinit var buttonSendInvitation: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_invitation_preview)

        generateInvitationText()
        clickButtonSendInvitation()
    }

    private fun clickButtonSendInvitation() {
        buttonSendInvitation = findViewById(R.id.button_send_invitation)

        buttonSendInvitation.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto: ${invitation.guestNumber}")
                putExtra("sms_body", invitationPreview)
            }
            startActivity(intent)
        }
    }

    private fun generateInvitationText() {
        invitation = intent.getSerializableExtra("Invitation") as Invitation
        textViewInvitationPreview = findViewById(R.id.text_view_invitation_preview)

        if (invitation.occasion == "Meeting") {
            invitationPreview = """
                Dear ${invitation.guestName},
                
            meet me at ${invitation.place} on ${invitation.date}.
                
            """.trimIndent()
        } else {

            invitationPreview = """
            Dear ${invitation.guestName},
            
        I invite you to my ${invitation.occasion} which will take place on: ${invitation.date} at ${invitation.place}.
        
        """.trimIndent()
        }

        if (invitation.confirmation == true) {
            invitationPreview += """
                
        Please confirm your arrival by: ${invitation.confirmationDate}.
        
        """.trimIndent()
        }

        if (invitation.dressCode == true) {
            invitationPreview += """
                
        Formal attire is required.
        
        """.trimIndent()
        }

        if (invitation.themeCheckBox == true) {
            invitationPreview += """
                
        The main theme of the event is: ${invitation.theme}.
        
        """.trimIndent()
        }

        invitationPreview += """
            
            Hope you will come. 
            Best regards.
            
        """.trimIndent()

        textViewInvitationPreview.text = invitationPreview
    }
}