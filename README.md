# 패스트캠퍼스 자바 웹 프로그래밍 CAMP
자바지기(박재성)님의 [패스트캠퍼스 자바 웹 프로그래밍 CAMP](http://www.fastcampus.co.kr/dev_camp_jwp/) 강의 내용 관련 프로젝트

## 1일차 - TDD 시작
* 일반적인 main()을 이용한 테스트 방식의 문제점
* 이클립스에서 Junit 사용법 (난 중간중간 인텔리제이로 검색해서 적용함)
* @Before 사용하는 이유 : 각 테스트들간의 고유한 객체를 생성해주기 위해 
* 선 테스트코드 작성후 실제 코드 개발
* 구현 -> 테스트 -> 리펙토링 과정으로 진행
* 테스트 메소드는 production 코드에서 exception을 던질수 있기 때문에 웬만하면 throws Exception을 추가하는게 좋다
* private 메소드의 테스트는 private를 지우고 테스트 or 해당 메소드를 class로 뽑아서(Refactor 사용) 진행도 가능
* Refactor 사용을 활성화 하자! (Method, Class 등)

## 2일차 - HTTP 웹서버 실습
* 순수 Java로 웹 서버 구축 - [프로젝트 코드] (https://github.com/jojoldu/web-application-server)
* 문자열 덧셈 게시판 못다한 이야기 <br />
sum(toInts(split(text))) 을 람다식으로 표현 <br />
Arrays.stream(split(text)) <br />
.map(t -> Integer.parseInt(t)) <br />
.reduce(0, (number, sum) -> number + sum); <br />
* RequestHandler는 Thread를 상속 받는다
* connection은 대기하다가, 사용자가 접속하면 Thread 객체를 생성한다. (즉, 사용자 1명당 Thread 객체 1개가 할당된다)

## 3일차 - HTTP 웹서버 리팩토링 실습
* 좋은개발자란? <br/>
 - 20대~ 30대초반 : 기술적으로 뛰어난 개발자 <br/>
 - 30대 중,후반 : 협업과 소통이 잘되어 같이 일하고 싶은 개발자 <br/>
 - 현재 : 사람을 중심에 두면서 기술을 사용할줄 아는 개발자 <br/>
* Exception <br/>
 - exception은 컴파일 exception <br/>
 - 잘모르겠으면 throw new RuntimeException을 보낸다.<br/>
 - 통장에서 돈을 인출하려는데 잔액 부족할 경우 - checked exception <br/>
 - DB연결이 끊길 경우 - unchecked exception <br/>
 - 확실히 시스템 문제라면 unchecked exception, 그외에는 checked exception <br/>
 - appender를 이용하면 별도의 코드 추가없이 모니터링 서비스로 에러로그를 보낼수가 있다.<br/>
* 복잡해진 웹 서버의 코드를 리팩토링 하자 - [프로젝트 코드] (https://github.com/jojoldu/web-application-server/tree/was-step1-bad-version) <br/>
* 소켓이 생성되려면 서버의 IP/Port 와 클라이언트의 IP/Port가 필요하다<br/>
* TCP three-way handshake 
 - 3번에 걸쳐 서버와 클라이언트간 교환작업<br/>
 - DB와 웹서버의 경우 이 작업이 너무 크므로 웹서버와 DB간의 three-way handshake를 미리 완료하여 이를 connection pool에 담아서 이 작업을 최소화 한다.<br/>
 - 이게 가능한 이유는 클라이언트(즉, 여기선 DB)의 IP와 포트를 고정시킬수 있기 때문이다. 브라우저는 IP가 고정되어있지 않으므로 불가능하다.<br/>
* HTTP Status
 - 302를 만나면 브라우저는 location 헤더 값이 있겠구나고 생각하고, location에 있는 값으로 다시 서버에 요청한다. 모든 리다이렉트 API는 이러한 방식이다.<br/>
 - 200은 index.html을 body에 담아서 보내는거라 리다이렉트 되지 않는다.<br/>
* HTTP 상태 vs 무상태 
 - 상태 protocol: 서버가 클라이언트의 상태값을 계속 갖고 있는 것(connection을 계속 이어간다.) <br/>
 - 무상태 protocol: 서버가 클라이언트에 결과를 보내면 connection을 끊어버린다.<br/>
 - HTTP는 상태를 가질수 있는 방식은 쿠키밖에 없다. 세션도 쿠키기반이다. <br/>
 - 세션은 정보를 서버에 저장하고, 세션 ID를 쿠키로 전달한다.<br/>
* CSRF 토큰: 주요 요청 직전에 발급하는 토큰으로, 이와 맞지 않으면 요청을 무효화시킨다.
 
                                                                                                                                   