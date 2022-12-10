package net.parttimepolymath.sandbox.springboot.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.*;
import net.parttimepolymath.sandbox.springboot.model.EchoRequest;
import net.parttimepolymath.sandbox.springboot.model.EchoResponse;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;


/**
 * This entity bean maps onto the test.messages table in the database.
 *
 * @since 2020-06-27
 * @author Robert Hook
 */
@Entity
@Table(schema="test", name = "messages")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Messages {
    @Id
    @Column(name="id", updatable=false, nullable=false)
    private String id;

    @Size(max=1024)
    @Column(name="message", updatable=false, length=1024)
    private String message;

    /**
     * convert the instance to an echo response.
     * @return a non-null response.
     */
    public EchoResponse toResponse() {
        return EchoResponse.builder().id(UUID.fromString(id)).message(message).build();
    }

    /**
     * construct an instance from an incoming request.
     * @param request the non-null request to use.
     */
    public static Messages fromRequest(final EchoRequest request) {
        assert request != null : "request must not be null";
        return new Messages(UUID.randomUUID().toString(), request.getMessage());
    }

    /**
     * convert an instance to an echo response.
     * @return a non-null response.
     */
    public static EchoResponse toResponse(final Messages entity) {
        assert entity != null : "entity must not be null";
        return entity.toResponse();
    }
}
