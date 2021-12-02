package com.curso.springboot.teatro.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@Table(name="teatro")
public class Teatro {

    @Id @Column(name = "TEATRO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "nombre") 
    private String nombre;
    
    @Column(name = "cantidadbutacas") 
    private String cantidadbutacas;

	@OneToMany(mappedBy = "teatro", fetch = FetchType.EAGER)
    private Set<Butaca> butacas;

    public Teatro() {
    }

}
