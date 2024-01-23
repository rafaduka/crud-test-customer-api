package br.com.foz.customer.user.dto

import br.com.foz.customer.user.dto.validation.ValidBirthday
import br.com.foz.customer.user.dto.validation.ValidStack
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate
import java.time.LocalDateTime

data class UserDto(
    val id: Long?,

    @field:Size(max = 32)
    val shortcut: String?,

    @field:NotBlank
    @field:Size(max = 255)
    val nome: String,

    @field:ValidBirthday
    val birthday: LocalDateTime,

    @field:ValidStack
    val stack: List<String>
)
