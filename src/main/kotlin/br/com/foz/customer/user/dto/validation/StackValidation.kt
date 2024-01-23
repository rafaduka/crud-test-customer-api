package br.com.foz.customer.user.dto.validation

import jakarta.validation.Constraint
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [StackValidator::class])
annotation class ValidStack(
    val message: String = "Invalid stack: must be a non-null list of strings with each string having a max length of 32",
    val groups: Array<KClass<*>> = [],  // Add this line
    val payload: Array<KClass<out Payload>> = []  // Add this line
)

class StackValidator : ConstraintValidator<ValidStack, List<String>?> {
    override fun isValid(value: List<String>?, context: ConstraintValidatorContext): Boolean {
        if (value == null) {
            return false
        }

        val maxLength = 32
        return value.all { it != null && it.length <= maxLength }
    }
}