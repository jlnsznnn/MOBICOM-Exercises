package com.mobdeve.siazonjalene.exercise3lifecyclesp

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    // Views for the switches
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var linearViewSwitch: Switch

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var hideLikeSwitch: Switch
    private lateinit var sharedPreference: SharedPreferences
    private lateinit var edit: SharedPreferences.Editor

    // Indicators for what Layout should be used or if the like buttons should be hidden
    // private val viewSelected = LayoutType.LINEAR_VIEW_TYPE.ordinal // int of LayoutType.LINEAR_VIEW_TYPE (default) or LayoutType.GRID_VIEW_TYPE
    // private val hideLikeSelected = false // true -> hidden buttons; false -> shown buttons (default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Instantiation of the Switch views
        this.linearViewSwitch = findViewById(R.id.viewSwitch)
        this.hideLikeSwitch = findViewById(R.id.hideLikeSwitch)

        sharedPreference = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        edit = sharedPreference.edit()

        val layoutType = sharedPreference.getInt("layoutType", LayoutType.LINEAR_VIEW_TYPE.ordinal)
        val hideLike = sharedPreference.getBoolean("hideLikeButtons", false)

        linearViewSwitch.isChecked = layoutType == LayoutType.LINEAR_VIEW_TYPE.ordinal
        hideLikeSwitch.isChecked = hideLike

        linearViewSwitch.setOnCheckedChangeListener { _, isChecked ->
            val newLayoutType =
                if (isChecked) LayoutType.LINEAR_VIEW_TYPE.ordinal else LayoutType.GRID_VIEW_TYPE.ordinal
            edit.putInt("layoutType", newLayoutType).apply()
        }

        hideLikeSwitch.setOnCheckedChangeListener { _, isChecked ->
            edit.putBoolean("hideLikeButtons", isChecked).apply()
        }
    }
}

/*
 * Method to map an integer to the appropriate boolean value based on the given LayoutType.
 *
private fun returnBoolean(value: Int): Boolean {
    if (value == LayoutType.LINEAR_VIEW_TYPE.ordinal)
        return true
    else
        return false
}

/*
 * Method to map a boolean value (representing whether the linearViewSwitch is checked or not)
 * to appropriate LayoutType in ordinal representation.
 * */
private fun returnInt(value: Boolean): Int {
    if (value == true)
        return LayoutType.LINEAR_VIEW_TYPE.ordinal
    else
        return LayoutType.GRID_VIEW_TYPE.ordinal
}
}
*/
