package com.mindtickle.integrations.android_sdk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.mindtickle.integrations.android_sdk.R;

/**
 * Created by himanshugupta on 15/10/16.
 */

public class MindTickleButton extends ImageButton {
    public MindTickleButton(Context context) {
        super(context);
        setImageDrawable(getResources().getDrawable(R.drawable.mt_sdk_button));
    }

    public MindTickleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setImageDrawable(getResources().getDrawable(R.drawable.mt_sdk_button));
    }

    public MindTickleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageDrawable(getResources().getDrawable(R.drawable.mt_sdk_button));
    }
}
