package com.orinab.shoporinab.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.local.bundle.BundleModel
import com.orinab.shoporinab.data.model.response.cabinet_kitchen.Pro
import com.orinab.shoporinab.data.model.response.cabinet_kitchen.ResponseCabinetKitchenModel
import com.orinab.shoporinab.databinding.FragmentCabinetKitchenBinding
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.ui.view.adapter.cabinet_kitchen.CabinetKitchenAdapter
import com.orinab.shoporinab.ui.viewmodel.DashboardViewModel
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BUNDLE_DETAIL_SINGLE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BUNDLE_SUB
import com.orinab.shoporinab.utils.extention.initBundle
import com.orinab.shoporinab.utils.extention.initCheckShowList
import com.orinab.shoporinab.utils.extention.resultBundle
import com.orinab.shoporinab.utils.manager.GridLayoutCountManager
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class CabinetKitchenFragment : Fragment() {

    private lateinit var binding: FragmentCabinetKitchenBinding
    private lateinit var navController: NavController
    private lateinit var bundleModel: BundleModel
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private val gson: Gson by inject()
    private val cabinetKitchenAdapter: CabinetKitchenAdapter by inject()
    private val gridLayoutCountManager: GridLayoutCountManager by inject()
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCabinetKitchenBinding.inflate(layoutInflater)
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
        initResultBundle()
        initNavController()
        initToolbar()
        initAdapter()
        initCheckRequestCabinetKitchen()
    }

    private fun initCheckRequestCabinetKitchen() = when {
        cabinetKitchenAdapter.getList().isEmpty() -> initRequestCabinetKitchen()
        else -> initShowList()
    }

    private fun initShowList() {
        initHideLoading()
        initCheckShowList(
            binding.rcCabinetKitchen,
            binding.lnDataNotFound.lnParent,
           cabinetKitchenAdapter.getList().isEmpty()
        )
    }

    private fun initNavController() {
        navController = Navigation.findNavController(AppShopOrinab.activity, R.id.my_nav_fragment)
    }

    private fun initResultBundle() {
        bundleModel =
            gson.fromJson(resultBundle(requireArguments(), BUNDLE_SUB), BundleModel::class.java)
    }

    private fun initAdapter() {
        val gridLayoutManager = GridLayoutManager(
            requireContext(),
            gridLayoutCountManager.getColumnWidthCabinetKitchen()
        )
        binding.rcCabinetKitchen.layoutManager = gridLayoutManager
        binding.rcCabinetKitchen.setHasFixedSize(true)
        binding.rcCabinetKitchen.adapter = cabinetKitchenAdapter
        cabinetKitchenAdapter.setOnClickItem(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initOnClickListener(any)
        })
    }

    private fun initOnClickListener(any: Any) {
        val pro: Pro = any as Pro
        navController.navigate(
            R.id.action_cabinetKitchenFragment_to_detailSingleCobinetFragment,
            initBundle(requireContext(),gson, BUNDLE_DETAIL_SINGLE, pro)
        )
    }

    private fun initRequestCabinetKitchen() {
        initShowLoading()
        dashboardViewModel.initCabinetKitchen(bundleModel.id).observe(
            viewLifecycleOwner,
            this::initResultCabinetKitchen
        )
    }

    private fun initResultCabinetKitchen(it: Any) {
        initHideLoading()
        when (it) {
            is ResponseCabinetKitchenModel -> initResponseCabinetKitchen(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initShowLoading() {
        binding.lnParent.visibility = View.GONE
        binding.vLoading.visibility = View.VISIBLE
    }

    private fun initHideLoading() {
        binding.lnParent.visibility = View.VISIBLE
        binding.vLoading.visibility = View.GONE
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handleErrorTools.handleError(it)
        toastTools.toast(message)
    }

    private fun initResponseCabinetKitchen(it: ResponseCabinetKitchenModel) {
        cabinetKitchenAdapter.updateList(it.pro)
        initCheckShowList(
            binding.rcCabinetKitchen,
            binding.lnDataNotFound.lnParent,
            it.pro.isEmpty()
        )
    }

    private fun initToolbar() {
        (AppShopOrinab.activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        Objects.requireNonNull((AppShopOrinab.activity as AppCompatActivity).supportActionBar)!!
            .setDisplayShowTitleEnabled(false)
        binding.imgBtnBack.setOnClickListener { navController.popBackStack() }
        binding.txtTitle.text = bundleModel.title
    }
}