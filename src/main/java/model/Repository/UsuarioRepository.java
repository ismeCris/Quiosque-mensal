package model.Repository;

import model.Entity.Usuario;

import javax.persistence.*;

public class UsuarioRepository  implements  BasicCrud{

   static EntityManager em = Persistence.createEntityManagerFactory("BancoPie").createEntityManager();



    @Override
    public Object create(Object object) {
        return null;
    }

    @Override
    public Object update(Object object) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Object findById(Object id) {
        return null;
    }

    public static Usuario login(String nome, String senha){
        try{
            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE u.nome = :nome AND u.senha = :senha", Usuario.class);
            query.setParameter("nome", nome);
            query.setParameter("senha", senha);
            return query.getSingleResult();

        } catch (NoResultException e) {
            System.out.println("Nenhum usuário encontrado com o nome e senha fornecidos.");
            return null;
        } catch (NonUniqueResultException e) {
            System.out.println("Mais de um usuário encontrado com o mesmo nome e senha. Isso não deveria acontecer.");
            return null;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao tentar fazer login: " + e.getMessage());
            return null;
        }
    }

}
