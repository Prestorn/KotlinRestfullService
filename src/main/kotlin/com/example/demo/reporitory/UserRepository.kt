package com.example.demo.reporitory

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface UserRepository : JpaRepository<User, Long> {

//    @Query(value = "select * from users where email = :email", nativeQuery = true)
//    fun findByEmail(email : String) : Optional<User>

//    @Query(value = "select u from User u where u.email = :email")
//    fun findByEmail(email : String) : Optional<User>

    // Ключевая конструкция findBy... говорит Spring о том, что это запрос к БД (спецификация JPA)
    fun findByEmail(email : String) : Optional<User>
}