package com.curso.springboot.teatro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name="butaca")
@Entity
public class Butaca {

    @Id @Column(name = "BUTACA_ID")
	private String butaca;

    @Column(name = "tipo_butaca")
    private int tipo_butaca;

    @Column(name = "vendida")
	private Boolean vendida;
    
    @Column(name = "areafumadores", nullable = true)
	private Boolean areafumadores;

    @Column(name = "numerobalcon", nullable = true)
	private int numerobalcon;

    @Column(name = "protocolo", nullable = true)
	private Boolean protocolo;

    @ManyToOne
    @JoinColumn(name="TEATRO_ID", nullable=true)
    private Teatro teatro;

    public Butaca() {
    }

    public Double precio(){
        if (this.tipo_butaca == 1){
            return (this.protocolo)? 30.00 : 20.00;
        }
        else {
            return (this.areafumadores)? this.numerobalcon * 0.5 + 5.00 : this.numerobalcon * 0.5 ;
        }   
    }

    public void vender(){
        this.vendida = true;
    }

}
