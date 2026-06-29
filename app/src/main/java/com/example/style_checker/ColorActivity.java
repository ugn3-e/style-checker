package com.example.style_checker;


import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import androidx.core.content.res.ResourcesCompat;


public class ColorActivity extends AppCompatActivity {


    // 화면 구성 요소
    TextView tvStageTitle;
    LinearLayout questionContainer;
    Button btnNext;


    // 라디오그룹 리스트
    List<RadioGroup> currentRgList = new ArrayList<>();


    // 현재 진행 중인 상태 저장 변수
    String currentState = "STAGE1_PART1";


    // 누적 점수 저장 변수
    int scoreOption1 = 0;
    int scoreOption2 = 0;
    int scoreOption3 = 0;


    // 최종 결과 저장 변수
    String finalTone = "";
    String finalSeason = "";
    String finalDetail = "";

    boolean isFirstTest;

    // 추가 문제 인덱스
    int tieBreakerIndex = 0;

    // 질문과 답 배열

    // [1단계: 웜/쿨]
    String[][] dataStage1 = {
            {"1. 자연광 아래서 손목 안쪽 혈관을 봤을 때, \n더 많이 보이는 색은?",
                    "초록색이나 올리브색 핏줄이 더 많이 보인다.",
                    "푸른색이나 보라색 핏줄이 더 많이 보인다."},

            {"2. 금반지와 은반지를 껴봤을 때, \n손 피부가 더 맑아 보이는 쪽은?",
                    "금반지(골드)를 꼈을 때 손이 덜 누래 보이고 맑아 보인다.",
                    "은반지(실버)를 꼈을 때 피부톤이 균일하고 화사해 보인다."},

            {"3. 염색하지 않은 본래 내 머리카락을 햇빛에 비춰보면?",
                    "완전한 흑색이 아니라 갈색빛, 혹은 약간의 노란기가 돈다.",
                    "푸른빛이 감도는 짙은 검은색이거나 선명한 흑갈색이다."},

            {"4. 네일을 하지 않은 손톱 밑(네일 바디)의 본래 색상은?",
                    "따뜻한 피치(복숭아)빛이나 살구색 톤이다.",
                    "차가운 핑크빛이나 보라색 톤이다."},

            {"5. 메이크업을 지운 상태의 입술 색상에 가까운 쪽은?",
                    "피치빛, 연어색, 혹은 코랄 베이지 톤이다.",
                    "푸른기가 도는 핑크빛, 보랏빛 혹은 팥죽색 톤이다."},

            {"6. 눈동자의 테두리와 전체적인 색감은?",
                    "부드러운 밀크 브라운 또는 밝은 갈색 톤이다.",
                    "아주 짙은 고동색, 새까만 검정 또는 쿨한 회갈색이다."},

            {"7. 흰자위(공막)를 자세히 관찰해보면?",
                    "완전한 순백색보다는 미세하게 아이보리나 노란기가 돈다.",
                    "노란기 없이 푸른빛이 감도는 깨끗한 순백색이다."},

            {"8. 피곤할 때 눈 밑에 내려오는 다크서클의 색상은?",
                    "녹색이나 갈색빛이 도는 거무튀튀한 색이다.",
                    "보라색이나 푸른빛이 도는 멍든 것 같은 색이다."},

            {"9. 순백색 옷과 따뜻한 아이보리 옷 중 \n얼굴의 잡티가 덜 부각되는 것은?",
                    "아이보리 옷을 입어야 피부가 안정적이고 잡티가 덜 보인다.",
                    "순백색 옷을 입어야 얼굴에 형광등이 켜진 듯 화사해진다."}
    };


