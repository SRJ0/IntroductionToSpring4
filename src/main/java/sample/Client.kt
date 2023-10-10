package sample

import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

class Client : InitializingBean, DisposableBean {
    private lateinit var host: String

    fun setHost(host1: String) {
        host = host1
        println("setHost() executed")
    }

    @Override
    override fun afterPropertiesSet() {
        println("afterPropertiesSet() executed")
    }

    fun connect() {
        println("connect()")
    }
    open fun send() {
        println("send to " + host)
    }
    @Override
    override fun destroy() {
        println("destory() executed")
    }

    fun close() {
        println("close()")
    }

}