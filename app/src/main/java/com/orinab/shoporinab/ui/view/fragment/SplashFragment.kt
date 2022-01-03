package com.orinab.shoporinab.ui.view.fragment

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.orinab.shoporinab.BuildConfig.VERSION_CODE
import com.orinab.shoporinab.BuildConfig.VERSION_NAME
import com.orinab.shoporinab.R
import com.orinab.shoporinab.databinding.FragmentSplashBinding
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.SplitterTools
import org.koin.android.ext.android.inject

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var navController: NavController
    private val handleErrorTools: HandleErrorTools by inject()
    private val splitterTools: SplitterTools by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        try {
            initAction()
        } catch (e: Exception) {
            handleErrorTools.handleError(e)
        }
    }

    private fun initAction() {
        initNavController()
        initSetView()
        initHandle()

    }

    private fun initHandle() {
        binding.lotView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) = Unit

            override fun onAnimationEnd(animation: Animator?) = initDashboard()

            override fun onAnimationCancel(animation: Animator?) = Unit

            override fun onAnimationRepeat(animation: Animator?) = Unit
        })
    }

    private fun initDashboard() {
        navController.popBackStack()
        navController.navigate(R.id.mainFragment)
    }


    @SuppressLint("SetTextI18n")
    private fun initSetView() {
        binding.lotView.setAnimation("lottie/splash.json")
        "A${VERSION_NAME}_B${VERSION_CODE}".also { binding.txtVersionApp.text = it }
    }

    private fun initNavController() {
        navController = Navigation.findNavController(AppShopOrinab.activity, R.id.my_nav_fragment)
    }

}