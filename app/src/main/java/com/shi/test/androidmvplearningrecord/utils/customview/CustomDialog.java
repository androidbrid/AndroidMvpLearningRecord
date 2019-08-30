package com.shi.test.androidmvplearningrecord.utils.customview;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.*;
import android.widget.Button;
import android.widget.TextView;
import com.shi.test.androidmvplearningrecord.R;

public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);
    }

    public CustomDialog(Context context, int theme) {
        super(context, theme);
    }


    public static class Builder {
        private Context context;
        private String title;
        private String message;
        private ColorStateList messageColor;
        private float messageSize;
        private String positiveButtonText;
        private String negativeButtonText;
        private View contentView;
        private ViewGroup contentContainer;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private int positiveButtonBackground;
        private boolean nocontentnoPadding;//如果设置中心无边距则无边距

        public Builder setNocontentnoPadding(boolean nocontentnoPadding) {
            this.nocontentnoPadding = nocontentnoPadding;
            return this;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        public Builder setMessageTextColor(ColorStateList messageColor) {
            this.messageColor = messageColor;
            return this;
        }

        public Builder setMessageTextSize(float size) {
            this.messageSize = size;
            return this;
        }

        /**
         * 设置确定按钮的背景
         */
        public Builder setPositiveButtonBackground(int positiveButtonBackground) {
            this.positiveButtonBackground = positiveButtonBackground;
            return this;
        }

        /**
         * Set the Dialog title from resource
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         */
        public Builder setPositiveButton(int positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context
                    .getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context
                    .getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }


        public Builder setNegativeButton(String negativeButtonText,
                                         DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        int theme;

        public Builder setTheme(int theme) {
            this.theme = theme;
            return this;
        }

        //确定按钮
        public CustomDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CustomDialog dialog = new CustomDialog(context, theme);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            View layout = inflater.inflate(R.layout.view_custom_dialog, null);
            //            dialog.addContentView(layout, new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            //                                                                         ViewGroup.LayoutParams.WRAP_CONTENT));
            //            Window window = dialog.getWindow();
            //            DisplayMetrics dm = DisplayUtil.getDisplayMetrics(dialog.getContext());
            //            WindowManager.LayoutParams lp = window.getAttributes();
            //            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            //            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            //            window.setAttributes(lp);
            //            window.getDecorView().setPadding((int) (50*dm.density),0, (int) (50*dm.density), 0);

            Window window = dialog.getWindow();
            WindowManager.LayoutParams windowpane = window.getAttributes();
            //            window.setGravity(Gravity.BOTTOM);
            Rect rect = new Rect();
            View view1 = window.getDecorView();
            view1.getWindowVisibleDisplayFrame(rect);
            windowpane.height = WindowManager.LayoutParams.WRAP_CONTENT;
            windowpane.width = (int) context.getResources().getDimension(R.dimen.qb_px_300);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setAttributes(windowpane);

            // set the dialog title
            if (title != null) {
                ((TextView) layout.findViewById(R.id.title)).setText(title);
            } else {
                layout.findViewById(R.id.title).setVisibility(View.GONE);
            }

            // set content view
            contentContainer = (ViewGroup) layout.findViewById(R.id.contentContainer);
            if (contentView != null) {
                contentContainer.removeAllViews();
                contentContainer.addView(contentView);
                if (nocontentnoPadding) {
                    contentContainer.setPadding(0, 0, 0, 0);
                }
            } else {
                // set the content message
                if (message != null) {
                    ((TextView) layout.findViewById(R.id.message)).setText(message);
                } else {
                    layout.findViewById(R.id.message).setVisibility(View.GONE);
                }

                if (messageColor != null) {
                    ((TextView) layout.findViewById(R.id.message)).setTextColor(messageColor);
                }

                if (messageSize != 0.0) {
                    ((TextView) layout.findViewById(R.id.message)).setTextSize(TypedValue.COMPLEX_UNIT_PX, messageSize);
                }
            }

            // set the confirm button
            if (positiveButtonText == null) {
                // 没有确定按钮
                if (negativeButtonText == null) {
                    layout.findViewById(R.id.dialog_bottom).setVisibility(View.GONE);
                    layout.findViewById(R.id.dialog_line_horizon).setVisibility(View.GONE);
                } else {
                    layout.findViewById(R.id.dialog_line).setVisibility(View.GONE);
                    layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
                    //layout.findViewById(R.id.negativeButton).setBackgroundResource(R.drawable.);
                    layout.findViewById(R.id.negativeButton)
                          .setBackgroundColor(context.getResources().getColor(R.color.white));
                }
            } else {
                // 有确定按钮
                if (negativeButtonText == null) {
                    layout.findViewById(R.id.dialog_line).setVisibility(View.GONE);
                    layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
                    if (positiveButtonBackground != 0) {//如果设置了背景颜色那么就给背景颜色
                        layout.findViewById(R.id.positiveButton).setBackgroundResource(positiveButtonBackground);
                    } else {
                        //layout.findViewById(R.id.positiveButton).setBackgroundResource(R.drawable.);
                        layout.findViewById(R.id.positiveButton)
                              .setBackgroundColor(context.getResources().getColor(R.color.white));
                    }
                }
            }


            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton))
                        .setText(positiveButtonText);
                if (positiveButtonClickListener == null) {
                    positiveButtonClickListener = new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    };
                }
                (layout.findViewById(R.id.positiveButton))
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.dismiss();
                                positiveButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(
                        View.GONE);
            }

            //取消按钮
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton))
                        .setText(negativeButtonText);
                if (negativeButtonClickListener == null) {
                    negativeButtonClickListener = new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    };
                }
                (layout.findViewById(R.id.negativeButton))
                        .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.dismiss();
                                negativeButtonClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                            }
                        });
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }

            dialog.setContentView(layout);

            dialog.setCancelable(cancelable);
            return dialog;
        }

        private boolean cancelable;

        public void setCancelable(boolean b) {
            cancelable = b;
        }

    }
    
}
