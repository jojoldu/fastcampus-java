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

## 4일차 - HTTP 웹서버 코드리뷰
* Github를 통해 관리해야하는 코드는?
 - build를 통해 생성되는 파일 (.settings, target 등)
 - 의존성 관리 툴(maven, gradle, npm, bower)을 통해 다운받는 라이브러리
 - IDE를 통해 생성되는 파일 (.project, .iml, .springbeans 등)
 - 모르겠으면 [gitignore.io](https://www.gitignore.io/)에서 java로 검색해서 제외해야할것 찾아서 .gitignore에 추가하자
 - Gradle 과 Maven 둘다 하기보다는 1개라도 잘하자
 
* 객체지향의 다형성에 익숙해지려면 어떻게 해야하나?
 - DB연동이 없고, UI를 고려하지 않는 프로젝트를 진행할것 (웹은 좋은 예제는 아니다)
 - ex) HTTP웹서버, 프레임워크 or 라이브러리, 체스게임, 지뢰찾기, 볼링게임점수판 등
 - [객체지향 생활 체조 총정리](https://developerfarm.wordpress.com/2012/02/03/object_calisthenics_summary/)의 내용을 참고
 - 하나의 프로젝트(주제)를 주기적으로 다시 만들어보자 여러 프로젝트를 하지 말자. <br/>
(예를 들어 전사케릭을 50까지 키웠는데 재미없어서, 마법사케릭을 다시 키워서 50까지 키우니 재미없어서 궁수케릭을 다시 키우는 식이 될수있다. 하나의 케릭을 쭈욱 키우는게 더 높은 곳까지 갈 수 있다.)

* HTTP 웹서버 코드리뷰 진행
 - 기존에 짠 코드를 테스트하는게 아니다 -> 테스트를 위해 기존 코드를 고치는 것이다. 즉, 테스트를 원하는 코드를 계속 method로 빼고, 세분화하자
 - 테스트하기 쉬운 코드 혹은 method는 input이 있고 return이 있어야 한다. (스칼라가 지향하는 방향과 동일하네?)
 - private method의 경우 method보다는 class가 오히려 더 잘어울수 있다. (힌트가 될수 있다)
 - logger같은 경우 IDE에 템플릿 등록해서 사용하면 반복작업을 최소화할수 있다.
 - 상태값을 가지는 객체의 경우 쓰레드마다 매번 객체 생성 해야하지만, 상태값이 없는 객체의 경우 재사용해야 한다.
 - 서블릿컨테이너 : 서블릿을 실행시켜주는 역할
 - 서블릿 컨테이너에서 서블릿 인스턴스는 1개만 생성되고 이를 재사용하는 방식이다.
 - 서블릿 컨테이너는 모든 사용자 요청에 대해 Thread를 생성해야하나? 1000명이 요청하면 1000개의 Thread가 필요한가? <br/>
 : 서블릿 컨테이너는 서버가 생성할 수 있는 Thread수를 제한한다. Thread Pool을 사용해 제한된 **Thread를 재사용**한다.<br/>
 Thread수를 초과한 사용자들은 큐에서 대기하여 처리될때마다 Thread를 할당한다. <br/>
 - Tomcat 설정에 maxThreads는 최대 Threads수, acceptCount는 큐의 최대 대기자수
  
* Servlet과 JSP 시작 - 1
 - @WebServlet과 extends HttpServlet 되어 있는 class를 등록한다. 

                                                                                                                                    