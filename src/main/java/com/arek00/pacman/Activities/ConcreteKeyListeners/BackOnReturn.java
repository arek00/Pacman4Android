package com.arek00.pacman.Activities.ConcreteKeyListeners;

import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import com.arek00.pacman.Utils.Validators.NullPointerValidator;

/**
 *
 */
public class BackOnReturn implements OnReturnKeyStrategy {

    private Class<?> activityToOpen;
    private Activity currentActivity;


    public BackOnReturn(Activity currentActivity, Class<?> activityToOpen) {
        NullPointerValidator.validate(currentActivity);
        NullPointerValidator.validate(activityToOpen);

        this.currentActivity = currentActivity;
        this.activityToOpen = activityToOpen;
    }

    public void executeOnReturnKey() {
        Intent intent = new Intent(currentActivity, activityToOpen);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }
}
