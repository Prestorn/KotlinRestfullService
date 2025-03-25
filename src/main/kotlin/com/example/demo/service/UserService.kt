package com.example.demo.service

import com.example.demo.reporitory.User
import com.example.demo.reporitory.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.Period
import java.util.Optional

@Service
class UserService(val userRepository: UserRepository) {

    fun findUsers() : List<User> {
        return userRepository.findAll()
    }

    fun findUserById(id : Long) : String {
        val optionalUser : Optional<User> = userRepository.findById(id)
        if (optionalUser.isEmpty) {
            return "Пользователя с ID: $id не существует"
        }
        return userRepository.findById(id).get().toString()
    }

    fun create(user: User) : User {
        val optionalUser : Optional<User> = userRepository.findByEmail(user.email)
        if (optionalUser.isPresent) {
            throw IllegalStateException("Пользователь с email: \"${user.email}\" уже существует")
        }
        user.age = Period.between(user.birthDate, LocalDate.now()).years
        return userRepository.save(user)
    }

    fun delete(id : Long) {
        val optionalUser : Optional<User> = userRepository.findById(id)
        if (optionalUser.isEmpty) {
            throw IllegalStateException("Пользователя с ID: $id не существует")
        }
        userRepository.deleteById(id)
    }

    @Transactional
    fun update(id: Long, name: String?, email: String?) {
        val optionalUser : Optional<User> = userRepository.findById(id)
        if (optionalUser.isEmpty) {
            throw IllegalStateException("Пользователя с ID: $id не существует")
        }
        val user : User = optionalUser.get()

        if (email != null && email != user.email) {
            val userFoundByEmail : Optional<User> = userRepository.findByEmail(email)
            if (userFoundByEmail.isPresent) {
                throw IllegalStateException("Пользователь с email: \"$email\" уже существует")
            }
            user.email = email
        }

        if (name != null && name != user.name) {
            user.name = name
        }

        //userRepository.save(user) // Использовать метод не нужно при использовании аннотации Transactional
    }
}