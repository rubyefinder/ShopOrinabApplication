package com.orinab.shoporinab.ui.view.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.get
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.orinab.shoporinab.R
import com.orinab.shoporinab.databinding.FragmentMainBinding
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var navBottomSheetController: NavController
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initAction()
        } catch (e: Exception) {
            handleErrorTools.handleError(e)
        }
    }

    private fun initAction() {
        initBottomNavigationView()
    }

    private fun initBottomNavigationView() {
        val navHostFragment = childFragmentManager
            .findFragmentById(R.id.main_nav_host_fragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.mainBottomNavigationView,
            Objects.requireNonNull(navHostFragment)!!.navController
        )
        navBottomSheetController = navHostFragment!!.navController
        binding.mainBottomNavigationView.setOnItemSelectedListener(this::initOnItemSelected)
    }

    private fun initOnItemSelected(it: MenuItem):Boolean {
        when (it.itemId) {
            R.id.nav_account -> initAccount()
            R.id.nav_cart -> initCart()
            R.id.nav_representation -> initRepresentation()
            R.id.nav_category -> initCategory()
            R.id.nav_home -> initHome()
        }
        it.isChecked = true
        return true
    }

    private fun initAccount() {
        navBottomSheetController.navigate(R.id.accountFragment)
    }

    private fun initCart() {
        navBottomSheetController.navigate(R.id.cartFragment)
    }

    private fun initRepresentation() {
        navBottomSheetController.navigate(R.id.representationFragment)
    }

    private fun initCategory() {
        navBottomSheetController.navigate(R.id.categoryFragment)
    }

    private fun initHome() {
        navBottomSheetController.navigate(R.id.homeFragment)
    }

}