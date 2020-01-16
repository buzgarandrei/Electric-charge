package com.example.demo.repositories;

import com.example.demo.entities.ChargerequestsEntity;
import com.example.demo.entities.UsersEntity;
import com.example.demo.entities.enums.ReadEnum;
import com.example.demo.request.ChargerequestRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ChargerequestResponse;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ChargerequestRepositoryImpl implements ChargerequestRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<ChargerequestsEntity> findAll() {

        Query query = entityManager.createQuery("select cr from ChargerequestsEntity cr", ChargerequestsEntity.class);
        List<ChargerequestsEntity> resultList = query.getResultList();

        return resultList;
    }

    @Override
    @Transactional
    public void addChargerequest(ChargerequestRequest chargerequestRequest) throws Exception {

        ChargerequestsEntity chargerequestsEntity = new ChargerequestsEntity();
        chargerequestsEntity.setMessage(chargerequestRequest.getMessage());
        chargerequestsEntity.setReadOrNot(ReadEnum.NOT_RESPONDED);
        try {
            UsersEntity entity1 = entityManager.find(UsersEntity.class,chargerequestRequest.getIdUserEntity());
            chargerequestsEntity.setUsersEntity1(entity1);
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("bad id's");
        }
        entityManager.persist(chargerequestsEntity);
    }

    @Override
    @Transactional
    public void updateChargerequest(ChargerequestRequest chargerequestRequest) throws Exception {

        ChargerequestsEntity chargerequestsEntity = entityManager.find(ChargerequestsEntity.class,chargerequestRequest.getId());

        if(chargerequestRequest.getMessage() != null)

            chargerequestsEntity.setMessage(chargerequestRequest.getMessage());

        if(chargerequestRequest.getReadOrNot() != null)

            chargerequestsEntity.setReadOrNot(chargerequestRequest.getReadOrNot());

        try {
            if(chargerequestRequest.getIdUserEntity() != null) {
                UsersEntity entity1 = entityManager.find(UsersEntity.class,chargerequestRequest.getIdUserEntity());
                chargerequestsEntity.setUsersEntity1(entity1);
            }

            if(chargerequestRequest.getIdUserEntity2() != null) {
                UsersEntity entity2 = entityManager.find(UsersEntity.class,chargerequestRequest.getIdUserEntity2());
                chargerequestsEntity.setUserEntity2(entity2);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            System.out.println("bad user id's");
        }
        entityManager.merge(chargerequestsEntity);
    }

    @Override
    @Transactional
    public void deleteChargerequest(RequestWithIdOnly id) throws Exception {

        try {
            ChargerequestsEntity entity = entityManager.find(ChargerequestsEntity.class,id.getId());
            entityManager.remove(entity);

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad charge request id");
        }
    }

    @Override
    @Transactional
    public ChargerequestResponse getRequestById(RequestWithIdOnly request) {

        ChargerequestsEntity entity = entityManager.find(ChargerequestsEntity.class,request.getId());
        ChargerequestResponse response = new ChargerequestResponse();

        response.setId(entity.getId());
        response.setReadOrNot(entity.getReadOrNot());
        response.setMessage(entity.getMessage());
        if(entity.getUserEntity2() != null) response.setIdUserEntity2(entity.getUserEntity2().getId());

        response.setIdUserEntity1(entity.getUsersEntity1().getId());

        return response;
    }
}
