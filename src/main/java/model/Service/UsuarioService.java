package model.Service;

import model.Entity.Usuario;
import model.Repository.UsuarioRepository;

import javax.persistence.EntityManager;

public class UsuarioService {


    public static Usuario login(String nome, String senha){
        Usuario user = UsuarioRepository.login(nome, senha);
        if(user !=null){
            System.out.println("Login bem-sucedido para o usuario:" + nome);
        }else {
            System.out.println("falha ao realizar login");
        }

        return user;
    }
}
