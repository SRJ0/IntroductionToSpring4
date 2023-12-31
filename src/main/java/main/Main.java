package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import sample.Client;
import sample.Greeter;


public class Main {
    public static void main(String[] args) {
        //1. 컨테이너 초기화
        //ApplicationContext 인터페이스를 구현한 클래스로, XML로부터 설정 정보를 가져와 빈 객체를 생성, 연결(의존 주입), 관리한다.
        //ApplicationContext를 container라고 부른다.
        //설정 정보를 담고 있는 conf.xml의 경로를 파라미터로 생성자에 전달한다.
        GenericXmlApplicationContext gctx = new GenericXmlApplicationContext("classpath:conf.xml");
        //유사한 클래스로 AnnotationConfigApplicationContext 와 GenericGroovyApplicationContext가 있다

        //2. 컨테이너 사용
        Client g = gctx.getBean("client", Client.class); //context가 생성한 빈 객체를 id와 타입으로 검색한다
        g.send();
//      싱글톤이 디폴트이므로 하나의 빈에 하나의 객체를 생성하고 따라서 g == g2이다
//      단, 빈의 scope를 prototype으로 지정하면 매번 새로운 객체가 생성되어 g != g2이다.
        Client g2 = gctx.getBean("client", Client.class);
        System.out.println("scope : " + (g == g2 ? "singleton" : "prototype"));

        //DI dependency injection 의존성 주입 : 한 클래스가 다른 클래스의 메서드를 실행할 때
        //실행되는 메서드가 변경되면 실행하는 클래스에 영향을 미치므로 실행하는 클래스는 실행되는 클래스에 의존한다.
        //1. 클래스 내부에서 직접 의존 객체를 생성
        //2. DI - 생성하는 대신 객체를 생성자로 전달받는다
        //3. service locator

        //DI는 의존 객체를 교체할 때 한 곳에서만 수정하면 되므로 편하다

        //3. 컨테이너 종료
        gctx.close();

        //빈 객체 라이프사이클 : 객체 생성 - 의존 설정 - 초기화 - 소멸
        //빈 객체가 InitializingBean 인터페이스를 구현한 경우 컨테이너는 초기화시 afterPropertiesSet() 메서드를 실행한다.
        //빈 객체가 DisposableBean 인터페이스를 구현한 경우 컨테이너는 소멸 과정에서 destroy() 메서드를 실행한다.
        //ex db connection pool 객체는 초기화시 db와 연결을 생성하고 소멸할 때 연결을 끊어주어야 한다.


    }
}