    // [2단계: 웜톤(봄vs가을)]
    String[][] dataStage2Warm = {
            {"1. 평소 주변 사람들에게 내 이미지나 첫인상에 대해 \n자주 듣는 말은?",
                    "'발랄하다', '귀엽다', '생기 있다'는 말을 많이 듣는다.",
                    "'분위기 있다', '성숙하다', '차분하다'는 말을 많이 듣는다."},

            {"2. 골드 액세서리를 착용했을 때 얼굴 느낌은?",
                    "반짝반짝 빛나는 '유광' 밝은 골드가 잘 어울린다.",
                    "광택이 없는 '무광'이나 빈티지한 앤틱 골드가 낫다."},

            {"3. 홍조가 올라왔을 때 색은?",
                    "맑고 투명한 복숭아/코랄빛으로 붉어진다.",
                    "붉은기보다는 탁한 오렌지/베이지빛으로 더워진다."},

            {"4. 눈썹을 진하게 그리면?",
                    "눈썹만 숯검댕이처럼 둥둥 떠서 인위적이고 어색하다.",
                    "이목구비가 깊어지며 화장한 티가 나서 좋다."},

            {"5. 눈동자와 흰자위 경계는?",
                    "경계가 뚜렷해서 눈망울이 반짝거려 보인다.",
                    "경계가 부드럽게 그라데이션 된 듯 그윽하다."},

            {"6. 베이지 계열 옷을 입었을 때 느낌은?",
                    "밝고 화사한 라이트 베이지(오트밀)가 잘 받는다.",
                    "낙엽 같은 짙은 딥 베이지(카멜)가 더 잘 어울린다."},

            {"7. 검은 마스크를 착용했을 때 느낌은?",
                    "얼굴의 그늘(다크서클, 팔자주름)이 부각되어 \n피곤해 보인다.",
                    "얼굴이 칙칙해지지 않고 무난하고 안정적으로 소화한다."},

            {"8. 흑백 사진으로 보면?",
                    "이목구비 대비가 약해 전체적으로 뽀얗고 흐릿하다.",
                    "명암 대비가 뚜렷해서 얼굴의 입체감이 잘 산다."},

            {"9. 생얼 상태에서 얼굴을 보면?",
                    "눈썹이나 눈동자보다 '피부 톤(밝기)'이 먼저 눈에 띈다.",
                    "피부 밝기보다 '눈썹이나 눈동자'의 선명함이 먼저 보인다."}
    };


    // [2단계: 쿨톤(여름vs겨울)]
    String[][] dataStage2Cool = {
            {"1. 평소 주변 사람들에게 내 이미지나 첫인상에 대해 \n자주 듣는 말은?",
                    "'청순하다', '부드럽다', '여리여리하다'는 말을 많이 듣는다.",
                    "'세련됐다', '시크하다', '카리스마 있다'는 말을 많이 듣는다."},

            {"2. 실버 액세서리를 착용했을 때 얼굴 느낌은?",
                    "작고 얇게 찰랑이는 은은한 실버가 잘 어울린다.",
                    "크고 화려하거나 광택이 강한 볼드한 실버가 잘 어울린다."},

            {"3. 입술과 피부의 대비는?",
                    "입술 색이 연해서 피부와 큰 대비 없이 부드럽게 이어진다.",
                    "입술 색이 붉고 또렷해서 피부와 대비가 확연하다."},

            {"4. 눈썹을 진하게 그리면?",
                    "눈썹만 숯검댕이처럼 둥둥 떠서 인위적이고 어색하다.",
                    "이목구비가 깊어지며 화장한 티가 나서 좋다."},

            {"5. 눈동자와 흰자위 경계는?",
                    "부드럽게 풀어져 있어 눈빛이 안개 낀 듯 은은하다.",
                    "경계가 칼같이 선명해서 눈빛이 강렬하다."},

            {"6. 회색 계열 옷을 입었을 때 느낌은?",
                    "회색이 피부에 잘 스며들어 안색이 맑아 보인다.",
                    "회색이 옷에 묻혀 칙칙해지며 차라리 블랙/화이트가 낫다."},

            {"7. 검은 마스크를 착용했을 때 느낌은?",
                    "얼굴의 그늘(다크서클, 팔자주름)이 부각되어 \n피곤해 보인다.",
                    "얼굴이 칙칙해지지 않고 무난하고 안정적으로 소화한다."},

            {"8. 흑백 사진으로 보면?",
                    "이목구비 대비가 약해 전체적으로 뽀얗고 흐릿하다.",
                    "명암 대비가 뚜렷해서 얼굴의 입체감이 잘 산다."},

            {"9. 생얼 상태에서 얼굴을 보면?",
                    "눈썹이나 눈동자보다 '피부 톤(밝기)'이 먼저 눈에 띈다.",
                    "피부 밝기보다 '눈썹이나 눈동자'의 선명함이 먼저 보인다."}
    };



