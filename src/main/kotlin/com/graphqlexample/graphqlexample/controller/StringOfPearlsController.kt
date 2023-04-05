package com.graphqlexample.graphqlexample.controller

import com.graphqlexample.graphqlexample.model.NewPearl
import com.graphqlexample.graphqlexample.service.StringOfPearlsService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StringOfPearlsController(val stringOfPearlsService: StringOfPearlsService) {
    @GetMapping("/string-of-pearls")
    @QueryMapping
    fun getAll() = stringOfPearlsService.getAll()

    @GetMapping("/pearls")
    @QueryMapping
    fun getByDate(@Argument date: String) = stringOfPearlsService.getPearlsByDate(date)

    @PostMapping("/create-pearl")
    @MutationMapping
    fun createPearl(@Argument pearl: NewPearl) = stringOfPearlsService.createPearl(pearl)
}