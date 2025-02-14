package com.hlc.etiquetas.entidad;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener letras, números y espacios")
    private String nombre;

    @Column(length = 255)
    @Size(min=3, max = 255, message = "La descripción debe tener entre 3 y no más de 255 caracteres")
    private String descripcion;

    @Column(nullable = false)
    @NotNull(message = "El estado activo no puede ser nulo")
    private Boolean activo;

    @Column
    @Min(value = 1, message = "La prioridad debe ser al menos 1")
    @Max(value = 5, message = "La prioridad no puede ser mayor a 5")
    @NotNull(message = "La prioridad no puede ser nulo")
    private Integer prioridad;
    
    @Pattern(regexp = "^(rojo|azul|verde|amarillo)$", message = "El color debe ser rojo, azul, verde o amarillo")
    @NotNull(message = "El color no puede ser nulo")
    private String color;
    
    // Constructor con parámetros
    public Etiqueta(
            @NotNull(message = "El nombre no puede ser nulo") @Size(min = 1, max = 50, message = "El nombre debe tener entre 1 y 50 caracteres")
            @Pattern(regexp = "^[A-Za-z0-9 ]+$", message = "El nombre solo puede contener letras, números y espacios") String nombre,
            @Size(max = 255, message = "La descripción no puede tener más de 255 caracteres") String descripcion,
            @NotNull(message = "El estado activo no puede ser nulo") Boolean activo,
            @Min(value = 1, message = "La prioridad debe ser al menos 1") @Max(value = 5, message = "La prioridad no puede ser mayor a 5") Integer prioridad,
            @NotNull(message = "El color no puede ser nulo")
            @Pattern(regexp = "⁽rojo|azul|verde|amarillo)$", message= "El color debe ser rojo, azul, verde o amarillo") String color
    		) {
    	
        	
    	this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
        this.prioridad = prioridad;
        this.color = color;
    }

    // Constructor por defecto
    public Etiqueta() {
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
    
    
}