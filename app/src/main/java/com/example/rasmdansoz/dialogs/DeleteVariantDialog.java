package com.example.rasmdansoz.dialogs;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatButton;

import com.example.rasmdansoz.R;
/**
 * Creator: Javohir Oromov
 * Date: 11/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class DeleteVariantDialog extends AlertDialog {
    private AppCompatButton noButton;
    private AppCompatButton yesButton;
    private HelpDialog.OnClickListener noButtonClickListener;
    private HelpDialog.OnClickListener yesButtonClickListener;
    public DeleteVariantDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_delete_variant,null,false);
        setView(view);
        setCancelable(false);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnim;
        noButton = view.findViewById(R.id.no);
        yesButton = view.findViewById(R.id.yes);

        noButton.setOnClickListener(view1 -> {
            if (noButtonClickListener != null){
                noButtonClickListener.onClick();
            }
            dismiss();
        });
        yesButton.setOnClickListener(view2 -> {
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
