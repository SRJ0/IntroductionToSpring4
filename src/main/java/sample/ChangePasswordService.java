package sample;

public class ChangePasswordService {

    private MemberDao memberDao;

    public ChangePasswordService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void changePassword(String loginId, String oldPW, String newPW) {
        Member member = memberDao.selectByLoginId(loginId);
        if (member == null)
            throw new MemberNotFoundException("NOT exist " + loginId);

        member.changePassword(oldPW, newPW);
        memberDao.update(member);
    }
}
