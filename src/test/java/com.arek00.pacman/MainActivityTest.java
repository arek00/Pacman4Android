package com.arek00.pacman;

import android.app.Activity;
import com.arek00.pacman.HelloAndroidActivity;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "./src/main/AndroidManifest.xml")
public class MainActivityTest {

    @org.junit.Test
    public void titleIsCorrect() throws Exception {
        Activity activity = Robolectric.setupActivity(HelloAndroidActivity.class);
        assertTrue(activity.getTitle().toString().equals("Pacman4Android"));
    }
}