package main

import aop.ExecTimeCalculator
import aop.ImpeCalculator
import aop.RecuCalculator

object MainProxy {
    @JvmStatic
    fun main(args: Array<String>) {
        val ec = ExecTimeCalculator(ImpeCalculator())
        println(ec.factorial(5000))
        val ec2 = ExecTimeCalculator(RecuCalculator())
        println(ec2.factorial(5000))
    }
}