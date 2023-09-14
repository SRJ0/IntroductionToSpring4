package sample;

public class MemberRegisterService {
    private MemberDao memberDao;

    public MemberRegisterService(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void regist(RegisterRequest req) {
        Member member = memberDao.selectByLoginId(req.getLoginId());
        if (member != null) {
            throw new AlreadyExistingMemberException("dup loginid" + req.getLoginId());
        }
        Member newMember = new Member(
            req.getLoginId(), req.getPassword()
        );
        memberDao.insert(newMember);
    }
}
