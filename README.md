### jwp-basic self check     
----

1.로컬 개발 환경에 Tomcat 서버를 시작하면 Servlet Container의 초기화 과정이 진행된다. 현 재 소스 코드에서 초기화되는 과정에 대해 설명해라

  * tomcat 서버를 시작하면 ContextLoaderListener jwp.sql을 ConnectionManager클래스에 있는 DB정보를 활용해 실행해서 DB내용을 초기화한다. 
  * DispatcherServlet 클래스에서 init()메소드를 통해 서블릿이 초기화된다. 이때 Request Mapping의 initMapping메소드가 실행되어 요청url에 따라 적합한 controller의 인스턴스를 매핑해둔다. 
  * DispatcherServlet service()메소드는 어떤 url이로든지 사용자의 요청이 올때 실행되어서 url에 해당하는 controller가 실행되고 적절한 view가 매칭되어 rendering한다.

**질문**
컨테이너는 다수의 서블릿을 만들지 않는다는 내용이 책에 있습니다.이 코드에서 dispatcherServlet이 여러 인스턴스로 생성되지 않는 것 같은데,request Mapping 클래스에서 매핑한 각각의 컨트롤러 인스턴스도 한 번만 생성되고 다수의 스레스로 동작하나요?  
- 우리가 만든 controller도 1개의 인스턴스만 만들고, 그걸 계속 사용한다. 
------------------------
loadOnStartup이 없으면, init()메소드는 사용자의 최초로 발생할 때 실행된다. WebServlet 어노테이션과 HttpServlet를 상속한 것을 보고 tomcat이 가장 먼저 실행해야 할 dispatcherServlet으로 알아본다. 

-------
**HeadFirst JSP&Servlet으로 최종학습 후 정리**

* 동적인 웹 애플리케이션을 지원하는 서버를 웹 애플리케이션 서버(Web Application Server)라고 한다. 줄여서 와스(WAS) 라고 부름. Tomcat는 WAS임. 
* WAS가 Servlet Container보다 큰 개념. ( Python Server, RoR 등도 WAS임!)

* Servlet Container (WAS의 일부 기능으로 Servlet Contatiner가 있는 것)
Servlet instance를 생성, 담고 있고 관리하는 것. -> tomcat 

### 로컬 개발 환경에 Tomcat 서버를 시작하면 Servlet Container의 초기화 과정이 진행된다. 현재 소스 코드에서 초기화되는 과정에 대해 설명해라(WebServerLauncher의 시작 과정이 아니라 clone한 프로젝트의 초기화 과정이다.)
* 컨테이너가 Web.xml을 읽어요. (그런데 web.xml이 없으면, ?? )
* 컨테이너가 servletcontext를 생성
* 컨테이너가 컨텍스트 초기화 파라미터에서 정해준 대로 이름-값 쌍을 만들고, ServletContext에 넣어둠. 
* 컨테이너는 contextListener 클래스 인스턴스를 만듦
* contextListner에서 contentInitialized() 메소드를 호출함. 이 메소드에서 sql을 실행해 DB를 생성
* dispatcherServlet을 생성하고, init()메소드를 실행합니다. 그러면 requestionMapping() 메소드가 실행되고,
다른 컨트롤러들도 초기화 되고 인스턴스가 하나씩 생기고, 맞는 url에 매핑됩니다. 
어떻게 컨테이너가 dispatcherServlet클래스를 제일 먼저 찾아갔을까? 그건, 여기에 @WebServlet 이라는 어노테이션이 매핑되어있기 때문에! 그리고 HttpServlet을 상속받고 있기 때문이야!
결국, dispatcher Servlet은 HeadFirst에서 설명하는 서블릿에 해당하고, dispatcherservlet과 requeset mapping 의 모든 contorller는 컨테이너 실행초기에서 1번만 생성되서 1개의 인스턴스를 갖고, 여러 요청이 와도
다시 샐행되서 인스턴스가 만들어지는 일은 없다! 이후로는 service()메소드만 불려지고 여러개의  thread를 만들어서 요청에 응답한다.  
@WebServlet
Annotation used to declare a servlet.
This annotation is processed by the container at deployment time, and the corresponding servlet made available at the specified URL patterns.


2.http://localhost:8080으로 접근해서 질문 목록이 보이기까지 소스 코드의 호출 순서 및 흐름을 설명하라.

  * 사용자는 "/"url로 요청한 것이고, DispatcherServlet service()은 requestUri를 찾아낸다. requestUri에 맞는 contorller를 findController()메소드로 찾는다. 이때 찾은 Controller는 HomeController 클래스의 인스턴스이다. 

  * "/"는 HomeController()와 매핑되어있으므로 HomeController.execute()메소드가 실행된다. "index.jsp" view를 보여주면서, addObject가 실행되는데, 이때  jsp에 DB에 있는 Questions을 모두 찾아 List Question 에 담아 questions라는 attribute name으로 "index.jsp"와 함께 client에 전달될 수 있게 한다.최종적으로 modelandView객체를 return 한다.
 
  * DispatcherServlet의 service메소드는 modelandview객체를 받아서 해당 view가 render될수있는 view.render()메소드를 실행하고, model객체와 request, response를 넘겨서 view가 client에 보여질수있게 한다. 여기서는 questions가 model로 클라이언트에 전달되기 때문에 index.jsp 에서 "${questions}"이렇게 처리된 부분이 전달받은 questions에 따라 질문내용이 보일 수 있음.

--------
**HeadFirst JSP&Servlet으로 최종학습 후 정리**

#### 사용자가 서블릿에 대한 링크를 클릭하면?
- Servlet에 어떤 요청이 오면, 서블릿을 바로 호출하지 않고, 서블릿을 관리하는 컨테이너에게
이 요청을 넘깁니다. 
- 요청을 넘겨받은 컨테이너는 HTTP Request 와  HTTP Response 객체를 만들어서,  HTTP Request 와  HTTP Response을 parameter에 넣어서 dispatcher 서블릿(thread 1 )의  Service()메소드를 실행합니다.
- 서블릿은response를 보냅니다. 
- thread1 서블릿의 작업이 끝나면 종료하고, request와 response객체를 소멸시킵니다. 

7.next.web.qna package의 ShowController는 멀티 쓰레드 상황에서 문제가 발생할 가능성이 있는 코드이다. 멀티 쓰레드 상황에서 문제가 발생하지 않도록 수정한다. 멀티 쓰레드에서 문 제가 되는 이유를 README.md 파일에 작성한다.

  * ShowController의 여러 개의 쓰레드는 인스턴스 변수인 question과 answers를 공유한다. 두 개의 변수에 이미 어떤 값이 쓰여있는 경우, 다른 사용자의 데이터가 넘어갈 수 있다. 각 쓰레드는 지역변수는 갖고 있으므로, 지역변수로 바꿔주었다. 

8.답변이 추가되면서 ajax로 html이 추가되고, n개의 답변 부분의 jsp 변수를 변경하고 싶은데 방법을 모르겠다.front로 countOfComment값도 넘겼는데 어떻게 변경하지? 
  
````
<p class="qna-comment-count">
<strong>${question.countOfComment}</strong> 개의 의견</p>
````

9.삭제하기 위해서 이벤트가 발생했던 element를 ajax에서 가져오고 싶은데, 방법을 잘 모르겠다. 서버에서 삭제는 되고, ajax도 200으로 성공은 함. 

10. 12. 13은 미완  

* contextLoader??? 