package com.example.configclient

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles(profiles = ["dev", "test"])
class MultipleProfilesTests {
	@Autowired
	private lateinit var environment: Environment

	@Value("\${my.custom.property}")
	private lateinit var customProperty: String

	@Test
	fun contextLoads() {
		assert(environment.activeProfiles.contains("test"))
		assert(environment.activeProfiles.contains("dev"))
	}

	@Test
	fun customProperty() {
		val customEnvProperty = environment.getProperty("my.custom.property")
		println("Custom Property ==> $customEnvProperty")
		println("Custom Property ==> $customProperty")
		Assertions.assertEquals("Config Server - config-repo/config-client/config-client-test.yaml", customEnvProperty)
		Assertions.assertEquals("Config Server - config-repo/config-client/config-client-test.yaml", customProperty)
	}
}
