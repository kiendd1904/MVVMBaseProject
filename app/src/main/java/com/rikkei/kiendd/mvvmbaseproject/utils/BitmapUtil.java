package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import androidx.annotation.Nullable;

public class BitmapUtil {

    public static String getBase64Image(Bitmap bitmap, int compressQuality) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressQuality, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.NO_WRAP);
    }

    //region -> Get Image (Bitmap) by Uri or File path

    public static Bitmap getImage(Context context, @Nullable Uri imageUri, @Nullable String filePath) {
        Bitmap tempBm = null;
        int imageRotate = 0;

        if (!TextUtils.isEmpty(filePath)) {
            imageRotate = getCameraPhotoOrientation(context, imageUri, filePath);
        }

        if (imageUri != null && !TextUtils.isEmpty(imageUri.toString()) && context != null) {
            tempBm = getImageByUri(imageUri, context);
        }

        if (tempBm == null && !TextUtils.isEmpty(filePath)) {
            tempBm = getImageByPath(filePath);
        }

        if (tempBm == null && context != null) {
            try {
                tempBm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (tempBm != null) {
            if (imageRotate != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(imageRotate);
                tempBm = Bitmap.createBitmap(tempBm, 0, 0, tempBm.getWidth(), tempBm.getHeight(), matrix, true);
            }
        }
        return tempBm;
    }

    public static Bitmap rotateImageIfNeeded(Bitmap bitmap, String filePath) {
        int imageRotate = getCameraPhotoOrientation(filePath);
        if (imageRotate != 0) {
            Matrix matrix = new Matrix();
            matrix.postRotate(imageRotate);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } else {
            return bitmap;
        }
    }

    public static Bitmap getImageByUri(Uri uri, Context context) {
        InputStream inputStream = null;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(inputStream, null, bmOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;
        return BitmapFactory.decodeStream(inputStream, null, bmOptions);
    }

    public static Bitmap getImageByPath(String filePath) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bmOptions);


        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(filePath, bmOptions);
    }

    //endregion

    //region -> Get Image (Bitmap) and Scale by Uri or File path
    public static Bitmap getImageScale(Context context, @Nullable Uri imageUri, @Nullable String filePath, int targetWidth, int targetHeight) {
        Bitmap tempBm = null;
        int imageRotate = 0;

        if (!TextUtils.isEmpty(filePath)) {
            imageRotate = getCameraPhotoOrientation(context, imageUri, filePath);
        }

        if (imageUri != null && !TextUtils.isEmpty(imageUri.toString()) && context != null) {
            tempBm = getScaleImageByUri(imageUri, context, targetWidth, targetHeight);
        }

        if (tempBm == null && !TextUtils.isEmpty(filePath)) {
            tempBm = getScaleImageByPath(filePath, targetWidth, targetHeight);
        }

        if (tempBm == null && context != null) {
            try {
                tempBm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
                if (tempBm != null && sizeOf(tempBm) > 1) {
                    tempBm = scaleBitmap(tempBm);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (tempBm != null) {
            if (imageRotate != 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(imageRotate);
                tempBm = Bitmap.createBitmap(tempBm, 0, 0, tempBm.getWidth(), tempBm.getHeight(), matrix, true);
            }
        }
        return tempBm;
    }

    public static int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
        int rotate = 0;
        try {
            //context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public static int getCameraPhotoOrientation(String imagePath) {
        int rotate = 0;
        try {
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap) {
        final int bitmapWith = bitmap.getWidth();
        final int bitmapHeight = bitmap.getHeight();
        final double scale = Math.sqrt(sizeOf(bitmap));
        final int scaleWith = (int) (bitmapWith / scale);
        final int scaleHeight = (int) (bitmapHeight / scale);
        return Bitmap.createScaledBitmap(bitmap, scaleWith, scaleHeight, true);
    }

    public static Bitmap getScaleImageByUri(Uri uri, Context context, int targetWidth, int targetHeight) {
        InputStream inputStream = null;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        try {
            inputStream = context.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(inputStream, null, bmOptions);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetWidth, photoH / targetHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeStream(inputStream, null, bmOptions);
    }

    public static Bitmap getScaleImageByPath(String filePath, int targetWidth, int targetHeight) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetWidth, photoH / targetHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(filePath, bmOptions);
    }

    public static int sizeOf(Bitmap data) {
        return data.getByteCount() / 1000000;
    }
    //endregion
}
