# prj1114

Last edit : 2022.11.30 10:00


### Packages
1. common   
1) current user, team id 등 앱 전역에서 사용되는 변수   
TODO 대안 : baseViewModel


2. data   
Firestore.kt : 파이어스토어 소스   
Model.kt : 데이터 모델 및 Dto 정의   
BaseRepository.kt : 리포지토리   
Type: 코드 내부에서 사용되는 custom type 정의   
JusoDataSource: 주소 가져오는 데 사용되는 data type, 레트로핏 연결   
JusoService: 주소를 Juso type 배열로 가져옴   


3. detail   
DetailFragment.kt : 상세화면   
ChatFragment.kt : 채팅화면   
ChatAdapter.kt : 채팅 리스트 어댑터   


4. search   
SearchFragment.kt : 검색화면   
JusoFragment.kt : 주소 검색 화면 (recycler)   
JusoAdapter.kt : 주소 리스트 어댑터


5. service   
MyFirebaseMessagingService.kt : 푸시알림 수신    


6. util   
NaverLogin.kt : 네이버 로그인 관련 기능   
NodeServer.kt : 외부 서버 연결 관련 기능 (sendEmail, sendFcm)   


7. viewmodel   
ChatViewModel.kt : 삭제예정   
DetailedViewModel.kt : 상세화면 뷰 모델   
RegisViewModel.kt : 로그인 뷰 모델   
SearchViewModel.kt : 검색 뷰 모델   


### Note.   
구현된 fragment를 view-viewmodel-model 패턴으로 변경   
데이터를 뿌려주는 경우 data 쪽 함수는 모두 viewmodel에서 call 하여 적용   
UI로부터 데이터를 가져오는 경우 Observer 연결하여 LiveData.value?. 형태   

```
  ( ui logic )      ( ui state )  ( data logic )  ( data save/get/delete)
Activity/Fragment --- ViewModel --- Repository ----- Firebase.kt
                                |__ NaverLogin ----- NaverLoginSDK (object)
                                |__ JusoService
                                |__ NodeServer  ---- Retrofit.kt
```

### 보충할 부분
1. Model.kt   
  1) can't deserialize error -> initailize   
  2) UserTeam data class   
2. Repository.kt   
  1) data null chk   
  2) Repository method 반환형 지정   
3. layout   

------------------------------------------------

박진우 12월 2일

추가된 부분

Act04List
Act05Create

act4_list
act5_create

Constants.kt
EachGroup.kt

dimens.xml
filters.xml

------------------------------------------------

whats' new 12-02 13:45

Act02Search:Activity - 검색과 생성 통합
- (미구현):
- - 시간 선택기능(임시로 버튼 누르면 현재 시간 획득하게 변경함)
- - 생성 이후 상세로 넘어가는 동작 미구현
- (구현된것):
- - 검색기능 & 조회기능
- (개인목표):
- - 검색 알고리즘 강화

Act06Article:Activity - 상세화면과 채팅 통합
- (구현된것: 상세화면에서 채팅참가 버튼으로 채팅으로 넘어감 / 이 외 전부 미구현)
- (구현된것: 채팅기능은 구현 완료)
