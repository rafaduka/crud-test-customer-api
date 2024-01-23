package br.com.foz.customer.user.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.format.DateTimeParseException
import java.util.NoSuchElementException

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(ex: NoSuchElementException): ResponseEntity<ErrorResponse> {
        val errorMessage = "Ei Borboleto... : ${ex.message}"
        val errorResponse = ErrorResponse(errorMessage, HttpStatus.NOT_FOUND.value())
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
    }

    @ExceptionHandler(DateTimeParseException::class)
    fun handleDateTimeParseException(ex: DateTimeParseException): ResponseEntity<ErrorResponse> {
        val errorMessage = "Ei Tubaíno: ${ex.message}"
        val errorResponse = ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleDateTimeParseException(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorMessage = "Ei Rito Cadilaco: ${ex.bindingResult.allErrors.map { it }}"
        val errorResponse = ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value())
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
    }
}

data class ErrorResponse(val message: String, val status: Int)
