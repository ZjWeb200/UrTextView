package com.example.UrTextView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.UrTextView.imagepipeline.ImageActions;
import com.example.UrTextView.utilities.HttpUtilities;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import java.io.IOException;
import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    // Connected to the backend GKE on Google Cloud Platform
    private static final String UPLOAD_HTTP_URL = "http://35.185.200.102/UrTextView/annotate";

    private static final int IMAGE_CAPTURE_CODE = 1;
    private static final int SELECT_IMAGE_CODE = 2;


    private static final int CAMERA_PERMISSION_REQUEST = 1001;

    private MainActivityUIController mainActivityUIController;
    private MainActivitySpeechController mainActivitySpeechController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityUIController = new MainActivityUIController(this);
        mainActivitySpeechController = new MainActivitySpeechController(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivityUIController.resume();
    }

    @Override
    // which item in the menu is clicked
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_capture:
                mainActivityUIController.updateResultView(getString(R.string.result_placeholder));
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    mainActivityUIController.askForPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_REQUEST);
                } else {
                    ImageActions.startCameraActivity(this, IMAGE_CAPTURE_CODE);
                }
                break;
            case R.id.action_gallery:
                mainActivityUIController.updateResultView(getString(R.string.result_placeholder));
                ImageActions.startGalleryActivity(this, SELECT_IMAGE_CODE);
                break;
            case R.id.action_play:
                mainActivitySpeechController.playAudio();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    // construct the menu bar
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    // startActivityForResult回到main activity时，会运行onActivityResult callback，来接住intent 传回来的data.
    //通过request code来识别是哪个intent activity传回来的data。
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK) {
            Bitmap bitmap = null;
            if (requestCode == IMAGE_CAPTURE_CODE) {
                bitmap = (Bitmap) data.getExtras().get("data");
                mainActivityUIController.updateImageViewWithBitmap(bitmap);
            } else if (requestCode == SELECT_IMAGE_CODE) {
                Uri selectedImage = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                    mainActivityUIController.updateImageViewWithBitmap(bitmap);
                } catch (IOException e) {
                    mainActivityUIController.showErrorDialogWithMessage(R.string.reading_error_message);
                }
            }
            if (bitmap != null) {
                final Bitmap  bitmapToUpload = bitmap;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        uploadImage(bitmapToUpload);
                    }
                });
                thread.start();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mainActivitySpeechController.playStop();   // when make the app to background or close the app, stop the speaker if it is playing.
    }

    private void uploadImage(Bitmap bitmap) {
        try {
            HttpURLConnection conn = HttpUtilities.makeHttpPostConnectionToUploadImage(bitmap, UPLOAD_HTTP_URL);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                mainActivityUIController.updateResultView(HttpUtilities.parseOCRResponse(conn));
            } else {
                mainActivityUIController.showInternetError();
            }
        } catch(ClientProtocolException e) {
            e.printStackTrace();
            mainActivityUIController.showInternetError();
        } catch(IOException e) {
            e.printStackTrace();
            mainActivityUIController.showInternetError();
        } catch (JSONException e) {
            e.printStackTrace();
            mainActivityUIController.showInternetError();
        }
    }
}