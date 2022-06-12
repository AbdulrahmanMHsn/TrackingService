package com.amhsn.mycustomservice

import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class FirebaseUtils {

    private lateinit var firebaseDB: FirebaseDatabase
    private lateinit var reference: DatabaseReference


    companion object {
        private var firebaseUtils: FirebaseUtils? = null

        fun getInstance(): FirebaseUtils {
            if (firebaseUtils == null) {
                firebaseUtils = FirebaseUtils()
                return firebaseUtils!!
            }
            return firebaseUtils!!
        }
    }


    fun init() {
        Firebase.database.setPersistenceEnabled(true)
        firebaseDB = FirebaseDatabase.getInstance()
        reference = firebaseDB.getReference("Abdo")
    }


    fun updateLocationList(shipmentId: Int, lat: Double, lon: Double) {

        reference.child("$shipmentId").child("location_list")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    reference.child("$shipmentId").child("location_list")
                        .child("${dataSnapshot.childrenCount}").setValue(LatLng(lat,lon))
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                }
            })
    }

}