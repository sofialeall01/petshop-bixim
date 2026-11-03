
package service;

import java.time.LocalDate;
import java.util.ArrayList;

import model.Cliente;
import model.Pet;
import model.Servico;
import model.ServicoPrestado;
import repository.PetRepository;
import repository.ServicoRepository;
import repository.ServicoPrestadoRepository;

public class ServicoPrestadoService {

    private ServicoPrestadoRepository repository;
    private PetRepository repositoryPet;
    private ServicoRepository repositoryServico;

    public ServicoPrestadoService(
            ServicoPrestadoRepository repository,
            PetRepository repositoryPet,
            ServicoRepository repositoryServico) {

        this.repository = repository;
        this.repositoryPet = repositoryPet;
        this.repositoryServico = repositoryServico;
    }

    public void adicionar(int id, double valorCobrado,
                          LocalDate data, int idServico, int idPet) {

        // Regra 1: não permitir data no passado
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Não é permitido cadastrar serviços no passado!");
        }

        // Regra 2: não permitir valor negativo
        if (valorCobrado < 0) {
            throw new IllegalArgumentException(
                    "O valor cobrado não pode ser negativo!");
        }

        Pet pet = repositoryPet.buscarPorId(idPet);
        Servico servico = repositoryServico.buscarPorId(idServico);

        if (pet == null || servico == null) {
            throw new IllegalArgumentException(
                    "Pet ou serviço não encontrado!");
        }

        ServicoPrestado sp = new ServicoPrestado(
                id, data, valorCobrado, pet, servico
        );

        repository.adicionar(sp);
    }

    public ArrayList<ServicoPrestado> listar() {
        return repository.listar();
    }

    public boolean remover(int id) {
        return repository.remover(id);
    }

    public ServicoPrestado buscar(int id) {
        return repository.buscarPorId(id);
    }

    public ArrayList<Pet> buscarBichosPorNomeDono(String nome){
        ArrayList<Pet> pets = repositoryPet.listar();
        ArrayList<Pet> petsCliente = new ArrayList<>();

        for (int index = 0; index < pets.size(); index++) {
            if (pets.get(index).getCliente().getNome().equalsIgnoreCase(nome)) {
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

}
