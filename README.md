### jwp-basic self check     
----

1. 로컬 개발 환경에 Tomcat 서버를 시작하면 Servlet Container의 초기화 과정이 진행된다. 현 재 소스 코드에서 초기화되는 과정에 대해 설명해라

  * tomcat 서버를 시작하면 ContextLoaderListener jwp.sql을 ConnectionManager클래스에 있는 DB정보를 활용해 실행해서 DB내용을 초기화한다. 
  * DispatcherServlet 클래스에서 init()메소드를 통해 서블릿이 초기화된다. 이때 Request Mapping의 initMapping메소드가 실행되어 요청url에 따라 적합한 controller의 인스턴스를 매핑해둔다. 
  * DispatcherServlet service()메소드는 어떤 url이로든지 사용자의 요청이 올때 실행되어서 url에 해당하는 controller가 실행되고 적절한 view가 매칭되어 rendering한다.

2.  http://localhost:8080으로 접근해서 질문 목록이 보이기까지 소스 코드의 호출 순서 및 흐름을 설명하라.

  * 사용자는 "/"url로 요청한 것이고, DispatcherServlet service()은 requestUri를 찾아낸다. requestUri에 맞는 contorller를 findController()메소드로 찾는다. 이때 찾은 Controller는 HomeController 클래스의 인스턴스이다. 

  * "/"는 HomeController()와 매핑되어있으므로 HomeController.execute()메소드가 실행된다. "index.jsp" view를 보여주면서, addObject가 실행되는데, 이때  jsp에 DB에 있는 Questions을 모두 찾아 List Question 에 담아 questions라는 attribute name으로 "index.jsp"와 함께 client에 전달될 수 있게 한다.최종적으로 modelandView객체를 return 한다.
 
  * DispatcherServlet의 service메소드는 modelandview객체를 받아서 해당 view가 render될수있는 view.render()메소드를 실행하고, model객체와 request, response를 넘겨서 view가 client에 보여질수있게 한다. 여기서는 questions가 model로 클라이언트에 전달되기 때문에 index.jsp 에서 "${questions}"이렇게 처리된 부분이 전달받은 questions에 따라 질문내용이 보일 수 있음. 