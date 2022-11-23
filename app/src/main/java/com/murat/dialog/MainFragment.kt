package com.murat.dialog

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainFragment : Fragment() {

    lateinit var pref: Pref

    private lateinit var image: ImageView
    private lateinit var deleteImage: ImageView
    lateinit var button: Button
    private lateinit var logButton: Button
    lateinit var userName : TextView
    private lateinit var bottomShit : Button
    lateinit var bottomShitDialog : BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = Pref(requireContext())

        logButton = view.findViewById(R.id.btn_login)
        userName = view.findViewById(R.id.name)
        image = view.findViewById(R.id.image)
        deleteImage = view.findViewById(R.id.delete_image_button)
         button = view.findViewById(R.id.button)
        bottomShit= view.findViewById(R.id.bottom_shit)
        button.setOnClickListener {
            gallery.launch(arrayOf("image/*"))
            button.text = "Поменять фото"

        }
        image.loadImage(pref.getImage())
        deleteImage.setOnClickListener {
            showConfirmDialog()
        }

        logButton.setOnClickListener {
            showLoginDialog()
        }
        userName.text = pref.getName()

        bottomShit.setOnClickListener {
           requireActivity().supportFragmentManager.beginTransaction()
               .replace(R.id.dialog_fr,AnimationFragment()).addToBackStack(null).commit()
        }

    }

    private fun showBottomShit(){
            var dialog = layoutInflater.inflate(R.layout.bottom_shit_layout,null)

        bottomShitDialog = BottomSheetDialog(requireContext(), R.style.BottomShitDialogThem)
        bottomShitDialog.setContentView(dialog)

        bottomShitDialog.show()
        bottomShitDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        bottomShitDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    private fun showDialog(){

        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_shit_layout)

        val editLayout : LinearLayout = dialog.findViewById(R.id.edit_layout)
        val shareLayout : LinearLayout = dialog.findViewById(R.id.share_layout)
        val uploadLayout : LinearLayout = dialog.findViewById(R.id.upload_layout)
        val printLayout : LinearLayout = dialog.findViewById(R.id.print_layout)
        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT    )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
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

    private fun showLoginDialog() {
        val dialogBinding = layoutInflater.inflate(R.layout.login, null)
        val myDialog = Dialog(requireContext())
        myDialog.setContentView(dialogBinding)
        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        val etUserName = dialogBinding.findViewById<EditText>(R.id.et_username)

        val btnLogin = dialogBinding.findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val uName = etUserName.text.toString()
            pref.saveName(uName)
            userName.text = uName
            myDialog.dismiss()}
        myDialog.show()
    }

    private  var gallery: ActivityResultLauncher<Array<String>> =
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


