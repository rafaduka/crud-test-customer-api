package br.com.foz.customer.user.service

import br.com.foz.customer.user.dto.UserDto


interface UserService {
    fun getUserById(id: Long): UserDto?
    fun getUsers(page: Int, size: Int): List<UserDto>
    fun deleteUser(id: Long)
    fun createUser(userDto: UserDto): UserDto
    fun updateUser(id: Long, userDto: UserDto): UserDto
}
