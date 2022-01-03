package com.orinab.shoporinab.ui.view.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.orinab.shoporinab.R
import com.orinab.shoporinab.databinding.FragmentRepresentationBinding
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import org.koin.android.ext.android.inject


class RepresentationFragment : Fragment() {

    private lateinit var binding: FragmentRepresentationBinding
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRepresentationBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        try {
            initAction()
        } catch (e: Exception) {
            handleErrorTools.handleError(e)
        }
    }

    private fun initAction() {
        initOnBackPress()
    }

    private fun initOnBackPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    initFinish()
                }
            })
    }

    private fun initFinish() {
        if (doubleBackToExitPressedOnce) {
            requireActivity().finish()
        } else toastTools.toast(resources.getString(R.string.exit_app))
        doubleBackToExitPressedOnce = true
        Handler().postDelayed({
            doubleBackToExitPressedOnce = false
        }, 2000)
    }

}