package main

import config.MainConf
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import sample.MemberInfoPrinter
import sample.MemberRegisterService
import sample.RegisterRequest

object MainForConfigAndXml {
    @JvmStatic
    fun main(args: Array<String>) {
//        val ctx: ApplicationContext = AnnotationConfigApplicationContext(MainConf::class.java)
        val ctx: ApplicationContext = GenericXmlApplicationContext("classpath:main-conf.xml")

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