package controller;

import model.entities.ClientesEntity;
import model.entities.FuncionariosEntity;
import model.service.FuncionariosService;

public class FuncionarioController {
    private FuncionariosService funcionariosService = new FuncionariosService();


    public FuncionariosEntity findFuncionarioById(Long id) {
        return funcionariosService.findPessoasById(id);
    }
    public FuncionariosEntity login(String nome, String senha) {
        return funcionariosService.login(nome, senha);
    }
    public void updateFuncionario(FuncionariosEntity funcionario) {
        funcionariosService.updateFuncionario(funcionario);
    }

    public void deleteFuncionarioById(Long id) {
        funcionariosService.deleteFuncionario(id);
    }

    public FuncionariosEntity createFuncionario(FuncionariosEntity funcionario) {
        return funcionariosService.createFuncionario(funcionario);
    }


}
