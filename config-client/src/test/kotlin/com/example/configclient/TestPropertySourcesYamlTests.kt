package com.example.configclient

import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = ["classpath:application-integrationtest.yaml"])
class TestPropertySourcesYamlTests {
	@Autowired
	private lateinit var environment: Environment

	@Value("\${my.custom.property}")
	private lateinit var customProperty: String

	@Test
	fun contextLoads() {
		assert(environment.activeProfiles.contains("test"))
	}

	@Test
	fun customProperty() {
		val customEnvProperty = environment.getProperty("my.custom.property")
		println("Custom Property ==> $customEnvProperty")
		println("Custom Property ==> $customProperty")
		Assertions.assertEquals("@TestPropertySources - src/test/resources/application-integrationtest.yaml", customEnvProperty)
		Assertions.assertEquals("@TestPropertySources - src/test/resources/application-integrationtest.yaml", customProperty)
	}
}
