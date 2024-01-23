package br.com.foz.customer.user.controller

import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.service.UserService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDateTime

@ExtendWith(MockitoExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class UserControllerIntegrationTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Mock
    private lateinit var userService: UserService

    @InjectMocks
    private lateinit var userController: UserController

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @Order(2)
    fun `Test fetching user by ID should return UserDto when the user exists`() {
        // Given
        val userId = 1L
        val userDto = UserDto(id = userId, nome = "Rafael Horácio", birthday = LocalDateTime.parse("1990-01-01T22:22:22"), stack = listOf("Java"), shortcut = "rafaduka")

        // When
        mockMvc.perform(get("/users/{id}", userId))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(userId.toInt()))
            .andExpect(jsonPath("$.nome").value(userDto.nome))
    }

    @Test
    @Order(1)
    fun `Test creating a user should return 201 Created`() {
        // Given
        val userDto = UserDto(
            id = null,
            nome = "Rafael Horácio",
            birthday = LocalDateTime.parse("1990-01-01T22:22:22"),
            stack = listOf("Java"),
            shortcut = "rafaduka"
        )

        // When
        mockMvc.perform(post("/users")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
            .andExpect(status().isCreated)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.nome").value(userDto.nome))
    }
}