package com.example.rasmdansoz.screens.game;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.rasmdansoz.R;
import com.example.rasmdansoz.dialogs.CorrectDialog;
import com.example.rasmdansoz.dialogs.DeleteVariantDialog;
import com.example.rasmdansoz.dialogs.GameDialog;
import com.example.rasmdansoz.dialogs.HelpDialog;
import com.example.rasmdansoz.screens.last.LastActivity;
import com.example.rasmdansoz.screens.start.StartActivity;
import com.example.rasmdansoz.storage.LocalStorage;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GameActivity extends AppCompatActivity implements GameContract.View {
    private GameContract.Presenter presenter;
    private ImageView questionImage1, questionImage2, questionImage3, questionImage4;
    private List<AppCompatButton> answerButtons;
    private List<AppCompatButton> variantButtons;
    private ImageView back;
    private CorrectDialog correctDialog;
    private HelpDialog helpDialog;
    private DeleteVariantDialog deleteDialog;
    private GameDialog gameDialog;
    private TextView coinText;
    private TextView levelText;
    private ImageView help, delete;
    private LinearLayout liner;
    private ImageView img;
    private MaterialCardView card;
    private MediaPlayer soundAnswer;
    private MediaPlayer soundWrong;
    private MediaPlayer soundVariant;
    private LocalStorage storage = LocalStorage.Companion.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameDialog = new GameDialog(this);
        int index = getIntent().getIntExtra("id", 0);

        if (index == 1) {
            loadViews();
            presenter = new GamePresenter(this, index);
            addClickEvents();
        } else {
            if (storage.getIndex() == 0) {
                loadViews();
                presenter = new GamePresenter(this);
                addClickEvents();
            } else {
                gameDialog.show();
                gameDialog.setYesButtonClickListener(() -> {
                    loadViews();
                    presenter = new GamePresenter(this);
                    addClickEvents();
                });
                gameDialog.setNoButtonClickListener(() -> {
                    Intent intent = new Intent(this, StartActivity.class);
                    startActivity(intent);
                    gameDialog.dismiss();
                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("WrongViewCast")
    private void loadViews() {
        back = findViewById(R.id.back);
        questionImage1 = findViewById(R.id.image1);
        questionImage2 = findViewById(R.id.image2);
        questionImage3 = findViewById(R.id.image3);
        questionImage4 = findViewById(R.id.image4);
        img = findViewById(R.id.image);
        liner = findViewById(R.id.liner);
        card = findViewById(R.id.card);

        correctDialog = new CorrectDialog(this);
        helpDialog = new HelpDialog(this);
        deleteDialog = new DeleteVariantDialog(this);
        coinText = findViewById(R.id.coin);
        levelText = findViewById(R.id.level);
        help = findViewById(R.id.help);
        delete = findViewById(R.id.delete);
        soundAnswer = MediaPlayer.create(this,R.raw.click_answer);
        soundWrong = MediaPlayer.create(this,R.raw.wrong);
        soundVariant = MediaPlayer.create(this,R.raw.click_variant);
        answerButtons = new ArrayList<>(6);
        LinearLayout containerAnswer = findViewById(R.id.containerAnswer);
        for (int i = 0; i < containerAnswer.getChildCount(); i++) {
            answerButtons.add((AppCompatButton) containerAnswer.getChildAt(i));
        }
        variantButtons = new ArrayList<>(12);
        LinearLayout containerVariant1 = findViewById(R.id.containerVariant1);
        for (int i = 0; i < containerVariant1.getChildCount() - 1; i++) {
            variantButtons.add((AppCompatButton) containerVariant1.getChildAt(i));
        }
        LinearLayout containerVariant2 = findViewById(R.id.containerVariant2);
        for (int i = 0; i < containerVariant2.getChildCount() - 1; i++) {
            variantButtons.add((AppCompatButton) containerVariant2.getChildAt(i));
        }
    }

    private void addClickEvents() {
        for (int i = 0; i < answerButtons.size(); i++) {
            answerButtons.get(i).setTag(i);
            answerButtons.get(i).setOnClickListener(view -> {
                presenter.clickAnswerButton((Integer) view.getTag());
            });
        }
        for (int i = 0; i < variantButtons.size(); i++) {
            variantButtons.get(i).setTag(i);
            variantButtons.get(i).setOnClickListener(view -> {
                presenter.clickVariantButton((Integer) view.getTag());
            });
        }
        back.setOnClickListener(view -> {
            presenter.clickBack();
        });
        correctDialog.setExitClickListener(() -> {
            presenter.clickDialogExitButton();
        });
        correctDialog.setNextClickListener(() -> {
            presenter.clickDialogNextButton();
        });
        help.setOnClickListener(view -> {
            presenter.clickHelpButton();
        });
        helpDialog.setNoButtonClickListener(() -> {
            presenter.clickHelpDialogNoButton();
        });
        helpDialog.setYesButtonClickListener(() -> {
            presenter.clickHelpDialogYesButton();
        });
        delete.setOnClickListener(v -> {
            presenter.clickDeleteVariantButton();
        });
        deleteDialog.setYesButtonClickListener(() -> {
            presenter.clickDeleteDialogYesButton();
        });
        questionImage1.setOnClickListener(v -> {
            presenter.clickImage1();
        });
        questionImage2.setOnClickListener(v -> {
            presenter.clickImage2();
        });
        questionImage3.setOnClickListener(v -> {
            presenter.clickImage3();
        });
        questionImage4.setOnClickListener(v -> {
            presenter.clickImage4();
        });
        img.setOnClickListener(v -> {
            presenter.clickImage();
        });
    }

    @Override
    public void showQuestionImage(int[] imageResID) {
        questionImage1.setImageResource(imageResID[0]);
        questionImage2.setImageResource(imageResID[1]);
        questionImage3.setImageResource(imageResID[2]);
        questionImage4.setImageResource(imageResID[3]);
    }

    @Override
    public void showAnswerButtonByLength(int length) {
        for (int i = 0; i < length; i++) {
            answerButtons.get(i).setVisibility(View.VISIBLE);
            answerButtons.get(i).setText("");
            answerButtons.get(i).setClickable(false);
        }
        for (int i = length; i < 7; i++) {
            answerButtons.get(i).setVisibility(View.GONE);
        }
    }

    @Override
    public void showVariant(String variant) {
        for (int i = 0; i < 12; i++) {
            variantButtons.get(i).setVisibility(View.VISIBLE);
            variantButtons.get(i).setText(String.valueOf(variant.charAt(i)));
        }
    }

    @Override
    public void selectUserAnswer(char ch, int index) {
        answerButtons.get(index).setText(String.valueOf(ch));
        answerButtons.get(index).setClickable(true);
    }

    @Override
    public void unselectUserAnswer(int index) {
        answerButtons.get(index).setText("");
        answerButtons.get(index).setClickable(false);
    }

    @Override
    public void hideVariantButton(int index) {
        variantButtons.get(index).setVisibility(View.INVISIBLE);
    }

    @Override
    public void showVariantButton(char ch, int index) {
        variantButtons.get(index).setVisibility(View.VISIBLE);
    }

    @Override
    public void openStartScreen() {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showCorrectDialog() {
        correctDialog.show();
    }

    @Override
    public void showWrongBackground(int length) {
        for (int i = 0; i < length; i++) {
            answerButtons.get(i).setBackgroundResource(R.drawable.bg_wrongans);
        }
    }

    @Override
    public void showInitialButton(int length) {
        for (int i = 0; i < length; i++) {
            answerButtons.get(i).setBackgroundResource(R.drawable.bg_answer);
        }
    }

    @Override
    public void setCoin(int coin) {
        coinText.setText(String.valueOf(coin));
    }

    @Override
    public void setLevel(String level) {
        levelText.setText(String.valueOf(level));
    }

    @Override
    public void openLastScreen() {
        Intent intent = new Intent(this, LastActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void deleteVariantButton(int index) {
        variantButtons.get(index).setVisibility(View.INVISIBLE);
    }

    @Override
    public void setTextAnswerButton(int index, char letter) {
        answerButtons.get(index).setText(String.valueOf(letter));
    }

    @Override
    public void showHelpDialog() {
        helpDialog.show();
    }

    @Override
    public void showDeleteDialog() {
        deleteDialog.show();
    }

    @Override
    public void helpDialogDismiss() {
        helpDialog.dismiss();
    }

    @Override
    public void UpdateCoin(int coin) {
        coinText.setText(String.valueOf(coin));
    }

    @Override
    public void showToast() {
        Toast.makeText(this, "Tanagalar yetarli emas", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void linerGone() {
        liner.setVisibility(View.GONE);
    }

    @Override
    public void linerVisible() {
        liner.setVisibility(View.VISIBLE);
    }

    @Override
    public void imageGone() {
        card.setVisibility(View.GONE);
    }

    @Override
    public void imageVisible() {
        card.setVisibility(View.VISIBLE);
    }

    @Override
    public void showImage(int image) {
        img.setImageResource(image);
    }

    @Override
    public void startSoundAnswer() {
        if (soundAnswer.isPlaying()){
            soundAnswer.stop();
            soundAnswer.release();
            soundAnswer = MediaPlayer.create(this,R.raw.click_answer);
        }
        soundAnswer.start();
    }

    @Override
    public void startSoundWrong() {
        if (soundWrong.isPlaying()){
            soundWrong.stop();
            soundWrong.release();
            soundWrong = MediaPlayer.create(this,R.raw.wrong);
        }
        soundWrong.start();
    }

    @Override
    public void startSoundVariant() {
        if (soundVariant.isPlaying()){
            soundVariant.stop();
            soundVariant.release();
            soundVariant = MediaPlayer.create(this,R.raw.click_variant);
        }
        soundVariant.start();
    }
    @Override
    public void setCorrectBackground(List<Integer> list) {
        for (Integer index : list) {
            if (index >= 0 && index < answerButtons.size()) {
                answerButtons.get(index).setBackgroundResource(R.drawable.bg_correct);
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (presenter != null) {
            presenter.save();
        } else {
            Log.e("GameActivity", "Presenter is null in onPause!");
        }
    }
}
