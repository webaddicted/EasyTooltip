package com.example.deepaksharma.tooltip;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.deepaksharma.tooltip.databinding.ActivityMainBinding;
import com.example.deepaksharma.tooltip.tooltip.Overlay;
import com.example.deepaksharma.tooltip.tooltip.Pointer;
import com.example.deepaksharma.tooltip.tooltip.ToolTip;
import com.example.deepaksharma.tooltip.tooltip.TourGuide;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding mBinding;
    private TourGuide mTutorialHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        tooltip(this, "First button", " my activity define here", (Gravity.RIGHT | Gravity.BOTTOM), mBinding.btnAdd1);
        clickListener();
    }

    private void clickListener() {
        mBinding.btnAdd1.setOnClickListener(this);
        mBinding.btnAdd2.setOnClickListener(this);
        mBinding.btnAdd3.setOnClickListener(this);
        mBinding.btnAdd4.setOnClickListener(this);
        mBinding.btnAdd5.setOnClickListener(this);
        mBinding.btnAdd6.setOnClickListener(this);

    }

    public void tooltip(Activity activity, String title, String description, int gravity, View view) {
        cleanTooltip();
        if (mTutorialHandler == null) {
            Animation enterAnimation = new AlphaAnimation(0f, 1f);
            enterAnimation.setDuration(600);
            enterAnimation.setFillAfter(true);

            Animation exitAnimation = new AlphaAnimation(1f, 0f);
            exitAnimation.setDuration(600);
            exitAnimation.setFillAfter(true);
            mTutorialHandler = TourGuide.init(activity).with(TourGuide.Technique.Click)
                    .setPointer(new Pointer()
                            .setGravity(Gravity.CENTER)
                            .setColor(getResources().getColor(R.color.tooltip_pointer_color)))
                    .setOverlay(new Overlay()
                            .setHoleRadius(160)
                            .setBackgroundColor(getResources().getColor(R.color.tooltip_background_color))
                            .setOnClickListener(view1 -> cleanTooltip())
                            .setEnterAnimation(enterAnimation)
                            .setExitAnimation(exitAnimation)
                    );

        }
        mTutorialHandler.setToolTip(new ToolTip()
                .setTitle(title)
                .setDescription(description)
                .setGravity(gravity)
        ).playOn(view);
        /* setup enter and exit animation */
    }

    public void cleanTooltip() {
        if (mTutorialHandler != null)
            mTutorialHandler.cleanUp();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add1:
                tooltip(this, "First button", " my activity define here", (Gravity.LEFT), mBinding.btnAdd2);
                break;
            case R.id.btn_add2:
                tooltip(this, "Second button", " my activity define here", Gravity.TOP, mBinding.btnAdd3);
                break;
            case R.id.btn_add3:
                tooltip(this, "Third button", " my activity define here", Gravity.RIGHT, mBinding.btnAdd4);
                break;
            case R.id.btn_add4:
                tooltip(this, "Fourth button", " my activity define here", (Gravity.TOP | Gravity.LEFT), mBinding.btnAdd5);
                break;
            case R.id.btn_add5:
                tooltip(this, "Fifth button", " my activity define here", (Gravity.TOP | Gravity.RIGHT), mBinding.btnAdd6);
                break;
            case R.id.btn_add6:
                cleanTooltip();
                break;

        }
    }
}
