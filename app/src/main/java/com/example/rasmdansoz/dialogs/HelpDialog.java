package com.example.rasmdansoz.dialogs;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;
import com.example.rasmdansoz.R;

import java.util.Objects;

/**
 * Creator: Javohir Oromov
 * Date: 11/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class HelpDialog extends AlertDialog {
    private AppCompatButton noButton;
    private AppCompatButton YesButton;
    private OnClickListener noButtonClickListener;
    private OnClickListener yesButtonClickListener;
    @SuppressLint("MissingInflatedId")
    public HelpDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_help,null,false);
        setView(view);
        setCancelable(false);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnim;
        noButton = view.findViewById(R.id.no);
        YesButton = view.findViewById(R.id.yes);

        noButton.setOnClickListener(view1 -> {
            if (noButtonClickListener != null){
                noButtonClickListener.onClick();
            }
            dismiss();
        });
        YesButton.setOnClickListener(view2 -> {
            if (yesButtonClickListener != null){
                yesButtonClickListener.onClick();
            }
            dismiss();
        });
    }
    public void setNoButtonClickListener(OnClickListener noButtonClickListener) {
        this.noButtonClickListener = noButtonClickListener;
    }

    public void setYesButtonClickListener(OnClickListener yesButtonClickListener) {
        this.yesButtonClickListener = yesButtonClickListener;
    }

    @FunctionalInterface
   public interface OnClickListener{
        void onClick();
    }
}
