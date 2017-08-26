package io.github.vhow.animation.principles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.vhow.animation.R;

public class FollowThroughAndOverlappingActionFragment extends BaseAnimationFragment {
    private View shape;

    public static BaseAnimationFragment newInstance(Principle principle) {
        final BaseAnimationFragment fragment = new FollowThroughAndOverlappingActionFragment();
        final Bundle args = new Bundle();
        args.putSerializable(KEY_PRINCIPLE, principle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_follow_through_and_overlapping_action, container, false);
        shape = view.findViewById(R.id.shape);
        return view;
    }

    private void start() {
        this.shape.getMatrix().postSkew((float) (-10 * Math.PI / 180), 0);
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
