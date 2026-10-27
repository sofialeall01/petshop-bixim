package visao.cmd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ClienteController;
import controller.PetController;
import model.Pet;

public class UiCmdPet {
    private Scanner teclado;
    private PetController controller;
    private ClienteController controllerCliente;

    public UiCmdPet( PetController controller,ClienteController controllerCliente) {
        teclado = new Scanner(System.in);
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }


    public void iniciar(){
        int opcao = 0;
     do{
          System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║            GERENCIAR PETS            ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - Adicionar Pet                   ║");
            System.out.println("║  2 - Remover Pet                     ║");
            System.out.println("║  3 - Listar Pets                     ║");
            System.out.println("║  4 - Buscar Pet                      ║");
            System.out.println("║  0 - Voltar ao Menu Principal        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            switch (opcao) {
                case 1: adcionar(); break;
                
                case 2: apagar(); break;

                case 3: listar(); break;

                case 4: buscar(); break;
            
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
     
    }while(opcao !=0);
    
}

    private void adcionar(){
       System.out.println("Informe o ID do pet: \n");
       int id = teclado.nextInt();
       System.out.println("Informe o nome do pet: \n");
       String nome = teclado.next();
       System.out.println("Informe a raca do pet: \n");
       String especie = teclado.next();
       System.out.println("Informe a raca do pet: \n");
       String raca = teclado.next();
       System.out.println("Informe a data de nascimneto do pet: ");
       String data = teclado.next();
       System.out.println("Informe o id do cliente: \n");
       int idCliente = teclado.nextInt();
       System.out.println("Informe o nome do cliente: \n");
       String nomeCliente = teclado.next();
       System.out.println("Informe o telefone do cliente: \n");
       String telefone = teclado.next();

        //formatando a data
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatador);

        // como o no PetConttroller o metodo adcionar espera somente o id do cliente eu preciso
        //primeiro adcionar o cliente, assim dentro do metodo adcionar no PetController ele buscar esse cliente
        //pelo id e depois relaciona ele ao pet
        controllerCliente.adcionar(idCliente,nomeCliente,telefone);
        controller.adicionar(id, nome, especie, raca, dataFormatada, idCliente);
    }

    private void apagar(){
        System.out.println("Informe o ID do pet que sera apagado: \n");
        int id = teclado.nextInt();

        boolean resultado = controller.remover(id);
        if (resultado == true) {
            System.out.println("Pet apagado com sucesso!");
        }else{
            System.out.println("Erro ao apagar pet");
        }

    }

    private void listar(){
        ArrayList<Pet> pets = controller.listar();
        for (int index = 0; index < pets.size(); index++) {
            System.out.println(pets.get(index).toString());
        }
    }

    private void buscar(){
        System.out.println("Informe o ID do pet: ");
        int id = teclado.nextInt();

        Pet encontrado = controller.buscar(id);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Pet nao encontrado");
        }
    }
}