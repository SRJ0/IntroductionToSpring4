package main

import aop.Calculator
import org.springframework.context.support.GenericXmlApplicationContext

object MainXmlAspect {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx: GenericXmlApplicationContext = GenericXmlApplicationContext("classpath:aopPojo.xml")
        var impeCal: Calculator = ctx.getBean("impeCal", Calculator::class.java)
        var fiveFact: Long = impeCal.factorial(5)
        println("impeCal.factorial(5) = " + fiveFact)
    }

}