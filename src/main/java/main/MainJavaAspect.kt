package main

import aop.Calculator
import config.Config
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext

object MainJavaAspect {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: AnnotationConfigApplicationContext = AnnotationConfigApplicationContext(Config::class.java)
        var recuCal: Calculator = ctx.getBean("recuCal", Calculator::class.java)
        var fiveFact: Long = recuCal.factorial(5)
        println("recuCal.factorial(5) = " + fiveFact)
    }

}