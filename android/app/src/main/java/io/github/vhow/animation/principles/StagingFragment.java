package io.github.vhow.animation.principles;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import io.github.vhow.animation.EaseUtil;
import io.github.vhow.animation.R;

public class StagingFragment extends BaseAnimationFragment {
    private View shape;
    private View shape1;
    private View shape2;

    public static BaseAnimationFragment newInstance(Principle principle) {
        final BaseAnimationFragment fragment = new StagingFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_PRINCIPLE, principle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_staging, container, false);
        shape = view.findViewById(R.id.shape);
        shape1 = view.findViewById(R.id.shape1);
        shape2 = view.findViewById(R.id.shape2);
        return view;
    }

    private Interpolator getInterpolator(double p1X, double p1Y, double p2X, double p2Y) {
        return PathInterpolatorCompat.create((float) p1X, (float) p1Y, (float) p2X, (float) p2Y);
    }

    private void start() {
        this.shape.setPivotX(0);
        this.shape.setPivotY(this.shape.getHeight());

        AnimatorSet fadeSet = new AnimatorSet();
        fadeSet.playTogether(
                ObjectAnimator.ofFloat(this.shape1, View.ALPHA, .2f),
                ObjectAnimator.ofFloat(this.shape2, View.ALPHA, .2f)
        );

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.setInterpolator(EaseUtil.easeInOut);
        set.playSequentially(
                fadeSet,
                ObjectAnimator.ofFloat(this.shape, View.ROTATION, -30)
        );
        set.start();
    }

    @Override
    public void onAnimationReset() {
        reset(this.shape);
        reset(this.shape1);
        reset(this.shape2);
    }

    @Override
    public void onAnimationStart() {
        start();
    }

}
