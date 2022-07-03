package com.example.configclient.config

import com.example.configclient.model.ConfigProperty
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class AccessPropertyTest(
        @Autowired private val configProperty: ConfigProperty,
        @Autowired private val environment: Environment,
        @Value("\${my.custom.property}") private val customProperty: String
) {
    companion object {
        private val logger = LoggerFactory.getLogger(AccessPropertyTest::class.java)
    }

    @PostConstruct
    fun init() {
        logger.info("================================================================")
        logger.info("PRINTING CUSTOM PROPERTY 'my.custom.property'")
        logger.info("Using @Value ==> $customProperty")
        logger.info("Using Environment API ==> ${environment.getProperty("my.custom.property")}")
        logger.info("Using @ConfigurationProperties ==> ${configProperty.property}")
        logger.info("Using System.getProperties ==> ${System.getProperty("my.custom.property")}")
        logger.info("================================================================")
    }
}