    // [3단계: 봄 웜톤 ] (라이트, 브라이트, 트루)
    String[][] dataStage3Spring = {
            {"1. 흰색 옷을 입었을 때 느낌은?",
                    "얼굴이 밝아지고 전체적으로 여리하고 부드러워 보인다.",
                    "피부가 하얗게 질리면서 이목구비가 쨍하게 살아난다.",
                    "무난하게 어울리며 자연스럽고 안정적인 인상이 된다."},

            {"2. 진한 브라운 옷을 입었을 때 느낌은?",
                    "옷이 너무 어두워 안색이 칙칙해지고 나이 들어 보인다.",
                    "옷은 무겁지만 명도 대비가 생겨 이목구비는 뚜렷해진다.",
                    "제법 따뜻하고 무난하게 잘 소화한다."},

            {"3. 밝은 코랄 계열 옷을 입었을 때 느낌은?",
                    "흰 기가 많이 섞인 연한 파스텔 코랄을 입었을 때 가장 맑다.",
                    "형광기 도는 쨍하고 선명한 코랄을 입어야 얼굴이 환해진다.",
                    "너무 연하거나 쨍하지 않은 '정석 코랄'이 제일 편안하다."},

            {"4. 밝은 핑크 계열 옷을 입었을 때 느낌은?",
                    "흰 기가 도는 연핑크가 여리여리하게 잘 받는다.",
                    "시선을 확 끄는 핫핑크나 코랄핑크를 입어야 생기가 돈다.",
                    "핑크보다는 살구/피치톤이 훨씬 자연스럽다."},

            {"5. 대비 있는 스타일의 옷을 입었을 때 느낌은?",
                    "상하의 대비가 크면(흑백 등) 얼굴이 옷에 묻혀 촌스럽다.",
                    "강렬한 배색의 옷을 입을수록 화려하게 잘 어울린다.",
                    "심한 대비보다는 비슷한 톤끼리 겹쳐 입어야 안정적이다."},

            {"6. 진한 메이크업을 했을 때 느낌은?",
                    "섀도우를 얹을수록 화장이 떡져 보여 뷰러+투명립이 낫다.",
                    "화려한 글리터와 쨍한 립스틱으로 포인트를 줄수록 예쁘다.",
                    "은은한 코랄/브라운 음영 메이크업 정도가 딱 적당하다."},

            {"7. 흑백 사진에서 인상은?",
                    "눈동자, 피부, 입술의 명암 차이가 적어 \n전체적으로 연하고 흐린 인상이다.",
                    "눈동자와 입술이 진해서 흰 피부와의 명암 대비가 뚜렷하다.",
                    "너무 연하지도, 너무 튀지도 않는 중간 정도의 명암 대비다."},

            {"8. 사진을 찍었을 때 전체적인 분위기는?",
                    "파스텔 필터를 씌운 듯 맑고 뽀얀 느낌이다.",
                    "비비드한 필터를 씌운 듯 채도가 높고 화려한 느낌이다.",
                    "따뜻하고 생기 있는 전형적인 웜톤의 무난한 분위기다."},

            {"9. 다양한 색을 얼굴 가까이에 두었을 때 느낌은?",
                    "물이 많이 섞인 연한 수채화 같은 색에서 얼굴이 산다.",
                    "눈이 시릴 정도로 쨍하고 채도 높은 원색에서 얼굴이 산다.",
                    "연한색은 밋밋하고 원색은 부담스러운 중간 톤이 베스트다."}
    };


    // [3단계: 여름 쿨톤] (라이트, 뮤트, 트루)
    String[][] dataStage3Summer = {
            {"1. 흰색 옷을 입었을 때 느낌은?",
                    "얼굴이 맑고 투명해지며 가장 찰떡으로 잘 어울린다.",
                    "새하얀 색은 둥둥 뜨고, 약간 탁한 오트밀이나 회색이 낫다.",
                    "깔끔하게 잘 어울리지만, 완전한 베스트는 아니다."},

            {"2. 회색 계열 옷을 입었을 때 느낌은?",
                    "아주 밝은 은회색(라이트 그레이)에서만 얼굴이 화사해진다.",
                    "쥐색, 차콜 등 모든 종류의 회색이 \n우아하고 고급스럽게 잘 어울린다.",
                    "회색을 입으면 칙칙해지고 차라리 시원한 네이비가 낫다."},

            {"3. 파스텔 계열 옷을 입었을 때 느낌은?",
                    "흰 기 가득한 딸기우유색을 입으면 가장 뽀얗고 예쁘다.",
                    "흰 기 도는 색은 뜨고, 회색이 섞인 탁한 인디핑크가 낫다.",
                    "파스텔은 너무 밋밋해서 차라리 시원한 쿨톤 원색이 낫다."},

            {"4. 진한 네이비 계열 옷을 입었을 때 느낌은?",
                    "색이 너무 무거워서 얼굴이 옷에 잡아먹혀 어색하다.",
                    "적당히 차분하고 안정감 있게 잘 어울린다.",
                    "이목구비가 또렷해지며 깔끔하고 세련되게 소화한다."},

            {"5. 대비감 있는 스타일의 옷을 입었을 때 느낌은?",
                    "대비가 거의 없이 비슷한 파스텔톤끼리 입어야 맑아 보인다.",
                    "탁한 색과 어두운 색을 톤온톤으로 입었을 때 우아하다.",
                    "네이비+화이트 처럼 명확하고 깔끔한 대비를 줘야 \n이목구비가 산다."},

            {"6. 메이크업을 진하게 했을 때 느낌은?",
                    "섀도우를 바를수록 나이 들어 보여서 \n무조건 투명 화장이 낫다.",
                    "톤다운된 보랏빛(모브)이나 장미색으로 \n부드럽게 층을 쌓아야 예쁘다.",
                    "음영은 지저분하고, 깔끔한 아이라인+쿨핑크 풀립이 낫다."},

            {"7. 흑백 사진에서 인상은?",
                    "명암 대비가 거의 없이 뽀얗고 여리여리하게 찍힌다.",
                    "전체적으로 안개가 낀 듯 회색조 느낌이 강하게 나온다.",
                    "이목구비가 흑과 백으로 선명하게 나뉘어 깔끔하다."},

            {"8. 사진을 찍었을 때 전체적인 분위기는?",
                    "투명하고 맑은 이온음료 같은 분위기가 강하다.",
                    "안개 낀 새벽이나 차분하고 우아한 무드가 강하다.",
                    "청량하고 시원하며 깔끔한 분위기가 만들어진다."},

            {"9. 다양한 색을 얼굴 가까이에 두었을 때 느낌은?",
                    "흰 기가 많이 섞인 연한 톤에서 안색이 가장 밝아진다.",
                    "회색기가 섞인 탁하고 부드러운 톤에서 가장 우아하다.",
                    "선명하고 쨍한 쿨톤 컬러에서 가장 뚜렷하고 시원해 보인다."}
    };


