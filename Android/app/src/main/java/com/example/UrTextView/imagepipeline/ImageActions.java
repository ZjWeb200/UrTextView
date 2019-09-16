package com.example.UrTextView.imagepipeline;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;

/**
 * Actions to get images from either the device back camera or photo gallery
 */
public class ImageActions {

    /**
     * Start the built-in back camera to capture a still image.
     * @param activity origin activity in which the intent will be from.
     * @param requestCode request code to get result when the camera activity is dismissed.
     */
    public static void startCameraActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //intent里面加要去的activity名称
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Start photo gallery image picker to select a saved image.
     * @param activity origin activity in which the intent will be from.
     * @param requestCode request code to get result when the gallery activity is dismissed.
     */
    public static void startGalleryActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        activity.startActivityForResult(intent, requestCode); // 回到原来的activity时，第二个activity要回传result。
    }
}
