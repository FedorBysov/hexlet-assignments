package exercise.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "guests")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Guest {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    // BEGIN
//    Имя пользователя name - должно быть не пустым
//    Электронная почта email – должна быть валидным Email
//    Номер телефона phoneNumber – должен начинаться с символа + и содержать от 11 до 13 цифр
//    Номер клубной карты clubCard – должен состоять ровно из 4 цифр
//    Срок действия клубной карты cardValidUntil должен быть еще не истекшим


    @NotBlank
    private String name;

    @Email
    private String email;

    @Column(unique = true)
    @Pattern(regexp = "\\+[0-9]{11,13}")
    private String phoneNumber;

    @Pattern(regexp = "\\d{4}")
    private String clubCard;

    @Future
    private Date cardValidUntil;


    // END

    @CreatedDate
    private Date createdAt;
}
