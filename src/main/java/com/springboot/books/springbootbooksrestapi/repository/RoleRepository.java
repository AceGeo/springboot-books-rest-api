package com.springboot.books.springbootbooksrestapi.repository;

import com.springboot.books.springbootbooksrestapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//Repository interface for performing CRUD operations on Role entities.
public interface RoleRepository extends JpaRepository<Role, Long> {

}
