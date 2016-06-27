# 패스트캠퍼스 자바 웹 프로그래밍 CAMP
자바지기(박재성)님의 [패스트캠퍼스 자바 웹 프로그래밍 CAMP](http://www.fastcampus.co.kr/dev_camp_jwp/) 강의 내용 관련 프로젝트

## 1주차
* 일반적인 main()을 이용한 테스트 방식의 문제점
* 이클립스에서 Junit 사용법 (난 중간중간 인텔리제이로 검색해서 적용함)
* @Before 사용하는 이유 : 각 테스트들간의 고유한 객체를 생성해주기 위해 
* 선 테스트코드 작성후 실제 코드 개발
* 구현 -> 테스트 -> 리펙토링 과정으로 진행
* 테스트 메소드는 production 코드에서 exception을 던질수 있기 때문에 웬만하면 throws Exception을 추가하는게 좋다
* private 메소드의 테스트는 private를 지우고 테스트 or 해당 메소드를 class로 뽑아서(Refactor 사용) 진행도 가능
* Refactor 사용을 활성화 하자! (Method, Class 등)
