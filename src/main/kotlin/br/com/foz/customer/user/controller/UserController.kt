package br.com.foz.customer.user.controller


import br.com.foz.customer.user.dto.UserDto
import br.com.foz.customer.user.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto?> {
        val userDto: UserDto? = userService.getUserById(id)
        return if (userDto != null) {
            ResponseEntity.ok(userDto)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping
    fun getUsers(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<List<UserDto>> {
        val users: List<UserDto> = userService.getUsers(page, size)
        return ResponseEntity.ok(users)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @PostMapping
    fun createUser(@RequestBody @Valid userDto: UserDto): ResponseEntity<UserDto> {
        val createdUser: UserDto = userService.createUser(userDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser)
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody @Valid userDto: UserDto): ResponseEntity<UserDto> {
        val updatedUser: UserDto = userService.updateUser(id, userDto)
        return ResponseEntity.ok(updatedUser)
    }
}
