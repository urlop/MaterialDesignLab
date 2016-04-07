package com.example.ruby.materiallab.utils;

import android.content.Context;
import android.util.AttributeSet;

import com.example.ruby.materiallab.utils.shape_image_view.ShaderHelper;
import com.example.ruby.materiallab.utils.shape_image_view.ShaderImageView;
import com.example.ruby.materiallab.utils.shape_image_view.SvgShader;

public class ShapeImageView extends ShaderImageView {

    public ShapeImageView(Context context) {
        super(context);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public ShaderHelper createImageViewHelper() {
        return new SvgShader();
    }
}