    // [3단계: 가을 웜톤] (뮤트, 딥, 트루)
    String[][] dataStage3Autumn = {
            {"1. 진한 브라운 옷을 입었을 때 느낌은?",
                    "너무 어두운 브라운은 칙칙해서, 차분한 코코아색이 낫다.",
                    "다크 초콜릿처럼 아주 짙은 브라운에서 이목구비가 확 산다.",
                    "따뜻한 단풍 브라운이 가장 무난하게 어울린다."},

            {"2. 파스텔 계열 옷을 입었을 때 느낌은?",
                    "그나마 회기가 섞인 탁한 파스텔(인디핑크)은 \n부드럽게 소화한다.",
                    "얼굴이 흐리멍덩하고 칙칙해져서 차라리 블랙이 낫다.",
                    "옷이 둥둥 뜨고 나이 들어 보여서 절대 안 입는다."},

            {"3. 베이지 계열 옷을 입었을 때 느낌은?",
                    "물 빠진 듯 부드러운 오트밀 베이지가 자연스럽고 우아하다.",
                    "밝은 베이지는 심심하고, 짙은 카멜색을 입어야 안정적이다.",
                    "따뜻한 노란기가 감도는 베이지가 균형 잡히게 어울린다."},

            {"4. 녹색 계열 옷을 입었을 때 느낌은?",
                    "탁한 카키색이나 올리브 그린이 부드럽게 어울린다.",
                    "아주 어둡고 깊은 다크 그린을 입었을 때 \n성숙한 분위기가 난다.",
                    "따뜻한 잔디색이나 중간 톤의 녹색이 안정적으로 받는다."},

            {"5. 대비감 있는 스타일의 옷을 입었을 때 느낌은?",
                    "비슷한 탁한 톤끼리 겹쳐 입었을 때가(톤온톤) \n가장 자연스럽다.",
                    "블랙과 베이지처럼 강한 명암 대비를 주어도 잘 소화한다.",
                    "너무 강하지 않은 중간 정도의 대비를 주었을 때 예쁘다."},

            {"6. 메이크업을 진하게 했을 때 느낌은?",
                    "진한 스모키는 과해서 은은한 말린 장미색 스머징이 낫다.",
                    "그윽하고 화려한 스모키 화장이 정말 잘 어울려 얼굴이 산다.",
                    "분위기 있는 가을 음영 화장이 자연스럽고 예쁘다."},

            {"7. 흑백 사진에서 인상은?",
                    "전체적으로 부드러운 회색조로 흐릿하고 우아하게 나온다.",
                    "흑백 명암 대비가 아주 뚜렷해서 카리스마 있고 강렬하다.",
                    "흐리지도 과하지도 않은 균형 잡힌 인상이다."},

            {"8. 사진을 찍었을 때 전체적인 분위기는?",
                    "꾸안꾸 느낌의 차분하고 부드러운 분위기이다.",
                    "깊고 무거우면서도 섹시하고 성숙한 분위기이다.",
                    "고급스럽고 따뜻한 클래식한 분위기이다."},

            {"9. 다양한 색을 얼굴 가까이에 두었을 때 느낌은?",
                    "회색기가 도는 탁한 색이 얼굴에 부드럽게 스며든다.",
                    "블랙이나 다크 브라운 등 아주 짙고 어두운 색이 찰떡이다.",
                    "가을 단풍처럼 적당히 따뜻하고 선명한 색이 베스트다."}
    };


