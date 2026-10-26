package controller;

import java.util.ArrayList;

import model.Cliente;
import repository.ClienteRepository;

public class ClienteController {
    private ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente buscar(int id){
        return this.repository.buscarPorId(id);
    }

    public ArrayList<Cliente> listar(){
        return this.repository.listar();
    }

    public boolean remover(int id){
        return this.repository.remover(id);
    }

    public void adcionar( int id, String nome,String telefone){
        Cliente c1 = new Cliente(id, nome, telefone);
        this.repository.adicionar(c1);
    }
}
