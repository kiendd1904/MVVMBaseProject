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

import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;

public class DialogUtil {
    private static boolean shown = false;

    private AlertDialog loadingDialog = null;

    private AlertDialog errorDialog = null;
    private AlertDialog.Builder builder;

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

        // LoadingDialog
        if (context != null && !DialogUtil.isShown()) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.dialog_loading, null, false);
            View dialogView = binding.getRoot();
            dialogBuilder.setView(dialogView);
            dialogBuilder.setCancelable(false);
            loadingDialog = dialogBuilder.create();
            if (loadingDialog.getWindow() != null) {
                loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            loadingDialog.setCancelable(false);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setOnKeyListener((dialog, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    // we cannot close loadingDialog when we press back button
                }
                return false;
            });
            binding.pbLoading.getIndeterminateDrawable().setColorFilter(context.getResources().getColor(R.color.colorPrimary),
                    PorterDuff.Mode.SRC_IN);
        }

        // ErrorDialog
        builder = new AlertDialog.Builder(context)
                .setOnDismissListener(dialog1 -> {
                    initialize();
                });
    }

    public void showLoading() {
        if (!((Activity) context).isFinishing()) {
            if (!DialogUtil.isShown() && loadingDialog != null) {
                forceShown();
                loadingDialog.show();
            }
        }
    }

    public void hideLoading() {
        if (DialogUtil.isShown() && loadingDialog != null && loadingDialog.isShowing()) {
            initialize();
            loadingDialog.dismiss();
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

    public DialogUtil(Context context, String title, String message,
                      String positive, OnDialogListener onPositiveClick,
                      String negative, OnDialogListener onNegativeClick,
                      boolean isCancelable) {
        builder = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positive, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onPositiveClick != null) {
                        onPositiveClick.onClick();
                    }
                })
                .setNegativeButton(negative, (dialog1, which) -> {
                    dialog1.cancel();
                    if (onNegativeClick != null) {
                        onNegativeClick.onClick();
                    }
                })
                .setOnDismissListener(dialog1 -> {
                    initialize();
                })
                .setCancelable(isCancelable);
        errorDialog = builder.create();
        errorDialog.show();
//        RxBus.getInstance().subscribe(value -> {
//            if (!(value instanceof String))
//                return;
//            if (value.equals(Define.Bus.OPEN_NOTIFICATION)) {
//                if (errorDialog != null) {
//                    errorDialog.dismiss();
//                }
//            }
//        });
    }

    public DialogUtil setTitle(String title) {
        builder.setTitle(title);
        return this;
    }

    public DialogUtil setTitle(@StringRes int title) {
        builder.setTitle(title);
        return this;
    }

    public DialogUtil setMessage(String message) {
        builder.setMessage(message);
        return this;
    }

    public DialogUtil setMessage(@StringRes int message) {
        builder.setMessage(message);
        return this;
    }

    public DialogUtil setPositiveButton(String label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public DialogUtil setPositiveButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setPositiveButton(label, (dialog, id) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public DialogUtil setNegativeButton(String label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public DialogUtil setNegativeButton(@StringRes int label, OnDialogListener onDialogListener) {
        builder.setNegativeButton(label, (dialog, which) -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public DialogUtil setCancelable(boolean isCancelable) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            initialize();
        });
        return this;
    }

    public DialogUtil setCancelable(boolean isCancelable, OnDialogListener onDialogListener) {
        builder.setCancelable(isCancelable);
        builder.setOnCancelListener(dialog -> {
            dialog.cancel();
            initialize();
            if (onDialogListener != null) {
                onDialogListener.onClick();
            }
        });
        return this;
    }

    public void showError() {
        hideLoading();
        if (!isShown() && builder != null) {
            errorDialog = builder.create();
            errorDialog.show();
            forceShown();
        }
    }

    public interface OnDialogListener {
        void onClick();
    }
}