    // [3단계: 겨울 쿨톤] (브라이트, 딥, 트루)
    String[][] dataStage3Winter = {
            {"1. 흰색 옷을 입었을 때 느낌은?",
                    "얼굴에 조명을 켠 듯 피부가 하얗게 질리며 예뻐 보인다.",
                    "가벼워 보여서 차라리 묵직한 어두운 와인색/블랙이 낫다.",
                    "얼굴이 깔끔하게 정리되며 균형 잡힌 정석 쿨톤 느낌이다."},

            {"2. 회색 계열 옷을 입었을 때 느낌은?",
                    "흰색에 가까운 차가운 아이스 그레이 외에는 \n얼굴이 칙칙해진다.",
                    "아주 어둡고 묵직한 차콜 그레이(딥 회색)를 입어야 \n정돈된다.",
                    "연회색, 진회색 상관없이 회색은 \n무난하고 깔끔하게 다 소화한다."},

            {"3. 선명한 원색 계열 옷을 입었을 때 느낌은?",
                    "형광기 있는 핫핑크 등 원색이 얼굴을 확 살려준다.",
                    "원색보다 어두운 딥 버건디, 다크 네이비가 더 깊이 있다.",
                    "형광기는 과하고 딱 깔끔한 기본 원색이 베스트다."},

            {"4. 검정색 계열 옷을 입었을 때 느낌은?",
                    "얼굴이 너무 답답해져서 무조건 흰색을 매치해 줘야 한다.",
                    "위아래 올블랙을 입었을 때 카리스마있게 제일 잘 어울린다.",
                    "무난하고 세련되게 정리되며 가장 자주 입는 베스트 컬러다."},

            {"5. 대비감 있는 스타일의 옷을 입었을 때 느낌은?",
                    "원색과 블랙처럼 강한 배색을 줄수록 얼굴이 \n또렷하고 화려해진다.",
                    "대비보다는 무겁고 어두운 톤으로 맞춰 입었을 때 멋지다.",
                    "블랙 앤 화이트 정장처럼 단정하고 극단적인 대비가 \n잘 어울린다."},

            {"6. 메이크업을 진하게 했을 때 느낌은?",
                    "색조를 올리면 더워 보여서 맑은 글리터와 \n쨍한 립 하나만 바른다.",
                    "음영은 빼고 고혹적인 버건디/보라색 매트 립을 발라야 \n분위기가 산다.",
                    "깔끔한 아이라인에 정석 레드 립스틱 풀립이 \n제일 잘 어울린다."},

            {"7. 흑백 사진에서 인상은?",
                    "눈동자와 입술의 강렬하고 선명한 인상이 강조된다.",
                    "그림자가 짙게 져서 아주 깊고 무게감 있는 인상이 나온다.",
                    "대비가 명확해 정돈되고 세련된 인상이 만들어진다."},

            {"8. 사진을 찍었을 때 전체적인 분위기는?",
                    "네온사인처럼 화려하고 통통 튀며 선명한 분위기다.",
                    "뱀파이어처럼 강렬하고 서늘하며 고혹적인 분위기다.",
                    "차도녀처럼 깔끔하고 단정하며 정제된 분위기다."},

            {"9. 다양한 색을 얼굴 가까이에 두었을 때 느낌은?",
                    "눈이 시릴 정도로 형광기 섞인 쨍한 고채도에서 \n얼굴이 산다.",
                    "블랙이나 다크 네이비처럼 아주 어둡고 짙은 색에서 \n존재감이 산다.",
                    "탁하지 않고 맑은 정석 원색 계열에서 균형이 딱 잡힌다."}
    };

    // 추가 질문
    String[][] dataTieSpring = {
            {"베이지 색상 비교: 더 화사한 쪽은?", "노란기가 도는 밝은 오트밀 베이지", "살구빛이 감도는 피치 베이지", "차분한 웜톤 정석 베이지"},
            {"나에게 가장 베스트인 헤어 컬러는?", "밝고 노란기가 도는 밀크 브라운", "화사하고 생기 있는 오렌지 브라운", "부드럽고 따뜻한 초코 브라운"},
            {"헤어 컬러 비교: 안색을 더 살려주는 쪽은?", "밝은 밀크 브라운이 더 화사하다.", "초코 브라운이 더 차분하고 안정적이다.", "오렌지 브라운이 더 생기 있다."},
            {"핑크 색상 비교: 안색을 밝혀주는 쪽은?", "흰 기 섞인 연한 피치 핑크", "형광기 도는 쨍한 코랄 핑크", "따뜻한 정석 웜 핑크"}
    };

    String[][] dataTieSummer = {
            {"블루 색상 비교: 얼굴을 맑게 하는 쪽은?", "맑고 투명한 스카이 블루", "연보라빛이 감도는 라벤더 블루", "회색 섞인 차분한 블루 그레이"},
            {"나에게 가장 베스트인 헤어 컬러는?", "부드럽고 자연스러운 애쉬 브라운", "투명한 느낌의 라이트 브라운", "차분하고 어두운 쿨 브라운"},
            {"헤어 컬러 비교: 인상이 더 또렷해지는 쪽은?", "애쉬 브라운이 피부를 더 맑게 한다.", "라이트 브라운이 인상을 부드럽게 한다.", "쿨 브라운이 이목구비를 또렷하게 한다."},
            {"핑크 색상 비교: 더 조화로운 쪽은?", "아주 연한 딸기우유 핑크", "차분하고 탁한 인디 핑크", "선명한 쿨 핫핑크"}
    };

