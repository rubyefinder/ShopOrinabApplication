package com.orinab.shoporinab.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.orinab.shoporinab.data.model.response.dashboard.ResponseDashboardModel
import com.orinab.shoporinab.databinding.FragmentHomeBinding
import com.orinab.shoporinab.ui.view.adapter.section_pager.SectionsPagerAdapter
import com.orinab.shoporinab.ui.viewmodel.DashboardViewModel
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
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
        initToolbar()
        initRequestDashboard()
    }

    private fun initTablelayout(responseDashboardModel: ResponseDashboardModel) {
        setupViewPager(binding.viewPager,responseDashboardModel)
        binding.tabLayout.post { binding.tabLayout.setupWithViewPager(binding.viewPager) }
    }

    private fun setupViewPager(viewPager: ViewPager,responseDashboardModel: ResponseDashboardModel) {
        val adapter = SectionsPagerAdapter(childFragmentManager)
        responseDashboardModel.homeDataList.forEach {
            val fragment= FragmentTabsDashboardValue.newInstance(it.value)
            adapter.addFragment(
               fragment, it.title
            )
        }
        viewPager.adapter = adapter
    }



    private fun initRequestDashboard() {
        initShowLoading()
        dashboardViewModel.initDashboard().observe(
            viewLifecycleOwner,
            this::initResultDashboard
        )
    }

    private fun initResultDashboard(it: Any) {
        initHideLoading()
        when (it) {
            is ResponseDashboardModel -> initResponseDashboardModel(it)
            is Throwable -> initThrowable(it)
        }
    }

    private fun initShowLoading() {
        binding.lnParent.visibility=View.GONE
        binding.vLoading.visibility=View.VISIBLE
    }

    private fun initHideLoading() {
        binding.lnParent.visibility=View.VISIBLE
        binding.vLoading.visibility=View.GONE
    }

    private fun initThrowable(it: Throwable) {
        val message = throwableTools.getThrowableError(it)
        handleErrorTools.handleError(it)
        toastTools.toast(message)
    }

    private fun initResponseDashboardModel(it: ResponseDashboardModel) {
        initTablelayout(it)
    }

    private fun initClickLocations(any: Any) {

    }

    private fun initToolbar() {
        (AppShopOrinab.activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        Objects.requireNonNull((AppShopOrinab.activity as AppCompatActivity).supportActionBar)!!.setDisplayShowTitleEnabled(false)
    }
}