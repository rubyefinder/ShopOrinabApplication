package com.orinab.shoporinab.ui.view.adapter.auto_slider;

import android.view.View;
import android.widget.ImageView;

import com.orinab.shoporinab.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
        }
    }