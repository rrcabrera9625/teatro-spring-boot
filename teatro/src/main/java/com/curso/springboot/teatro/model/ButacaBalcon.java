package com.curso.springboot.teatro.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@DiscriminatorValue("2")
public class ButacaBalcon extends Butaca {

    @Column(name = "areafumadores")
	private Boolean areafumadores;

    @Column(name = "numerobalcon")
	private int numerobalcon;

    public Double precio(){
        return (this.areafumadores)? this.numerobalcon * 0.5 + 5.00 : this.numerobalcon * 0.5 ;
     }
    
}
