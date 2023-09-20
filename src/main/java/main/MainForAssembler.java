package main;

import assembler.Assembler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import sample.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainForAssembler {

    private static ApplicationContext ctx = null;
    private static Assembler assembler = new Assembler();

    public static void main(String[] args) throws IOException {
//        String[] conf = {"classpath:conf1.xml", "classpath:conf2.xml"}; 여러 개의 설정 파일을 포함할 경우는 스트링 배열을 이용
        ctx = new GenericXmlApplicationContext("classpath:conf2.xml");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.print("$ ");
            String command = br.readLine();
            if (command.equalsIgnoreCase("exit")) { // 대소문자 무시
                System.out.println("종료");
                break;
            }
            if (command.startsWith("new")) {
                processNewCommand(command.split(" "));
                continue;
            } else if (command.startsWith("change")) {
                processChangeCommand(command.split(" "));
                continue;
            } else if (command.equals("list")) {
                processListCommand();
                continue;
            } else if (command.startsWith("info")) {
                processInfoCommand(command.split(" "));
                continue;
            } else if (command.equals("ver")) {
                processVersionCommand();
                continue;
            }
            printHelp();
        }

    }

    private static void processVersionCommand() {
        VersionPrinter versionPrinter = ctx.getBean("versionPrinter", VersionPrinter.class);
        versionPrinter.print();
    }

    private static void processInfoCommand(String[] arg) {
        if (arg.length != 2) {
            printHelp();
            return;
        }
        MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
        infoPrinter.printMemberInfo(arg[1]);
    }
    private static void processNewCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }

//        MemberRegisterService regSvc = assembler.getMemberRegisterService(); // 객체를 조립해주는 어셈블러 클래스를 만들어 의존 객체를 주입
        MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class); // 스프링 컨테이너 사용
        RegisterRequest req = new RegisterRequest();
        req.setLoginId(arg[1]);
        req.setPassword(arg[2]);
        req.setConfirmPassword(arg[3]);

        if (!req.isPasswordEqualToConfirmPassword()) {
            System.out.println("암호 불일치");
            return;
        }
        try {
            regSvc.regist(req);
            System.out.println("가입 완료");
        } catch (AlreadyExistingMemberException e) {
            System.out.println("중복 아이디");
        }
    }

    private static void processChangeCommand(String[] arg) {
        if (arg.length != 4) {
            printHelp();
            return;
        }
//        ChangePasswordService pwdSvc = assembler.getChangePasswordService();
        ChangePasswordService pwdSvc = ctx.getBean("changePwdSvc", ChangePasswordService.class);
        try {
            pwdSvc.changePassword(arg[1], arg[2], arg[3]);
            System.out.println("비번 변경 완료");
        } catch (MemberNotFoundException | IdPasswordNotMatchingException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processListCommand() {
        MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
        listPrinter.printAll();
    }
    private static void printHelp() {
        System.out.println("잘못된 명령입니다. 검색을 하세요.");
    }

}
