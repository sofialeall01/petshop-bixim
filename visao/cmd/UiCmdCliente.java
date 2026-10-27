package visao.cmd;

import java.util.ArrayList;
import java.util.Scanner;

import controller.ClienteController;
import model.Cliente;

public class UiCmdCliente {
    private Scanner teclado;
    private ClienteController controller;

    
    public UiCmdCliente(ClienteController controller) {
        this.controller = controller;
    }

    public void iniciar(){
        int opcao = 0;
     do{
          System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║          GERENCIAR CLIENTES          ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - Remover Cliente                 ║");
            System.out.println("║  2 - Listar Clientes                 ║");
            System.out.println("║  3 - Buscar Cliente                  ║");
            System.out.println("║  0 - Voltar ao Menu Principal        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            switch (opcao) {
                
                case 1: apagar(); break;

                case 2: listar(); break;

                case 3: buscar(); break;
            
                default:
                    System.out.println("Opcao invalida");
                    break;
            }
     
    }while(opcao !=0);
    
}

   
    private void apagar(){
        System.out.println("Informe o ID do cliente que sera apagado: \n");
        int id = teclado.nextInt();

        boolean resultado = controller.remover(id);
        if (resultado == true) {
            System.out.println("Cliente apagado com sucesso!");
        }else{
            System.out.println("Erro ao apagar Cliente");
        }

    }

    private void listar(){
        ArrayList<Cliente> clientes = controller.listar();
        for (int index = 0; index < clientes.size(); index++) {
            System.out.println(clientes.get(index).toString());
        }
    }

    private void buscar(){
        System.out.println("Informe o ID do cliente: ");
        int id = teclado.nextInt();

        Cliente encontrado = controller.buscar(id);
        if(encontrado != null){
            System.out.println(encontrado.toString());
        }else{
            System.out.println("Cliente nao encontrado");
        }
    }
}
