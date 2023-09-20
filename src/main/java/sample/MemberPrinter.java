package sample;

public class MemberPrinter {
    public void print(Member member) {
        System.out.printf("회원아이디:%s/ 비번:%s \n", member.getLoginId(), member.getPassword().substring(0, 2) + "**");
    }
}
