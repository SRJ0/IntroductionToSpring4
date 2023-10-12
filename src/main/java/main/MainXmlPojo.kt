package main

import aop.Calculator
import org.springframework.context.support.GenericXmlApplicationContext

object MainXmlPojo {
    @JvmStatic
    fun main(args: Array<String>) {
        var ctx: GenericXmlApplicationContext = GenericXmlApplicationContext("classpath:aopPojo.xml")
        var impeCal: Calculator = ctx.getBean("impeCal", Calculator::class.java)
        var ff1: Long = impeCal.factorial(5) //
        println(ff1)

        var recuCal: Calculator = ctx.getBean("recuCal", Calculator::class.java)
        var ff2: Long = recuCal.factorial(5)
        println(ff2)


    }
}