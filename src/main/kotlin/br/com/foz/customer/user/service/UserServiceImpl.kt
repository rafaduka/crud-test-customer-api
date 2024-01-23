package br.com.foz.customer.user.service


import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.entity.User
import br.com.foz.customer.user.entity.extension.toDto
import br.com.foz.customer.user.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import kotlin.NoSuchElementException

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun getUserById(id: Long): UserDto? {
        val user= userRepository.findById(id).orElseThrow { NoSuchElementException("User not found with id: $id") }
        return user.toDto()
    }

    override fun getUsers(page: Int, size: Int): List<UserDto> {
        val pageable = PageRequest.of(page, size)
        val users = userRepository.findAll(pageable).content
        return users.map { it.toDto() }
    }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    override fun createUser(userDto: UserDto): UserDto {
        val user = User(
            shortcut = userDto.shortcut,
            nome = userDto.nome,
            birthday = userDto.birthday,
            stack = userDto.stack
        )

        val savedUser = userRepository.save(user)
        return savedUser.toDto()
    }

    override fun updateUser(id: Long, userDto: UserDto): UserDto {
        val updatedUser = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User not found with id: $id") }
            .copy(
                shortcut = userDto.shortcut,
                nome = userDto.nome,
                birthday = userDto.birthday,
                stack = userDto.stack
            )
        return userRepository.save(updatedUser).toDto()
    }
}
