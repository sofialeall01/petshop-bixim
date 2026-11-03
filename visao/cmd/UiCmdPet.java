package visao.cmd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ClienteController;
import controller.PetController;
import model.Cliente;
import model.Pet;

public class UiCmdPet {
    private Scanner teclado;
    private PetController controller;
    private ClienteController controllerCliente;

    public UiCmdPet( PetController controller,ClienteController controllerCliente) {
        this.teclado = new Scanner(System.in);
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
            opcao = teclado.nextInt();

            switch (opcao) {
                case 1: adcionar(); break;
                
                case 2: apagar(); break;

                case 3: listar(); break;

                case 4: buscar(); break;
            
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
        } while(opcao !=0);
    
}

    private void adcionar(){
       System.out.println("Informe o ID do pet: ");
       int id = teclado.nextInt();
       System.out.println("Informe o nome do pet: ");
       String nome = teclado.next();
       System.out.println("Informe a espécie do pet: ");
       String especie = teclado.next();
       System.out.println("Informe a raça do pet: ");
       String raca = teclado.next();

       DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/uuuu");
       LocalDate dataFormatada = null;

        while (dataFormatada == null) {
            try {
                System.out.println("Informe a data de nascimento (dd/MM/yyyy):");
                String data = teclado.next();

                dataFormatada = LocalDate.parse(data, formatador);

            } catch (Exception e) {
                System.out.println("Data inválida! Tente novamente.");
            }
        }
       System.out.println("Informe o id do cliente: ");
       int idCliente = teclado.nextInt();
       teclado.nextLine(); //limpa o buffer


        Cliente cliente = controllerCliente.buscar(idCliente);
        if(cliente == null){
            System.out.println("Informe o nome do cliente:");
            String nomeCliente = teclado.next();

            System.out.println("Informe o telefone do cliente ((dd) xxxxx-xxxx):");

            teclado.nextLine(); // Consome a quebra de linha anterior

            String telefone = teclado.nextLine();

            while (!telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}")) {
                System.out.println("Telefone inválido! Tente novamente:");
                telefone = teclado.nextLine();
            }

            controllerCliente.adicionar(idCliente, nomeCliente, telefone);
        }
        controller.adicionar(id, nome, especie, raca, dataFormatada, idCliente);
    }

    private void apagar(){
        System.out.println("Informe o ID do pet que sera apagado: \n");
        int id = teclado.nextInt();
        teclado.nextLine(); //limpa o buffer
        if (id == 0) {
            System.out.println("Insira um ID diferente de 0");
            return;
        }

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
        teclado.nextLine(); //limpa o buffer
        if (id == 0) {
            System.out.println("Insira um ID diferente de 0");
            return;
        }

        Pet encontrado = controller.buscar(id);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Pet nao encontrado");
        }
    }
}