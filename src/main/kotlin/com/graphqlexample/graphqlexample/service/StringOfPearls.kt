package com.graphqlexample.graphqlexample.service

import com.graphqlexample.graphqlexample.model.NewPearl
import com.graphqlexample.graphqlexample.model.Pearl
import com.graphqlexample.graphqlexample.model.StringOfPearls
import io.r2dbc.spi.ConnectionFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.time.LocalDate

@Service
class StringOfPearlsService(private val connectionFactory: ConnectionFactory) {
    fun getAll(): Flux<StringOfPearls> = connectionFactory
        .create()
        .toMono()
        .flatMapMany {
            it.createStatement("select creation_date, count(1) as total from string_of_pearls group by creation_date order by 1 DESC")
                .execute()
        }
            .flatMap {
                it.map { row, metadata ->
                    StringOfPearls(
                        date = row.get(0, String::class.java) ?: "",
                        total = row.get(1, String::class.java)?.let { it.toInt() } ?: 0
                    )
                }
            }

    fun getPearlsByDate(date: String): Flux<Pearl> = connectionFactory
        .create()
        .toMono()
        .flatMapMany {
            it.createStatement("select name, notes, turd from string_of_pearls where creation_date = $1")
                .bind("$1", LocalDate.parse(date))
                .execute()
        }
        .flatMap {
            it.map { row, metadata ->
                Pearl(
                    name = row.get(0, String::class.java) ?: "",
                    notes = row.get(1, String::class.java) ?: "",
                    turd = row.get(2, String::class.java) ?: ""
                )
            }
        }

    fun createPearl(newPearl: NewPearl): Mono<Pearl> = connectionFactory
        .create()
        .toMono()
        .flatMap {
            it.createStatement("insert into string_of_pearls values($1, $2, $3, $4)")
                .bind("$1", LocalDate.parse(newPearl.date))
                .bind("$2", newPearl.notes)
                .bind("$3", newPearl.turd)
                .bind("$4", newPearl.name)
                .returnGeneratedValues()
                .execute()
                .toMono()
        }
        .map {
            it.map { row, metadata ->
                Pearl(
                    name = row.get(0, String::class.java) ?: "",
                    turd = row.get(1, String::class.java) ?: "",
                    notes = row.get(2, String::class.java) ?: ""
                )
            }.toMono()
        }
        .flatMap { it }

}