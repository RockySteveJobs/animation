package io.github.vhow.animation.principles;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.vhow.animation.EaseUtil;
import io.github.vhow.animation.R;

public class StraightAheadActionAndPoseToPoseFragment extends BaseAnimationFragment {
    private View shape;
    private View shape1;

    public static BaseAnimationFragment newInstance(Principle principle) {
        final BaseAnimationFragment fragment = new StraightAheadActionAndPoseToPoseFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_PRINCIPLE, principle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_straight_ahead_action_and_pose_to_pose, container, false);
        shape = view.findViewById(R.id.shape);
        shape1 = view.findViewById(R.id.shape1);
        return view;
    }

    private void start() {
        animate(this.shape, 0);
        animate(this.shape1, 500);
    }

    private void animate(View view, int delay) {
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(EaseUtil.ease);
        set.play(ObjectAnimator.ofFloat(view, View.SCALE_X, 1.5f))
                .with(ObjectAnimator.ofFloat(view, View.SCALE_Y, 1.5f))
                .after(ObjectAnimator.ofFloat(view, View.ROTATION, -45))
                .before(ObjectAnimator.ofFloat(view, View.ROTATION, 0));
        set.setStartDelay(delay);
        set.setDuration(1000);
        set.start();
    }

    @Override
    public void onAnimationReset() {
        reset(this.shape);
        reset(this.shape1);
    }

    @Override
    public void onAnimationStart() {
        start();
    }

}
