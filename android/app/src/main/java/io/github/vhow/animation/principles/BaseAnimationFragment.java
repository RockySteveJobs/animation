package io.github.vhow.animation.principles;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

public class BaseAnimationFragment extends Fragment implements Animatable {
    protected static final String KEY_PRINCIPLE = "principle";

    @Override
    public void onAnimationStart() {

    }

    @Override
    public void onAnimationReset() {

    }

    protected void reset(View view) {
        view.animate()
                .alpha(1)
                .scaleX(1).scaleY(1)
                .translationX(0).translationY(0)
                .rotation(0)
                .setInterpolator(new AccelerateInterpolator())
                .setDuration(100)
                .start();
    }
}
