package com.estim.es.estim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;

@Entity()
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) //Auto-incremental
    private Integer id;
    @Size(min = 1, max = 40, message = "La longitud del nombre debe estar entre {min} y {max} caracteres")
    private String nombre;
    @Size(min = 4, max = 80, message = "La longitud de los apellidos debe estar entre {min} y {max} caracteres")
    private String apellidos;
    @Pattern(regexp = "\\d{7,8}(-?[A-Z])?", message = "El DNI no es válido, debe tener 8 cifras y una letra mayúscula")
    private String dni;
    @Pattern(regexp = "^([\\w-\\.]+)@((?:[\\w]+\\.)+)([a-zA-Z]{2,4})$", message = "El correo electrónico no es válido")
    private String email;
    private LocalDate fechaNac;
    private String rol;
    @Size(min = 4, max = 12, message = "La longitud del usuario debe estar entre {min} y {max} caracteres")
    private String usuario;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[a-zA-Z]).{8,}$", message = "La contraseña debe tener al menos una mayúscula, un número y una longitud mínima de 8 caracteres.")
    private String psswd;
    private String calle;
    private Integer num;
    private String ciudad;
    @Pattern(regexp = "^[0-9]{5}$", message = "El código postal debe tener 5 cifras")
    private String codPostal;
    private String pais;

    public Cliente(){
        id = 0;
        nombre = "";
        apellidos = "";
        dni = "";
        email = "";
        fechaNac = LocalDate.now();
        rol = "";
        usuario = "";
        psswd = "";
        calle = "";
        num = 0;
        ciudad = "";
        codPostal = "";
        pais = "";
    }

    public Cliente(Integer id, String nombre, String apellidos, String dni, String email, LocalDate fechaNac, String rol, String usuario, String psswd, String calle, Integer num, String ciudad, String codPostal, String pais) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.email = email;
        this.fechaNac = fechaNac;
        this.rol = rol;
        this.usuario = usuario;
        this.psswd = psswd;
        this.calle = calle;
        this.num = num;
        this.ciudad = ciudad;
        this.codPostal = codPostal;
        this.pais = pais;
    }

    public Cliente(Cliente c) {
        this.id = c.id;
        this.nombre = c.nombre;
        this.apellidos = c.apellidos;
        this.dni = c.dni;
        this.email = c.email;
        this.fechaNac = c.fechaNac;
        this.rol = c.rol;
        this.usuario = c.usuario;
        this.psswd = c.psswd;
        this.calle = c.calle;
        this.num = c.num;
        this.ciudad = c.ciudad;
        this.codPostal = c.codPostal;
        this.pais = c.pais;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() { return apellidos; }

    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPsswd() {
        return psswd;
    }

    public void setPsswd(String psswd) {
        this.psswd = psswd;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        return hash;
    }

    @Override
    public String toString() {
        return "Cliente[ id=" + id + " ]";
    }
}
