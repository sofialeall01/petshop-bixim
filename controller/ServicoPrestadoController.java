package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;
import repository.ClienteRepository;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private service.ServicoPrestadoService service;


    public ServicoPrestadoController(PetRepository repositoryPet, ServicoRepository repositoryServico,
                                     service.ServicoPrestadoService service) {
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
        this.service = service;
    }

    public double maiorValorPorCliente(Cliente cliente){
       return service.maiorValorPorCliente(cliente);
    }
    
    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente){
        return service.buscarPorCliente(cliente);
    }

    public boolean removerServicoPrestado(int id){
        return service.remover(id);
    }

    public ServicoPrestado buscar(int id){
       return service.buscar(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome){
       return service.buscarBichosPorNomeDono(nome);
    }
    
    public ArrayList<ServicoPrestado> buscarPorPerido( LocalDate dataInicial , LocalDate dataFinal){
       return service.buscarPorPerido(dataInicial, dataFinal);
    }

    public void adicionar(int id, double valorCobrado,
                          LocalDate data, int idServico, int idPet) {

        service.adicionar(id, valorCobrado, data, idServico, idPet);
    }

    public ArrayList<ServicoPrestado> listar(){
        return service.listar();
    }
}
