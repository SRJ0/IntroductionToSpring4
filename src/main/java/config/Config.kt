package config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import sample.Client

@Configuration
open class Config {

    @Bean(initMethod = "connect", destroyMethod = "close")
    open fun client(): Client {
        val client = Client()
        client.setHost("server")
        return client
    }

}