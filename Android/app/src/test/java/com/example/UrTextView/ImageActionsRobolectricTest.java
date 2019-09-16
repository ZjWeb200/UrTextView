package com.example.UrTextView;

import android.content.Intent;
import android.os.Build;
import android.provider.MediaStore;

import com.example.UrTextView.imagepipeline.ImageActions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;

import static org.junit.Assert.*;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP)
public class ImageActionsRobolectricTest {

    @Test
    public void cameraActionSendsSystemCameraIntent() {
    }

    @Test
    public void galleryActionSendsSystemPickIntent() {

    }
}
