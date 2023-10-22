package main

import aop.Calculator
import aop.ImpeCalculator
import org.springframework.context.support.GenericXmlApplicationContext

object MainXmlPojo {
    @JvmStatic
    fun main(args: Array<String>) {
        var ctx: GenericXmlApplicationContext = GenericXmlApplicationContext("classpath:aopPojo.xml")
        var impeCal: Calculator = ctx.getBean("impeCal", Calculator::class.java)
        // var impeCal: ImpeCalculator = ctx.getBean("impeCal", ImpeCalculator::class.java)
        // Bean named 'impeCal' is expected to be of type 'aop.ImpeCalculator' but was actually of type 'jdk.proxy2.$Proxy12' 에러 발생
        // $Proxy12 클래스는 Calculator 인터페이스를 상속한다
        // 스프링은 AOP를 위한 프록시 객체를 생성할 때 실제 생성할 빈 객체가 인터페이스를 상속하면
        // 상속받은 인터페이스를 이용해 프록시를 생성한다.
        // 클래스를 이용해 프록시를 생성하고 싶다면
        // 1. POJO XML
        // <aop:config proxy-target-class="true">
        // 2. @Aspect XML
        // <aop:aspectj-autoproxy proxy-target-class="true">
        // 3. @Aspect JAVA
        // @EnableAspectJAutoProxy(proxyTargetClass = true)


        var ff1: Long = impeCal.factorial(5) //
        println(ff1)

        var recuCal: Calculator = ctx.getBean("recuCal", Calculator::class.java)
        var ff2: Long = recuCal.factorial(5)
        println(ff2)


    }
}