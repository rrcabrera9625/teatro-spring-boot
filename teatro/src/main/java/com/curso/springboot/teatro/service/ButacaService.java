package com.curso.springboot.teatro.service;

import java.util.List;

import com.curso.springboot.teatro.model.Butaca;
import com.curso.springboot.teatro.repository.ButacaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ButacaService {

    @Autowired
	private ButacaRepository butacaRepository;


    // CREATE
	public Butaca crearButaca(Butaca butacaRequest) {
		Butaca newButaca = new Butaca();
		newButaca.setId(butacaRequest.getId());
		newButaca.setTipo_butaca(butacaRequest.getTipo_butaca());
        if (newButaca.getTipo_butaca()==1){
            newButaca.setProtocolo(butacaRequest.getProtocolo());
        }
        else {
            newButaca.setAreafumadores(butacaRequest.getAreafumadores());
            newButaca.setNumerobalcon(butacaRequest.getNumerobalcon());
        }
        newButaca.setVendida(false);
        newButaca.setTeatro(butacaRequest.getTeatro());
		return butacaRepository.save(newButaca);
	}

	// READ
	public List<Butaca> listarButacas(int id) {
		return butacaRepository.findByTeatroId(id);
	}

	// VENDER
	public void venderButaca(int id) {
		Butaca butaca = butacaRepository.findById(id).get();
        butaca.vender();
		butacaRepository.save(butaca);
	}
    
}
