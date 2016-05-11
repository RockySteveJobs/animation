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

public class AnticipationFragment extends BaseAnimationFragment {
    private View shape;
    private View surface;

    public static BaseAnimationFragment newInstance(Principle principle) {
        final BaseAnimationFragment fragment = new AnticipationFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_PRINCIPLE, principle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_anticipation, container, false);
        shape = view.findViewById(R.id.shape);
        surface = view.findViewById(R.id.surface);
        return view;
    }

    private Interpolator getInterpolator(double p1X, double p1Y, double p2X, double p2Y) {
        return PathInterpolatorCompat.create((float) p1X, (float) p1Y, (float) p2X, (float) p2Y);
    }

    private void start() {
        this.shape.setPivotX(this.shape.getWidth() >> 1);
        this.shape.setPivotY(this.shape.getHeight());
        int changeX = surface.getWidth() >> 1;
        ObjectAnimator translationX = ObjectAnimator.ofFloat(this.shape, View.TRANSLATION_X, 0, -changeX);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(this.shape, View.ROTATION, 0, -20, -10, -30);

        AnimatorSet dismissSet = new AnimatorSet();
        ObjectAnimator translationXX = ObjectAnimator.ofFloat(this.shape, View.TRANSLATION_X, -changeX, -changeX - this.shape.getWidth());
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this.shape, View.TRANSLATION_Y, 0, this.shape.getHeight());
        ObjectAnimator alpha = ObjectAnimator.ofFloat(this.shape, View.ALPHA, 0);
        ObjectAnimator rotation2 = ObjectAnimator.ofFloat(this.shape, View.ROTATION, -30, -90);
        dismissSet.playTogether(translationXX, translationY, alpha, rotation2);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
//        set.setInterpolator(getInterpolator(0.895, 0.030, 0.685, 0.220));
        set.playSequentially(translationX, rotation, dismissSet);
        set.setInterpolator(EaseUtil.easeInOut);
        set.start();
//        this.shape.animate()
//                .translationY(this.shape.getHeight())
//                .alpha(0)
//                .setStartDelay(800)
//                .setDuration(600)
//                .start();
    }

    @Override
    public void onAnimationReset() {
        reset(this.shape);
    }

    @Override
    public void onAnimationStart() {
        start();
    }

}
