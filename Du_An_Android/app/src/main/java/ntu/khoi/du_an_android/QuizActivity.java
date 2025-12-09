package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.media.AudioManager;
import android.media.ToneGenerator;

public class QuizActivity extends AppCompatActivity {

    TextView tvTimer, tvQuestion, tvScoreInfo;
    Button btnOp1, btnOp2, btnOp3, btnOp4;
    ToneGenerator toneGen;

    String userName, level;
    int currentScore = 0;
    int correctCount = 0;
    int wrongCount = 0;
    int totalQuestions = 0;

    long timeLimit; // Thời gian cho mỗi câu
    CountDownTimer timer;
    Random random = new Random();
    int correctAnswer; // Lưu đáp án đúng (dùng cho trắc nghiệm)
    boolean isMathCorrect; // Lưu trạng thái Đúng/Sai (dùng cho câu hỏi True/False)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // 1. Ánh xạ View
        tvTimer = findViewById(R.id.tvTimer);
        tvQuestion = findViewById(R.id.tvQuestion);
        tvScoreInfo = findViewById(R.id.tvScoreInfo);
        btnOp1 = findViewById(R.id.btnOp1);
        btnOp2 = findViewById(R.id.btnOp2);
        btnOp3 = findViewById(R.id.btnOp3);
        btnOp4 = findViewById(R.id.btnOp4);

        // Khởi tạo bộ âm thanh
        try {
            toneGen = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. Nhận dữ liệu
        userName = getIntent().getStringExtra("USER_NAME");
        level = getIntent().getStringExtra("LEVEL");

        // 3. Cài đặt độ khó
        if (level.equals("Easy")) timeLimit = 12000;
        else if (level.equals("Normal")) timeLimit = 10000;
        else timeLimit = 8000;

        // 4. Bắt đầu game
        generateQuestion();
    }

    private void generateQuestion() {
        // Kiểm tra nếu đã đủ 10 câu thì dừng
        if (totalQuestions >= 10) {
            gameOver();
            return;
        }

        totalQuestions++;
        startTimer(); // Bắt đầu đếm ngược

        // --- SINH SỐ NGẪU NHIÊN ---
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

        // --- QUYẾT ĐỊNH LOẠI CÂU HỎI (50/50) ---
        int questionType = random.nextInt(2); // 0 hoặc 1

        if (questionType == 0) {
            // === DẠNG 1: TRẮC NGHIỆM 4 ĐÁP ÁN ===
            setupMultipleChoice(num1, num2, operatorSymbol, result);
        } else {
            // === DẠNG 2: ĐÚNG / SAI ===
            setupTrueFalse(num1, num2, operatorSymbol, result);
        }
    }

    // --- XỬ LÝ DẠNG TRẮC NGHIỆM ---
    private void setupMultipleChoice(int n1, int n2, String op, int res) {
        // Hiện đủ 4 nút
        btnOp1.setVisibility(View.VISIBLE);
        btnOp2.setVisibility(View.VISIBLE);
        btnOp3.setVisibility(View.VISIBLE);
        btnOp4.setVisibility(View.VISIBLE);

        tvQuestion.setText(n1 + " " + op + " " + n2 + " = ?");
        correctAnswer = res;

        // Tạo đáp án nhiễu
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

        // Gán sự kiện click cho Trắc nghiệm
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            try {
                int selected = Integer.parseInt(b.getText().toString());
                checkAnswerMultipleChoice(selected);
            } catch (NumberFormatException e) {
                // Phòng hờ lỗi ép kiểu
            }
        };
        btnOp1.setOnClickListener(listener);
        btnOp2.setOnClickListener(listener);
        btnOp3.setOnClickListener(listener);
        btnOp4.setOnClickListener(listener);
    }

    // --- XỬ LÝ DẠNG ĐÚNG/SAI ---
    private void setupTrueFalse(int n1, int n2, String op, int res) {
        // Ẩn 2 nút dưới, chỉ dùng 2 nút trên
        btnOp1.setVisibility(View.VISIBLE);
        btnOp2.setVisibility(View.VISIBLE);
        btnOp3.setVisibility(View.GONE);
        btnOp4.setVisibility(View.GONE);

        // Tung đồng xu xem hiển thị phép tính Đúng hay Sai
        isMathCorrect = random.nextBoolean();
        int displayResult;

        if (isMathCorrect) {
            displayResult = res; // Hiển thị kết quả đúng
        } else {
            // Hiển thị kết quả sai
            do {
                displayResult = res + random.nextInt(10) - 5;
            } while (displayResult == res);
        }

        tvQuestion.setText(n1 + " " + op + " " + n2 + " = " + displayResult);

        // Gán chữ cho nút
        btnOp1.setText("Đúng");
        btnOp2.setText("Sai");

        // Gán sự kiện click cho Đúng/Sai
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            checkAnswerTrueFalse(b.getText().toString());
        };
        btnOp1.setOnClickListener(listener);
        btnOp2.setOnClickListener(listener);
    }

    // --- KIỂM TRA ĐÁP ÁN TRẮC NGHIỆM ---
    private void checkAnswerMultipleChoice(int selected) {
        timer.cancel();
        if (selected == correctAnswer) {
            processCorrect();
        } else {
            processWrong("Sai rồi! Đáp án là " + correctAnswer);
        }
        nextQuestionDelayed();
    }

    // --- KIỂM TRA ĐÁP ÁN ĐÚNG/SAI ---
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

    // Hàm xử lý chung khi Đúng
    private void processCorrect() {
        currentScore += 10;
        correctCount++;
        if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_PROP_BEEP, 150);
        Toast.makeText(this, "Chính xác!", Toast.LENGTH_SHORT).show();
        tvScoreInfo.setText("Score: " + currentScore);
    }

    // Hàm xử lý chung khi Sai
    private void processWrong(String msg) {
        wrongCount++;
        if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_CDMA_LOW_L, 200);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        tvScoreInfo.setText("Score: " + currentScore);
    }

    // Đồng hồ đếm ngược
    private void startTimer() {
        if (timer != null) timer.cancel();
        timer = new CountDownTimer(timeLimit, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvTimer.setText("Time: " + millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                wrongCount++;
                if(toneGen != null) toneGen.startTone(ToneGenerator.TONE_CDMA_LOW_L, 200);
                Toast.makeText(QuizActivity.this, "Hết giờ!", Toast.LENGTH_SHORT).show();
                nextQuestionDelayed();
            }
        }.start();
    }

    // Chuyển câu hỏi (có delay 1 giây)
    private void nextQuestionDelayed() {
        new android.os.Handler().postDelayed(() -> generateQuestion(), 1000);
    }

    // Kết thúc game
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