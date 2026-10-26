package controller;

import java.util.ArrayList;

import model.Cliente;
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

    
}
