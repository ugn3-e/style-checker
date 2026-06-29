package com.example.style_checker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 데이터 저장 변수
    String myName = "", myColor = "", myShape = "", myDetail = "";
    double myHeight = 0, myWeight = 0;

    Button btnColor, btnShape, btnResult, btnRecommend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 화면 요소 연결
        btnColor = findViewById(R.id.btnColorTest);
        btnShape = findViewById(R.id.btnShapeTest);
        btnResult = findViewById(R.id.btnMyResult);
        btnRecommend = findViewById(R.id.btnRecommend);

        // 데이터 가져오기
        if (savedInstanceState != null) {
            myName = savedInstanceState.getString("NAME", "");
            myColor = savedInstanceState.getString("COLOR", "");
            myShape = savedInstanceState.getString("SHAPE", "");
            myDetail = savedInstanceState.getString("DETAIL", "");
            myHeight = savedInstanceState.getDouble("HEIGHT", 0);
            myWeight = savedInstanceState.getDouble("WEIGHT", 0);
        } else {
            // 결과/ 추천에서 홈으로
            Intent homeIntent = getIntent();
            if (homeIntent != null) {
                myName = homeIntent.getStringExtra("NAME");
                myColor = homeIntent.getStringExtra("COLOR");
                myShape = homeIntent.getStringExtra("SHAPE");
                myDetail = homeIntent.getStringExtra("DETAIL");
                myHeight = homeIntent.getDoubleExtra("HEIGHT", 0);
                myWeight = homeIntent.getDoubleExtra("WEIGHT", 0);

                // 처음 앱 실행 시
                if (myName == null) myName = "";
                if (myColor == null) myColor = "";
                if (myShape == null) myShape = "";
                if (myDetail == null) myDetail = "";
            }
        }

        // 퍼스널 컬러 검사 (처음이면 팝업)
        btnColor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myColor == null || myColor.equals("")) showColorGuideDialog();
                else startColorTest();
            }
        });

        // 체형 검사 (처음이면 팝업)
        btnShape.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myShape == null || myShape.equals("")) showShapeGuideDialog();
                else startShapeTest();
            }
        });

        // 내 결과 보기
        btnResult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myColor.equals("") || myShape.equals("")) {
                    Toast.makeText(MainActivity.this, "퍼스널 컬러와 체형, 2가지 검사를 모두 마쳐야 합니다!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), MyResultActivity.class);
                intent.putExtra("NAME", myName);
                intent.putExtra("COLOR", myColor);
                intent.putExtra("SHAPE", myShape);
                intent.putExtra("DETAIL", myDetail);
                startActivity(intent);
            }
        });

        // 추천 보기
        btnRecommend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (myColor.equals("") || myShape.equals("")) {
                    Toast.makeText(MainActivity.this, "퍼스널 컬러와 체형, 2가지 검사를 모두 마쳐야 합니다!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), RecommendActivity.class);
                intent.putExtra("NAME", myName);
                intent.putExtra("COLOR", myColor);
                intent.putExtra("SHAPE", myShape);
                intent.putExtra("DETAIL", myDetail);
                startActivity(intent);
            }
        });
    }

    // 퍼컬 팝업
    private void showColorGuideDialog() {
        View v = View.inflate(this, R.layout.dialog_color_guide, null);
        final AlertDialog dlg = new AlertDialog.Builder(this).setView(v).create();

        Button btnStart = v.findViewById(R.id.btnColorStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "체형과 퍼컬 중 처음 검사일 때만" 정보 입력 대화상자를 띄우기 위한 플래그 계산
                boolean isFirstOverall = (myColor == null || myColor.equals("")) && (myShape == null || myShape.equals(""));
                Intent i = new Intent(MainActivity.this, ColorActivity.class);
                i.putExtra("IS_FIRST", isFirstOverall);
                startActivityForResult(i, 100); // A 방법: 팝업에서 만든 Intent로 바로 시작
                dlg.dismiss();
            }
        });
        dlg.show();
    }

    // 체형 팝업
    private void showShapeGuideDialog() {
        View v = View.inflate(this, R.layout.dialog_shape_guide, null);
        final AlertDialog dlg = new AlertDialog.Builder(this).setView(v).create();

        Button btnStart = v.findViewById(R.id.btnShapeStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // "체형과 퍼컬 중 처음 검사일 때만" 정보 입력 대화상자를 띄우기 위한 플래그 계산
                boolean isFirstOverall = (myColor == null || myColor.equals("")) && (myShape == null || myShape.equals(""));
                Intent i = new Intent(MainActivity.this, ShapeActivity.class);
                i.putExtra("IS_FIRST", isFirstOverall);
                startActivityForResult(i, 200); // A 방법: 팝업에서 만든 Intent로 바로 시작
                dlg.dismiss();
            }
        });
        dlg.show();
    }

    private void startColorTest() {
        Intent i = new Intent(this, ColorActivity.class);
        startActivityForResult(i, 100);
    }

    private void startShapeTest() {
        Intent i = new Intent(this, ShapeActivity.class);
        startActivityForResult(i, 200);
    }

    // 데이터 저장
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("NAME", myName);
        outState.putString("COLOR", myColor);
        outState.putString("SHAPE", myShape);
        outState.putString("DETAIL", myDetail);
        outState.putDouble("HEIGHT", myHeight);
        outState.putDouble("WEIGHT", myWeight);
    }

    // 메뉴 탭 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // 정보 수정
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_modify) {
            showModifyDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 정보 수정 대화상자
    private void showModifyDialog() {
        final View dialogView = View.inflate(this, R.layout.dialog_info, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("내 정보 수정");
        builder.setView(dialogView);

        final EditText edtName = dialogView.findViewById(R.id.dlgName);
        final EditText edtH = dialogView.findViewById(R.id.dlgHeight);
        final EditText edtW = dialogView.findViewById(R.id.dlgWeight);

        // 기존 값으로 채우기 (값이 없으면 빈칸)
        edtName.setText(myName != null ? myName : "");
        if (myHeight != 0) edtH.setText(String.valueOf(myHeight));
        if (myWeight != 0) edtW.setText(String.valueOf(myWeight));

        builder.setPositiveButton("수정 완료", null);
        builder.setNegativeButton("취소", null);

        final AlertDialog dlg = builder.create();
        dlg.show();

        dlg.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String hStr = edtH.getText().toString().trim();
                String wStr = edtW.getText().toString().trim();

                if (name.isEmpty() || hStr.isEmpty() || wStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "이름, 키, 몸무게를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double hVal, wVal;
                try {
                    hVal = Double.parseDouble(hStr);
                    wVal = Double.parseDouble(wStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "키와 몸무게는 숫자로 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 값 저장
                myName = name;
                myHeight = hVal;
                myWeight = wVal;

                Toast.makeText(MainActivity.this, "정보가 성공적으로 수정되었습니다!", Toast.LENGTH_SHORT).show();
                dlg.dismiss();
            }
        });
    }

    // 검사 완료 후 돌아왔을 때
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            // 이름/키/몸무게가 전달되면 업데이트
            if (data.hasExtra("NAME")) {
                myName = data.getStringExtra("NAME");
            }
            if (data.hasExtra("HEIGHT")) {
                myHeight = data.getDoubleExtra("HEIGHT", myHeight);
            }
            if (data.hasExtra("WEIGHT")) {
                myWeight = data.getDoubleExtra("WEIGHT", myWeight);
            }

            if (requestCode == 100) {
                myColor = data.getStringExtra("COLOR");
                myDetail = data.getStringExtra("DETAIL");

                if (myShape.equals("")) {
                    showNextChoiceDialog("컬러 검사 완료!", "이어서 체형 검사를 받으시겠습니까?", btnShape);
                } else {
                    showFinalChoiceDialog();
                }
            } else if (requestCode == 200) {
                myShape = data.getStringExtra("SHAPE");

                if (myColor.equals("")) {
                    showNextChoiceDialog("체형 검사 완료!", "이어서 퍼스널 컬러 검사를 받으시겠습니까?", btnColor);
                } else {
                    showFinalChoiceDialog();
                }
            }
        }
    }

    // 다음 검사, 화면으로 이동 팝업
    private void showNextChoiceDialog(String title, String msg, final Button nextBtn) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle(title);
        dlg.setMessage(msg);
        dlg.setPositiveButton("네, 이어서 할게요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                nextBtn.performClick();
            }
        });
        dlg.setNegativeButton("아니오, 홈으로", null);
        dlg.show();
    }

    private void showFinalChoiceDialog() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setTitle("모든 검사 완료!");
        dlg.setMessage("모든 진단이 끝났습니다. 종합 진단 결과를 바로 확인하시겠습니까?");

        dlg.setPositiveButton("네, 결과 볼래요", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                btnResult.performClick();
            }
        });

        dlg.setNegativeButton("아니오, 홈에 남을게요", null);
        dlg.show();
    }
}
