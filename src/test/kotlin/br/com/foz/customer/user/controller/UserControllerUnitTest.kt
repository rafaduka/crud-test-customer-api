package br.com.foz.customer.user.controller

import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.service.UserService
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
class UserControllerTest {

    @Mock
    private lateinit var userService: UserService

    @InjectMocks
    private lateinit var userController: UserController

    @Test
    fun testGetUserById() {
        val userId = 1L
        val userDto = UserDto(id = userId, nome = "Horácio", birthday = LocalDateTime.parse("1990-01-01"), stack = listOf("Java"), shortcut = "test")

        // Mocking userService.getUserById
        Mockito.`when`(userService.getUserById(userId)).thenReturn(userDto)

        // Calling the controller method
        val responseEntity: ResponseEntity<UserDto?> = userController.getUserById(userId)

        // Assertions
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(userDto, responseEntity.body)
    }

    @Test
    fun testGetUserByIdNotFound() {
        val userId = 1L

        // Mocking userService.getUserById
        Mockito.`when`(userService.getUserById(userId)).thenReturn(null)

        // Calling the controller method
        val responseEntity: ResponseEntity<UserDto?> = userController.getUserById(userId)

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.statusCode)
    }

    @Test
    fun testCreateUser() {
        val userDto = UserDto(id = null, nome = "John Doe", birthday = LocalDateTime.parse("1990-01-01")
            , stack = listOf("Java"), shortcut = "shortcut")

        // Mocking userService.createUser
        Mockito.`when`(userService.createUser(userDto)).thenReturn(userDto)

        // Calling the controller method
        val responseEntity: ResponseEntity<UserDto> = userController.createUser(userDto)

        // Assertions
        assertEquals(HttpStatus.CREATED, responseEntity.statusCode)
        assertEquals(userDto, responseEntity.body)
    }

    @Test
    fun testCreateUserValidationFailure() {
        val userDto = UserDto(id = null, nome = "", birthday = LocalDateTime.parse("1990-01-01"), stack = listOf("Java"), shortcut = "short")

        // Mocking userService.createUser to throw ConstraintViolationException
        Mockito.`when`(userService.createUser(userDto)).thenThrow(ConstraintViolationException(Validation.buildDefaultValidatorFactory().validator.validate(userDto)))

        // Ensure that ConstraintViolationException is thrown
        assertThrows<ConstraintViolationException> {
            // This line will throw ConstraintViolationException if validation fails
            userController.createUser(userDto)
        }
    }

}
