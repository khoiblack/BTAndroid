package ntu.khoi.du_an_android;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {

    TextView tvTimer, tvQuestion, tvScoreInfo;
    Button btnOp1, btnOp2, btnOp3, btnOp4;
    ImageView btnPause;
    ToneGenerator toneGen;
    String userName, level;
    int currentScore = 0;
    int correctCount = 0;
    int wrongCount = 0;
    int totalQuestions = 0;

    long initialTime;
    long timeLeftInMillis;
    CountDownTimer timer;
    Random random = new Random();
    int correctAnswer;
    boolean isMathCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScoreInfo = findViewById(R.id.tvScoreInfo);
        btnOp1 = findViewById(R.id.btnOp1);
        btnOp2 = findViewById(R.id.btnOp2);
        btnOp3 = findViewById(R.id.btnOp3);
        btnOp4 = findViewById(R.id.btnOp4);
        btnPause = findViewById(R.id.btnPause);

        try {
            toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }


        userName = getIntent().getStringExtra("USER_NAME");
        level = getIntent().getStringExtra("LEVEL");


        if (level.equals("Easy")) initialTime = 12000;
        else if (level.equals("Normal")) initialTime = 10000;
        else initialTime = 8000;

        btnPause.setOnClickListener(v -> showPauseDialog());

        generateQuestion();
    }

    private void showPauseDialog() {
        if (timer != null) {
            timer.cancel();
        }

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_pause);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        dialog.setCancelable(false);

        Button btnExit = dialog.findViewById(R.id.btnExitGame);
        Button btnResume = dialog.findViewById(R.id.btnResumeGame);

        btnResume.setOnClickListener(v -> {
            dialog.dismiss();
            startTimer(timeLeftInMillis);
        });


        btnExit.setOnClickListener(v -> {
            dialog.dismiss();
            finish();
        });

        dialog.show();
    }

    private void generateQuestion() {

        if (totalQuestions >= 10) {
            gameOver();
            return;
        }

        totalQuestions++;

        timeLeftInMillis = initialTime;
        startTimer(timeLeftInMillis);

        int num1 = 0, num2 = 0;
        if (level.equals("Easy")) {
            num1 = random.nextInt(10) + 1;
            num2 = random.nextInt(10) + 1;
        } else if (level.equals("Normal")) {
            num1 = random.nextInt(40) + 10;
            num2 = random.nextInt(40) + 10;
        } else {
            num1 = random.nextInt(450) + 50;
            num2 = random.nextInt(450) + 50;
        }

        int operator = random.nextInt(4);
        String operatorSymbol = "";
        int result = 0;

        switch (operator) {
            case 0: result = num1 + num2; operatorSymbol = "+"; break;
            case 1:
                if(num1 < num2) { int t=num1; num1=num2; num2=t; }
                result = num1 - num2; operatorSymbol = "-"; break;
            case 2: result = num1 * num2; operatorSymbol = "x"; break;
            case 3: result = num1; num1 = result * num2; operatorSymbol = "/"; break;
        }


        int questionType = random.nextInt(2);

        if (questionType == 0) {
            setupMultipleChoice(num1, num2, operatorSymbol, result);
        } else {
            setupTrueFalse(num1, num2, operatorSymbol, result);
        }
    }


    private void startTimer(long duration) {
        if (timer != null) timer.cancel();

        timer = new CountDownTimer(duration, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished; // Cập nhật liên tục

                // Format thành 00:09, 00:08...
                long seconds = millisUntilFinished / 1000;
                tvTimer.setText(String.format("00:%02d", seconds));
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                tvTimer.setText("00:00");

                wrongCount++;
                if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_CDMA_LOW_L, 200);
                Toast.makeText(QuizActivity.this, "Hết giờ!", Toast.LENGTH_SHORT).show();
                nextQuestionDelayed();
            }
        }.start();
    }

    private void setupMultipleChoice(int n1, int n2, String op, int res) {
        btnOp1.setVisibility(View.VISIBLE);
        btnOp2.setVisibility(View.VISIBLE);
        btnOp3.setVisibility(View.VISIBLE);
        btnOp4.setVisibility(View.VISIBLE);

        tvQuestion.setText(n1 + " " + op + " " + n2 + " = ?");
        correctAnswer = res;

        ArrayList<Integer> answers = new ArrayList<>();
        answers.add(res);
        while (answers.size() < 4) {
            int wrong = res + random.nextInt(20) - 10;
            if (wrong != res && !answers.contains(wrong)) {
                answers.add(wrong);
            }
        }
        Collections.shuffle(answers);

        btnOp1.setText(String.valueOf(answers.get(0)));
        btnOp2.setText(String.valueOf(answers.get(1)));
        btnOp3.setText(String.valueOf(answers.get(2)));
        btnOp4.setText(String.valueOf(answers.get(3)));

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            try {
                int selected = Integer.parseInt(b.getText().toString());
                checkAnswerMultipleChoice(selected);
            } catch (NumberFormatException e) { }
        };
        btnOp1.setOnClickListener(listener);
        btnOp2.setOnClickListener(listener);
        btnOp3.setOnClickListener(listener);
        btnOp4.setOnClickListener(listener);
    }

    private void setupTrueFalse(int n1, int n2, String op, int res) {
        btnOp1.setVisibility(View.VISIBLE);
        btnOp2.setVisibility(View.VISIBLE);
        btnOp3.setVisibility(View.GONE);
        btnOp4.setVisibility(View.GONE);

        isMathCorrect = random.nextBoolean();
        int displayResult;

        if (isMathCorrect) {
            displayResult = res;
        } else {
            do {
                displayResult = res + random.nextInt(10) - 5;
            } while (displayResult == res);
        }

        tvQuestion.setText(n1 + " " + op + " " + n2 + " = " + displayResult);

        btnOp1.setText("Đúng");
        btnOp2.setText("Sai");

        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            checkAnswerTrueFalse(b.getText().toString());
        };
        btnOp1.setOnClickListener(listener);
        btnOp2.setOnClickListener(listener);
    }

    private void checkAnswerMultipleChoice(int selected) {
        timer.cancel();
        if (selected == correctAnswer) {
            processCorrect();
        } else {
            processWrong("Sai rồi! Đáp án là " + correctAnswer);
        }
        nextQuestionDelayed();
    }

    private void checkAnswerTrueFalse(String selectedText) {
        timer.cancel();
        boolean userChoseCorrect = false;

        if (selectedText.equals("Đúng") && isMathCorrect) userChoseCorrect = true;
        if (selectedText.equals("Sai") && !isMathCorrect) userChoseCorrect = true;

        if (userChoseCorrect) {
            processCorrect();
        } else {
            processWrong("Sai rồi!");
        }
        nextQuestionDelayed();
    }

    private void processCorrect() {
        currentScore += 10;
        correctCount++;
        if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150);
        Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
        tvScoreInfo.setText("Score " + currentScore);
    }

    private void processWrong(String msg) {
        wrongCount++;
        if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_CDMA_LOW_L, 200);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tvScoreInfo.setText("Score " + currentScore);
    }

    private void nextQuestionDelayed() {
        new android.os.Handler().postDelayed(() -> generateQuestion(), 1000);
    }

    private void gameOver() {
        if(timer != null) timer.cancel();

        Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
        int[] stats = {correctCount, wrongCount, currentScore, totalQuestions};
        intent.putExtra("SCORE_OBJ", stats);
        intent.putExtra("USER_NAME", userName);
        intent.putExtra("LEVEL", level);
        startActivity(intent);
        finish();
    }
}