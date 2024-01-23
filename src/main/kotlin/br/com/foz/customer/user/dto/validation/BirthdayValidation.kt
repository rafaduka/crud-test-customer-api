package br.com.foz.customer.user.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [BirthdayValidator::class])
annotation class ValidBirthday(
    val message: String = "Invalid birthday format. Must be in ISO8601 format.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)

class BirthdayValidator : ConstraintValidator<ValidBirthday, LocalDateTime?> {
    override fun isValid(value: LocalDateTime?, context: ConstraintValidatorContext): Boolean {
        val currentDate = LocalDateTime.now()
        val age = Period.between(value?.toLocalDate(), currentDate.toLocalDate())
        return age.years >= 18
    }
}