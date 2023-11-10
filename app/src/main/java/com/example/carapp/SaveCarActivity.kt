package com.example.carapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carapp.databinding.ActivitySaveCarBinding
import com.google.android.material.snackbar.Snackbar

class SaveCarActivity : AppCompatActivity() {

    private val binding:ActivitySaveCarBinding by lazy {
        ActivitySaveCarBinding.inflate(layoutInflater)
    }

    private val sharedPref: CarsDatabase by lazy {
        CarsDatabase(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.saveCard.setOnClickListener{
            saveCars()
        }
        binding.backCard.setOnClickListener {
            finish()
        }
    }

    private fun  saveCars() = binding.apply {
        if (marksEt.text?.isNotEmpty() == true && modelsEt.text?.isNotEmpty() == true){
            sharedPref.saveCar(
                CarModels(

                    mark = marksEt.text.toString(),
                    model = modelsEt.text.toString()
                )
            )
            startActivity(Intent ( this@SaveCarActivity, MainActivity::class.java))
        } else showToastMessage (resources.getString(R.string.text_not))
    }

    private fun showToastMessage(massage: String) {
        Snackbar.make(
            binding.root,
            massage,
            Snackbar.LENGTH_SHORT,
        ).show()
    }
}