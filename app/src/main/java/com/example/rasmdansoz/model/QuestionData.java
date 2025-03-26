package com.example.rasmdansoz.model;
/**
 * Creator: Javohir Oromov
 * Date: 08/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class QuestionData {
    private int[] image;
    private String answer;
    private String variant;

    public QuestionData(int[] image, String answer, String variant) {
        this.image = image;
        this.answer = answer;
        this.variant = variant;
    }

    public int[] getImage() {
        return image;
    }

    public String getAnswer() {
        return answer;
    }

    public String getVariant() {
        return variant;
    }
}
