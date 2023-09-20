package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import sample.Greeter;


public class Main {
    public static void main(String[] args) {
        //ApplicationContext 인터페이스를 구현한 클래스로 XML로부터 설정 정보를 가져와 빈 객체를 생성, 관리한다. ApplicationContext를 container라고도 부른다.
        GenericXmlApplicationContext gctx = new GenericXmlApplicationContext("classpath:applicationContext.xml"); //설정 정보를 담고 있는 applicationContext.xml 경로를 파라미터로 생성자에 전달한다.
        //유사한 클래스로 AnnotationConfigApplicationContext 와 GenericGroovyApplicationContext가 있다

        Greeter g = gctx.getBean("greeter", Greeter.class); //context가 생성한 빈 객체를 id와 타입으로 검색한다
        String msg = g.greet("spring");
        Greeter g2 = gctx.getBean("greeter", Greeter.class);
        System.out.println(g == g2); //는 트루 싱글톤이 디폴트, 하나의 빈에 하나의 객체를 생성한다
        Greeter g3 = gctx.getBean("greeter2", Greeter.class);

        //DI dependency injection 의존성 주입 : 한 클래스가 다른 클래스의 메서드를 실행할 때
        //실행되는 메서드가 변경되면 실행하는 클래스에 영향을 미치므로 실행하는 클래스는 실행되는 클래스에 의존한다.
        //1. 클래스 내부에서 직접 의존 객체를 생성
        //2. DI - 생성하는 대신 객체를 생성자로 전달받는다
        //3. service locator

        //DI는 의존 객체를 교체할 때 한 곳에서만 수정하면 되므로 편하다

    }
}
