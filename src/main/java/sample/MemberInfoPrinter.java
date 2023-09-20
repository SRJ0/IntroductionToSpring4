package sample;

public class MemberInfoPrinter {
    private MemberDao memDao;
    private MemberPrinter printer;

    public void setMemberDao(MemberDao memberDao) {
        this.memDao = memberDao;
    }

    public void setPrinter(MemberPrinter printer) {
        this.printer = printer;
    }

    public void printMemberInfo(String id) {
        Member member = memDao.selectByLoginId(id);
        if (member == null) {
            System.out.println("nodata");
            return;
        }
        printer.print(member);
        System.out.println();
    }
}
