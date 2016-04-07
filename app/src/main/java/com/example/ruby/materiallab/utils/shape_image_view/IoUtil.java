package com.example.ruby.materiallab.utils.shape_image_view;

import java.io.InputStream;

public class IoUtil {
    public static final void closeQuitely(InputStream is) {
        if(is != null) {
            try {
                is.close();
            } catch (Throwable ignored) {
                //ignored
            }
        }
    }
}
