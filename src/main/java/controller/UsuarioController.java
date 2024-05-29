package controller;

import model.Entity.Usuario;
import model.Service.UsuarioService;

public class UsuarioController {



    public static Usuario login(String nome, String senha){
        return UsuarioService.login(nome, senha);
    }
}
