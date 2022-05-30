package com.example.configclient

import com.example.configclient.model.ConfigProperty
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperty::class)
class ConfigClientApplication

//fun main(args: Array<String>) {
//	System.setProperty("my.custom.property", "Configured VIA Java System Properties")
//	runApplication<ConfigClientApplication>(*args)
//}

fun main(args: Array<String>) {
	val app = SpringApplication(ConfigClientApplication::class.java)
	val properties = Properties()
	properties.setProperty("my.custom.property", "CONFIGURED VIA DEFAULT PROPERTIES")
	app.setDefaultProperties(properties)
	app.run(*args)
}
