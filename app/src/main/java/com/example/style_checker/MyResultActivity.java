package com.example.style_checker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MyResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_result);

        // 화면 요소 연결
        TextView tvColorExplain = findViewById(R.id.tvColorExplain);
        TextView tvShapeExplain = findViewById(R.id.tvShapeExplain);

        View colorBox1 = findViewById(R.id.colorBox1);
        View colorBox2 = findViewById(R.id.colorBox2);
        View colorBox3 = findViewById(R.id.colorBox3);

        Button btnRec = findViewById(R.id.btnRecommend);
        Button btnHome = findViewById(R.id.btnHome);

        // 데이터 가져오기
        String userName = getIntent().getStringExtra("NAME");
        String color = getIntent().getStringExtra("COLOR");
        String detail = getIntent().getStringExtra("DETAIL");
        String shape = getIntent().getStringExtra("SHAPE");

        // Null 에러 방지용 기본값
        if (userName == null || userName.isEmpty()) userName = "고객";
        if (color == null) color = "";
        if (detail == null) detail = "";
        if (shape == null) shape = "";

        final String safeName = userName;
        final String safeColor = color;
        final String safeDetail = detail;
        final String safeShape = shape;

        // 퍼스널 컬러 설명 텍스트
        String colorText = "[퍼스널 컬러]\n" + safeName + "님은 '" + safeColor + " " + safeDetail + "' 타입입니다.\n\n";

        if (safeColor.contains("봄 웜톤")) {
            colorText += "밝고 화사한 봄의 에너지를 가득 담은 타입입니다. 맑고 투명한 피부 톤을 가졌으며, 노란기가 감도는 따뜻하고 생동감 있는 컬러가 얼굴에 형광등을 켜줍니다. 칙칙하거나 무거운 색보다는 명도와 채도가 높은 상큼한 파스텔톤이나 비비드한 컬러를 매치했을 때 특유의 사랑스러운 매력이 극대화됩니다.\n\n";
        }
        else if (safeColor.contains("여름 쿨톤")) {
            colorText += "맑고 청량하며 우아한 분위기를 풍기는 타입입니다. 붉은기나 노란기 없는 시원한 파스텔톤이나 흰 기가 섞인 컬러가 피부를 더욱 뽀얗고 투명하게 만들어줍니다. 강하고 쨍한 색상보다는, 안개가 낀 듯 부드럽고 차분한 컬러톤(그레이쉬, 모브)을 활용할 때 세련되고 이지적인 매력이 가장 잘 살아납니다.\n\n";
        }
        else if (safeColor.contains("가을 웜톤")) {
            colorText += "깊이 있고 성숙하며 고급스러운 무드를 지닌 타입입니다. 가을의 단풍이나 낙엽, 따뜻한 흙을 연상시키는 차분하고 풍부한 색감들이 찰떡처럼 어울립니다. 가벼운 파스텔톤이나 쨍한 원색보다는, 채도가 낮고 명도도 살짝 낮은 분위기 있는 음영 컬러(브릭, 카멜, 카키 등)를 매치했을 때 클래식한 매력이 돋보입니다.\n\n";
        }
        else if (safeColor.contains("겨울 쿨톤")) {
            colorText += "차갑고 도시적이며 압도적인 카리스마를 지닌 타입입니다. 흑과 백의 대비처럼 명확하고 선명한 대비감이 가장 잘 어울리며, 푸른기가 도는 강렬한 원색이나 어두운 다크 톤이 이목구비를 또렷하게 살려줍니다. 애매한 중간 톤이나 탁색보다는, 맑고 쨍하거나 시크한 무채색 계열을 활용할 때 세련되고 화려한 매력이 완성됩니다.\n\n";
        }

        if (safeDetail.equals("라이트")) {
            colorText += "세부 톤: [라이트] 타입은 색에 흰색 물감을 듬뿍 타서 물을 많이 머금은 듯한 '고명도/저채도'의 컬러가 베스트입니다. 색감이 진해질수록 얼굴이 묻힐 수 있으니, 무겁지 않고 여리여리하며 투명한 느낌의 컬러링을 추천합니다.";
        }
        else if (safeDetail.equals("브라이트")) {
            colorText += "세부 톤: [브라이트] 타입은 눈이 시릴 정도로 선명하고 쨍한 '고명도/고채도'의 원색 컬러가 베스트입니다. 채도가 낮거나 탁한 색을 입으면 안색이 피곤해 보일 수 있으니, 톡톡 튀고 화려한 컬러 포인트로 이목구비의 생기를 확 끌어올려 보세요.";
        }
        else if (safeDetail.equals("뮤트(소프트)")) {
            colorText += "세부 톤: [뮤트(소프트)] 타입은 원색에 회색 물감을 한 방울 톡 떨어뜨린 듯한 '중명도/저채도'의 컬러가 베스트입니다. 쨍한 색보다는 물 빠진 듯 차분하고 탁한 컬러(그레이쉬)들이 피부에 부드럽게 스며들어 우아함을 극대화해 줍니다.";
        }
        else if (safeDetail.equals("딥")) {
            colorText += "세부 톤: [딥] 타입은 색상에 검은색이 섞여 아주 짙고 무게감 있는 '저명도/고채도'의 컬러가 베스트입니다. 가벼운 파스텔톤보다는 다크 초콜릿, 와인처럼 깊이감이 확실한 색상을 입었을 때 얼굴의 입체감이 살고 분위기가 압도적으로 깊어집니다.";
        }
        else if (safeDetail.equals("트루")) {
            colorText += "세부 톤: [트루] 타입은 너무 밝지도, 어둡지도, 탁하지도 않은 해당 계절의 '가장 정석적인 맑은 원색'이 베스트입니다. 다른 계절의 색상이 섞이지 않은 순수한 웜/쿨 베이스의 맑은 톤을 사용할 때 밸런스가 가장 완벽하게 맞습니다.";
        }

        tvColorExplain.setText(colorText);

        // 세부 톤 별 색 적용
        String hex1 = "#FFFFFF";
        String hex2 = "#FFFFFF";
        String hex3 = "#FFFFFF";

        if (safeColor.contains("봄")) {
            if (safeDetail.equals("라이트")) { hex1 = "#FFDAB9"; hex2 = "#FFFACD"; hex3 = "#B8E2F2"; }
            else if (safeDetail.equals("브라이트")) { hex1 = "#FF6F61"; hex2 = "#FF9933"; hex3 = "#39FF14"; }
            else { hex1 = "#FFB347"; hex2 = "#F4C430"; hex3 = "#7BA05B"; }
        }
        else if (safeColor.contains("여름")) {
            if (safeDetail.equals("라이트")) { hex1 = "#F8BBD0"; hex2 = "#E6E6FA"; hex3 = "#E0FFFF"; }
            else if (safeDetail.equals("뮤트(소프트)")) { hex1 = "#D8BFD8"; hex2 = "#B0C4DE"; hex3 = "#C8A2C8"; }
            else { hex1 = "#FF69B4"; hex2 = "#4169E1"; hex3 = "#8A2BE2"; }
        }
        else if (safeColor.contains("가을")) {
            if (safeDetail.equals("뮤트(소프트)")) { hex1 = "#D2B48C"; hex2 = "#BC8F8F"; hex3 = "#8FBC8F"; }
            else if (safeDetail.equals("딥")) { hex1 = "#8B0000"; hex2 = "#5C4033"; hex3 = "#2F4F4F"; }
            else { hex1 = "#CC7722"; hex2 = "#B87333"; hex3 = "#556B2F"; }
        }
        else if (safeColor.contains("겨울")) {
            if (safeDetail.equals("브라이트")) { hex1 = "#FF007F"; hex2 = "#00FFFF"; hex3 = "#CCFF00"; }
            else if (safeDetail.equals("딥")) { hex1 = "#800020"; hex2 = "#4B0082"; hex3 = "#013220"; }
            else { hex1 = "#E60000"; hex2 = "#000000"; hex3 = "#FFFFFF"; }
        }

        colorBox1.setBackgroundColor(Color.parseColor(hex1));
        colorBox2.setBackgroundColor(Color.parseColor(hex2));
        colorBox3.setBackgroundColor(Color.parseColor(hex3));

        // 체형 설명 텍스트
        String shapeText = "[체형 및 골격]\n" + safeName + "님은 '" + safeShape + "' 체형입니다.\n\n";

        if (safeShape.equals("스트레이트")) {
            shapeText += "바스트와 힙의 위치가 높고, 몸통 자체가 둥글고 입체적인 서구형 체형입니다. 근육의 탄력이 잘 느껴지며, 상하체의 밸런스가 좋아 다리가 곧고 예쁜 경우가 많습니다. 장식이 많고 얇은 소재보다는, 각이 잘 잡혀 있고 탄탄한 고급 소재의 옷을 깔끔하게(미니멀) 입었을 때 글래머러스한 장점이 가장 잘 살아납니다. V넥이나 세로 스트라이프를 활용해 상체를 슬림하게 연출하는 것이 좋습니다.";
        }
        else if (safeShape.equals("웨이브")) {
            shapeText += "뼈대가 얇고 가녀리며, 무게 중심이 하체 쪽에 있는 부드러운 곡선미의 체형입니다. 목선부터 쇄골로 이어지는 라인이 특히 아름답습니다. 상체가 다소 평면적일 수 있어 프릴, 셔링, 리본 등 볼륨감을 더해주는 화려한 장식과 하늘하늘한 쉬폰 소재가 아주 잘 어울립니다. 하이웨스트 하의나 크롭 기장의 상의로 허리선을 위로 확실히 끌어올려 주면 비율이 훨씬 좋아 보입니다.";
        }
        else if (safeShape.equals("내추럴")) {
            shapeText += "뼈대와 관절이 뚜렷하게 두드러지고, 어깨 등 골격(프레임)이 튼튼하게 잡혀 있어 모델처럼 옷을 멋스럽게 소화하는 체형입니다. 몸에 딱 붙거나 얇은 소재보다는, 골격을 자연스럽게 덮어주는 오버핏, 리넨, 데님, 코듀로이 같은 질감이 뚜렷한 소재가 찰떡입니다. 넉넉한 기장의 와이드 팬츠나 맥시 스커트로 툭 걸친 듯한 무심한 꾸안꾸 스타일링이 베스트입니다.";
        }
        else if (safeShape.equals("스트레이트+웨이브")) {
            shapeText += "스트레이트 체형의 '입체적이고 글래머러스한 상체'와 웨이브 체형의 '부드러운 하체 곡선'이 조화롭게 섞인 하이브리드 체형입니다. 상체는 깔끔하고 단정하게 실루엣을 잡아주고, 하체는 A라인 스커트나 부츠컷 팬츠로 곡선을 부드럽게 살려주는 스타일링이 좋습니다. 너무 루즈한 핏보다는 허리 라인을 가볍게 잡아주는 세미 포멀룩이 가장 훌륭하게 어울립니다.";
        }
        else if (safeShape.equals("스트레이트+내추럴")) {
            shapeText += "스트레이트 체형의 '입체적인 바디라인'에 내추럴 체형의 '멋스러운 골격'이 더해져 건강미와 시크함을 동시에 지닌 체형입니다. 하체는 스트레이트의 장점인 곧게 뻗은 일자핏(스트레이트 팬츠)을 살리고, 상체는 내추럴의 특성을 살려 넉넉한 오버핏 셔츠나 테일러드 자켓을 매치하는 것이 좋습니다. 각 잡힌 오버핏 실루엣이 가장 시크하게 연출됩니다.";
        }
        else if (safeShape.equals("웨이브+내추럴")) {
            shapeText += "웨이브 체형의 '여리여리하고 얇은 선'을 가졌지만, 내추럴 체형의 '뚜렷한 직각 어깨와 관절' 덕분에 옷태가 잘 사는 체형입니다. 상체는 스퀘어넥이나 깊은 U넥으로 쇄골을 시원하게 드러내고 크롭 기장으로 허리선을 높이되, 하체는 텍스처가 살아있는 와이드 슬랙스로 넉넉하게 덮어주는 믹스매치가 가장 패셔너블하게 어울립니다.";
        }
        else if (safeShape.equals("복합(스+웨+내) 균형형")) {
            shapeText += "스트레이트의 입체감, 웨이브의 곡선미, 내추럴의 골격미가 모두 밸런스 있게 섞인 체형입니다. 극단적인 스타일링에 치우치지 않기 때문에, 트렌디한 데일리룩부터 기본 베이직 아이템까지 어떤 옷이든 무난하고 감각적으로 소화할 수 있는 황금 비율을 자랑합니다. 상의는 적당한 포인트가 있는 기본템을, 하의는 와이드 데님을 믹스매치하는 등 정석적인 코디를 강력 추천합니다.";
        }

        tvShapeExplain.setText(shapeText);

        // 추천 화면으로
        btnRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RecommendActivity.class);
                intent.putExtra("NAME", safeName);
                intent.putExtra("COLOR", safeColor);
                intent.putExtra("SHAPE", safeShape);
                intent.putExtra("DETAIL", safeDetail);
                startActivity(intent);
            }
        });

        // 홈으로
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("NAME", safeName);
                intent.putExtra("COLOR", safeColor);
                intent.putExtra("SHAPE", safeShape);
                intent.putExtra("DETAIL", safeDetail);
                startActivity(intent);
                finish(); // 현재 화면 닫기
            }
        });
    }
}