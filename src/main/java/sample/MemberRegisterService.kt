package sample

class MemberRegisterService(private val memberDao: MemberDao) {
    fun regist(req: RegisterRequest) {
        val member = memberDao.selectByLoginId(req.loginId)
        if (member != null) {
            throw AlreadyExistingMemberException("dup loginid" + req.loginId)
        }
        val newMember = Member(
            req.loginId, req.password
        )
        memberDao.insert(newMember)
    }
}

// IN JAVA
//public class MemberRegisterService {
//    private MemberDao memberDao;
//
//    public MemberRegisterService(MemberDao memberDao) {
//        this.memberDao = memberDao;
//    }
//
//    public void regist(RegisterRequest req) {
//        Member member = memberDao.selectByLoginId(req.getLoginId());
//        if (member != null) {
//            throw new AlreadyExistingMemberException("dup loginid" + req.getLoginId());
//        }
//        Member newMember = new Member(
//            req.getLoginId(), req.getPassword()
//        );
//        memberDao.insert(newMember);
//    }
//}
