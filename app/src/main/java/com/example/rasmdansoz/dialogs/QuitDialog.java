package com.example.rasmdansoz.dialogs;
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
 * Project: RasmdanSo'z
 * Date: 26/03/25
 * Javohir's MacBook Air
 */
public class QuitDialog extends AlertDialog {

    private AppCompatButton noBtn;
    private AppCompatButton yesBtn;

    private Runnable yesBtnClickListener;

    public QuitDialog(@NonNull Context context) {
        super(context);

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_quit, null);
        setView(view);
        setCancelable(false);
        Objects.requireNonNull(getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnim;
        noBtn = view.findViewById(R.id.no);
        yesBtn = view.findViewById(R.id.yes);

        noBtn.setOnClickListener(v -> dismiss());
        yesBtn.setOnClickListener(v -> {
            if (yesBtnClickListener != null) {
                yesBtnClickListener.run();
            }
        });
    }

    public void setYesBtnClickListener(Runnable yesBtnClickListener) {
        this.yesBtnClickListener = yesBtnClickListener;
    }
}

