package sample

import org.springframework.beans.factory.annotation.Autowired

class MemberInfoPrinter {
    @Autowired
    private var memDao: MemberDao? = null
    private var printer: MemberPrinter? = null
    fun setMemberDao(memberDao: MemberDao?) {
        memDao = memberDao
    }

    @Autowired
    fun setPrinter(printer: MemberPrinter?) {
        this.printer = printer
    }

    fun printMemberInfo(id: String?) {
        val member = memDao!!.selectByLoginId(id)
        if (member == null) {
            println("nodata")
            return
        }
        printer!!.print(member)
        println()
    }
}
