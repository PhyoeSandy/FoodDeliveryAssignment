package com.padcmyanmar.padcx.fooddeliveryassignment.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.padcmyanmar.padcx.fooddeliveryassignment.R
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.AccountPresenter
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.presenters.impls.AccountPresenterImpl
import com.padcmyanmar.padcx.fooddeliveryassignment.mvp.views.AccountView
import com.padcmyanmar.padcx.fooddeliveryassignment.utils.load
import com.padcmyanmar.padcx.shared.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_account.*
import java.io.IOException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AccountFragment : BaseFragment(), AccountView {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var mPresenter: AccountPresenter
    private var mBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    companion object {

        const val PICK_IMAGE_REQUEST = 222

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPresenter()
        setupListeners()
    }

    private fun setupListeners() {
        btnCancel.setOnClickListener {
            mPresenter.onTapCancel()
        }

        btnSave.setOnClickListener {
            mBitmap?.let {
                mPresenter.onPhotoTaken(it)
            }
        }

        ivProfile.setOnClickListener {
            mPresenter.onTapProfile()
        }
    }

    private fun setupPresenter() {
        mPresenter = getPresenter<AccountPresenterImpl, AccountView>()
        mPresenter.onUiReady(this)
    }

    override fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent, "Choose Picture"
            ), PICK_IMAGE_REQUEST
        )
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            val filePath = data.data


            try {
                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source = ImageDecoder.createSource(
                            context?.contentResolver!!, filePath
                        )
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        mBitmap = bitmap
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            context?.contentResolver, filePath
                        )
                        mBitmap = bitmap
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    override fun closeProfile() {
        //activity?.onBackPressed()
    }

    override fun showProfileData(user: UserVO) {
        user.image?.let { ivProfile.load(it.toUri()) }
        //etName.text = Editable.Factory.getInstance().newEditable(user.name)
        etEmail.text = Editable.Factory.getInstance().newEditable(user.email)
        etPhone.text = Editable.Factory.getInstance().newEditable(user.phone)
        etPassword.text = Editable.Factory.getInstance().newEditable(user.password)
    }
}