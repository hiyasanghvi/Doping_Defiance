package com.example.doping_defiance;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class quiz extends AppCompatActivity {

    private static class Question {
        private final int id;
        private final String question;
        private final String answer;
        private final List<String> options;

        public Question(int id, String question, String answer, List<String> options) {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.options = options;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public List<String> getOptions() {
            return options;
        }
    }

    private final List<Question> questions = new ArrayList<>();
    private List<Question> selectedQuestions;
    private int currentQuestionIndex;
    private int score;
    private CountDownTimer timer;

    private TextView questionNumberTextView, questionTextView, timerTextView, scoreTextView;
    private LinearLayout optionsContainer;
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);//
            EdgeToEdge.enable(this);

        questionNumberTextView = findViewById(R.id.question_number);
        questionTextView = findViewById(R.id.quiz_question);
        timerTextView = findViewById(R.id.timer_text);
        optionsContainer = findViewById(R.id.options_container);
        startButton = findViewById(R.id.start_btn);
        scoreTextView = findViewById(R.id.score_display);

        createQuestions();

        startButton.setOnClickListener(v -> startQuiz());
    }

    private void createQuestions() {
        // Add the first question
        questions.add(new Question(
                1,
                "What is the purpose of anti-doping rules?",
                "To ensure fair play",
                new ArrayList<String>() {{
                    add("To enhance performance");
                    add("To ensure fair play");
                    add("To avoid injuries");
                    add("To reduce costs");
                }}
        ));

        // Add the second question
        questions.add(new Question(
                2,
                "What is doping in sports?",
                "The use of prohibited substances",
                new ArrayList<String>() {{
                    add("The use of supplements");
                    add("The use of prohibited substances");
                    add("Regular training");
                    add("Legal medical treatments");
                }}
        ));

        // Add the third question
        questions.add(new Question(
                3,
                "Which organization governs anti-doping globally?",
                "World Anti-Doping Agency (WADA)",
                new ArrayList<String>() {{
                    add("World Health Organization (WHO)");
                    add("International Olympic Committee (IOC)");
                    add("World Anti-Doping Agency (WADA)");
                    add("United Nations (UN)");
                }}
        ));

        // Add the fourth question
        questions.add(new Question(
                4,
                "What is one common type of doping substance?",
                "Anabolic steroids",
                new ArrayList<String>() {{
                    add("Vitamins");
                    add("Anabolic steroids");
                    add("Caffeine");
                    add("Electrolytes");
                }}
        ));

        // Add the fifth question
        questions.add(new Question(
                5,
                "What is blood doping?",
                "Increasing red blood cells to enhance performance",
                new ArrayList<String>() {{
                    add("Reducing oxygen levels in blood");
                    add("Increasing red blood cells to enhance performance");
                    add("Taking painkillers");
                    add("Using anti-inflammatory drugs");
                }}
        ));

        // Add the sixth question
        questions.add(new Question(
                6,
                "Which test is commonly used to detect doping?",
                "Urine test",
                new ArrayList<String>() {{
                    add("MRI scan");
                    add("Urine test");
                    add("X-ray");
                    add("Blood pressure test");
                }}
        ));

        // Add the seventh question
        questions.add(new Question(
                7,
                "What is the penalty for doping in sports?",
                "Suspension or ban from competition",
                new ArrayList<String>() {{
                    add("Medal deduction");
                    add("Suspension or ban from competition");
                    add("Warning letter");
                    add("Reduction in rank");
                }}
        ));

        // Add the eighth question
        questions.add(new Question(
                8,
                "Which of these is a prohibited substance in sports?",
                "Erythropoietin (EPO)",
                new ArrayList<String>() {{
                    add("Vitamin C");
                    add("Erythropoietin (EPO)");
                    add("Creatine");
                    add("Protein powder");
                }}
        ));

        // Add the ninth question
        questions.add(new Question(
                9,
                "What is the role of WADA?",
                "To oversee anti-doping efforts worldwide",
                new ArrayList<String>() {{
                    add("To regulate athlete salaries");
                    add("To oversee anti-doping efforts worldwide");
                    add("To organize competitions");
                    add("To monitor team selections");
                }}
        ));

        // Add the tenth question
        questions.add(new Question(
                10,
                "Why are diuretics banned in sports?",
                "They can mask other prohibited substances",
                new ArrayList<String>() {{
                    add("They improve endurance");
                    add("They can mask other prohibited substances");
                    add("They reduce body weight");
                    add("They boost muscle growth");
                }}
        ));

        // Add the eleventh question
        questions.add(new Question(
                11,
                "What is the whereabouts rule in anti-doping?",
                "Athletes must provide their location for testing",
                new ArrayList<String>() {{
                    add("Athletes must stay in training camps");
                    add("Athletes must provide their location for testing");
                    add("Athletes must avoid certain regions");
                    add("Athletes must disclose travel plans");
                }}
        ));

        // Add the twelfth question
        questions.add(new Question(
                12,
                "What is the purpose of TUE (Therapeutic Use Exemption)?",
                "To allow the use of necessary prohibited substances",
                new ArrayList<String>() {{
                    add("To avoid testing");
                    add("To allow the use of necessary prohibited substances");
                    add("To increase performance");
                    add("To regulate substance prices");
                }}
        ));

        // Add the thirteenth question
        questions.add(new Question(
                13,
                "What does anabolic refer to in anabolic steroids?",
                "Muscle building",
                new ArrayList<String>() {{
                    add("Weight loss");
                    add("Endurance");
                    add("Muscle building");
                    add("Fat burning");
                }}
        ));

        // Add the fourteenth question
        questions.add(new Question(
                14,
                "What is a side effect of anabolic steroid use?",
                "Liver damage",
                new ArrayList<String>() {{
                    add("Improved vision");
                    add("Liver damage");
                    add("Increased flexibility");
                    add("Reduced heart rate");
                }}
        ));

        // Add the fifteenth question
        questions.add(new Question(
                15,
                "What is the goal of doping control?",
                "To ensure fairness and athlete health",
                new ArrayList<String>() {{
                    add("To increase competition");
                    add("To promote supplements");
                    add("To ensure fairness and athlete health");
                    add("To reduce expenses");
                }}
        ));

        // Add the sixteenth question
        questions.add(new Question(
                16,
                "Why is gene doping banned?",
                "It provides an unfair genetic advantage",
                new ArrayList<String>() {{
                    add("It reduces training time");
                    add("It provides an unfair genetic advantage");
                    add("It is costly");
                    add("It is hard to detect");
                }}
        ));

        // Add the seventeenth question
        questions.add(new Question(
                17,
                "Which substance is banned due to its stimulant effects?",
                "Amphetamines",
                new ArrayList<String>() {{
                    add("Glucose");
                    add("Amphetamines");
                    add("Iron supplements");
                    add("Calcium tablets");
                }}
        ));

        // Add the eighteenth question
        questions.add(new Question(
                18,
                "What is one ethical issue with doping?",
                "It undermines the integrity of sports",
                new ArrayList<String>() {{
                    add("It increases competition");
                    add("It undermines the integrity of sports");
                    add("It promotes team spirit");
                    add("It is expensive");
                }}
        ));

        // Add the nineteenth question
        questions.add(new Question(
                19,
                "What is a biological passport in anti-doping?",
                "A record of an athlete's biological data",
                new ArrayList<String>() {{
                    add("A travel document");
                    add("A record of an athlete's biological data");
                    add("A certification of eligibility");
                    add("A medical prescription");
                }}
        ));

        // Add the twentieth question
        questions.add(new Question(
                20,
                "Why is education important in anti-doping?",
                "To inform athletes about prohibited substances",
                new ArrayList<String>() {{
                    add("To encourage supplement use");
                    add("To inform athletes about prohibited substances");
                    add("To increase participation in sports");
                    add("To reduce training costs");
                }}
        ));
    }


    private void startQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        startButton.setVisibility(View.GONE);
        scoreTextView.setText("Score: 0");
        Collections.shuffle(questions);
        selectedQuestions = questions.subList(0, 10);

        showQuestion(currentQuestionIndex);
    }

    private void showQuestion(int index) {
        if (timer != null) {
            timer.cancel();
        }

        Question currentQuestion = selectedQuestions.get(index);
        questionNumberTextView.setText("Question " + (index + 1) + "/10");
        questionTextView.setText(currentQuestion.getQuestion());
        optionsContainer.removeAllViews();

        for (String option : currentQuestion.getOptions()) {
            Button optionButton = new Button(this);
            optionButton.setText(option);
            optionButton.setOnClickListener(v -> checkAnswer(option, currentQuestion.getAnswer()));
            optionsContainer.addView(optionButton);
        }

        timerTextView.setText("Time Left: 30");
        timer = new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText("Time Left: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(quiz.this, "Time's up!", Toast.LENGTH_SHORT).show();
                moveToNextQuestion();
            }
        }.start();
    }

    private void checkAnswer(String selectedAnswer, String correctAnswer) {
        if (timer != null) {
            timer.cancel();
        }

        if (selectedAnswer.equals(correctAnswer)) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Wrong answer.", Toast.LENGTH_SHORT).show();
        }

        scoreTextView.setText("Score: " + score);
        moveToNextQuestion();
    }

    private void moveToNextQuestion() {
        if (currentQuestionIndex < selectedQuestions.size() - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        } else {
            finishQuiz();
        }
    }

    private void finishQuiz() {
        if (timer != null) {
            timer.cancel();
        }
        Toast.makeText(this, "Quiz Complete! Your score: " + score + "/10", Toast.LENGTH_LONG).show();
        startButton.setText("Restart Quiz");
        startButton.setVisibility(View.VISIBLE);
    }
}