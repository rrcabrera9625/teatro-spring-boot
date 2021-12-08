package com.curso.springboot.teatro.service;

import java.util.List;
import java.util.Optional;

import com.curso.springboot.teatro.model.Butaca;
import com.curso.springboot.teatro.model.Teatro;
import com.curso.springboot.teatro.repository.TeatroRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeatroService {

    @Autowired
    private TeatroRepository teatroRepository;

    // CREATE
	public Teatro crearTeatro(Teatro teatroRequest) {
		Teatro newTeatro = new Teatro();
		newTeatro.setNombre(teatroRequest.getNombre());
        newTeatro.setCantidadbutacas(teatroRequest.getCantidadbutacas());
		return teatroRepository.save(newTeatro);
	}

	// READ
	public List<Teatro> listarTeatros() {
		return teatroRepository.findAll();
	}

	// POR ID
	public Optional<Teatro> obtenerTeatroPorId(int id){
		return teatroRepository.findById(id);
	}

	// VENDIDAS
	public int butacasVendidas(Teatro teatro){
		int butacasVendidas = 0;
		for (Butaca butaca : teatro.getButacas()){
			if (butaca.getVendida())
				butacasVendidas += 1;
		}
		return butacasVendidas;
	}

	// RECAUDO
	public double recaudado(Teatro teatro){
		int recaudo = 0;
		for (Butaca butaca : teatro.getButacas()){
			recaudo += butaca.precio();
		}
		return recaudo;
	}

	// RECAUDO FUMADORES
	public double recaudoFumadores(Teatro teatro){
		int recaudo = 0;
		for (Butaca butaca : teatro.getButacas()){
			if (butaca.getAreafumadores())
				recaudo += butaca.precio();
		}
		return recaudo;
	}
}

