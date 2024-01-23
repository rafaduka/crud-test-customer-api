package br.com.foz.customer.user.service

import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.entity.User
import br.com.foz.customer.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class UserServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userService: UserServiceImpl

    @Test
    fun `getUserById should return UserDto when user exists`() {
        // Mocking data
        val userId = 1L
        val user = User(id = userId, nome = "John", birthday = LocalDateTime.now(), stack = listOf("Java"))
        val optionalUser: Optional<User> = Optional.of(user)

        // Mocking repository
        whenever(userRepository.findById(userId)).thenReturn(optionalUser)

        // Call the service method
        val result: UserDto? = userService.getUserById(userId)

        // Assertions
        assertEquals(userId, result?.id)
        assertEquals("John", result?.nome)
    }
}