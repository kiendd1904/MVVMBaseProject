package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import androidx.annotation.NonNull;

public class FileUtil {

    private static final String IMAGE_TEMP_FOLDER = "OAB_IMAGE";
    // Max image size: 1MB in bit
    public static final int MAX_WIDTH_IMAGE = 800;
    public static final int MAX_HEIGHT_IMAGE = 600;
    // Default bit depth
    public static final String IMAGE_TEMP_PREFIX = "image_message";
    public static final String IMAGE_JPG_POSTFIX = ".jpg";

    public static String getRealPathFromURIByCursor(Context context, Uri contentUri) {
        if (context != null) {
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            if (cursor != null) {
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(proj[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();
                return filePath;
            }
        }
        return null;
    }

    public static String checkSource(String path) {
        String[] subDir = path.split("/");
        for (int i = subDir.length - 1; i >= 0; i--) {
            String pathDir = subDir[0];
            for (int j = 1; j < i; j++) {
                pathDir = pathDir.concat("/").concat(subDir[j]);
            }
            File directory = new File(pathDir);
            if (directory.isDirectory()) {
                String fileName = subDir[i];
                for (int j = i + 1; j < subDir.length; j++) {
                    fileName = fileName.concat("/").concat(subDir[j]);
                }
                fileName = fileName.replace("/", "%2F");
                fileName = fileName.replace(":", "%3A%2F");
                if (i < subDir.length - 1) {
                    return pathDir + "/" + fileName;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public static File createUniqueFileFrom(@NonNull Context context, String filePath) {
        try {
            File imageCacheDir = new File(context.getCacheDir().getPath() + File.separator + IMAGE_TEMP_FOLDER);

            if (!imageCacheDir.exists()) {
                boolean result = imageCacheDir.mkdirs();
            }

            File origin = new File(filePath);
            File tempFile = File.createTempFile(
                    IMAGE_TEMP_PREFIX, IMAGE_JPG_POSTFIX, imageCacheDir);
            copy(origin, tempFile);

            return tempFile;
        } catch (IOException ioe) {
        }

        return null;
    }

    public static boolean deleteFileFrom(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    private static void copy(File src, File dst) throws IOException {
        FileInputStream inStream = new FileInputStream(src);
        FileOutputStream outStream = new FileOutputStream(dst);
        FileChannel inChannel = inStream.getChannel();
        FileChannel outChannel = outStream.getChannel();
        inChannel.transferTo(0, inChannel.size(), outChannel);
        inStream.close();
        outStream.close();
    }

    public static Bitmap prepareImageToBeSent(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        Log.i("BITMAP BEFORE: ", bitmap.getWidth() + " " + bitmap.getHeight());
        if (bitmap.getWidth() > MAX_WIDTH_IMAGE && bitmap.getHeight() > MAX_HEIGHT_IMAGE) {
            if (bitmap.getWidth() > bitmap.getHeight()) {
                bitmap = getResizedBitmap(bitmap, true);
            } else {
                bitmap = getResizedBitmap(bitmap, false);
            }
        }
        Log.i("BITMAP AFTER: ", bitmap.getWidth() + " " + bitmap.getHeight());
        bitmap = BitmapUtil.rotateImageIfNeeded(bitmap, filePath);
        return bitmap;
    }

    private static Bitmap getResizedBitmap(Bitmap bm, boolean isWidthLarger) {
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();
        if (isWidthLarger) {
            int height = MAX_HEIGHT_IMAGE;
            int width = Math.round(height * aspectRatio);
            if (width < MAX_WIDTH_IMAGE) {
                width = MAX_WIDTH_IMAGE;
                height = (int) (width / aspectRatio);
            }
            return Bitmap.createScaledBitmap(bm, width, height, false);
        } else {
            int width = MAX_WIDTH_IMAGE;
            int height = Math.round(width / aspectRatio);
            if (height < MAX_HEIGHT_IMAGE) {
                height = MAX_HEIGHT_IMAGE;
                width = (int) (height * aspectRatio);
            }
            return Bitmap.createScaledBitmap(bm, width, height, false);
        }
    }

    public static Bitmap getResizedBitmap(Bitmap bm) {
        float aspectRatio = bm.getWidth() / (float) bm.getHeight();
        int width = (800 * 2);
        int height = Math.round(width / aspectRatio);
        return Bitmap.createScaledBitmap(bm, width, height, false);
    }

    //region -> Complete filePath

    public static String getRealPathFromUri(Context context, final Uri uri) {
        // DocumentProvider
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static String getDataColumn(Context context, Uri uri, String selection,
                                        String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
    //endregion
}
