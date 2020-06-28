package net.parttimepolymath.sandbox.springboot.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * sets up repository for Messages, providing the glue between the Spring JPA automagic and the Messages entity class.
 */
@Repository
public  interface MessagesRepository extends JpaRepository<Messages, String> {
}
