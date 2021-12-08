package com.curso.springboot.teatro.controller;

import java.util.List;

import com.curso.springboot.teatro.model.Butaca;
import com.curso.springboot.teatro.model.Teatro;
import com.curso.springboot.teatro.service.TeatroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class IndexController {

    @Autowired
    private  TeatroService teatroService;

    @GetMapping("/index")
    public String showIndex(Model model) {
        model.addAttribute("teatros", teatroService.listarTeatros());
        model.addAttribute("recaudo", recaudo());
        model.addAttribute("vendidas", vendidas());
        model.addAttribute("recaudoFumadores", recaudoFumadores());
        return "index";
    }

    @PostMapping("/index/crear-teatro")
    public String crear(Model model, String nombre, int cantidadbutacas) {
        Teatro newTeatro = new Teatro();
        newTeatro.setNombre(nombre);
        newTeatro.setCantidadbutacas(cantidadbutacas);
        teatroService.crearTeatro(newTeatro);
        model.addAttribute("teatros", teatroService.listarTeatros());
        model.addAttribute("recaudo", recaudo());
        model.addAttribute("vendidas", vendidas());
        model.addAttribute("recaudoFumadores", recaudoFumadores());
        return "index";
    }

    public Double recaudo(){
        List<Teatro> teatros = teatroService.listarTeatros();
        Double recaudo = 0.00;
        for (Teatro teatro:teatros){
            recaudo += recaudo + teatroService.recaudado(teatro);
        }
        return recaudo;
    }

    public Double recaudoFumadores(){
        List<Teatro> teatros = teatroService.listarTeatros();
        Double recaudo = 0.00;
        for (Teatro teatro:teatros){
            for(Butaca butaca: teatro.getButacas()){
                if (butaca.getVendida()){
                    if (butaca.getAreafumadores()){
                        recaudo += recaudo + butaca.precio();
                    }
                }
            }
        }
        return recaudo;
    }

    public int vendidas(){
        List<Teatro> teatros = teatroService.listarTeatros();
        int vendidas = 0;
        for (Teatro teatro:teatros){
            for(Butaca butaca: teatro.getButacas()){
                if(butaca.getVendida())
                    vendidas+=1;
            }
        }
        return vendidas;
    }
    
}
