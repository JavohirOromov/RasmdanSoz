package com.example.rasmdansoz.screens.game;

import com.example.rasmdansoz.model.QuestionData;
import com.example.rasmdansoz.repository.AppRepository;

/**
 * Creator: Javohir Oromov
 * Date: 08/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class GameModel implements GameContract.Model{
   private AppRepository repository;

    public GameModel() {
        repository = AppRepository.getInstance();
    }

    @Override
    public QuestionData getQuestionByIndex(int index) {
        return repository.getQuestionByIndex(index);
    }
}
