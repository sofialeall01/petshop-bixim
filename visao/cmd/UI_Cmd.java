package visao.cmd;

import java.util.Scanner;

public class UI_Cmd {
    private Scanner teclado;

    private UiCmdCliente uiCmdCliente;
    private UiCmdPet uiCmdPet;
    private UiCmdServico uiCmdServico;
    private UiCmdServicoPrestado uiCmdServicoPrestado;

    public UI_Cmd(
            UiCmdCliente uiCmdCliente,
            UiCmdPet uiCmdPet,
            UiCmdServico uiCmdServico,
            UiCmdServicoPrestado uiCmdServicoPrestado) {
        teclado = new Scanner(System.in);
        this.uiCmdCliente = uiCmdCliente;
        this.uiCmdPet = uiCmdPet;
        this.uiCmdServico = uiCmdServico;
        this.uiCmdServicoPrestado = uiCmdServicoPrestado;
    }

    public void iniciar() {
        int opcao = 0;
        do {;
            menu();
             opcao = teclado.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Gerenciando Clientes ---");
                    uiCmdCliente.iniciar(); 
                    break;
                 case 2:
                    System.out.println("\n--- Gerenciando Pets ---");
                    uiCmdPet.iniciar(); 
                    break;
                 case 3:
                    System.out.println("\n--- Gerenciando Servicos ---");
                    uiCmdServico.iniciar(); 
                    break;
                 case 4:
                    System.out.println("\n--- Gerenciando Servicos Prestados ---");
                    //uiCmdServicoPrestado.iniciar(); 
                    break;
                case 0:
                    System.out.println("\nSaindo do sistema..."); 
                    break;
                default:
                   System.out.println("\nOpcao invalida! Tente novamente");
            }
            
        } while (opcao != 0);
    
    }

    public void menu(){
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║               PET SHOP               ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  1 - Clientes                        ║");
            System.out.println("║  2 - Pets                            ║");
            System.out.println("║  3 - Servicos                        ║");
            System.out.println("║  4 - Servicos Prestados              ║");
            System.out.println("║  0 - Sair                            ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");
    }
}
