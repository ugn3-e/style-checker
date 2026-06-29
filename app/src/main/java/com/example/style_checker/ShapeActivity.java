package com.example.style_checker;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ShapeActivity extends AppCompatActivity {

    // 신체 정보 입력 변수
    EditText edtShoulder;
    EditText edtWaist;
    EditText edtHip;

    // 스트레이트 특징 체크박스
    CheckBox chkS1, chkS2, chkS3, chkS4, chkS5, chkS6;

    // 웨이브 특징 체크박스
    CheckBox chkW1, chkW2, chkW3, chkW4, chkW5, chkW6;

    // 내추럴 특징 체크박스
    CheckBox chkN1, chkN2, chkN3, chkN4, chkN5, chkN6;

    Button btnFinish;

    // 첫 검사인지 확인
    boolean isFirstTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);

        isFirstTest = getIntent().getBooleanExtra("IS_FIRST", false);

        // 화면 요소 연결
        edtShoulder = findViewById(R.id.edtShoulder);
        edtWaist = findViewById(R.id.edtWaist);
        edtHip = findViewById(R.id.edtHip);

        chkS1 = findViewById(R.id.chkS1);
        chkS2 = findViewById(R.id.chkS2);
        chkS3 = findViewById(R.id.chkS3);
        chkS4 = findViewById(R.id.chkS4);
        chkS5 = findViewById(R.id.chkS5);
        chkS6 = findViewById(R.id.chkS6);

        chkW1 = findViewById(R.id.chkW1);
        chkW2 = findViewById(R.id.chkW2);
        chkW3 = findViewById(R.id.chkW3);
        chkW4 = findViewById(R.id.chkW4);
        chkW5 = findViewById(R.id.chkW5);
        chkW6 = findViewById(R.id.chkW6);

        chkN1 = findViewById(R.id.chkN1);
        chkN2 = findViewById(R.id.chkN2);
        chkN3 = findViewById(R.id.chkN3);
        chkN4 = findViewById(R.id.chkN4);
        chkN5 = findViewById(R.id.chkN5);
        chkN6 = findViewById(R.id.chkN6);

        btnFinish = findViewById(R.id.btnShapeFinish);

        // 완료 버튼 클릭
        btnFinish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String sStr = edtShoulder.getText().toString();
                String wStr = edtWaist.getText().toString();
                String hStr = edtHip.getText().toString();

                // 빈칸 검사
                if (sStr.isEmpty() || wStr.isEmpty() || hStr.isEmpty()) {
                    Toast.makeText(ShapeActivity.this, "어깨, 허리, 골반의 정면 너비(inch)를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double S = 0, W = 0, H = 0;
                try {
                    S = Double.parseDouble(sStr);
                    W = Double.parseDouble(wStr);
                    H = Double.parseDouble(hStr);
                } catch (Exception e) { // 숫자만
                    Toast.makeText(ShapeActivity.this, "숫자만 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                int scoreS = 0;
                int scoreW = 0;
                int scoreN = 0;

                // 인치 기준 체형 판별
                if (H > S + 1.0) { // 골반이 어깨보다 1인치 이상 넓으면 웨이브
                    scoreW += 3;
                } else if (S > H + 1.0) { // 어깨가 골반보다 1인치 이상 넓으면 내추럴
                    scoreN += 3;
                } else { // 차이가 1인치 이내면 스트레이트
                    scoreS += 3;
                }

                // 체크박스 점수 합산
                if (chkS1.isChecked()) scoreS++;
                if (chkS2.isChecked()) scoreS++;
                if (chkS3.isChecked()) scoreS++;
                if (chkS4.isChecked()) scoreS++;
                if (chkS5.isChecked()) scoreS++;
                if (chkS6.isChecked()) scoreS++;

                if (chkW1.isChecked()) scoreW++;
                if (chkW2.isChecked()) scoreW++;
                if (chkW3.isChecked()) scoreW++;
                if (chkW4.isChecked()) scoreW++;
                if (chkW5.isChecked()) scoreW++;
                if (chkW6.isChecked()) scoreW++;

                if (chkN1.isChecked()) scoreN++;
                if (chkN2.isChecked()) scoreN++;
                if (chkN3.isChecked()) scoreN++;
                if (chkN4.isChecked()) scoreN++;
                if (chkN5.isChecked()) scoreN++;
                if (chkN6.isChecked()) scoreN++;

                // 체형 분류 로직
                String finalShape = "";
                if (scoreS > scoreW && scoreS > scoreN) {
                    finalShape = "스트레이트";
                }
                else if (scoreW > scoreS && scoreW > scoreN) {
                    finalShape = "웨이브";
                }
                else if (scoreN > scoreS && scoreN > scoreW) {
                    finalShape = "내추럴";
                }
                else if (scoreS == scoreW && scoreS > scoreN) {
                    finalShape = "스트레이트+웨이브";
                }
                else if (scoreS == scoreN && scoreS > scoreW) {
                    finalShape = "스트레이트+내추럴";
                }
                else if (scoreW == scoreN && scoreW > scoreS) {
                    finalShape = "웨이브+내추럴";
                }
                else {
                    finalShape = "복합(스+웨+내) 균형형";
                }

                processFinish(finalShape);
            }
        });
    }

    // 체형 검사 완료
    private void processFinish(String finalShape) {
        if (isFirstTest) {
            showFirstInfoDialog(finalShape);
        } else {
            returnToMain(finalShape, "", 0, 0);
        }
    }

    // 첫 검사이면 기본 정보 입력
    private void showFirstInfoDialog(final String finalShape) {
        final View dialogView = View.inflate(this, R.layout.dialog_info, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("기본 정보 입력");
        builder.setView(dialogView);
        builder.setCancelable(false);
        builder.setPositiveButton("완료", null);

        final AlertDialog dlg = builder.create();
        dlg.show();

        dlg.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtName = dialogView.findViewById(R.id.dlgName);
                EditText edtH = dialogView.findViewById(R.id.dlgHeight);
                EditText edtW = dialogView.findViewById(R.id.dlgWeight);

                String name = edtName.getText().toString().trim();
                String hStr = edtH.getText().toString().trim();
                String wStr = edtW.getText().toString().trim();

                if (name.isEmpty() || hStr.isEmpty() || wStr.isEmpty()) {
                    Toast.makeText(ShapeActivity.this, "이름, 키, 몸무게를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dlg.dismiss();
                returnToMain(finalShape, name, Double.parseDouble(hStr), Double.parseDouble(wStr));
            }
        });
    }

    // 홈으로
    private void returnToMain(String shape, String name, double h, double w) {
        Intent out = new Intent();
        out.putExtra("SHAPE", shape);
        if (isFirstTest) {
            out.putExtra("NAME", name);
            out.putExtra("HEIGHT", h);
            out.putExtra("WEIGHT", w);
        }
        setResult(RESULT_OK, out);
        finish();
    }
}