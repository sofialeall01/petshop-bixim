/*
Sofia Maria de Jesus Leal
Camily Leal Silva
 */
import controller.ClienteController;
import controller.PetController;
import controller.ServicoController;
import controller.ServicoPrestadoController;
import model.Cliente;
import model.Pet;
import model.ServicoPrestado;
import repository.ClienteRepository;
import repository.PetRepository;
import repository.ServicoPrestadoRepository;
import repository.ServicoRepository;
import visao.cmd.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<Pet> pets = new ArrayList<>();
        ArrayList<ServicoPrestado> servicoPrestados = new ArrayList<>();

        ClienteRepository clienteRepository = new ClienteRepository(clientes);
        PetRepository petRepository = new PetRepository(pets);
        ServicoRepository servicoRepository = new ServicoRepository();
        ServicoPrestadoRepository servicoPrestadoRepository = new ServicoPrestadoRepository(servicoPrestados);
        service.ServicoPrestadoService servicoPrestadoService = new service.ServicoPrestadoService(servicoPrestadoRepository,petRepository, servicoRepository);

        clienteRepository.carregarDados("dados/clientes.txt");
        petRepository.carregarDados("dados/pets.txt", clienteRepository);
        servicoRepository.carregarDados("dados/servicos.txt");
        servicoPrestadoRepository.carregarDados("dados/servicosPrestados.txt", petRepository, servicoRepository);

        ClienteController clienteController = new ClienteController(clienteRepository);
        PetController petController = new PetController(petRepository, clienteRepository);
        ServicoController servicoController = new ServicoController(servicoRepository);
        ServicoPrestadoController servicoPrestadoController = new ServicoPrestadoController(petRepository, servicoRepository ,servicoPrestadoService);

        UiCmdCliente uiCmdCliente = new UiCmdCliente(clienteController);
        UiCmdPet uiCmdPet = new UiCmdPet(petController, clienteController);
        UiCmdServico uiCmdServico = new UiCmdServico(servicoController);
        UiCmdServicoPrestado uiCmdServicoPrestado = new UiCmdServicoPrestado(servicoPrestadoController, clienteController);


        UI_Cmd uiCmd =
                new UI_Cmd(
                        uiCmdCliente,
                        uiCmdPet,
                        uiCmdServico,
                        uiCmdServicoPrestado
                );
        uiCmd.iniciar();
    }
}