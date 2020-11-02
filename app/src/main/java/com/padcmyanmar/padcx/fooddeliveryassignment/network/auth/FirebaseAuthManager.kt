package com.padcmyanmar.padcx.fooddeliveryassignment.network.auth

import android.graphics.Bitmap
import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.storage.FirebaseStorage
import com.padcmyanmar.padcx.fooddeliveryassignment.data.vos.UserVO
import java.io.ByteArrayOutputStream
import java.util.*

/**
 * Created by Phyoe Sandy Soe Tun
 * on 10/31/2020.
 */
object FirebaseAuthManager : AuthManager {
    private val mFirebaseAuth = FirebaseAuth.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val storageReference = storage.reference

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isComplete && it.isSuccessful) onSuccess()
                else onFailure(it.exception?.message ?: "Please Check Internet Connection")
            }
    }

    override fun register(
        userName: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful && it.isComplete) {
                    mFirebaseAuth.currentUser?.updateProfile(
                        UserProfileChangeRequest.Builder()
                            .setDisplayName(userName).build()
                    )
                    onSuccess()
                } else onFailure(it.exception?.message ?: "Please Check Internet Connection")
            }
    }

    override fun getProfile(): UserVO {
        var user = UserVO()
        user.name = mFirebaseAuth.currentUser?.displayName ?: ""
        user.email = mFirebaseAuth.currentUser?.email ?: ""
        user.phone = mFirebaseAuth.currentUser?.phoneNumber ?: ""
        user.image = mFirebaseAuth.currentUser?.photoUrl.toString() ?: ""
        return user
    }

    override fun uploadProfileImage(
        bitmap: Bitmap,
        onSuccess: (imageUrl: String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            //
        }.addOnSuccessListener {
            //
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val photoUrl = task.result?.toString()
            photoUrl?.let {
                onSuccess(it)
            }
        }
    }

    override fun updateProfile(
        imageUrl: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.currentUser?.updateProfile(
            UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse(imageUrl)).build()
        )?.addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) onSuccess()
            else onFailure(it.exception?.message ?: "Profile Image Upload Failed.")
        }
    }

}