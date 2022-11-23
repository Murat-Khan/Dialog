package com.murat.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class AnimationFragment : Fragment() {
    lateinit var buttonWarning : Button
    lateinit var buttonError : Button
    lateinit var buttonSuccess : Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_animation, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonWarning = view.findViewById(R.id.buttonWarning)
        buttonError= view.findViewById(R.id.buttonError)
        buttonSuccess = view.findViewById(R.id.buttonSuccess)




        buttonWarning.setOnClickListener {
            showWarning()
        }

        buttonError.setOnClickListener {
            showError()
        }

        buttonSuccess.setOnClickListener {
            showSuccess()
        }



    }

    private fun showSuccess() {
        val dialogBinding = layoutInflater.inflate(R.layout.layout_success_dialog, null)
        val myDialog = Dialog(requireContext(),R.style.AlertDialogThem)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)

        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun showError() {
        val dialogBinding = layoutInflater.inflate(R.layout.layout_error_dialog, null)
        val myDialog = Dialog(requireContext(),R.style.AlertDialogThem)
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        dialogBinding.findViewById<TextView>(R.id.textTitle).text = "ОШИБКА"
       dialogBinding.findViewById<ImageView>(R.id.imageIcon).setImageResource(R.drawable.ic_baseline_error_24)
        dialogBinding.findViewById<ImageView>(R.id.imageIcon).animate().apply {
            duration= 1000
            alpha(0.0f)
        }.withEndAction{ dialogBinding.findViewById<ImageView>(R.id.imageIcon).animate().apply {
            duration=1000

            alpha(1.0f)
        }}

        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }

    private fun showWarning() {
         val dialogBinding = layoutInflater.inflate(R.layout.layout_warning_dialog, null)
         val myDialog = Dialog(requireContext(),R.style.AlertDialogThem)
         myDialog.setContentView(dialogBinding)
         myDialog.setCancelable(true)

         myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()
    }
}