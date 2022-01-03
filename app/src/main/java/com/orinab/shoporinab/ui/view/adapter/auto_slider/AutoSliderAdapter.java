package com.orinab.shoporinab.ui.view.adapter.auto_slider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orinab.shoporinab.R;
import com.orinab.shoporinab.interfaces.OnClickListenerAny;
import com.orinab.shoporinab.utils.tools.GlideTools;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class AutoSliderAdapter extends
        SliderViewAdapter<SliderAdapterVH> {

    private final GlideTools glideTools;
    private List<String> mSliders = new ArrayList<>();

    public AutoSliderAdapter(Context context, GlideTools glideTools) {
        this.glideTools = glideTools;
    }

    public void renewItems(List<String> Sliders) {
        this.mSliders = Sliders;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliders.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String Slider) {
        this.mSliders.add(Slider);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        String slider = mSliders.get(position);
        glideTools.displayImageOriginal(viewHolder.imageViewBackground,slider);
    }

    @Override
    public int getCount() {
        return mSliders.size();
    }



}
