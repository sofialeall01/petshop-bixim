package visao.cmd;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ClienteController;
import controller.ServicoPrestadoController;
import model.Cliente;
import model.Pet;
import model.ServicoPrestado;

public class UiCmdServicoPrestado {
    private Scanner teclado;
    private ServicoPrestadoController controller;
    private ClienteController controllerCliente;

    


    public UiCmdServicoPrestado(ServicoPrestadoController controller, ClienteController controllerCliente) {
        this.controller = controller;
        this.controllerCliente = controllerCliente;
    }

    public void iniciar(){
        int opcao = 0;
     do{
          System.out.println("\n╔════════════════════════════════════════════╗");
            System.out.println("║       GERENCIAR SERVICOS PRESTADOS         ║");
            System.out.println("╠════════════════════════════════════════════╣");
            System.out.println("║  1 - Adicionar Servicos Prestados          ║");
            System.out.println("║  2 - Remover Servicos Prestados            ║");
            System.out.println("║  3 - Listar Servicos Prestados             ║");
            System.out.println("║  4 - Buscar Servicos Prestados Por ID      ║");
            System.out.println("║  5 - Buscar Servicos Prestados Por Periodo ║");
            System.out.println("║  6 - Buscar Animais do Dono                ║");
            System.out.println("║  7 - Buscar Servicos Prestados Por Cliente ║");
            System.out.println("║  8 - Buscar o Servico de Maior Valor       ║");
            System.out.println("║  0 - Voltar ao Menu Principal              ║");
            System.out.println("╚════════════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            switch (opcao) {
                case 1: adcionar(); break;
                
                case 2: apagar(); break;

                case 3: listar(); break;

                case 4: buscar(); break;

                case 5: servicosPorPeriodo(); break;

                case 6: animaisDoDono(); break;

                case 7: servicosDoCliente(); break;

                case 8: maiorValor(); break;
            
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
     
    }while(opcao !=0);
    
}

    private void adcionar(){
       System.out.println("Informe o ID do servico prestado: \n");
       int id = teclado.nextInt();
       System.out.println("Informe o valor: ");
       double valor = teclado.nextDouble();
       System.out.println("Informe a data de realizacao: ");
       String data = teclado.next();

        //formatando a data
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataFormatada = LocalDate.parse(data, formatador);

        System.out.println("Informe o ID do pet vinculado ao servico prestado: ");
        int idPet = teclado.nextInt();
        System.out.println("Infome o ID do servico realizado: ");
        int idServico = teclado.nextInt();

        // como o no ServicoPrestadoConttroller o metodo adcionar espera somente o id do pet e o id do servico,
        // dentro do metodo adcionar no ServicoPrestadoConttroller ele buscar esse pet e o servico
        //pelo id e depois relaciona ele ao servico prestado
        controller.adcionar(id, valor, dataFormatada, idServico, idPet);
    }

    private void apagar(){
        System.out.println("Informe o ID do servico prestado que sera apagado: \n");
        int id = teclado.nextInt();

        boolean resultado = controller.removerServicoPrestado(id);
        if (resultado == true) {
            System.out.println("Servico prestado apagado com sucesso!");
        }else{
            System.out.println("Erro ao apagar o servico prestado");
        }

    }

    private void listar(){
        ArrayList<ServicoPrestado> servicos = controller.listar();
        for (int index = 0; index < servicos.size(); index++) {
            System.out.println(servicos.get(index).toString());
        }
    }

    private void buscar(){
        System.out.println("Informe o ID do servico prestado: ");
        int id = teclado.nextInt();

        ServicoPrestado encontrado = controller.buscar(id);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Pet nao encontrado");
        }
    }

    private void servicosPorPeriodo(){
        System.out.println("Informe a data para o intervalo inicial: ");
        String dataInicial = teclado.next();
        System.out.println("Informe a data para o intervalo final: ");
        String dataFinal = teclado.next();

        //formatando a data
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicialFormatada = LocalDate.parse(dataInicial, formatador);
        LocalDate dataFinalFormatada = LocalDate.parse(dataFinal, formatador);

        ArrayList<ServicoPrestado> encontrados = controller.buscarPorPerido(dataInicialFormatada, dataFinalFormatada);
        if (encontrados != null) {
            for (int index = 0; index < encontrados.size(); index++) {
                System.out.println(encontrados.get(index).toString());
                
            }
        }else{
            System.out.println("Nenhum servico prestado encontrado. ");
        }
    }

    private void animaisDoDono(){
        System.out.println("Informe o nome do cliente: ");
        String nome = teclado.next();

        ArrayList<Pet> encontrados = controller.buscarBichosPorNomeDono(nome);
        if (encontrados != null) {
            for (int index = 0; index < encontrados.size(); index++) {
                System.out.println(encontrados.get(index).toString());
                
            }
        }else{
            System.out.println("Nenhum pet encontrado. ");
        }

    }

    private void maiorValor(){
        System.out.println("Informe o ID co cliente: ");
        int id = teclado.nextInt();

        Cliente cliente = controllerCliente.buscar(id);
        double maiorValor = controller.maiorValorPorCliente(cliente);
        System.out.println("Servico de maior valor : "+ maiorValor +" R$");
    }

    private void servicosDoCliente(){
        System.out.println("Infome o ID do cliente: ");
        int id = teclado.nextInt();

        Cliente cliente = controllerCliente.buscar(id);
        ArrayList<ServicoPrestado> encontrados = controller.buscarPorCliente(cliente);
        if (encontrados != null) {
            for (int i = 0; i < encontrados.size(); i++) {
                System.out.println(encontrados.get(i).toString());
            }
        }else{
            System.out.println("Nenhum servico prestado encontrado");
        }
    }
}