    String[][] dataTieAutumn = {
            {"브라운 색상 비교: 얼굴이 또렷한 쪽은?", "따뜻하고 짙은 카멜색", "커피 섞인 듯한 딥 브라운", "황토빛 감도는 오렌지 브라운"},
            {"나에게 가장 베스트인 헤어 컬러는?", "깊이 있는 다크 초콜릿 브라운", "분위기 있는 매트 브라운", "풍부한 느낌의 딥 카멜 브라운"},
            {"헤어 컬러 비교: 인상이 더 고급스러운 쪽은?", "다크 초콜릿이 피부를 차분하게 한다.", "매트 브라운이 피부 결을 고급스럽게 한다.", "딥 카멜이 얼굴에 건강한 생기를 준다."},
            {"레드 색상 비교: 성숙해 보이는 쪽은?", "깊이 있는 벽돌 레드(레드브라운)", "차분한 와인 레드", "주황기 도는 클래식 레드"}
    };

    String[][] dataTieWinter = {
            {"레드 색상 비교: 화려해 보이는 쪽은?", "쨍한 정석 레드", "차가운 쿨 레드(체리 레드)", "고혹적인 딥 버건디"},
            {"나에게 가장 베스트인 헤어 컬러는?", "아주 진하고 선명한 블루 블랙", "차갑고 차분한 다크 브라운", "인위적이지 않은 아주 짙은 흑색"},
            {"헤어 컬러 비교: 이미지가 더 또렷한 쪽은?", "블루 블랙이 피부를 하얗게 질리게 한다.", "다크 브라운이 세련된 느낌을 한다.", "자연 흑색이 이목구비를 가장 또렷하게 한다."},
            {"블랙 vs 화이트: 인상이 더 또렷한 쪽은?", "머리부터 발끝까지 올 블랙", "머리부터 발끝까지 올 화이트", "대비감이 확실한 블랙 앤 화이트"}
    };

    // 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        isFirstTest = getIntent().getBooleanExtra("IS_FIRST", false);

        // 화면 요소 연결
        tvStageTitle = findViewById(R.id.tvStageTitle);
        questionContainer = findViewById(R.id.questionContainer);
        btnNext = findViewById(R.id.btnNext);

