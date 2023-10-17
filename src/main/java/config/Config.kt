package config

import aop.Calculator
import aop.RecuCalculator
import aspect.ExecTimeAspect2
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import sample.Client

@Configuration
@EnableAspectJAutoProxy
open class Config {

    @Bean(initMethod = "connect", destroyMethod = "close")
    open fun client(): Client {
        val client = Client()
        client.setHost("server")
        return client
    }

    @Bean
    open fun execTimeAspect(): ExecTimeAspect2 {
        return ExecTimeAspect2()
    }

    @Bean
    open fun recuCal(): Calculator {
        return RecuCalculator()
    }

}