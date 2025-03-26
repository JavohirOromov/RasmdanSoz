package com.example.rasmdansoz.screens.game;
import com.example.rasmdansoz.model.QuestionData;

import java.util.List;

/**
 * Creator: Javohir Oromov
 * Date: 08/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public interface GameContract {

    interface Model{
        QuestionData getQuestionByIndex(int index);  // ---> presenterdan kerakli index bo'yicha question olib keladi
    }

    interface View{
        void showQuestionImage(int[] imageResID); // --->  view ekranga 4 ta rasmni ko'rsatadi
        void showAnswerButtonByLength(int length);// ---> rasmga qarab answer buttonlarni visibility_sini visible qilib chiqadi
        void showVariant(String variant);// ---> variant buttonlarni textiga belgilarni qo'yib chiqadi

        void selectUserAnswer(char ch, int index);// ---> answer buttonga harfni qo'shadi, buttonni yashiradi
        void unselectUserAnswer(int index); // ---> answer buttondan harfni o'chiradi buttonni ko'rinadigan qiladi
        void hideVariantButton(int index); // --> variant button_ni yashiradi
        void showVariantButton(char ch,int index); // ---> variant tugmasini qayta ko'rsatadi
        void openStartScreen();
        void showCorrectDialog();
        void showWrongBackground(int length);
        void showInitialButton(int length);
        void setCoin(int coin);
        void setLevel(String level);
        void openLastScreen();
        void deleteVariantButton(int index);
        void setTextAnswerButton(int index,char letter);
        void showHelpDialog();
        void showDeleteDialog();
        void helpDialogDismiss();
        void UpdateCoin(int coin);
        void showToast();
        void linerGone();
        void linerVisible();
        void imageGone();
        void imageVisible();
        void showImage(int image);
        void startSoundAnswer();
        void startSoundWrong();
        void startSoundVariant();
        void setCorrectBackground(List<Integer> list);
    }

    interface Presenter{
        void clickAnswerButton(int index);
        void clickVariantButton(int index);
        void clickBack();
        void clickDialogExitButton();
        void clickDialogNextButton();
        void clickHelpButton();
        void clickDeleteVariantButton();
        void clickHelpDialogNoButton();
        void clickHelpDialogYesButton();
        void clickDeleteDialogYesButton();
        void clickImage1();
        void clickImage2();
        void clickImage3();
        void clickImage4();
        void clickImage();
    }
}
