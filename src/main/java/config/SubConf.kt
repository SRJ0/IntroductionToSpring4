package config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sample.MemberDao
import sample.MemberPrinter
import sample.MemberRegisterService

@Configuration
open class SubConf {

    @Autowired
    private lateinit var memberDao: MemberDao

    @Bean
    open fun memberPrinter(): MemberPrinter {
        return MemberPrinter()
    }

    @Bean
    open fun memberRegSvc(): MemberRegisterService {
        return MemberRegisterService(memberDao)
    }
}