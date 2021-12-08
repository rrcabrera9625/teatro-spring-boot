package com.curso.springboot.teatro.controller;

import com.curso.springboot.teatro.model.Butaca;
import com.curso.springboot.teatro.model.Teatro;
import com.curso.springboot.teatro.service.ButacaService;
import com.curso.springboot.teatro.service.TeatroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ButacaController {
    
    @Autowired
    public ButacaService butacaService;

    @Autowired
    public TeatroService teatroService;

    @GetMapping("index/butacas/{id}")
    public String showButacasPorTeatro(@PathVariable(value = "id") int id,  Model model){
        model.addAttribute("butacas", butacaService.listarButacas(id));
        model.addAttribute("teatro", obtenerTeatro(id));
        return "butacas";
    }

    @PostMapping("index/butacas/{teatroid}")
    public String crearButaca(@PathVariable(value = "teatroid") int teatroid,  Model model, String id, int tipo_butaca, int numerobalcon, boolean vendida, boolean areafumadores, boolean protocolo){
        Butaca newButaca = new Butaca();
        newButaca.setButaca(id);
        newButaca.setTipo_butaca(tipo_butaca);
        newButaca.setNumerobalcon(numerobalcon);
        newButaca.setAreafumadores(areafumadores);
        newButaca.setProtocolo(protocolo);
        newButaca.setVendida(vendida);
        newButaca.setTeatro(teatroService.obtenerTeatroPorId(teatroid).get());
        butacaService.crearButaca(newButaca);
        model.addAttribute("butacas", butacaService.listarButacas(teatroid));
        model.addAttribute("teatro", obtenerTeatro(teatroid));
        return "butacas";
    }

    @PostMapping("index/butacas/{teatroid}/vender/{id}")
    public String venderButaca(@PathVariable(value = "id") String id, @PathVariable(value = "teatroid") int teatroid, Model model){
        butacaService.venderButaca(id);
        model.addAttribute("butacas", butacaService.listarButacas(teatroid));
        model.addAttribute("teatro", obtenerTeatro(teatroid));
        return "butacas";
    }


    public Teatro obtenerTeatro(int id){
        Teatro teatro = teatroService.obtenerTeatroPorId(id).get();
        return teatro;

    }
    
}
