package com.example.tablayout;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTwoEmptyState2 extends Fragment {

    private static final String TAG_TAKE_PICTURE = "TAKE_PICTURE";

    // This is the request code when start camera activity use implicit intent.
    public static final int REQUEST_CODE_TAKE_PICTURE = 1;

    // This imageview is used to show camera taken picture.
    private ImageView takePictureImageView;

    // This output image file uri is used by camera app to save taken picture.
    private Uri outputImgUri;

    // Save the camera taken picture in this folder.
    private File pictureSaveFolderPath;

    public FragmentTwoEmptyState2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View postView = inflater.inflate(R.layout.fragment_two_empty_state2, container, false);


        // Get this app's external cache directory, manipulate this directory in app do not need android os system permission check.
        // The cache folder is application specific, when the app is uninstalled it will be removed also.
        pictureSaveFolderPath = getActivity().getExternalCacheDir();

        // Get the display camera taken picture imageview object.
        takePictureImageView = postView.findViewById(R.id.take_picture_image_view);

        // When you click the image view object, all the taken pictures will be shown one by one like slide.

        // Get the take picture button object.
        Button takePictureButton = postView.findViewById(R.id.take_picture_button);

        // When the button is clicked.
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    // Create a random image file name.
                    String imageFileName = "outputImage_" + System.currentTimeMillis() + ".jpg";

                    // Construct a output file to save camera taken picture temporary.
                    File outputImageFile = new File(pictureSaveFolderPath, imageFileName);

                    // If cached temporary file exist then delete it.
                    if (outputImageFile.exists()) {
                        outputImageFile.delete();
                    }

                    // Create a new temporary file.
                    outputImageFile.createNewFile();

                    // Get the output image file Uri wrapper object.
                    outputImgUri = getImageFileUriByOsVersion(outputImageFile);

                    // Startup camera app.
                    // Create an implicit intent which require take picture action..
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // Specify the output image uri for the camera app to save taken picture.
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputImgUri);
                    // Start the camera activity with the request code and waiting for the app process result.
                    startActivityForResult(cameraIntent, REQUEST_CODE_TAKE_PICTURE);

                }catch(IOException ex)
                {
                    Log.e(TAG_TAKE_PICTURE, ex.getMessage(), ex);
                }
            }
        });

        return postView;
    }
    /* Get the file Uri object by android os version.
     *  return a Uri object. */
    private Uri getImageFileUriByOsVersion(File file)
    {
        Uri ret = null;

        // Get output image unique resource identifier. This uri is used by camera app to save taken picture temporary.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
        {
            // For android os version bigger than or equal to 7.0 use FileProvider class.
            // Otherwise android os will throw FileUriExposedException.
            // Because the system considers it is unsafe to use local real path uri directly.
            Context ctx = getActivity();
            ret = FileProvider.getUriForFile(ctx, "com.example.fileprovider", file);
        }
        else
        {
            // For android os version less than 7.0 there are no safety issue,
            // So we can get the output image uri by file real local path directly.
            ret = Uri.fromFile(file);
        }

        return ret;
    }

    /* This method is used to process the result of camera app. It will be invoked after camera app return.
    It will show the camera taken picture in the image view component. */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // Process result for camera activity.
            if (requestCode == REQUEST_CODE_TAKE_PICTURE) {

                // If camera take picture success.
                if (resultCode == RESULT_OK) {

                    // Get content resolver.
                    ContentResolver contentResolver = getActivity().getContentResolver();

                    // Use the content resolver to open camera taken image input stream through image uri.
                    InputStream inputStream = contentResolver.openInputStream(outputImgUri);

                    // Decode the image input stream to a bitmap use BitmapFactory.
                    Bitmap pictureBitmap = BitmapFactory.decodeStream(inputStream);

                    //Resize Bitmap Code while maintaining Aspect Ratio
                    int maxHeight = 1024;
                    int maxWidth = 1024;
                    float scale = Math.min(((float)maxHeight / pictureBitmap.getWidth()), ((float)maxWidth / pictureBitmap.getHeight()));
                    Matrix matrix = new Matrix();
                    matrix.postScale(scale, scale);
                    Bitmap rescaleBitmap;
                    rescaleBitmap = Bitmap.createBitmap(pictureBitmap, 0, 0, pictureBitmap.getWidth(), pictureBitmap.getHeight(), matrix, true);

                    // Set the RESIZED camera taken image bitmap in the image view component to display.
                    takePictureImageView.setImageBitmap(rescaleBitmap);
                }
            }
        }catch(FileNotFoundException ex)
        {
            Log.e(TAG_TAKE_PICTURE, ex.getMessage(), ex);
        }
    }

}

