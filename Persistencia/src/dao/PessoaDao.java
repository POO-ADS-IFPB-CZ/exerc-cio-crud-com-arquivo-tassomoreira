package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    private File file;

    public PessoaDao() {
        file = new File("pessoa.ser");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<Pessoa> getPessoas() {
        if(file.length() > 0) {
            try {
                FileInputStream inputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
            } catch(FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch(IOException e) {
                System.out.println("Falha ao ler arquivo");
            } catch(ClassNotFoundException e) {
                System.out.println("Falha ao ler arquivo");
            }
        }

        return new HashSet<>();
    }

    public boolean salvar(Pessoa pessoa) {
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch(FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch(IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public void listar() {
        Set<Pessoa> pessoas = getPessoas();
        System.out.println();
        System.out.println("--------------- LISTA DE PESSOAS --------------");
        for(Pessoa pessoa : pessoas) {
            System.out.println("|\tNome: " + pessoa.getName());
            System.out.println("|\tE-mail: " + pessoa.getEmail());
            System.out.println("|");
        }
        System.out.println("-----------------------------------------------");
    }

    public Pessoa buscaPorEmail(String email) {
        Set<Pessoa> pessoas = getPessoas();
        for(Pessoa pessoa : pessoas) {
            if(pessoa.getEmail().equals(email)) return pessoa;
        }
        return null;
    }

    public boolean deletar(String email) {
        Set<Pessoa> pessoas = getPessoas();
        Pessoa pessoa = buscaPorEmail(email);

        if(pessoas.remove(pessoa)) {
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch(IOException e) {
                System.out.println("Falha ao escrever em arquivo");
            }
        }
        return false;
    }
}
