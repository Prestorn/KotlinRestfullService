package com.example.demo.controller

import com.example.demo.reporitory.User
import com.example.demo.reporitory.UserRepository
import com.example.demo.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(path = ["api/users"])
class UserController(private var userService: UserService) {


    @GetMapping
    fun greeting() : List<User> {
        return userService.findUsers()
    }

    @GetMapping(path = ["user"])
    fun showUserById(@RequestParam id : Long) : String {
        return userService.findUserById(id)
    }

    @PostMapping
    fun create(@RequestBody user : User) : User {
        return userService.create(user)
    }

    @DeleteMapping(path = ["{id}"])
    fun delete(@PathVariable(name = "id") id : Long) { // name = "id" нужно указывать, если имена различны
        userService.delete(id)
    }

    @PutMapping(path = ["{id}"])
    fun update(@PathVariable id : Long,
               @RequestParam(required = false) name : String?,
               @RequestParam(required = false) email : String?) {
        userService.update(id, name, email)
    }
}