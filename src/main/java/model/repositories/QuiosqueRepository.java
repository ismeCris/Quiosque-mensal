package model.repositories;

import model.entities.ClientesEntity;
import model.entities.QuiosqueEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class QuiosqueRepository implements  BasicCrud{

    EntityManager em = Persistence.createEntityManagerFactory("bancoQuiosque").createEntityManager();

    @Override
    public Object create(Object object) {
        try {
            QuiosqueEntity quiosque = (QuiosqueEntity) object;
            em.getTransaction().begin();
            em.persist(quiosque);
            em.getTransaction().commit();
            return quiosque; // Retorna o próprio objeto persistido
        } catch (Exception ex) {
            // Em caso de exceção, reverta a transação
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Lança uma exceção ou trata conforme necessário
            throw new RuntimeException("Erro ao criar o quiosque: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Object update(Object object) {
        QuiosqueEntity quiosqueUpdate = (QuiosqueEntity) object;
        em.getTransaction().begin();
        em.merge(quiosqueUpdate);
        em.getTransaction().commit();
        return quiosqueUpdate;
    }


    @Override
    public void delete(Long id) {
        em.getTransaction().begin();
        QuiosqueEntity quiosque = em.find(QuiosqueEntity.class, id); // Encontrar o QuiosqueEntity pelo ID
        if (quiosque != null) { // Verificar se o QuiosqueEntity foi encontrado
            em.remove(quiosque); // Remover o QuiosqueEntity encontrado
        } else {
            System.out.println("Quiosque não encontrado com o ID fornecido."); // Mensagem de erro se o QuiosqueEntity não for encontrado
        }
        em.getTransaction().commit();
    }


    public List<QuiosqueEntity> findAll(){
        return em.createQuery("SELECT f FROM QuiosqueEntity",QuiosqueEntity.class).getResultList();
    }

    @Override
    public Object findById(Object id) {
        try {
            QuiosqueEntity quiosqueInBD = em.find(QuiosqueEntity.class, id);
            return quiosqueInBD;
        } catch (Exception e) {

        }
        return null;
    }
}
