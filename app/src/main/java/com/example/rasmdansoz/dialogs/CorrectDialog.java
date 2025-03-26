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

public class CorrectDialog extends AlertDialog {
    private AppCompatButton exit;
    private AppCompatButton next;
    private OnClickListener exitClickListener;
    private OnClickListener nextClickListener;

    public void setExitClickListener(OnClickListener exitClickListener) {
        this.exitClickListener = exitClickListener;
    }

    public void setNextClickListener(OnClickListener nextClickListener) {
        this.nextClickListener = nextClickListener;
    }

    public CorrectDialog(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog,null,false);
        setView(view);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnim;
        exit = view.findViewById(R.id.exit);
        next = view.findViewById(R.id.next);
        exit.setOnClickListener(view1 -> {
            if (exitClickListener != null){
                exitClickListener.onClick();
            }
            dismiss();
        });
        next.setOnClickListener(view2 -> {
            if (nextClickListener != null){
                nextClickListener.onClick();
            }
            dismiss();
        });
    }
    @FunctionalInterface
    public interface OnClickListener{
        void onClick();
    }
}
