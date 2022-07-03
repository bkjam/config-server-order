package com.example.configclient

import com.example.configclient.model.ConfigProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(ConfigProperty::class)
class ConfigClientApplication

fun main(args: Array<String>) {
	runApplication<ConfigClientApplication>(*args)
}
