package br.com.foz.customer.user.entity.extension

import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.entity.User

fun User.toDto(): UserDto {
    return UserDto(
        id = this.id,
        shortcut = this.shortcut,
        nome = this.nome,
        birthday = this.birthday,
        stack = this.stack
    )
}