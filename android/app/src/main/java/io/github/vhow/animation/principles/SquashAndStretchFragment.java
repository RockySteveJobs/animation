package io.github.vhow.animation.principles;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

import io.github.vhow.animation.EaseUtil;
import io.github.vhow.animation.R;

public class SquashAndStretchFragment extends BaseAnimationFragment {
    private View mShape;
    private View mWall;

    public static BaseAnimationFragment newInstance(Principle principle) {
        final BaseAnimationFragment fragment = new SquashAndStretchFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_PRINCIPLE, principle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_squash_and_stretch, container, false);
        mShape = view.findViewById(R.id.shape);
        mWall = view.findViewById(R.id.surface);
        return view;
    }

    private void start() {
        reset(mShape);

        ObjectAnimator backTranslateX = ObjectAnimator.ofFloat(mShape, View.TRANSLATION_X, 0, -mShape.getWidth());
        ObjectAnimator backScaleX = ObjectAnimator.ofFloat(mShape, View.SCALE_X, 1.5f);
        ObjectAnimator backScaleY = ObjectAnimator.ofFloat(mShape, View.SCALE_Y, 0.5f);

        AnimatorSet backward = new AnimatorSet();
        backward.setDuration(300);
        backward.play(backTranslateX).with(backScaleX).with(backScaleY);
        backward.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mShape.setPivotX(mShape.getWidth());
            }
        });
        backward.setInterpolator(new OvershootInterpolator());

        final float distance = mWall.getX() - mShape.getX();

        ObjectAnimator forwardTranslateX =
                ObjectAnimator.ofFloat(mShape, View.TRANSLATION_X, distance - mShape.getWidth());

        ObjectAnimator forwardScaleX = ObjectAnimator.ofFloat(mShape, View.SCALE_X, mShape.getScaleX(), 1f, 1f, 0.5f);
        ObjectAnimator forwardScaleY = ObjectAnimator.ofFloat(mShape, View.SCALE_Y, mShape.getScaleY(), 1f, 1f, 1.5f);

        AnimatorSet forward = new AnimatorSet();
        forward.play(forwardTranslateX).with(forwardScaleX).with(forwardScaleY);
        forward.setStartDelay(100);
        forward.setDuration(300);
        forward.setInterpolator(EaseUtil.easeInExpo);

        ObjectAnimator downTranslateY =
                ObjectAnimator.ofFloat(mShape, View.TRANSLATION_Y, mWall.getHeight() / 2 + mShape.getHeight() / 2);
        ObjectAnimator downAlpha = ObjectAnimator.ofFloat(mShape, View.ALPHA, 1, 0.8f, 0);
        AnimatorSet down = new AnimatorSet();
        down.setStartDelay(300);
        down.playTogether(downTranslateY, downAlpha);
        down.setInterpolator(EaseUtil.easeInExpo);
        down.setDuration(800);

        AnimatorSet set = new AnimatorSet();
        set.play(backward).before(forward).before(down);
        set.start();
    }

    @Override
    public void onAnimationReset() {
        reset(mShape);
    }

    @Override
    public void onAnimationStart() {
        start();
    }

}
