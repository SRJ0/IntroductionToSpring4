package assembler

import sample.ChangePasswordService
import sample.MemberDao
import sample.MemberRegisterService

class Assembler {
    val memberDao: MemberDao
    val memberRegisterService: MemberRegisterService
    val changePasswordService: ChangePasswordService

    init {
        memberDao = MemberDao()
        memberRegisterService = MemberRegisterService(memberDao)
        changePasswordService = ChangePasswordService(memberDao)
    }
}