        // 1단계 1~5번(Part 1) 화면 띄우기
        renderQuestions("[1단계] 웜/쿨 진단 (1/2)", dataStage1, 0, 5);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 질문 선택 확인
                if (!checkAllAnswered()) {
                    Toast.makeText(ColorActivity.this, "모든 항목을 선택해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // 선택한 답들을 점수로 환산해서 저장
                calculateScores();

                // 상태 변경
                if (currentState.equals("STAGE1_PART1")) {
                    currentState = "STAGE1_PART2";
                    renderQuestions("[1단계] 웜/쿨 진단 (2/2)", dataStage1, 5, 9);
                }
                else if (currentState.equals("STAGE1_PART2")) {
                    if (scoreOption1 >= scoreOption2) {
                        finalTone = "웜톤";
                        currentState = "STAGE2_WARM_PART1";
                        resetScores();
                        renderQuestions("[2단계: 웜톤] 봄 vs 가을 (1/2)", dataStage2Warm, 0, 5);
                    } else {
                        finalTone = "쿨톤";
                        currentState = "STAGE2_COOL_PART1";
                        resetScores();
                        renderQuestions("[2단계: 쿨톤] 여름 vs 겨울 (1/2)", dataStage2Cool, 0, 5);
                    }
                }
                else if (currentState.equals("STAGE2_WARM_PART1")) {
                    currentState = "STAGE2_WARM_PART2";
                    renderQuestions("[2단계: 웜톤] 봄 vs 가을 (2/2)", dataStage2Warm, 5, 9);
                }
                else if (currentState.equals("STAGE2_WARM_PART2")) {
                    if (scoreOption1 >= scoreOption2) {
                        finalSeason = "봄";
                        currentState = "STAGE3_SPRING_PART1";
                        resetScores();
                        renderQuestions("[3단계: 봄 웜톤] 세부 톤 진단 (1/2)", dataStage3Spring, 0, 5);
                    } else {
                        finalSeason = "가을";
                        currentState = "STAGE3_AUTUMN_PART1";
                        resetScores();
                        renderQuestions("[3단계: 가을 웜톤] 세부 톤 진단 (1/2)", dataStage3Autumn, 0, 5);
                    }
                }
                else if (currentState.equals("STAGE2_COOL_PART1")) {
                    currentState = "STAGE2_COOL_PART2";
                    renderQuestions("[2단계: 쿨톤] 여름 vs 겨울 (2/2)", dataStage2Cool, 5, 9);
                }
                else if (currentState.equals("STAGE2_COOL_PART2")) {
                    if (scoreOption1 >= scoreOption2) {
                        finalSeason = "여름";
                        currentState = "STAGE3_SUMMER_PART1";
                        resetScores();
                        renderQuestions("[3단계: 여름 쿨톤] 세부 톤 진단 (1/2)", dataStage3Summer, 0, 5);
                    } else {
                        finalSeason = "겨울";
                        currentState = "STAGE3_WINTER_PART1";
                        resetScores();
                        renderQuestions("[3단계: 겨울 쿨톤] 세부 톤 진단 (1/2)", dataStage3Winter, 0, 5);
                    }
                }
                else if (currentState.contains("STAGE3") && currentState.contains("PART1")) {
                    String nextTitle = tvStageTitle.getText().toString().replace("(1/2)", "(2/2)");

                    if (currentState.contains("SPRING")) {
                        currentState = "STAGE3_SPRING_PART2";
                        renderQuestions(nextTitle, dataStage3Spring, 5, 9);
                    } else if (currentState.contains("SUMMER")) {
                        currentState = "STAGE3_SUMMER_PART2";
                        renderQuestions(nextTitle, dataStage3Summer, 5, 9);
                    } else if (currentState.contains("AUTUMN")) {
                        currentState = "STAGE3_AUTUMN_PART2";
                        renderQuestions(nextTitle, dataStage3Autumn, 5, 9);
                    } else if (currentState.contains("WINTER")) {
                        currentState = "STAGE3_WINTER_PART2";
                        renderQuestions(nextTitle, dataStage3Winter, 5, 9);
                    }
                    btnNext.setText("퍼스널 컬러 분석 완료");
                }
                else if (currentState.contains("STAGE3") && currentState.contains("PART2")) {
                    // 3단계 최종 문제(18번)까지 완료 후 동점 여부 검사
                    int maxScore = Math.max(scoreOption1, Math.max(scoreOption2, scoreOption3));
                    int tieCount = 0;
                    if(scoreOption1 == maxScore) tieCount++;
                    if(scoreOption2 == maxScore) tieCount++;
                    if(scoreOption3 == maxScore) tieCount++;

                    if (tieCount > 1) { // 1위가 여러 개 일 때
                        currentState = "STAGE3_TIE_BREAKER";
                        tieBreakerIndex = 0;
                        btnNext.setText("다음");
                        renderSingleTieQuestion();
                    } else { // 단독 1위가 있을 때
                        determineFinalDetailAndFinish();
                    }
                }
                // 추가 질문
                else if (currentState.equals("STAGE3_TIE_BREAKER")) {
                    int maxScore = Math.max(scoreOption1, Math.max(scoreOption2, scoreOption3));
                    int tieCount = 0;
                    if(scoreOption1 == maxScore) tieCount++;
                    if(scoreOption2 == maxScore) tieCount++;
                    if(scoreOption3 == maxScore) tieCount++;

                    if (tieCount > 1 && tieBreakerIndex < 3) {
                        // 아직 동점이면 다음 질문
                        tieBreakerIndex++;
                        if(tieBreakerIndex == 3) btnNext.setText("최종 결정");
                        renderSingleTieQuestion();
                    } else {
                        // 1위가 나왔거나 다 풀면
                        determineFinalDetailAndFinish();
                    }
                }
            }
        });
    }

    // 추가 문제 띄우기
    private void renderSingleTieQuestion() {
        tvStageTitle.setText("추가 질문." + (tieBreakerIndex + 1));
        questionContainer.removeAllViews();
        currentRgList.clear();

        String[][] targetTieData;
        if(finalSeason.equals("봄")) targetTieData = dataTieSpring;
        else if(finalSeason.equals("여름")) targetTieData = dataTieSummer;
        else if(finalSeason.equals("가을")) targetTieData = dataTieAutumn;
        else targetTieData = dataTieWinter;

        TextView tvQuestion = new TextView(this);
        tvQuestion.setText(targetTieData[tieBreakerIndex][0]);
        tvQuestion.setTextSize(16f);
        tvQuestion.setPadding(0, 40, 0, 10);

        // 색 설정
        tvQuestion.setTextColor(Color.parseColor("#390F1C"));
        // 폰트 설정
        tvQuestion.setTypeface(ResourcesCompat.getFont(this, R.font.zen_serif_regular));

        RadioGroup rg = new RadioGroup(this);
        rg.setOrientation(RadioGroup.VERTICAL);
        rg.setPadding(0, 0, 0, 30);

        for (int j = 1; j < targetTieData[tieBreakerIndex].length; j++) {
            RadioButton rb = new RadioButton(this);
            rb.setText(targetTieData[tieBreakerIndex][j]);
            rb.setId(View.generateViewId());
            rb.setTag(j);

            // 색 설정
            rb.setTextColor(Color.parseColor("#502533"));
            // 폰트 설정
            rb.setTypeface(ResourcesCompat.getFont(this, R.font.zen_serif_regular));

            rg.addView(rb);
        }

        questionContainer.addView(tvQuestion);
        questionContainer.addView(rg);
        currentRgList.add(rg);
    }

    // 점수로 세부톤 결정
    private void determineFinalDetailAndFinish() {
        if (scoreOption1 >= scoreOption2 && scoreOption1 >= scoreOption3) {
            if(finalSeason.equals("봄") || finalSeason.equals("여름")) finalDetail = "라이트";
            else if(finalSeason.equals("가을")) finalDetail = "뮤트(소프트)";
            else finalDetail = "브라이트";
        }
        else if (scoreOption2 >= scoreOption1 && scoreOption2 >= scoreOption3) {
            if(finalSeason.equals("봄")) finalDetail = "브라이트";
            else if(finalSeason.equals("여름")) finalDetail = "뮤트(소프트)";
            else finalDetail = "딥";
        }
        else {
            finalDetail = "트루"; // 추가 문제까지 완전 동점이면 트루
        }

        processFinish();
    }

    // 질문 띄우기
    private void renderQuestions(String title, String[][] data, int startIndex, int endIndex) {
        tvStageTitle.setText(title);
        questionContainer.removeAllViews();
        currentRgList.clear();

        for (int i = startIndex; i < endIndex; i++) {
            TextView tvQuestion = new TextView(this);
            tvQuestion.setText(data[i][0]);
            tvQuestion.setTextSize(16f);
            tvQuestion.setPadding(0, 40, 0, 10);

            // 색 설정
            tvQuestion.setTextColor(Color.parseColor("#390F1C"));
            // 폰트 설정
            tvQuestion.setTypeface(ResourcesCompat.getFont(this, R.font.zen_serif_regular));

            RadioGroup rg = new RadioGroup(this);
            rg.setOrientation(RadioGroup.VERTICAL);
            rg.setPadding(0, 0, 0, 30);

            for (int j = 1; j < data[i].length; j++) {
                RadioButton rb = new RadioButton(this);
                rb.setText(data[i][j]);
                rb.setId(View.generateViewId());
                rb.setTag(j);

                // 색 설정
                rb.setTextColor(Color.parseColor("#502533"));
                // 폰트 설정
                rb.setTypeface(ResourcesCompat.getFont(this, R.font.zen_serif_regular));

                rg.addView(rb);
            }
            questionContainer.addView(tvQuestion);
            questionContainer.addView(rg);
            currentRgList.add(rg);
        }
    }

    // 질문 선택 확인
    private boolean checkAllAnswered() {
        for (RadioGroup rg : currentRgList) {
            if (rg.getCheckedRadioButtonId() == -1) {
                return false;
            }
        }
        return true;
    }

    // 점수 계산
    private void calculateScores() {
        for (RadioGroup rg : currentRgList) {
            int checkedId = rg.getCheckedRadioButtonId();
            RadioButton checkedRb = findViewById(checkedId);
            int selectedIndex = (int) checkedRb.getTag();

            if (selectedIndex == 1) scoreOption1++;
            else if (selectedIndex == 2) scoreOption2++;
            else if (selectedIndex == 3) scoreOption3++;
        }
    }

    // 점수 초기화
    private void resetScores() {
        scoreOption1 = 0;
        scoreOption2 = 0;
        scoreOption3 = 0;
    }

    // 퍼컬 검사 완료
    private void processFinish() {
        if (isFirstTest) {
            showFirstInfoDialog();
        } else {
            returnToMain("", 0, 0);
        }
    }

    // 첫 검사이면 기본 정보 입력
    private void showFirstInfoDialog() {
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
                    Toast.makeText(ColorActivity.this, "이름, 키, 몸무게를 모두 입력해주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dlg.dismiss();
                returnToMain(name, Double.parseDouble(hStr), Double.parseDouble(wStr));
            }
        });
    }

    // 홈으로
    private void returnToMain(String name, double h, double w) {
        Intent out = new Intent();
        out.putExtra("COLOR", finalSeason + " " + finalTone);
        out.putExtra("DETAIL", finalDetail);

        if (isFirstTest) {
            out.putExtra("NAME", name);
            out.putExtra("HEIGHT", h);
            out.putExtra("WEIGHT", w);
        }

        setResult(RESULT_OK, out);
        finish();
    }
}