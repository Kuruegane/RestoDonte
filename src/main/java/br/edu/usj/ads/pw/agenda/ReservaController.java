package br.edu.usj.ads.pw.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservaController {

    @Autowired
    ReservaRepository reservaRepository;


    @GetMapping(value="/")
    public ModelAndView getIndex() {
        
        List<Reserva> lista = new ArrayList<>();
        lista = reservaRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("lista", lista);
        return modelAndView;
    }

    @GetMapping(value="/editar/{id}")
    public ModelAndView getEditar(@PathVariable Long id) {
        Reserva reserva = new Reserva();
        reserva = reservaRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("reserva", reserva);

        return modelAndView;
    }

    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getDetalhes(@PathVariable Long id) {
        Reserva reserva = new Reserva();
        reserva = reservaRepository.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("reserva", reserva);
        return modelAndView;
    }

    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        Reserva reserva = new Reserva();
        ModelAndView modelAndView = new ModelAndView("cadastro");

        modelAndView.addObject("reserva", reserva);
        return modelAndView;
    } 
    
    @PostMapping(value="/adicionar")
    public ModelAndView postAdicionar(Reserva reserva) {
        reservaRepository.save(reserva);
        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("reserva", reserva);
        return modelAndView;
    }

    @GetMapping(value="/deletar/{id}")
    public String getDeletar(@PathVariable Long id) {
        reservaRepository.deleteById(id);
        return "redirect:/";
    }

}
