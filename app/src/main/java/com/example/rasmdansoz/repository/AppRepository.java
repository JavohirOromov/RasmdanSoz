package com.example.rasmdansoz.repository;
import com.example.rasmdansoz.R;
import com.example.rasmdansoz.model.QuestionData;
import java.util.ArrayList;
import java.util.List;
/**
 * Creator: Javohir Oromov
 * Date: 08/02/25
 * Project: RasmdanSo'z
 * Javohir's MacBook Air
 */
public class AppRepository {
    private List<QuestionData> questions;
    private static AppRepository instance;

    private AppRepository(){
        questions = new ArrayList<>();
        loadQuestions();
    }
    public static AppRepository getInstance(){
        if (instance == null){
            instance = new AppRepository();
        }
        return instance;
    }
    private void loadQuestions(){
        questions.add(new QuestionData(new int[]{R.drawable.meta1,R.drawable.meta2,R.drawable.meta3,R.drawable.meta4},"meta","qwermtyuiaoj"));
        questions.add(new QuestionData(new int[]{R.drawable.google1,R.drawable.google2,R.drawable.google3,R.drawable.google4},"google","wgroyohglfge"));
        questions.add(new QuestionData(new int[]{R.drawable.ice1,R.drawable.ice2,R.drawable.ice3,R.drawable.ice4},"muz","axmlupzokdnt"));
        questions.add(new QuestionData(new int[]{R.drawable.box1,R.drawable.box2,R.drawable.box3,R.drawable.box4},"boks","amkbxsulopdznt"));
        questions.add(new QuestionData(new int[]{R.drawable.ring1,R.drawable.ring2,R.drawable.ring3,R.drawable.ring4},"uzuk","mxlupzokauka"));
    }
    public QuestionData getQuestionByIndex(int index){
        return questions.get(index);
    }
}
