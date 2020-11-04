package com.example.dialogfragment_example

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.cardflight.mobilebowling.R
import com.cardflight.mobilebowling.view.SharedViewModel
import kotlinx.android.synthetic.main.frame_editor_layout.*
import kotlinx.android.synthetic.main.frame_editor_layout.view.*


class FrameEditorFragment : DialogFragment() {

    companion object {

        const val TAG = "FrameEditFragment"

    }

    private lateinit var viewModel: SharedViewModel
    private lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

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

        //custom spinner
        val stringArr: Array<String> = resources.getStringArray(R.array.mode);
        val dataAdapter = ArrayAdapter<String>(this.context!!.applicationContext, R.layout.frame_spinner_item, stringArr)
        dataAdapter.setDropDownViewResource(R.layout.frame_spinner_drop_down)
        frame_number.adapter = dataAdapter


        frame_number.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val ctx = context ?: return
                Toast.makeText(ctx, stringArr[position], Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

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
            viewModel.sendRoll2Score(view.roll2score.text.toString())
            viewModel.sendRoll3Score(view.roll3score.text.toString())
            viewModel.sendFrameNumber(view.frame_number.toString())
            dismiss()
        }
    }

}