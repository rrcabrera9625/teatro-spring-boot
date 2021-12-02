package com.curso.springboot.teatro.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
@DiscriminatorValue("1")
public class ButacaPlatea extends Butaca {

    @Column(name = "protocolo")
	private Boolean protocolo;

    public Double precio(){
       return (this.protocolo)? 30.00 : 20.00;
    }
    
}
