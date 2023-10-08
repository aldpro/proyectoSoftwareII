package co.emp.prisig.entidades;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Administrador implements Serializable{

    @Id
    @EqualsAndHashCode.Include
    private Integer cedula;

    @Column(nullable = false, length = 100)
    private String nombre;

    @NotNull
    @Email
    @Column(nullable = false, unique = true, length = 200)
    private String correo;

    @Column(nullable = false,length = 100)
    @ToString.Exclude
    private String password;

    @Builder
    public Administrador(Integer cedula, String nombre, String correo, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.correo = correo;
        this.password = password;
    }
}
