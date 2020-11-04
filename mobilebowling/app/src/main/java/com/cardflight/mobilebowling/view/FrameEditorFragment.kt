package com.example.dialogfragment_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cardflight.mobilebowling.R
import com.cardflight.mobilebowling.view.SharedViewModel
import kotlinx.android.synthetic.main.frame_editor_layout.view.*

class FrameEditorFragment : DialogFragment() {

    companion object {

        const val TAG = "FrameEditFragment"

    }

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frame_editor_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        setupClickListeners(view)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners(view: View) {
        view.btnSubmit.setOnClickListener {
            viewModel.sendRoll1Score(view.roll1score.text.toString())
            dismiss()
        }
    }

}