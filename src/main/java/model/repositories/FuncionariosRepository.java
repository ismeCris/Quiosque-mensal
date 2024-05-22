package model.repositories;

import model.entities.FuncionariosEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class FuncionariosRepository implements BasicCrud{
    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();


    public FuncionariosEntity login(String nome, String senha){
        try {
            System.out.println("Tentando autenticar usuário: " + nome);
            FuncionariosEntity funcionario = em.createQuery("SELECT f FROM FuncionariosEntity f WHERE f.nome = :nome AND f.senha = :senha", FuncionariosEntity.class)
                    .setParameter("nome", nome)
                    .setParameter("senha", senha)
                    .getSingleResult();
            return funcionario;
        } catch (Exception e) {
           ;
            return null;
        }
    }

    @Override
    public Object create(Object object) {
        FuncionariosEntity funcionarios1 = (FuncionariosEntity) object;
        em.getTransaction().begin();
        em.persist(funcionarios1);
        em.getTransaction().commit();
        return findById(funcionarios1.getId());
    }

    @Override
    public Object update(Object object) {
        FuncionariosEntity funcionarioUpfate = (FuncionariosEntity) object;
        em.getTransaction().begin();
        em.merge(funcionarioUpfate);
        em.getTransaction().commit();
        return null;
    }

    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        var funcionario = (FuncionariosEntity) findById(id);
        em.remove(funcionario);
        em.getTransaction().commit();
    }

    public List<FuncionariosEntity> findAll(){
       // System.out.println("teste");
       // return new ArrayList<FuncionariosEntity>();
        return em.createQuery("SELECT f FROM FuncionariosEntity f", FuncionariosEntity.class).getResultList();
    }

    @Override
    public Object findById(Object id) {
        try {
            FuncionariosEntity funcionarioInBd = em.find(FuncionariosEntity.class, id);
            return funcionarioInBd;
        } catch (Exception e) {

        }
        return null;
    }


}
