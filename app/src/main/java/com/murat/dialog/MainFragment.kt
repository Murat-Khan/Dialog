package com.murat.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class MainFragment : Fragment() {

    lateinit var pref: Pref

    private lateinit var image: ImageView
    private lateinit var deleteImage: ImageView
    lateinit var button: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        image = view.findViewById(R.id.image)
        deleteImage = view.findViewById(R.id.delete_image_button)
         button = view.findViewById(R.id.button)
        button.setOnClickListener {
            gallery.launch(arrayOf("image/*"))
            button.text = "Поменять фото"

        }
        image.loadImage(pref.getImage())
        deleteImage.setOnClickListener {
            showConfirmDialog()


        }




    }

    private fun showConfirmDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.castom_dialog, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val delete = dialogBinding.findViewById<TextView>(R.id.alert_delete)
        delete.setOnClickListener {
            pref.deleteImage()
            image.loadImage(pref.getImage())

            myDialog.dismiss()
        }


        val cancel = dialogBinding.findViewById<TextView>(R.id.alert_cancel)
        cancel.setOnClickListener { myDialog.dismiss() }


        myDialog.show()


    }


    private var gallery: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.OpenDocument(),
            ActivityResultCallback<Uri?> { result ->
                if (result != null) {
                    image.loadImage(result.toString())
                    pref.saveImage(result.toString())
                } else {
                    Log.d("lol", "onActivityResult: the result is null for some reason")
                }
            }
        )


}


