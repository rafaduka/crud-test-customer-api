package br.com.foz.customer.user.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 32)
    val shortcut: String? = null,

    @Column(length = 255, nullable = false)
    val nome: String,

    @Column(nullable = false)
    val birthday: LocalDateTime,

    @ElementCollection
    @CollectionTable(name = "user_stack")
    val stack: List<String> = mutableListOf()
)
