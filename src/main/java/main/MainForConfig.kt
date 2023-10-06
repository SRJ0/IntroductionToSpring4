package main

import config.KotlinConfig
import config.KotlinConfig1
import config.KotlinConfig2
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import sample.MemberInfoPrinter
import sample.MemberRegisterService
import sample.RegisterRequest

object MainForConfig {
    @JvmStatic
    // Java static 처럼 만들어준다. 메모리 상에 올라왔다 사라지고를 반복하는 다른 변수들과 달리 static 변수는 프로그램이 종료될 때까지 메모리에 계속 머물러 있기 때문에 정적 static 이다. 즉, 객체 생성 없이도 프로그램이 처음 로드될 때 메모리에 할당되며 주소가 변하지 않으므로 여러 객체들이 공유할 수 있다.
    // static 메서드는 유틸리티용으로 많이 사용된다. class.staticmethod() 같은 식으로 사용할 수 있다.
    // main 함수는 전체 프로그램을 구동하는 역할을 하므로 당연히 static이어야 한다

    fun main(args: Array<String>) {
//        val ctx: ApplicationContext = AnnotationConfigApplicationContext(KotlinConfig::class.java)
        // java에서는 classname.class 였던 것이 classname::class.java로 바뀌었다.
        // :: 는 리플렉션, 런타임에 특정 클래스 정보를 추출할 수 있는 기법
        // kotlin에서 .class는 KClass를 반환하기 때문에 .java로 java class를 넘겨준다.
//        val ctx: ApplicationContext = AnnotationConfigApplicationContext(KotlinConfig1::class.java)
        val ctx: ApplicationContext = AnnotationConfigApplicationContext(KotlinConfig::class.java)
        val regSvc = ctx.getBean("memberRegSvc", MemberRegisterService::class.java)
        val infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter::class.java)
        val regReq = RegisterRequest()
        regReq.loginId = "hong"
        regReq.password = "1234"
        regReq.confirmPassword = "1234"
        regSvc.regist(regReq)

        infoPrinter.printMemberInfo("hong")

    }
}
