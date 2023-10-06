package config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import sample.MemberDao
import sample.MemberInfoPrinter
import sample.MemberPrinter
import sample.MemberRegisterService

@Configuration
open class KotlinConfig {

    @Bean // 메서드가 리턴한 객체를 빈 객체로 사용한다. 태그당 하나의 객체를 생성하므로 싱글톤이다.
    open fun memberDao(): MemberDao {
        return MemberDao()
    }
    // 는 XML의 <bean id="memberDao" class="spring.MemberDao"> 와 같다.
    // XML의 경우 스프링 컨테이너가 빈 객체를 생성하는 것과 달리
    // 코틀린 설정 코드에서는 직접 객체를 생성해야한다.
    // 메서드에서 객체를 직접 생성하기 때문에 설정 코드를 활용한 자동 주입은 필드나 메서드에 대해서만 가능하고, 생성자에 자동 주입할 수 없다.

    @Bean
    open fun memberRegSvc(): MemberRegisterService {
        return MemberRegisterService(memberDao()) // 객체 주입도 메서드를 이용해서 직접 한다.
        // 는 XML의 <constructor-arg ref="memberDao" /> 와 같다.
    }


}

// 스프링은 CGLIB를 이용해 런타임에 KotlinConfig를 상속받은 새로운 클래스를 생성하는데 다음과 같은 식이다.
// 따라서 싱글톤을 유지할 수 있다.
// CGLIB가 클래스를 생성하기 위해서는 final이 아니어야 하고 기본 생성자를 제공해야 한다.
//class SpringExtConfig: KotlinConfig() {
//    private lateinit var memberDaoBean: MemberDao
//    @Bean
//    override fun memberDao(): MemberDao {
//        if (memberDaoBean == null) memberDaoBean = super.memberDao();
//        return memberDaoBean
//    }
//}