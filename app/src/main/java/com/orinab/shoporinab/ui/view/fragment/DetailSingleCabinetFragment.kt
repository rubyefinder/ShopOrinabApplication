package com.orinab.shoporinab.ui.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.orinab.shoporinab.R
import com.orinab.shoporinab.data.model.local.bundle.BundleModel
import com.orinab.shoporinab.data.model.local.sub_slider.ItemSubSlider
import com.orinab.shoporinab.data.model.response.cabinet_kitchen_single.ResponseCabinetKitchenSingle
import com.orinab.shoporinab.databinding.FragmentDetailSingleCabinetBinding
import com.orinab.shoporinab.interfaces.OnClickListenerAny
import com.orinab.shoporinab.ui.view.adapter.auto_slider.AutoSliderAdapter
import com.orinab.shoporinab.ui.view.adapter.sub_slider_list.SubSliderAdapter
import com.orinab.shoporinab.ui.viewmodel.DashboardViewModel
import com.orinab.shoporinab.utils.application.AppShopOrinab
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BASE_URL_SITE
import com.orinab.shoporinab.utils.build_config.BuildConfig.Companion.BUNDLE_DETAIL_SINGLE
import com.orinab.shoporinab.utils.extention.*
import com.orinab.shoporinab.utils.tools.HandleErrorTools
import com.orinab.shoporinab.utils.tools.ThrowableTools
import com.orinab.shoporinab.utils.tools.ToastTools
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class DetailSingleCabinetFragment : Fragment() {
    private lateinit var binding: FragmentDetailSingleCabinetBinding
    private lateinit var navController: NavController
    private lateinit var bundleModel: BundleModel
    private val handleErrorTools: HandleErrorTools by inject()
    private val throwableTools: ThrowableTools by inject()
    private val toastTools: ToastTools by inject()
    private val gson: Gson by inject()
    private val autoSliderAdapter: AutoSliderAdapter by inject()
    private val subSliderAdapter: SubSliderAdapter by inject()
    private val dashboardViewModel: DashboardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailSingleCabinetBinding.inflate(layoutInflater)
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
        initBundle()
        initNavController()
        initToolbar()
        initOnClick()
        initRequestCabinetKitchenSingle()
    }

    private fun initOnClick() {
        binding.btnViewProduct.setOnClickListener { initOnClickViewProduct() }
    }

    private fun initOnClickViewProduct() {
        val resultUrl=BASE_URL_SITE+bundleModel.url
        intentChrome(requireContext(),resultUrl)
    }

    private fun initRequestCabinetKitchenSingle() {
        initShowLoading()
        dashboardViewModel.initCabinetKitchenSingle(bundleModel.url)
            .observe(viewLifecycleOwner, this::initResultCabinetKitchenSingle)
    }

    private fun initResultCabinetKitchenSingle(it: Any) {
        when (it) {
            is ResponseCabinetKitchenSingle -> initResponseCabinetKitchenSingleModel(it)
            is Throwable -> initThrowable(it)
        }
        initHideLoading()
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

    private fun initResponseCabinetKitchenSingleModel(it: ResponseCabinetKitchenSingle) {
        initAdapter(initFetchListSlider(it))
        initSlider(initFetchListStrSlider(it))
        initPalletDescription(requireContext(), binding.txtModel, it.model)
        initCheckDiscountMoney(requireContext(),binding.txtPrice,binding.txtDiscountPrice, it.discountPrice)
        initCheckMoney(it)
        initTextHtml( binding.txtDescription,it.description)
        initTextHtml( binding.txtDescriptionAll,it.descriptionAll)
        initPalletColor(requireContext(), binding.txtColor,it.color)
        initPalletPiece(requireContext(), binding.txtPiece,it.piece)
        initPalletThickness(requireContext(),binding.lnThickness, binding.txtThickness,it.thickness)
        initPalletAerialHeight(requireContext(),binding.lnAerialHeight, binding.txtAerialHeight,it.aerialHeight)
        initPalletAerialDepth(requireContext(), binding.lnAerialDepth,binding.txtAerialDepth,it.aerialDepth)
        initPalletGroundHeight(requireContext(),binding.lnGroundHeight, binding.txtGroundHeight,it.groundHeight)
        initPalletGroundDepth(requireContext(),binding.lnGroundDepth, binding.txtGroundDepth,it.groundDepth)
        initPalletScreenDepth(requireContext(),binding.lnScreenDepth, binding.txtScreenDepth,it.screenDepth)
        initPalletScreen(binding.lnScreen, binding.txtScreen,it.screen)
        initPalletBase(requireContext(),binding.lnBase, binding.txtBase,it.base)
        initPalletKnobs(requireContext(), binding.lnKnobs,binding.txtKnobs,it.knobs)
        initPalletValidPiece(requireContext(),binding.txtValidPiece,it.validPiece)
        binding.txtUrl.text = it.name
        binding.txtProductCode.text = it.productCode
        binding.txtSize.text = it.size
    }

    private fun initCheckMoney(it: ResponseCabinetKitchenSingle) {
        if (it.discountPrice.isNullOrEmpty()){
            initPalletMoney(binding.txtPrice, it.price)
            initPalletMoney(requireContext(),binding.lnParentDiscountPrice,binding.txtDiscounted,binding.txtDiscountPrice, it.discountPrice)
        }else{
            initPalletMoney(requireContext(),binding.lnParentDiscountPrice,binding.txtDiscounted,binding.txtDiscountPrice, it.price)
            initPalletMoney(binding.txtPrice, it.discountPrice)
        }
    }

    private fun initFetchListSlider(it: ResponseCabinetKitchenSingle): List<ItemSubSlider> {
        val sliderList: ArrayList<ItemSubSlider> = ArrayList()
        val resultList=initFetchList(gson,it.images)
        resultList.forEach { sliderList.add(ItemSubSlider(0, it)) }
        return sliderList
    }

    private fun initFetchListStrSlider(it: ResponseCabinetKitchenSingle): List<String> {
        return initFetchList(gson,it.images)
    }


    private fun initSlider(sliderList:List<String>) {
        autoSliderAdapter.renewItems(sliderList)
        binding.imageSlider.setSliderAdapter(autoSliderAdapter)
        binding.imageSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imageSlider.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
        binding.imageSlider.autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
        binding.imageSlider.indicatorSelectedColor = Color.WHITE
        binding.imageSlider.indicatorUnselectedColor = Color.GRAY
        binding.imageSlider.scrollTimeInSec = 4
        binding.imageSlider.startAutoCycle()
        binding.imageSlider.setCurrentPageListener {  Handler().postDelayed({ initOnItemScrolling(it) }, 500) }
    }

    private fun initOnItemScrolling(any: Any) {
        val position:Int=any as Int
        subSliderAdapter.activeItem(position)
    }

    private fun initAdapter(slierList: List<ItemSubSlider>) {
        val layoutManager =
            LinearLayoutManager(AppShopOrinab.context, RecyclerView.HORIZONTAL, true)
        layoutManager.reverseLayout = false
        layoutManager.stackFromEnd = false
        binding.rcSubSlider.layoutManager = layoutManager
        binding.rcSubSlider.isNestedScrollingEnabled = false
        binding.rcSubSlider.adapter = subSliderAdapter
        subSliderAdapter.setOnClickItem(object : OnClickListenerAny {
            override fun onClickListener(any: Any) = initOnClickItemSubSlider(any)
        })
        subSliderAdapter.updateList(slierList)
    }

    private fun initOnClickItemSubSlider(any: Any) {
        val position:Int=any as Int
        subSliderAdapter.activeItem(position)
        binding.imageSlider.currentPagePosition=position
    }

    private fun initToolbar() {
        (AppShopOrinab.activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        Objects.requireNonNull((AppShopOrinab.activity as AppCompatActivity).supportActionBar)!!
            .setDisplayShowTitleEnabled(false)
        binding.imgBtnBack.setOnClickListener { navController.popBackStack() }
        binding.txtTitle.text = bundleModel.title
    }

    private fun initNavController() {
        navController = Navigation.findNavController(AppShopOrinab.activity, R.id.my_nav_fragment)
    }

    private fun initBundle() {
        bundleModel = gson.fromJson(
            resultBundle(requireArguments(), BUNDLE_DETAIL_SINGLE),
            BundleModel::class.java
        )
    }
}