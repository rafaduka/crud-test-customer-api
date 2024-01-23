package br.com.foz.customer.user.repository

import br.com.foz.customer.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : JpaRepository<User, Long>
