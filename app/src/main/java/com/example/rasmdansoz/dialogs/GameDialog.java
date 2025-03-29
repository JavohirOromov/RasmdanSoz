package com.example.rasmdansoz.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.rasmdansoz.R;

import java.util.Objects;

/**
 * Creator: Javohir Oromov
 * Project: RasmdanSo'z
 * Date: 29/03/25
 * Javohir's MacBook Air
 */
public class GameDialog extends AlertDialog {
    private AppCompatButton noButton;
    private AppCompatButton YesButton;
    private HelpDialog.OnClickListener noButtonClickListener;
    private HelpDialog.OnClickListener yesButtonClickListener;

    public GameDialog(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_game,null,false);
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
    public void setNoButtonClickListener(HelpDialog.OnClickListener noButtonClickListener) {
        this.noButtonClickListener = noButtonClickListener;
    }

    public void setYesButtonClickListener(HelpDialog.OnClickListener yesButtonClickListener) {
        this.yesButtonClickListener = yesButtonClickListener;
    }

    @FunctionalInterface
    public interface OnClickListener{
        void onClick();
    }
}
