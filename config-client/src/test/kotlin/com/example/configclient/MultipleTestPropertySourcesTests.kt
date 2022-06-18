package com.example.configclient

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@ActiveProfiles(profiles = ["dev", "test"])
@TestPropertySource(locations = ["classpath:application-integrationtest.properties", "classpath:application-integrationtest-v2.properties"])
class MultipleTestPropertySourcesTests {
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
		Assertions.assertEquals("@TestPropertySources - src/test/resources/application-integrationtest-v2.properties", customEnvProperty)
		Assertions.assertEquals("@TestPropertySources - src/test/resources/application-integrationtest-v2.properties", customProperty)
	}
}
