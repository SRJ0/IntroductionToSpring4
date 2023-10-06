package config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import sample.MemberDao
import sample.MemberInfoPrinter
import sample.MemberPrinter

@Configuration
// 3. @Import로 설정 클래스 목록을 지정한다. 둘 이상이면 배열로
@Import(KotlinConfig::class)

open class KotlinConfig1 {

    @Autowired
    // 1. 다른 설정 클래스에서 생성한 빈 객체를 주입받아 사용
//    private lateinit var memberDao: MemberDao
    // 2. @Configuration 적용 클래스가 빈 객체로 생성되기 때문에 그 빈 객체를 자동주입받을 수도 있다.
    //이러면 빈 객체가 어느 설정 클래스에 정의되어 있는지 알 수 있다.
    private lateinit var kotlinConfig: KotlinConfig

    @Bean
    open fun printer(): MemberPrinter {
        return MemberPrinter()
    }

    @Bean
    open fun infoPrinter(): MemberInfoPrinter {
        val infoPrinter = MemberInfoPrinter()
//        infoPrinter.setMemberDao(memberDao)
        infoPrinter.setMemberDao(kotlinConfig.memberDao())
        infoPrinter.setPrinter(printer())
        return infoPrinter
    }
}
