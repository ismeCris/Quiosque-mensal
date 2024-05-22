package model.service;

import model.entities.FuncionariosEntity;
import model.repositories.FuncionariosRepository;

import java.util.List;

public class FuncionariosService {
    private FuncionariosRepository funcionariosRepository = new FuncionariosRepository();

    public FuncionariosEntity findPessoasById(Long id) {
        return (FuncionariosEntity) funcionariosRepository.findById(id);
    }

    public List<FuncionariosEntity> findAll() {
        return funcionariosRepository.findAll();
    }

    public FuncionariosEntity login(String username, String password) {
        FuncionariosEntity funcionario = funcionariosRepository.login(username, password);
        if (funcionario != null) {
            System.out.println("Login bem-sucedido para o usuário: " + username);
        } else {
            System.out.println("Falha no login para o usuário: " + username);
        }
        return funcionario;
    }
    public void updateFuncionario(FuncionariosEntity funcionario) {
        funcionariosRepository.update(funcionario);
    }

    public void deleteFuncionario(Long id) {
        funcionariosRepository.delete(id);
    }


    public FuncionariosEntity createFuncionario(FuncionariosEntity funcionario) {
        return (FuncionariosEntity) funcionariosRepository.create(funcionario);
    }

}
