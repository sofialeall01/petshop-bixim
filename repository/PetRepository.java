package repository;

import model.Cliente;
import model.Pet;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class PetRepository
{
    private ArrayList<Pet> pets;

    public PetRepository(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public void adicionar(Pet pet){
        this.pets.add(pet);
    }

    public ArrayList<Pet> listar(){
        return pets;
    }

    public Pet buscarPorId(int id){
        if(id != 0){
            for (int i = 0; i < pets.size();i++){
                if (pets.get(i).getId() == id){
                    return pets.get(i);
                }
            }
        }
        return null;
    }

    public boolean remover(int id){
        if(id != 0){
            for (int i = 0; i < pets.size();i++){
                if (pets.get(i).getId() == id){
                    Pet p = pets.get(i);
                    pets.remove(p);
                    return true;
                }
            }
        }
        return false;
    }

    public void carregarDados(String caminhoArquivo, ClienteRepository clienteRepository) {

        File arquivo = new File(caminhoArquivo);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try (Scanner scanner = new Scanner(arquivo, "UTF-8")) {

            while (scanner.hasNextLine()) {

                String linha = scanner.nextLine();
                String[] dados = linha.split(",");

                int id = Integer.parseInt(dados[0].trim());
                String nome = dados[1].trim();
                String especie = dados[2].trim();
                String raca = dados[3].trim();

                LocalDate nascimento = LocalDate.parse(dados[4].trim(), formato);

                int idCliente = Integer.parseInt(dados[5].trim());

                Cliente cliente = clienteRepository.buscarPorId(idCliente);

                if (cliente != null) {

                    Pet pet = new Pet(id, nome, especie, raca, cliente, nascimento);

                    adicionar(pet);

                } else {
                    System.out.println("Cliente " + idCliente + " não encontrado!");
                }
            }

            System.out.println("Dados carregados com sucesso!");

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());

        } catch (DateTimeParseException e) {
            System.out.println("Erro no formato de data: " + e.getMessage());
        }
    }
}
