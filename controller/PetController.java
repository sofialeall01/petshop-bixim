package controller;

import model.Cliente;
import model.Pet;
import repository.ClienteRepository;
import repository.PetRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class PetController {

    private PetRepository repository;
    private ClienteRepository clienteRepository;

    public PetController(PetRepository repository, ClienteRepository clienteRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    public void adicionar(int id, String nome, String especie, String raca, Cliente cliente, LocalDate nascimento){
        Pet pet = new Pet(id, nome, especie, raca, cliente, nascimento);
        this.repository.adicionar(pet);
    }

    public ArrayList<Pet> listar(){
        return this.repository.listar();
    }
    public Pet buscar(int id){
        return this.repository.buscarPorId(id);
    }
    public boolean remover(int id){
        return this.repository.remover(id);
    }
}
