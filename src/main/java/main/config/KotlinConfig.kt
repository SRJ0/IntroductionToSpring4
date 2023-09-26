package main.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sample.MemberDao
import sample.MemberInfoPrinter
import sample.MemberPrinter
import sample.MemberRegisterService

@Configuration
open class KotlinConfig {

    @Bean // 메서드가 리턴한 객체를 빈 객체로 사용한다
    open fun memberDao(): MemberDao {
        return MemberDao()
    }

    // 는 XML의 <bean id="memberDao" class="spring.MemberDao"> 와 같다.
    // XML의 경우 스프링 컨테이너가 빈 객체를 생성하는 것과 달리
    // 코틀린 설정 코드에서는 직접 객체를 생성해야한다.

    @Bean
    open fun memberRegSvc(): MemberRegisterService {
        return MemberRegisterService(memberDao()) // 객체 주입도 메서드를 이용해서 직접 한다.
        // 는 XML의 <constructor-arg ref="memberDao" /> 와 같다.
    }

    @Bean
    open fun printer(): MemberPrinter {
        return MemberPrinter()
    }

    @Bean
    open fun infoPrinter(): MemberInfoPrinter {
        val infoPrinter = MemberInfoPrinter()
        infoPrinter.setMemberDao(memberDao())
        infoPrinter.setPrinter(printer())
        return infoPrinter
    }
}
