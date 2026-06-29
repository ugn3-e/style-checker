package com.example.style_checker;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class RecommendActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        // 화면 요소 연결
        TextView tvMakeupRec = findViewById(R.id.tvMakeupRec);
        TextView tvOutfitRec = findViewById(R.id.tvOutfitRec);

        // 화장품 이미지
        ImageView imgRec1 = findViewById(R.id.imgRec1);
        ImageView imgRec2 = findViewById(R.id.imgRec2);
        ImageView imgRec3 = findViewById(R.id.imgRec3);
        ImageView imgRec4 = findViewById(R.id.imgRec4);

        // 패션 이미지
        ImageView imgOutfit1 = findViewById(R.id.imgOutfit1);
        ImageView imgOutfit2 = findViewById(R.id.imgOutfit2);
        ImageView imgOutfit3 = findViewById(R.id.imgOutfit3);

        Button btnHome = findViewById(R.id.btnHome);

        // 데이터 가져오기
        Intent intent = getIntent();
        String userName = intent.getStringExtra("NAME");
        String color = intent.getStringExtra("COLOR");
        String detail = intent.getStringExtra("DETAIL");
        String shape = intent.getStringExtra("SHAPE");

        // Null 에러 방지용 기본값
        if (userName == null || userName.isEmpty()) userName = "고객";
        if (color == null) color = "";
        if (detail == null) detail = "";
        if (shape == null) shape = "";

        final String safeName = userName;
        final String safeColor = color;
        final String safeDetail = detail;
        final String safeShape = shape;

        // 화장품 추천 멘트
        String makeupRec = "";

        if (safeColor.contains("봄 웜톤")) {
            if ("라이트".equals(safeDetail)) {
                makeupRec = safeName + "님은 맑고 투명한 광택감이 찰떡인 [고명도/저채도]의 봄 라이트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n탁기가 섞이면 안색이 칙칙해집니다. 두꺼운 파운데이션보다 맑은 피부 표현을 하고, 투명하게 차오르는 코랄 립글로스로 생기를 더해보세요.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 클리오 쉐이드 앤 섀도우 팔레트 [스쿱 오브 셰이드]\n"
                        + "• 블러셔: VDL 치크 스테인 블러셔 [바운싱 피치]\n"
                        + "• 립: 하트퍼센트 도트 온 무드 원웨이 글로이 틴트 [피치 밀크] \n딘토 멜팅 글로우 립밤 [샬롯]";
            } else if ("브라이트".equals(safeDetail)) {
                makeupRec = safeName + "님은 채도 높고 쨍한 컬러로 생기발랄함을 내뿜는 [고명도/고채도]의 봄 브라이트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n이목구비가 뚜렷해 보이도록 형광기 있는 코랄 핑크나 오렌지 레드를 포인트로 사용하세요. 블러셔로 과즙미를 살리는 것이 핵심입니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 클리오 프로 아이 팔레트 [낮잠자는 치즈]\n"
                        + "• 블러셔: 프레시안 에그라이크 크림 블러쉬 [선샤인]\n"
                        + "• 립: 3CE 캐시미어 허그 립스틱 [버디] \n클리오 크리스탈 글램 틴트 [베이비 애플]";
            } else {
                makeupRec = safeName + "님은 따뜻하고 화사한 봄의 정석, [고명도/중채도] 트루 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n극단적인 쨍함이나 탁함 없이, 맑게 올라오는 피치 톤과 따뜻한 코랄 브라운 음영이 얼굴의 생기를 완벽하게 살려줍니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 웨이크메이크 소프트 블러링 아이팔레트 [캔디 코랄 블러링]\n"
                        + "• 블러셔: 에뛰드 하트 팝 블러셔 [슈퍼 선셋]\n"
                        + "• 립:  퓌 3D 볼류밍 글로스 [바닐라 30%] \n얼터너티브스테레오 무드 레이어 스틱 크레용 [카라멜 드리즐]";
            }
        }
        else if (safeColor.contains("여름 쿨톤")) {
            if ("라이트".equals(safeDetail)) {
                makeupRec = safeName + "님은 시원하고 청량한 정석 쿨 핑크가 어울리는 [고명도/저채도]의 여름 라이트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n흰 기가 많이 도는 딸기 우유색 핑크와 투명한 글래스 광택 위주로 메이크업을 가볍게 덜어낼수록 얼굴이 화사해집니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 롬앤 베러 댄 아이즈 [피오니 누드 가든]\n"
                        + "• 블러셔: 데이지크 블렌딩 무드 치크 [쿨블렌딩]\n"
                        + "• 립: 롬앤 쥬시 래스팅 틴트 [피오니 발레] \n데이지크 무드 글로우 립스틱 [핑크 베리]";
            } else if ("뮤트(소프트)".equals(safeDetail)) {
                makeupRec = safeName + "님은 차분하고 우아한 분위기가 매력적인 [중명도/저채도]의 여름 뮤트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n회기가 살짝 섞인 모브(Mauve) 핑크나 애쉬 로즈 컬러를 사용하여 튀지 않고 부드럽게 톤 온 톤(Tone-on-tone) 음영을 쌓아주세요.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 삐아 에센셜 아이 팔레트 [유어 모브]\n"
                        + "• 블러셔: 롬앤 베러 댄 치크 [바인 누드]\n"
                        + "• 립: 힌스 무드인핸서 마뜨 [얼루어] \n삐아 글로우 틴트 [데일리 보틀]";
            } else {
                makeupRec = safeName + "님은 쿨하고 선명한 마젠타 핑크가 찰떡인 [고명도/고채도]의 여름 브라이트(트루) 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n깔끔한 피부 표현 위에 채도 높은 브라이트 핑크 립 하나만으로 확실한 포인트를 주어 시선을 집중시켜 보세요.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 릴리바이레드 무드 키보드 [밀키베리 스테레오]\n"
                        + "• 블러셔: 릴리바이레드 러브빔 치크빔 [순수빔]\n"
                        + "• 립: 에스쁘아 노웨어 바밍글로우 립스틱 [헤이베스티] \n페리페라 잉크 무드 글로이 틴트 [어쩔체리]";
            }
        }
        else if (safeColor.contains("가을 웜톤")) {
            if ("뮤트(소프트)".equals(safeDetail)) {
                makeupRec = safeName + "님은 부드러운 베이지와 말린 장미 컬러가 고급스러운 [중명도/저채도]의 가을 뮤트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n글로시한 질감보다는 피부에 녹아드는 포슬포슬한 매트 제형이 잘 어울리며, 그윽하고 가벼운 스머지 립이 베스트입니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 투쿨포스쿨 프로타주 팔레트 [밀크 스모어]\n"
                        + "• 블러셔: 삐아 레디 투 웨어 다우니 치크 [다우니 초코]\n"
                        + "• 립: 어뮤즈 젤핏 틴트 [오트 무화과] \n롬앤 제로 매트 립스틱 [스모크드 베이지]";
            } else if ("딥".equals(safeDetail)) {
                makeupRec = safeName + "님은 고혹적이고 다크한 분위기를 지배하는 [저명도/고채도]의 가을 딥 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n가벼운 파스텔톤은 피하고, 깊이감 있는 칠리나 브릭 레드 립, 그리고 다크 브라운 섀도우로 무게감 있는 음영을 넣어주세요.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 데이지크 섀도우 팔레트 [초콜릿 퍼지]\n"
                        + "• 블러셔: 릴리바이레드 러브빔 치크빔 [너티빔]\n"
                        + "• 립: 맥 실키 매트 립스틱 [칠리] \n페리페라 오버 블러 틴트 [힙수고대]";
            } else {
                makeupRec = safeName + "님은 가을의 정석, 단풍과 골드 브라운이 어울리는 [중명도/중채도] 트루 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n오렌지 브라운 립과 골드 펄 섀도우를 사용하여 따뜻하면서도 성숙한 고급스러움을 연출하는 것이 가장 좋습니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 에스쁘아 아이 코어 팔레트 [빈티지 뮤트]\n"
                        + "• 블러셔: 크리니크 치크팝 [누드팝]\n"
                        + "• 립: 롬앤 글래스팅 멜팅 밤 [카야 피그] \n삐아 오버 글레이즈 [피그당]";
            }
        }
        else if (safeColor.contains("겨울 쿨톤")) {
            if ("브라이트".equals(safeDetail)) {
                makeupRec = safeName + "님은 네온처럼 쨍한 컬러와 은펄이 시선을 사로잡는 [고명도/고채도]의 겨울 브라이트 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n푸시아 핑크나 형광기 도는 워터 틴트로 입술을 맑게 밝히고, 화려한 실버 펄 섀도우로 눈가에 포인트를 주세요.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 웨이크메이크 소프트 블러링 아이팔레트 [하이 핑크 블러링]\n"
                        + "• 블러셔: 크리니크 치크팝 [플럼팝]\n"
                        + "• 립: 무지개맨션 오브제 글로시 [체리 드롭] \n웨이크메이크 듀이 젤 글레이즈 스틱 [플럼 브루]";
            } else if ("딥".equals(safeDetail)) {
                makeupRec = safeName + "님은 검은 기가 섞인 와인과 플럼 컬러가 우아한 [저명도/고채도]의 겨울 딥 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n블러셔는 생략하거나 최소화하고, 다크 버건디나 딥 플럼 립스틱으로 고혹적인 무게감을 잡아주면 시크함이 극대화됩니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 입큰 퍼스널 무드 팔레트 [플레인 윈터]\n"
                        + "• 블러셔: 누그레이 스웨이 치크 블러셔 [캐리에]\n"
                        + "• 립: 맥 실키 매트 립스틱 [디바] \n롬앤 쥬시 래스팅 틴트 [플럼 콕]";
            } else {
                makeupRec = safeName + "님은 대비감이 생명인 맑고 쨍한 레드와 쿨 그레이가 정답인 [중명도/고채도] 겨울 트루 톤입니다.\n\n"
                        + " 메이크업 꿀팁\n어중간한 색은 버리세요! 새하얀 피부를 살리는 깔끔한 쿨 그레이 음영에, 정석 레드 립 하나만 발라도 완벽합니다.\n\n"
                        + " 추천 화장품\n"
                        + "• 섀도우: 삐아 에센셜 아이 팔레트 [쿨 그레이]\n"
                        + "• 블러셔: 에뛰드 러블리 쿠키 블러셔 [라벤더 쉬폰 케이크]\n"
                        + "• 립: 클리오 매드 매트 스테인 립 [번트 버건디] \n뮤드 엔젤 허그 글레이즈 [플러미]";
            }
        }
        else {
            makeupRec = "퍼스널 컬러 진단 정보가 부족합니다. 먼저 검사를 완료해주세요!";
        }

        String topText = " [" + safeName + "님을 위한 맞춤 추천]\n\n\n[맞춤 메이크업 & 화장품 추천]\n\n" + makeupRec;
        tvMakeupRec.setText(topText);

        // 화장품 이미지 로드
        if (safeColor.contains("봄")) {
            if (safeDetail.equals("라이트")) {
                imgRec1.setImageResource(R.drawable.spring_light_cheek);
                imgRec2.setImageResource(R.drawable.spring_light_eye);
                imgRec3.setImageResource(R.drawable.spring_light_lip);
                imgRec4.setImageResource(R.drawable.spring_light_lipstick);
            } else if (safeDetail.equals("브라이트")) {
                imgRec1.setImageResource(R.drawable.spring_bright_cheek);
                imgRec2.setImageResource(R.drawable.spring_bright_eye);
                imgRec3.setImageResource(R.drawable.spring_bright_lip);
                imgRec4.setImageResource(R.drawable.spring_bright_lipstick);
            } else { // 트루
                imgRec1.setImageResource(R.drawable.spring_true_cheek);
                imgRec2.setImageResource(R.drawable.spring_true_eye);
                imgRec3.setImageResource(R.drawable.spring_true_lip);
                imgRec4.setImageResource(R.drawable.spring_true_lipstick);
            }
        } else if (safeColor.contains("여름")) {
            if (safeDetail.equals("라이트")) {
                imgRec1.setImageResource(R.drawable.summer_light_cheek);
                imgRec2.setImageResource(R.drawable.summer_light_eye);
                imgRec3.setImageResource(R.drawable.summer_light_lip);
                imgRec4.setImageResource(R.drawable.summer_light_lipstick);
            } else if (safeDetail.equals("뮤트(소프트)")) {
                imgRec1.setImageResource(R.drawable.summer_mute_cheek);
                imgRec2.setImageResource(R.drawable.summer_mute_eye);
                imgRec3.setImageResource(R.drawable.summer_mute_lip);
                imgRec4.setImageResource(R.drawable.summer_mute_lipstick);
            } else { // 트루
                imgRec1.setImageResource(R.drawable.summer_true_cheek);
                imgRec2.setImageResource(R.drawable.summer_true_eye);
                imgRec3.setImageResource(R.drawable.summer_true_lip);
                imgRec4.setImageResource(R.drawable.summer_true_lipstick);
            }
        } else if (safeColor.contains("가을")) {
            if (safeDetail.equals("뮤트(소프트)")) {
                imgRec1.setImageResource(R.drawable.autumn_mute_cheek);
                imgRec2.setImageResource(R.drawable.autumn_mute_eye);
                imgRec3.setImageResource(R.drawable.autumn_mute_lip);
                imgRec4.setImageResource(R.drawable.autumn_mute_lipstick);
            } else if (safeDetail.equals("딥")) {
                imgRec1.setImageResource(R.drawable.autumn_deep_cheek);
                imgRec2.setImageResource(R.drawable.autumn_deep_eye);
                imgRec3.setImageResource(R.drawable.autumn_deep_lip);
                imgRec4.setImageResource(R.drawable.autumn_deep_lipstick);
            } else { // 트루
                imgRec1.setImageResource(R.drawable.autumn_true_cheek);
                imgRec2.setImageResource(R.drawable.autumn_true_eye);
                imgRec3.setImageResource(R.drawable.autumn_true_lip);
                imgRec4.setImageResource(R.drawable.autumn_true_lipstick);
            }
        } else if (safeColor.contains("겨울")) {
            if (safeDetail.equals("브라이트")) {
                imgRec1.setImageResource(R.drawable.winter_bright_cheek);
                imgRec2.setImageResource(R.drawable.winter_bright_eye);
                imgRec3.setImageResource(R.drawable.winter_bright_lip);
                imgRec4.setImageResource(R.drawable.winter_bright_lipstick);
            } else if (safeDetail.equals("딥")) {
                imgRec1.setImageResource(R.drawable.winter_deep_cheek);
                imgRec2.setImageResource(R.drawable.winter_deep_eye);
                imgRec3.setImageResource(R.drawable.winter_deep_lip);
                imgRec4.setImageResource(R.drawable.winter_deep_lipstick);
            } else { // 트루
                imgRec1.setImageResource(R.drawable.winter_true_cheek);
                imgRec2.setImageResource(R.drawable.winter_true_eye);
                imgRec3.setImageResource(R.drawable.winter_true_lip);
                imgRec4.setImageResource(R.drawable.winter_true_lipstick);
            }
        }

        // 코디 추천 멘트
        String outfitRec = "";
        String outfitPrefix = ""; // 코디 파일 로드용 프리픽스

        if ("스트레이트".equals(safeShape)) {
            outfitPrefix = "straight_";
            outfitRec = "각 잡힌 소재와 깔끔하게 떨어지는 실루엣으로 상체의 입체감을 슬림하게 정돈해 주는 미니멀 룩입니다. 세로 스트라이프 셔츠를 오픈하여 아우터처럼 걸치면 훨씬 날씬해 보입니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 무신사 스탠다드 우먼즈 릴렉스 핏 옥스포드 셔츠\n"
                    + "• 하의: 슬로우앤드 마켓 와이드 트레이닝 슬랙스\n"
                    + "• 아우터: 미쏘 테일러드 싱글 크롭 자켓";
        }
        else if ("웨이브".equals(safeShape)) {
            outfitPrefix = "wave_";
            outfitRec = "화사한 프릴 and 타이 디테일로 상체의 볼륨감을 가득 채우고, 하이웨스트 하의와 크롭 기장 아우터로 허리선을 높여 하체 밸런스를 위로 완벽하게 끌어올린 룩입니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: LAP 브이넥 프릴 타이끈 블라우스\n"
                    + "• 하의: 로엠 롱 부츠컷 슬랙스\n"
                    + "• 아우터: 미쏘 라운드넥 크롭 가디건";
        }
        else if ("내추럴".equals(safeShape)) {
            outfitPrefix = "natural_";
            outfitRec = "탄탄한 프레임과 골격을 스타일리시하게 감싸주는 오버핏 매치입니다. 넉넉한 통의 와이드 데님과 볼륨감 있는 파카 조합으로 트렌디하고 시크한 꾸안꾸 무드를 완성합니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 마리떼 프랑소와 저버 CLASSIC LOGO TEE\n"
                    + "• 하의: 무신사 스탠다드 우먼즈 와이드 데님 팬츠\n"
                    + "• 아우터: 파르티멘토 비건 레더 피쉬테일 파카";
        }
        else if ("스트레이트+웨이브".equals(safeShape)) {
            outfitPrefix = "straight_wave_";
            outfitRec = "슬림핏 상의로 목선과 실루엣을 깔끔하게 잡아주고, 골반 라인부터 부드럽게 퍼지는 세미 A라인 스커트와 탄탄한 트위드 자켓을 매치해 단정하면서도 세련된 무드를 살린 룩입니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 링클 프리 슬림핏 셔츠\n"
                    + "• 하의: 세미 A라인 미디 스커트\n"
                    + "• 아우터: 룩캐스트 하나 노카라 트위드 자켓";
        }
        else if ("스트레이트+내추럴".equals(safeShape)) {
            outfitPrefix = "straigh_natural_"; // 유진님 소문자 파일명 완벽 대치
            outfitRec = "하체는 스트레이트 체형의 장점인 곧고 슬림한 라인을 일자 핏으로 살려주고, 상체는 내추럴 체형의 멋스러운 어깨 각을 살려 오버핏 셔츠와 자켓으로 시크하게 연출한 하이브리드 정석 룩입니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 시티브리즈 클래식 오버핏 셔츠\n"
                    + "• 하의: 리 우먼스 스트레이트 핏 데님\n"
                    + "• 아우터: 에잇세컨즈 오버핏 테일러드 자켓";
        }
        else if ("웨이브+내추럴".equals(safeShape)) {
            outfitPrefix = "wave_natural_";
            outfitRec = "스퀘어넥으로 목선과 쇄골 라인을 시원하게 강조하면서, 텍스처가 살아있는 꽈배기 짜임 크롭 가디건과 부드럽게 찰랑이는 투턱 슬랙스를 매치해 여리여리하면서도 편안한 무드를 동시에 챙긴 룩입니다.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 시티브리즈 골지 스퀘어넥 반팔 티셔츠\n"
                    + "• 하의: 시야쥬 더블 위브 투턱 와이드 슬랙스\n"
                    + "• 아우터: 스파오 우디 케이블 크롭 가디건";
        }
        else if ("복합(스+웨+내) 균형형".equals(safeShape)) {
            outfitPrefix = "all_";
            outfitRec = "전체적인 체형의 균형감이 좋아 어떤 스타일이든 쉽게 소화하는 황금 비율입니다. 베이직한 체리 로고 티셔츠와 와이드 데님에 클래식한 트위드 자켓을 믹스매치하여 감각적인 데일리 룩을 연출해 보세요.\n\n"
                    + " 추천 아이템\n"
                    + "• 상의: 키르시 빅 체리 반팔 티셔츠\n"
                    + "• 하의: 캘빈클라인 진 여성 아시아와이드 데님\n"
                    + "• 아우터: 미쏘 베이직 라운드넥 트위드 자켓";
        }
        else {
            outfitRec = "체형 진단 정보가 부족합니다. 먼저 검사를 완료해주세요!";
        }

        tvOutfitRec.setText("[맞춤 체형 코디 추천]\n\n" + outfitRec);

        // 코디 이미지 로드
        if ("스트레이트".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.straight_1);
            imgOutfit2.setImageResource(R.drawable.straight_2);
            imgOutfit3.setImageResource(R.drawable.straight_3);
        } else if ("웨이브".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.wave_1);
            imgOutfit2.setImageResource(R.drawable.wave_2);
            imgOutfit3.setImageResource(R.drawable.wave_3);
        } else if ("내추럴".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.natural_1);
            imgOutfit2.setImageResource(R.drawable.natural_2);
            imgOutfit3.setImageResource(R.drawable.natural_3);
        } else if ("스트레이트+웨이브".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.straight_wave_1);
            imgOutfit2.setImageResource(R.drawable.straight_wave_2);
            imgOutfit3.setImageResource(R.drawable.straight_wave_3);
        } else if ("스트레이트+내추럴".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.straight_natural_1);
            imgOutfit2.setImageResource(R.drawable.straight_natural_2);
            imgOutfit3.setImageResource(R.drawable.straight_natural_3);
        } else if ("웨이브+내추럴".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.wave_natural_1);
            imgOutfit2.setImageResource(R.drawable.wave_natural_2);
            imgOutfit3.setImageResource(R.drawable.wave_natural_3);
        } else if ("복합(스+웨+내) 균형형".equals(safeShape)) {
            imgOutfit1.setImageResource(R.drawable.all_1);
            imgOutfit2.setImageResource(R.drawable.all_2);
            imgOutfit3.setImageResource(R.drawable.all_3);
        }

        // 홈으로
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("NAME", safeName);
                outIntent.putExtra("COLOR", safeColor);
                outIntent.putExtra("SHAPE", safeShape);
                outIntent.putExtra("DETAIL", safeDetail);
                startActivity(outIntent);
                finish(); // 추천 화면 닫기
            }
        });
    }
}