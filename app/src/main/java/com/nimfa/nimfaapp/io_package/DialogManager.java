package com.nimfa.nimfaapp.io_package;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.Spanned;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nimfa.nimfaapp.R;

/**
 * NimfaApp
 * Created by Alex on 05.07.2017.
 * contact on luck.alex13@gmail.com
 * © Alexander Novikov 2017
 */

public enum DialogManager {
    Instance;

    private MaterialDialog dialog;
    private Context context;

    public void init(Context externalContext) {

    }

    public void showAlert(Context context, CharSequence title, CharSequence contentText, MaterialDialog.SingleButtonCallback positiveCallback, DialogInterface.OnCancelListener cancelCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.horosho)
                .onPositive(positiveCallback)
                .cancelListener(cancelCallback);
        dialog = builder.build();
        dialog.show();
    }

    public void showAlert(Context context, int title, int contentText, MaterialDialog.SingleButtonCallback positiveCallback, DialogInterface.OnCancelListener cancelCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.horosho)
                .onPositive(positiveCallback)
                .cancelListener(cancelCallback);
        dialog = builder.build();
        dialog.show();
    }

    public void showAlert(Context context, int title, int contentText, MaterialDialog.SingleButtonCallback positiveCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok)
                .negativeText(R.string.action_cancel)
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                    }
                })
                .onPositive(positiveCallback);

        dialog = builder.build();
        dialog.show();
    }

    public void showAlert(Context context, int title, String contentText, MaterialDialog.SingleButtonCallback positiveCall, MaterialDialog.SingleButtonCallback negativeCall) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.all_rigth)
                .negativeText(R.string.action_cancel)
                .onPositive(positiveCall)
                .onNegative(negativeCall);
        dialog = builder.build();
        dialog.show();
    }

    public void showMessage(Context context, int title, int contentText) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            }
                );
        dialog = builder.build();
        dialog.show();
    }

    public void showMessage(Context context, int title, int contentText, MaterialDialog.SingleButtonCallback callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok)
                .onPositive(callback);
        dialog = builder.build();
        dialog.show();
    }

    public void showMessage(Context context, int title, String contentText) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.ok);
        dialog = builder.build();
        dialog.show();
    }

    public void showAlert(Context context, CharSequence title, CharSequence contentText, MaterialDialog.SingleButtonCallback positiveCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.horosho)
                .onPositive(positiveCallback);

        dialog = builder.build();
        dialog.show();
    }

    public void showAlert(Context context, String title, Spanned contentText, MaterialDialog.SingleButtonCallback positiveCallback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(contentText)
                .positiveText(R.string.horosho)
                .onPositive(positiveCallback);

        dialog = builder.build();
        dialog.show();
    }

    public void showProgressDialog(Context context, int title, int message) {

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .content(message)
                .progress(true, 0)
                .cancelable(false);
        dialog = builder.build();
        dialog.show();

    }

    public void showProgressDialog(Context context) {
        Utils.p("showProgressDialog");
        if (dialog != null && dialog.isShowing()) {
            return;
        }

        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .cancelable(false)
                .content(R.string.please_wait)
                .progress(true, 0);

        dialog = builder.build();
        dialog.show();
    }

    public void showProgressDialog(Context context, int text) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .content(text)
                .progress(true, 0)
                .cancelable(false);
        dialog = builder.build();
        dialog.show();
    }

    public void showInputDialog(Context context, String title, MaterialDialog.InputCallback callback) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context)
                .title(title)
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input(context.getString(R.string.amount), "", false, callback);
        dialog = builder.build();
        dialog.show();
    }

    public void hide() {
        try {
            if (dialog != null && dialog.isShowing()) {
                Utils.p("dialog.dismiss()");
                dialog.dismiss();
            }
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            Utils.p(ex);
            dialog = null;
        }
    }


}
