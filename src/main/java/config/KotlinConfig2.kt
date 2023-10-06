package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sample.*

@Configuration
open class KotlinConfig2 {
    @Bean
    open fun memberDao(): MemberDao {
        return MemberDao()
    }

    @Bean
    open fun memberRegSvc(): MemberRegisterService {
        return MemberRegisterService(memberDao())
    }

    @Bean
    open fun printer(): MemberPrinter {
        return MemberPrinter()
    }

    @Bean
    open fun infoPrinter(): MemberInfoPrinter {
        val infoPrinter = MemberInfoPrinter()
        return infoPrinter
    }
}