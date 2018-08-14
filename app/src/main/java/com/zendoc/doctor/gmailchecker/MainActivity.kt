package com.zendoc.doctor.gmailchecker

import android.accounts.Account
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.accounts.AccountManager
import androidx.databinding.DataBindingUtil
import com.zendoc.doctor.gmailchecker.databinding.ActivityMainBinding
import androidx.core.content.ContextCompat
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    val TAG = "PermissionDemo"
    val RCODE = 101
    var gname: String? = "null"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= 23) {
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS)
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(this, Array<String>(1) { Manifest.permission.GET_ACCOUNTS }, 1)

                }

            }



        }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RCODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")

                    val binding : ActivityMainBinding= DataBindingUtil.setContentView(this, R.layout.activity_main)
                    val accounts : Array<Account> =  AccountManager.get(this).getAccounts()
                    if (accounts.isNotEmpty()) {
                        val account : String = accounts[0].name
                        this.gname = account
                    }
                    binding.setGuser(this)
                }
            }
        }
    }

 }




