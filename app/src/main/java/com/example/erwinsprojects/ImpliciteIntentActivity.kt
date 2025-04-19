package com.example.erwinsprojects

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.erwinsprojects.databinding.ActivityImpliciteIntentBinding

class ImpliciteIntentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImpliciteIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityImpliciteIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        /* ------------------------------------------------------------------ */

        // TOMBOL UTK SHARE TEXT/PESAN
        binding.btnShareTo.setOnClickListener() {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,binding.editTxt.text.toString())
            intent.type = "text/plain"

            startActivity(Intent(Intent.createChooser(intent, "Share to")))
        }

        // TOMBOL UNTUK MELEKUKAN PANGGILAN
        binding.btnCall.setOnClickListener() {
            val uri = ("tel:" + binding.editTxtPhone.text.toString()).toUri()
            startActivity(Intent(Intent.ACTION_DIAL,uri))
        }

        // TOMBOL OPEN GOOGLE MAPS
        binding.btnOpenGoogleMap.setOnClickListener() {
            val uri = ("geo:0,0?q=" + binding.editTxtLocation.text.toString()).toUri()
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        // TOMBOL OPEN WEB
        binding.btnOpenWeb.setOnClickListener() {
            val uri = binding.editTxtWebURL.text.toString().toUri()
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }

        // TOMBOL OPEN CAMERA
        binding.btnOpenCamera.setOnClickListener() {
            startActivity(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }

        // TOMBOL OPEN GALERY
        binding.btnOpenGalery.setOnClickListener() {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "content://media/external/images/media".toUri()
            startActivity(intent)
        }

        // TOMBOL OPEN ALARM
        binding.btnOpenAlaram.setOnClickListener() {
            startActivity(Intent(AlarmClock.ACTION_SHOW_ALARMS))
        }
    }
}