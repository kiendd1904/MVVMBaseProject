package com.rikkei.kiendd.mvvmbaseproject.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.rikkei.kiendd.mvvmbaseproject.R;
import com.rikkei.kiendd.mvvmbaseproject.databinding.DialogLoadingBinding;

import androidx.databinding.DataBindingUtil;

public class DialogUtil {
    private static boolean shown = false;

    private AlertDialog dialog = null;

    private DialogLoadingBinding binding;

    private static DialogUtil instance = null;

    private Context context;

    public static DialogUtil getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new DialogUtil(context);
            return instance;
        }
    }

    private DialogUtil(Context context) {
        this.context = context;
        if (context != null && !DialogUtil.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_loading, null, false);
            View dialogView = binding.getRoot();
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            dialog = dialogBuilder.create();
            if (dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // we cannot close dialog when we press back button
                }
                return false;
            });
            binding.pbLoading.getIndeterminateDrawable().setColorFilter(context.getColor(R.color.colorPrimary),
                    PorterDuff.Mode.SRC_IN);
        }
    }

    public void show() {
        if (!((Activity) context).isFinishing()) {
            if (!DialogUtil.isShown() && dialog != null) {
                forceShown();
                dialog.show();
            }
        }
    }

    public void hidden() {
        if (DialogUtil.isShown() && dialog != null && dialog.isShowing()) {
            initialize();
            dialog.dismiss();
        }
    }

    private static boolean isShown() {
        return shown;
    }

    private static void forceShown() {
        shown = true;
    }

    private static void initialize() {
        shown = false;
    }

    public void destroyLoadingDialog() {
        instance = null;
    }
}
