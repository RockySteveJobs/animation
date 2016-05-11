package io.github.vhow.animation.principles;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.vhow.animation.EaseUtil;
import io.github.vhow.animation.R;

public class SquashAndStretchFragment extends BaseAnimationFragment {
    private View shape;
    private View surface;

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
        shape = view.findViewById(R.id.shape);
        surface = view.findViewById(R.id.surface);
        return view;
    }

    private void start() {
        this.shape.setPivotX(this.shape.getWidth());
        float destX = surface.getX() - shape.getWidth() - shape.getX();
        AnimatorSet set = new AnimatorSet();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(this.shape, View.TRANSLATION_X, 0, destX);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(this.shape, View.SCALE_X, 1f, 1f, .5f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(this.shape, View.SCALE_Y, 1f, 1f, 1.6f);
        set.setDuration(300);
        set.setInterpolator(EaseUtil.easeInExpo);

        set.play(translationX)
                .with(scaleX)
                .with(scaleY);

        set.start();
        this.shape.animate()
                .translationY((this.surface.getHeight() - this.shape.getHeight()) >> 1)
                .alpha(0)
                .setStartDelay(300)
                .setDuration(600)
                .start();
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
