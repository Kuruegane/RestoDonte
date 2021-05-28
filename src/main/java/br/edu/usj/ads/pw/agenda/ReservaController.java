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
        // criar um objeto lista
        List<Reserva> lista = new ArrayList<>();

        // preencher esta lista com os dados do banco
        lista = reservaRepository.findAll();

        // instanciar um template
        ModelAndView modelAndView = new ModelAndView("index");

        // preencher o template com a lista
        modelAndView.addObject("lista", lista);

        //retornar o template
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
        // ler reserva no banco pelo id
        Reserva reserva = new Reserva();
        reserva = reservaRepository.findById(id).get();

        // instanciar o template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // preencher o template com o reserva selecionado
        modelAndView.addObject("reserva", reserva);

        // retornar template
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
        // salvar no banco (usando repository)
        reservaRepository.save(reserva);

        // criar template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // popular o template
        modelAndView.addObject("reserva", reserva);

        // retornar
        return modelAndView;
    }

    @GetMapping(value="/deletar/{id}")
    public String getDeletar(@PathVariable Long id) {
        // deletar o objeto com o id passado pelo parametro
        reservaRepository.deleteById(id);

        // retornar o template 
        return "redirect:/";
    }

}
