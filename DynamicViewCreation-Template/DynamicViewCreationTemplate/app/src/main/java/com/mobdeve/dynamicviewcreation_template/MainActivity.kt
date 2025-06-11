package com.mobdeve.dynamicviewcreation_template

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import com.mobdeve.dynamicviewcreation_template.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    // View Binding holds all variables
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Modified for ViewBinding
        val viewBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.addBtn.setOnClickListener(View.OnClickListener {
            //  (1) Create a TextView object
            val tempTextView: TextView = TextView(this@MainActivity)
            //  (2) Set the Text attribute of the object to the <Last>, <First> format
            tempTextView.text = viewBinding.lastNameEtv.text.toString() + ", " + viewBinding.firstNameEtv.text.toString()
            //  (3) Add the newly created view to the LinearLayout
            viewBinding.nameListLl.addView(tempTextView)
        })
    }
}