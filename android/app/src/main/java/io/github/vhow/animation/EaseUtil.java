package io.github.vhow.animation;

import android.support.v4.view.animation.PathInterpolatorCompat;
import android.view.animation.Interpolator;

public class EaseUtil {
    public static Interpolator ease = PathInterpolatorCompat.create(.25f, 1, .25f, 1);
    public static Interpolator easeIn = PathInterpolatorCompat.create(.42f, 0, 1, 1);
    public static Interpolator easeOut = PathInterpolatorCompat.create(0, 0, .58f, 1);
    public static Interpolator easeInOut = PathInterpolatorCompat.create(.42f, 0, .58f, 1);
    public static Interpolator easeInQuad = PathInterpolatorCompat.create(.55f, .085f, .68f, .53f);
    public static Interpolator easeInCubic = PathInterpolatorCompat.create(.55f, .55f, .67f, .19f);
    public static Interpolator easeInQuart = PathInterpolatorCompat.create(.895f, .03f, .685f, .22f);
    public static Interpolator easeInQuint = PathInterpolatorCompat.create(.755f, .05f, .855f, .06f);
    public static Interpolator easeInSine = PathInterpolatorCompat.create(.47f, 0, .745f, .715f);
    public static Interpolator easeInExpo = PathInterpolatorCompat.create(0.950f, 0.050f, 0.795f, 0.035f);
    public static Interpolator easeInCirc = PathInterpolatorCompat.create(0.600f, 0.040f, 0.980f, 0.335f);
    public static Interpolator easeInBack = PathInterpolatorCompat.create(0.600f, -0.280f, 0.735f, 0.045f);
    public static Interpolator easeOutQuad = PathInterpolatorCompat.create(0.600f, -0.280f, 0.735f, 0.045f);
    public static Interpolator easeOutCubic = PathInterpolatorCompat.create(0.215f, 0.610f, 0.355f, 1.000f);
    public static Interpolator easeOutQuart = PathInterpolatorCompat.create(0.165f, 0.840f, 0.440f, 1.000f);
    public static Interpolator easeOutQuint = PathInterpolatorCompat.create(0.230f, 1.000f, 0.320f, 1.000f);
    public static Interpolator easeOutSine = PathInterpolatorCompat.create(0.390f, 0.575f, 0.565f, 1.000f);
}
