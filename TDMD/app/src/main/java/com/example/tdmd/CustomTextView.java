package com.example.tdmd;

import android.content.Context;
import android.widget.TextView;

public class CustomTextView extends androidx.appcompat.widget.AppCompatTextView {
    // You could also just apply your default style if none is given
    public CustomTextView(Context context) {
        super(context, null, R.attr.customTextViewStyle);
    }
}
