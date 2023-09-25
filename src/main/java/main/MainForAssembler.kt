package main

import assembler.Assembler
import org.springframework.context.ApplicationContext
import org.springframework.context.support.GenericXmlApplicationContext
import sample.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object MainForAssembler {
    private var ctx: ApplicationContext? = null
    private val assembler = Assembler()
    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
//        String[] conf = {"classpath:conf1.xml", "classpath:conf2.xml"}; 여러 개의 설정 파일을 포함할 경우는 스트링 배열을 이용
        ctx = GenericXmlApplicationContext("classpath:conf2.xml")
        val br = BufferedReader(InputStreamReader(System.`in`))
        while (true) {
            print("$ ")
            val command = br.readLine()
            if (command.equals("exit", ignoreCase = true)) { // 대소문자 무시
                println("종료")
                break
            }
            if (command.startsWith("new")) {
                processNewCommand(command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                continue
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                continue
            } else if (command == "list") {
                processListCommand()
                continue
            } else if (command.startsWith("info")) {
                processInfoCommand(command.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                continue
            } else if (command == "ver") {
                processVersionCommand()
                continue
            }
            printHelp()
        }
    }

    private fun processVersionCommand() {
        val versionPrinter = ctx!!.getBean("versionPrinter", VersionPrinter::class.java)
        versionPrinter.print()
    }

    private fun processInfoCommand(arg: Array<String>) {
        if (arg.size != 2) {
            printHelp()
            return
        }
        val infoPrinter = ctx!!.getBean("infoPrinter", MemberInfoPrinter::class.java)
        infoPrinter.printMemberInfo(arg[1])
    }

    private fun processNewCommand(arg: Array<String>) {
        if (arg.size != 4) {
            printHelp()
            return
        }

//        MemberRegisterService regSvc = assembler.getMemberRegisterService(); // 객체를 조립해주는 어셈블러 클래스를 만들어 의존 객체를 주입
        val regSvc = ctx!!.getBean("memberRegSvc", MemberRegisterService::class.java) // 스프링 컨테이너 사용
        val req = RegisterRequest()
        req.loginId = arg[1]
        req.password = arg[2]
        req.confirmPassword = arg[3]
        if (!req.isPasswordEqualToConfirmPassword) {
            println("암호 불일치")
            return
        }
        try {
            regSvc.regist(req)
            println("가입 완료")
        } catch (e: AlreadyExistingMemberException) {
            println("중복 아이디")
        }
    }

    private fun processChangeCommand(arg: Array<String>) {
        if (arg.size != 4) {
            printHelp()
            return
        }
        //        ChangePasswordService pwdSvc = assembler.getChangePasswordService();
        val pwdSvc = ctx!!.getBean("changePwdSvc", ChangePasswordService::class.java)
        try {
            pwdSvc.changePassword(arg[1], arg[2], arg[3])
            println("비번 변경 완료")
        } catch (e: MemberNotFoundException) {
            println(e.message)
        } catch (e: IdPasswordNotMatchingException) {
            println(e.message)
        }
    }

    private fun processListCommand() {
        val listPrinter = ctx!!.getBean("listPrinter", MemberListPrinter::class.java)
        listPrinter.printAll()
    }

    private fun printHelp() {
        println("잘못된 명령입니다. 검색을 하세요.")
    }
}
