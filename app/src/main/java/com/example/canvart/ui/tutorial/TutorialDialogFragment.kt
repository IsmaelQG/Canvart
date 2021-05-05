package com.example.canvart.ui.tutorial

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.canvart.R
import com.example.canvart.base.Event

class TutorialDialogFragment : DialogFragment(){

    private val viewModel : ViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        isCancelable = false
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setPositiveButton(getString(R.string.dialog_yes)) { _, _ ->
                viewModel.reply(true)
            }
            .setNegativeButton(getString(R.string.dialog_no)) { _, _ ->
                viewModel.reply(false)
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