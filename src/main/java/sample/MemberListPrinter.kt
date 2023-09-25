package sample

class MemberListPrinter(private val memberDao: MemberDao, private val printer: MemberPrinter) {
    fun printAll() {
        val members = memberDao.selectAll()
        for (m in members!!) {
            printer.print(m)
        }
    }
}
