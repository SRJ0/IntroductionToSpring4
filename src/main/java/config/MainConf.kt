package config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import sample.MemberDao
import sample.MemberPrinter
import sample.MemberRegisterService

@Configuration
// XML 임포트하기
@ImportResource("classpath:sub-conf.xml")
open class MainConf {

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
