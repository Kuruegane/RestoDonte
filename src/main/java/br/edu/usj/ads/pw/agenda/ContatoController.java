package br.edu.usj.ads.pw.agenda;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContatoController {

    @Autowired
    ContatoRepository contatoRepository;


    @GetMapping(value="/")
    public ModelAndView getIndex() {
        // criar um objeto lista
        List<Contato> lista = new ArrayList<>();

        // preencher esta lista com os dados do banco
        lista = contatoRepository.findAll();

        // instanciar um template
        ModelAndView modelAndView = new ModelAndView("index");

        // preencher o template com a lista
        modelAndView.addObject("lista", lista);

        //retornar o template
        return modelAndView;
    }
    

    @GetMapping(value = "/cadastro")
    public ModelAndView getCadastro() {
        ModelAndView modelAndView = new ModelAndView("cadastro");

        return modelAndView;
    }

    @PostMapping(value = "/adicionar")
    public ModelAndView postAdicionar(@RequestParam String nome, @RequestParam String tipo,
            @RequestParam String telefone) {
        // criar objeto de tipo contato
        Contato contato = new Contato();

        // preencher objeto contato com os dados
        contato.setNome(nome);
        contato.setTipo(tipo);
        contato.setTelefone(telefone);

        // salvar no banco (usando repository)
        contatoRepository.save(contato);

        // criar template
        ModelAndView modelAndView = new ModelAndView("detalhes");

        // popular o template
        modelAndView.addObject("contato", contato);

        // retornar
        return modelAndView;
    }

}
