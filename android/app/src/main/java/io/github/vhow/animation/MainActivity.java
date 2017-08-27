package io.github.vhow.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.github.vhow.animation.principles.AnticipationFragment;
import io.github.vhow.animation.principles.BaseAnimationFragment;
import io.github.vhow.animation.principles.FollowThroughAndOverlappingActionFragment;
import io.github.vhow.animation.principles.Principle;
import io.github.vhow.animation.principles.SquashAndStretchFragment;
import io.github.vhow.animation.principles.StagingFragment;
import io.github.vhow.animation.principles.StraightAheadActionAndPoseToPoseFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private BaseAnimationFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show(Principle.SQUASH_AND_STRETCH);
        setTitle(R.string.squash_and_stretch);
    }

    private void show(Principle principle) {
        switch (principle) {
            case SQUASH_AND_STRETCH:
                fragment = SquashAndStretchFragment.newInstance(principle);
                break;
            case ANTICIPATION:
                fragment = AnticipationFragment.newInstance(principle);
                break;
            case STAGING:
                fragment = StagingFragment.newInstance(principle);
                break;
            case STRAIGHT_AHEAD_ACTION_AND_POSE_TO_POSE:
                fragment = StraightAheadActionAndPoseToPoseFragment.newInstance(principle);
                break;
            case FOLLOW_THROUGH_AND_OVERLAPPING_ACTION:
                fragment = FollowThroughAndOverlappingActionFragment.newInstance(principle);
                break;
            case SLOW_IN_AND_SLOW_OUT:
                break;
            case ARC:
                break;
            case SECONDARY_ACTION:
                break;
            case TIMING:
                break;
            case EXAGGERATION:
                break;
            case SOLID_DRAWING:
                break;
            case APPEAL:
                break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();

    }


    public void onClick(View view) {
        Log.d(TAG, "onClick() called with: " + "view = [" + view + "]");
        switch (view.getId()) {
            case R.id.btnStart:
                fragment.onAnimationStart();
                break;
            case R.id.btnReset:
                fragment.onAnimationReset();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Principle principle = null;
        switch (item.getItemId()) {
            case R.id.squash_and_stretch:
                principle = Principle.SQUASH_AND_STRETCH;
                break;
            case R.id.anticipation:
                principle = Principle.ANTICIPATION;
                break;
            case R.id.staging:
                principle = Principle.STAGING;
                break;
            case R.id.straight_and_pose:
                principle = Principle.STRAIGHT_AHEAD_ACTION_AND_POSE_TO_POSE;
                break;
            case R.id.follow_and_overlap:
                principle = Principle.FOLLOW_THROUGH_AND_OVERLAPPING_ACTION;
                break;
            case R.id.slow_in_and_slow_out:
                principle = Principle.SLOW_IN_AND_SLOW_OUT;
                break;
            case R.id.arc:
                principle = Principle.ARC;
                break;
            case R.id.secondary_action:
                principle = Principle.SECONDARY_ACTION;
                break;
            case R.id.timing:
                principle = Principle.TIMING;
                break;
            case R.id.exaggeration:
                principle = Principle.EXAGGERATION;
                break;
            case R.id.solid_drawing:
                principle = Principle.SOLID_DRAWING;
                break;
            case R.id.appeal:
                principle = Principle.APPEAL;
                break;
        }
        show(principle);
        setTitle(item.getTitle());
        return true;
    }
}
