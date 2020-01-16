package com.example.demo.repositories;

import com.example.demo.entities.Region;
import com.example.demo.request.RegionRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class RegionRepositoryImpl implements RegionRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void addRegion(RegionRequest regionRequest) throws Exception {

        Region regionEntity = new Region();
        regionEntity.setCity(regionRequest.getCity());
        regionEntity.setCountry(regionRequest.getCountry());

        entityManager.persist(regionEntity);


    }

    @Override
    @Transactional
    public void deleteRegion(RequestWithIdOnly requestWithIdOnly) throws Exception {

        Region region = entityManager.find(Region.class,requestWithIdOnly.getId());

        entityManager.remove(region);
    }

    @Override
    @Transactional
    public void updateRegion(RegionRequest regionRequest) throws Exception {

        Region region = entityManager.find(Region.class,regionRequest.getId());

        if(regionRequest.getCity() != null)
            region.setCity(regionRequest.getCity());

        if(regionRequest.getCountry() != null)
            region.setCountry(regionRequest.getCountry());

        entityManager.merge(region);

    }

    @Override
    @Transactional
    public List<Region> getRegions() throws Exception {
        Query query = entityManager.createQuery(" select regions from Region regions ",Region.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Region getRegionById(RequestWithIdOnly requestWithIdOnly) throws Exception {

        Query query = entityManager.createQuery(" select region from Region region where region.id = :id ",Region.class);
        query.setParameter("id",requestWithIdOnly.getId());

        return (Region) query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public Region getRegion(RegionRequest regionRequest) throws Exception {

        Query query = entityManager.createQuery(" select region from Region region where region.city = :city and region.country = :country ", Region.class);
        query.setParameter("city",regionRequest.getCity());
        query.setParameter("country",regionRequest.getCountry());

        if(query.getResultList() != null && !query.getResultList().isEmpty())
            return (Region) query.getSingleResult();
        return null;
    }

    @Override
    @Transactional
    public List<Region> getRegionsOfACountry(RegionRequest regionRequest) throws Exception {
        Query query = entityManager.createQuery(" select regions from Region  regions where regions.country = :country ",Region.class);

        query.setParameter("country",regionRequest.getCountry());

        return  query.getResultList();

    }
}
