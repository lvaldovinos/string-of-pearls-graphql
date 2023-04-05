package com.graphqlexample.graphqlexample.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import io.r2dbc.spi.ConnectionFactoryOptions.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration

@Configuration
class DatabaseConfiguration : AbstractR2dbcConfiguration() {
    @Value("\${app.datasource.host}")
    lateinit var host: String

    @Value("\${app.datasource.port}")
    lateinit var port: String

    @Value("\${app.datasource.username}")
    lateinit var username: String

    @Value("\${app.datasource.password}")
    lateinit var password: String

    @Value("\${app.datasource.database}")
    lateinit var database: String

    @Override
    @Bean
    override fun connectionFactory(): ConnectionFactory {
        return ConnectionFactories.get(ConnectionFactoryOptions.builder()
            .option(DRIVER, "postgresql")
            .option(PORT, port.toInt())
            .option(HOST, host)
            .option(USER, username)
            .option(PASSWORD, password)
            .option(DATABASE, database)
            .option(SSL, false)
            .build()
        )
    }
}