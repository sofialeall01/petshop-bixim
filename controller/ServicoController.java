package controller;

import model.Servico;
import repository.ServicoRepository;

import java.util.ArrayList;

public class ServicoController {

    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository) {
        this.repository = repository;
    }

    public void adicionar(int id, String descricao, double valor){
        Servico servico = new Servico(descricao, id, valor);
        this.repository.adicionar(servico);
    }

    public ArrayList<Servico> listar(){
        return this.repository.listar();
    }
    public Servico buscar(int id){
       return this.repository.buscarPorId(id);
    }
    public boolean remover(int id){
        return this.repository.remover(id);
    }
}
