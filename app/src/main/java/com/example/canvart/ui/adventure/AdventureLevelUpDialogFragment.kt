package com.example.canvart.ui.adventure

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvart.R
import com.example.canvart.base.Event

class AdventureLevelUpDialogFragment(private val level : Int) : DialogFragment() {

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_level, level) + ": " +
            when(level){
                1 -> getString(R.string.level_1_nick)
                2 -> getString(R.string.level_2_nick)
                3 -> getString(R.string.level_3_nick)
                4 -> getString(R.string.level_4_nick)
                5 -> getString(R.string.level_5_nick)
                6 -> getString(R.string.level_6_nick)
                7 -> getString(R.string.level_7_nick)
                8 -> getString(R.string.level_8_nick)
                9 -> getString(R.string.level_9_nick)
                10 -> getString(R.string.level_10_nick)
                11 -> getString(R.string.level_11_nick)
                12 -> getString(R.string.level_12_nick)
                13 -> getString(R.string.level_13_nick)
                14 -> getString(R.string.level_14_nick)
                15 -> getString(R.string.level_15_nick)
                else -> "Error"
            }
            )
            .setMessage(
                when(level){
                    1 -> getString(R.string.level_1_unlock)
                    2 -> getString(R.string.level_2_unlock)
                    3 -> getString(R.string.level_3_unlock)
                    4 -> getString(R.string.level_4_unlock)
                    5 -> getString(R.string.level_5_unlock)
                    6 -> getString(R.string.level_6_unlock)
                    7 -> getString(R.string.level_7_unlock)
                    8 -> getString(R.string.level_8_unlock)
                    9 -> getString(R.string.level_9_unlock)
                    10 -> getString(R.string.level_10_unlock)
                    11 -> getString(R.string.level_11_unlock)
                    12 -> getString(R.string.level_12_unlock)
                    13 -> getString(R.string.level_13_unlock)
                    14 -> getString(R.string.level_14_unlock)
                    15 -> getString(R.string.level_15_unlock)
                    else -> "Error"
                }
            )
            .setPositiveButton(getString(R.string.dialog_level_ok)) { _, _ ->
                viewModel.reply(true)
            }
            .create()

    class ViewModel : androidx.lifecycle.ViewModel(){

        private val _response : MutableLiveData<Event<Boolean>> = MutableLiveData()
        val response : LiveData<Event<Boolean>>
            get() = _response

        fun reply(value : Boolean){
            _response.value = Event(value)
        }

    }

}