package com.orinab.shoporinab.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.response.dashboard.Banner
import com.orinab.shoporinab.data.model.response.dashboard.Category
import com.orinab.shoporinab.data.model.response.dashboard.Sub
import com.orinab.shoporinab.data.model.response.dashboard.Value
import com.orinab.shoporinab.databinding.FragmentDashboardValueBinding
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.ui.view.adapter.dashboard.DashboardAdapter
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BUNDLE_DETAIL_SINGLE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BUNDLE_SUB
import com.orinab.shoporinab.utils.extention.initBundle
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import org.koin.android.ext.android.inject

class FragmentTabsDashboardValue(private val value: List<Value>) : Fragment() {

    private lateinit var binding: FragmentDashboardValueBinding
    private lateinit var navController: NavController
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private val dashboardAdapter: DashboardAdapter by inject()
    private val gson: Gson by inject()

    companion object{
        fun newInstance(value: List<Value>): FragmentTabsDashboardValue {
            return FragmentTabsDashboardValue(value)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDashboardValueBinding.inflate(layoutInflater)
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
        initNavController()
        initAdapter()
    }

    private fun initNavController() {
        navController = Navigation.findNavController(AppShopOrinab.activity, R.id.my_nav_fragment)
    }

    private fun initAdapter() {
        val layoutManager =
            LinearLayoutManager(AppShopOrinab.context, RecyclerView.VERTICAL, false)
        binding.rcDashboard.layoutManager = layoutManager
        binding.rcDashboard.setHasFixedSize(true)
        binding.rcDashboard.adapter = dashboardAdapter
        dashboardAdapter.setOnClickItem(object : OnClickListenerAny {
            override fun onClickListener(any: Any) {
                initOnClickItem(any)
            }
        })
        dashboardAdapter.updateList(value)
    }

    private fun initOnClickItem(any: Any) {
        when(any){
            is Banner->{initOClickItemBanner(any)}
            is Sub ->{initOClickItemSub(any)}
            is Category ->{initOClickItemCategory(any)}
        }
    }

    private fun initOClickItemCategory(category: Category) {
        navController.navigate(R.id.action_mainFragment_to_detailSingleCobinetFragment,initBundle(requireContext(),gson,BUNDLE_DETAIL_SINGLE,category))
    }

    private fun initOClickItemSub(sub: Sub) {
        navController.navigate(R.id.action_mainFragment_to_cabinetKitchenFragment,initBundle(requireContext(),gson,BUNDLE_SUB,sub))
    }

    private fun initOClickItemBanner(banner: Banner) {

    }
}