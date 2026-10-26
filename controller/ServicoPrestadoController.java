package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;

public class ServicoPrestadoController {
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;
    private ServicoPrestadoRepository repository;

    

    public ServicoPrestadoController(PetRepository repositoryPet, ServicoRepository repositoryServico,
            ServicoPrestadoRepository repository) {
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
        this.repository = repository;
    }

    public double maiorValorPorCliente(Cliente cliente){
    
        ArrayList<ServicoPrestado> servicosCliente = buscarPorCliente(cliente);
        double maiorGasto = 0;
        for (int i = 0; i < servicosCliente.size(); i++) {
                if (servicosCliente.get(i).getValorCobrado() > maiorGasto) {
                    maiorGasto = servicosCliente.get(i).getValorCobrado();
                }
            }
            return maiorGasto;
      }
    
    public ArrayList<ServicoPrestado> buscarPorCliente(Cliente cliente){

    ArrayList<ServicoPrestado> servicos = repository.listar();
    ArrayList<ServicoPrestado> servicosCliente = new ArrayList<>();

    for (int i = 0; i < servicos.size(); i++) {
     // verificamos se o serviço tem um pet e se esse pet tem um cliente
        if(servicos.get(i).getPet() != null && servicos.get(i).getPet().getCliente() != null){
            if (servicos.get(i).getPet().getCliente().equals(cliente)){
                servicosCliente.add(servicos.get(i));
            }
        }

    }
    return servicosCliente;
    }

    public boolean removerServicoPrestado(int id){
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id){
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome){
        ArrayList<Pet> pets = repositoryPet.listar();
        ArrayList<Pet> petsCliente = new ArrayList<>();

        for (int index = 0; index < pets.size(); index++) {
            if (pets.get(index).getCliente().getNome() == nome) {
                petsCliente.add(pets.get(index));
            }
        }
        return petsCliente;
    }
    
    public ArrayList<ServicoPrestado> buscarPorPerido( LocalDate dataInicial , LocalDate dataFinal){
        ArrayList<ServicoPrestado> servicos = repository.listar();
        ArrayList<ServicoPrestado> servicosEncontrados = new ArrayList<>();

       for (int i = 0; i < servicos.size(); i++) {
        //Se a data do serviço não é anterior à data inicial (before) E não é posterior data final (after), significa que ela está dentro do período
        if (!servicos.get(i).getData().isBefore(dataInicial) && !servicos.get(i).getData().isAfter(dataFinal) ) {
            servicosEncontrados.add(servicos.get(i));
        }
       }
       return servicosEncontrados;
    }

    public void adcionar(int id, double valorCobrado, LocalDate data, Pet pet, Servico servico){
        ServicoPrestado sp = new ServicoPrestado(id,data,valorCobrado, pet, servico);
        repository.adicionar(sp);
    }

    public ArrayList<ServicoPrestado> listar(){
        return repository.listar();
    }
}
