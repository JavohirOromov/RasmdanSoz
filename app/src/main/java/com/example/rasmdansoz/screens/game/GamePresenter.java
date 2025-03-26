package com.example.rasmdansoz.screens.game;
import static com.example.rasmdansoz.utils.AppUtils.MAX_COUNT;

import android.util.Log;

import com.example.rasmdansoz.model.QuestionData;
import com.example.rasmdansoz.storage.LocalStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Creator: Javohir Oromov
 * Date: 08/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class GamePresenter implements GameContract.Presenter {
    private GameContract.Model model;
    private GameContract.View view;
    private int index;
    private StringBuilder _sbAnswer;
    private StringBuilder _sbVariant;
    private int coin;
    private List<Integer> list = new ArrayList<>();
    private LocalStorage storage = LocalStorage.Companion.getInstance();
    public GamePresenter(GameContract.View view) {
        this.view = view;
        this.model = new GameModel();
        showQuestion();
        Log.d("TTT","1 ta");
    }

    public GamePresenter(GameContract.View view, int index){
        this.view = view;
        this.model = new GameModel();
        setStorage();
        showQuestion();
    }
    private void setStorage(){
        this.index = storage.getIndex();
        this.coin = storage.getCoin();
        view.setCoin(this.coin);
        view.setLevel("Level " + (this.index + 1));
    }
    private void showQuestion() {
        if (index < MAX_COUNT) {
            QuestionData data = model.getQuestionByIndex(this.index);
            view.showQuestionImage(data.getImage());
            view.showAnswerButtonByLength(data.getAnswer().length());
            view.showVariant(data.getVariant());
            adjustStringBuilder(data);
        } else {
            view.openLastScreen();
        }
    }

    private void adjustStringBuilder(QuestionData data) {
        _sbVariant = new StringBuilder(data.getVariant());
        _sbAnswer = new StringBuilder();
        for (int i = 0; i < data.getAnswer().length(); i++) {
            _sbAnswer.append("#");
        }
    }

    @Override
    public void clickAnswerButton(int index) {
        if (index < 0 || index >= _sbAnswer.length()) return;
        char ch = _sbAnswer.charAt(index);
        if (ch == '#') return;
        int variantIndex = _sbVariant.indexOf("*");
        for (int i = 0; i < _sbVariant.length(); i++) {
            if (_sbVariant.charAt(i) == '*' && ch == model.getQuestionByIndex(this.index).getVariant().charAt(i)) {
                variantIndex = i;
            }
        }
        if (variantIndex != -1) {
            _sbVariant.setCharAt(variantIndex, ch);
        }
        _sbAnswer.setCharAt(index, '#');
        view.startSoundAnswer();
        view.showVariantButton(ch, variantIndex);
        view.unselectUserAnswer(index);
        view.showInitialButton(model.getQuestionByIndex(this.index).getAnswer().length());
        view.setCorrectBackground(list);
    }
    @Override
    public void clickVariantButton(int index) {
        int answerIndex = _sbAnswer.indexOf("#");
        if (answerIndex != -1) {
            char ch = _sbVariant.charAt(index);
            _sbVariant.setCharAt(index, '*');
            _sbAnswer.setCharAt(answerIndex, ch);
            view.hideVariantButton(index);
            view.selectUserAnswer(ch, answerIndex);
            view.startSoundVariant();
            if (!_sbAnswer.toString().contains("#")) {
                if (!_sbAnswer.toString().equals(model.getQuestionByIndex(this.index).getAnswer())){
                    view.showWrongBackground(model.getQuestionByIndex(this.index).getAnswer().length());
                    view.startSoundWrong();
                }else {
                    coin = coin + 30;
                    view.setCoin(coin);
                    view.showCorrectDialog();
                }
            }
        }
    }
    @Override
    public void clickBack() {
        view.openStartScreen();
    }

    @Override
    public void clickDialogExitButton() {
        view.openStartScreen();
    }

    @Override
    public void clickDialogNextButton() {
        this.index++;
        showQuestion();
        if (MAX_COUNT > this.index){
            view.setLevel("Level " + (this.index + 1));
            storage.saveIndex(this.index);
            storage.saveCoin(this.coin);
            view.showInitialButton(model.getQuestionByIndex(this.index).getAnswer().length());
        }
    }
    @Override
    public void clickHelpButton() {
        view.showHelpDialog();
    }
    @Override
    public void clickDeleteVariantButton() {
        view.showDeleteDialog();
    }
    @Override
    public void clickHelpDialogNoButton() {
        view.helpDialogDismiss();
    }

    @Override
    public void clickHelpDialogYesButton() {
        if (coin < 10){
            view.showToast();
            return;
        }
        String answer = model.getQuestionByIndex(this.index).getAnswer();
        List<Integer> emptyIndexes = new ArrayList<>();
        for (int i = 0; i < _sbAnswer.length(); i++) {
            if (_sbAnswer.charAt(i) == '#') {
                emptyIndexes.add(i);
            }
        }
        if (emptyIndexes.isEmpty()) return;
        Random random = new Random();
        int answerIndex;
        int variantIndex;
        char correctChar;
        do {
            answerIndex = emptyIndexes.get(random.nextInt(emptyIndexes.size()));
            correctChar = answer.charAt(answerIndex);
            variantIndex = _sbVariant.indexOf(String.valueOf(correctChar));
        } while (variantIndex == -1);

        _sbAnswer.setCharAt(answerIndex, correctChar);
        coin = coin - 10;
        view.UpdateCoin(coin);
        view.setTextAnswerButton(answerIndex, correctChar);
        _sbVariant.setCharAt(variantIndex, '*');
        view.deleteVariantButton(variantIndex);
        list.add(answerIndex);
        view.setCorrectBackground(list);
    }

    @Override
    public void clickDeleteDialogYesButton() {
       if (coin < 5){
           view.showToast();
           return;
       }
        // Joriy savolning javobini olamiz
        String javob = model.getQuestionByIndex(this.index).getAnswer();
        List<Integer> notogriIndexlar = new ArrayList<>();

        // Variantlardan javobda yo'q bo'lgan harflarni qidiramiz
        for (int i = 0; i < _sbVariant.length(); i++) {
            char hozirgiHarf = _sbVariant.charAt(i);
            // Agar harf ishlatilmagan (* emas) va javobda yo'q bo'lsa
            if (hozirgiHarf != '*' && javob.indexOf(hozirgiHarf) == -1) {
                notogriIndexlar.add(i);
            }
        }
        // Agar noto'g'ri harflar topilmasa, metodni tugatamiz
        if (notogriIndexlar.isEmpty()) {
            return;
        }
        // Tasodifiy bitta noto'g'ri harfni tanlaymiz
        Random random = new Random();
        int variantIndex = notogriIndexlar.get(random.nextInt(notogriIndexlar.size()));

        // Tanlangan harfni o'chiramiz (* qo'yamiz)
        _sbVariant.setCharAt(variantIndex, '*');
        // Viewga o'chirilgan harfni bildiramiz
        view.deleteVariantButton(variantIndex);
    }

    @Override
    public void clickImage1() {
        view.linerGone();
        view.imageVisible();
        int[] image = model.getQuestionByIndex(this.index).getImage();
        view.showImage(image[0]);
    }

    @Override
    public void clickImage2() {
        view.linerGone();
        view.imageVisible();
        int[] image = model.getQuestionByIndex(this.index).getImage();
        view.showImage(image[1]);
    }

    @Override
    public void clickImage3() {
        view.linerGone();
        view.imageVisible();
        int[] image = model.getQuestionByIndex(this.index).getImage();
        view.showImage(image[2]);
    }

    @Override
    public void clickImage4() {
        view.linerGone();
        view.imageVisible();
        int[] image = model.getQuestionByIndex(this.index).getImage();
        view.showImage(image[3]);
    }

    @Override
    public void clickImage() {
        view.linerVisible();
        view.imageGone();
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public LocalStorage getStorage() {
        return storage;
    }

    public void setStorage(LocalStorage storage) {
        this.storage = storage;
    }
}