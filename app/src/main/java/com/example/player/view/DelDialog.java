package com.example.player.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.player.R;

public class DelDialog extends Dialog {
    /* Constructor */
    public  DelDialog(Context context) {
        super(context);
    }

    public  DelDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public  DelDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    /* Builder */
    public static class Builder {
        private TextView tvTitle, tvWarning, tvInfo,tvCancel,tvConfirm;

        private View mLayout;
        private View.OnClickListener mButtonCancelClickListener;
        private View.OnClickListener mButtonConfirmClickListener;

        private DelDialog mDialog;

        public Builder(Context context) {
            mDialog = new DelDialog(context, R.style.custom_dialog);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 加载布局文件
            mLayout = inflater.inflate(R.layout.dialog_del, null, false);
            // 添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            tvTitle = (TextView) mLayout.findViewById(R.id.tv_title);
            tvInfo = (TextView) mLayout.findViewById(R.id.tv_info);
            tvCancel = (TextView) mLayout.findViewById(R.id.tv_cancel);
            tvConfirm = (TextView) mLayout.findViewById(R.id.tv_confirm);
        }

        /**
         * 设置 Dialog 标题
         */
        public Builder setTitle(String title) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
            return this;
        }

        /**
         * 设置 Warning
         */
        public Builder setWarning(String waring) {
            tvWarning.setText(waring);
            if (waring == null || waring.equals("")) {
                tvWarning.setVisibility(View.GONE);
            }
            return this;
        }

        /**
         * 设置 Info
         */
        public Builder setInfo(String message) {
            tvInfo.setText(message);
            return this;
        }

        /**
         * 设置取消按钮文字和监听
         */
        public Builder setButtonCancel(String text, View.OnClickListener listener) {
            tvCancel.setText(text);
            mButtonCancelClickListener = listener;
            return this;
        }

        /**
         * 设置确认按钮文字和监听
         */
        public Builder setButtonConfirm(String text, View.OnClickListener listener) {
            tvConfirm.setText(text);
            mButtonConfirmClickListener = listener;
            return this;
        }

        public DelDialog create() {
            tvCancel.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    mButtonCancelClickListener.onClick(view);
                }
            });

            tvConfirm.setOnClickListener(new android.view.View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    mButtonConfirmClickListener.onClick(view);
                }
            });

            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);
            mDialog.setCanceledOnTouchOutside(false);
            return mDialog;
        }
    }
}
