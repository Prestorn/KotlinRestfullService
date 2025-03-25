package com.example.demo.reporitory

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0
    var name : String = ""
    var email : String = ""
    @Column(name = "birth_date")
    var birthDate : LocalDate = LocalDate.MIN
    var age : Int? = 0

    constructor() {}

    constructor(id: Long, name: String, email: String, birthDate: LocalDate, age: Int) {
        this.id = id
        this.name = name
        this.email = email
        this.birthDate = birthDate
        this.age = age
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', email='$email', birthDate=$birthDate, age=$age)"
    }
}