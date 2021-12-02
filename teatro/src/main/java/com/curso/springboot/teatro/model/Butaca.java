package com.curso.springboot.teatro.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name="butaca")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_butaca", 
  discriminatorType = DiscriminatorType.INTEGER)
public class Butaca {

    @Id @Column(name = "BUTACA_ID")
	private int id;

    @Column(name = "vendida")
	private Boolean vendida;

    @ManyToOne
    @JoinColumn(name="TEATRO_ID", nullable=true)
    private Teatro teatro;

    public Butaca() {
    }

}
