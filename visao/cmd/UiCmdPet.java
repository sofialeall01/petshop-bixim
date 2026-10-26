package visao.cmd;

import java.util.Scanner;

import controller.ClienteController;
import controller.PetController;

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
                case 1:
                
                break;
            
                default:
                    break;
            }
     
    }while(opcao !=0);
    
}
}