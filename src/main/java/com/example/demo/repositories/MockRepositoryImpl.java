package com.example.demo.repositories;

import com.example.demo.entities.*;
import com.example.demo.entities.enums.RoleEnum;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.UUID;

@Repository
public class MockRepositoryImpl implements MockRepository {

    @PersistenceContext
    EntityManager entityManager;

    public String generateRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    @Override
    @Transactional
    public void populateDB() throws SQLIntegrityConstraintViolationException {



        UsersEntity user1 = new UsersEntity();
        user1.setUsername("badra.alex");
        user1.setName("Alex Badra");
        user1.setPassword("122");
        user1.setRole(RoleEnum.ADMIN);
        user1.setCity("Bucuresti");
        entityManager.persist(user1);

        UsersEntity user2 = new UsersEntity();
        user2.setName("Andrei Buzgar");
        user2.setUsername("buzgar.andrei");
        user2.setPassword("123");
        user2.setCity("Cluj-Napoca");
        user2.setRole(RoleEnum.ADMIN);
        entityManager.persist(user2);

        UsersEntity user3 = new UsersEntity();
        user3.setName("Vlad Truta");
        user3.setUsername("truta.vlad");
        user3.setRole(RoleEnum.ADMIN);
        user3.setPassword("124");
        user3.setCity("Cluj-Napoca");
        entityManager.persist(user3);

        UsersEntity user4 = new UsersEntity();
        user4.setName("Marcu Marian");
        user4.setUsername("marian.marcu");
        user4.setPassword("125");
        user4.setCity("Cluj-Napoca");
        user4.setRole(RoleEnum.STATION_OWNER);
        entityManager.persist(user4);

        UsersEntity user5 = new UsersEntity();
        user5.setName("Tudor Rotaru");
        user5.setUsername("rotaru.tudor");
        user5.setCity("Budapest");
        user5.setPassword("126");
        user5.setRole(RoleEnum.BASIC_USER);
        entityManager.persist(user5);

        UsersEntity user6 = new UsersEntity();
        user6.setName("Rob Munceanu");
        user6.setUsername("rob");
        user6.setPassword("127");
        user6.setCity("Cluj-Napoca");
        user6.setRole(RoleEnum.PREMIUM_USER);
        entityManager.persist(user6);

        CompaniesEntity company1 = new CompaniesEntity();
        company1.setName("BMW");
        entityManager.persist(company1);

        CompaniesEntity company2 = new CompaniesEntity();
        company2.setName("Audi");
        entityManager.persist(company2);

        CompaniesEntity company3 = new CompaniesEntity();
        company3.setName("Tesla");
        entityManager.persist(company3);

        CompaniesEntity company4 = new CompaniesEntity();
        company4.setName("Honda");
        entityManager.persist(company4);

        CarsEntity car1 = new CarsEntity();
        car1.setModel("I8");
        car1.setIdCompany(company1);
        car1.setChargingTime("4h-5h AC / 30min DC");
        car1.setAutonomy("150km");
        entityManager.persist(car1);

        CarsEntity car2 = new CarsEntity();
        car2.setModel("R8 e-tron");
        car2.setIdCompany(company2);
        car2.setAutonomy("140km");
        car2.setChargingTime("~4h AC / 20-30min DC");
        entityManager.persist(car2);

        CarsEntity car3 = new CarsEntity();
        car3.setModel("S Model");
        car3.setIdCompany(company3);
        car3.setChargingTime("10h-12h AC / 30min DC");
        car3.setAutonomy("383km");
        entityManager.persist(car3);

        CarsEntity car4 = new CarsEntity();
        car4.setModel("Honda Clarity 2019");
        car4.setIdCompany(company4);
        car4.setChargingTime("4h-6h 240V / 30-40min DC");
        car4.setAutonomy("246km");
        entityManager.persist(car4);

        CarsEntity car5 = new CarsEntity();
        car5.setModel("Model X");
        car5.setIdCompany(company3);
        car5.setAutonomy("230km");
        car5.setChargingTime("7.5h AC / 30-40 min DC");
        entityManager.persist(car5);

        StationsEntity station1 = new StationsEntity();
        station1.setPhotos("photo 1");
        station1.setPrice(20);
        station1.setName("Memo");
        station1.setAddress("Memo, nr. 14");
        station1.setCity("Cluj-Napoca");
        station1.setLng(23.5972907);
        station1.setLat(46.7703884);
        station1.setAccuracy(2);
        //station1.setCompany(company3);
        entityManager.persist(station1);
        StationsEntity savedStation1 = entityManager.find(StationsEntity.class,1L);

        StationsEntity station2 = new StationsEntity();
        station2.setCity("Cluj-Napoca");
        station2.setName("Eroilor");
        station2.setAddress("Eroilor, nr.44");
        station2.setPrice(35);
        station2.setPhotos("photo 2");
        station2.setAccuracy(3);
        station2.setLng(23.6038855);
        station2.setLat(46.7714372);
        //station2.setCompany(company2);
        entityManager.persist(station2);
        StationsEntity savedStation2 = entityManager.find(StationsEntity.class,2L);


        StationsEntity station3 = new StationsEntity();
        station3.setName("Dristor");
        station3.setAddress("Dristor, nr.21");
        station3.setPhotos("photo 3");
        station3.setPrice(45);
        station3.setCity("Bucuresti");
        station3.setLat(44.4256856);
        station3.setLng(26.0931378);
        station3.setAccuracy(2.5);
        //station3.setCompany(company4);
        entityManager.persist(station3);
        StationsEntity savedStation3 = entityManager.find(StationsEntity.class,3L);

        PowerUnitEntity powerUnit1 = new PowerUnitEntity();
        powerUnit1.setPower(35);
        powerUnit1.setFastCharge(true);
        powerUnit1.setStationEntity(savedStation1);
        powerUnit1.setAvailable(true);
        powerUnit1.setDescription("aceasta unitate are o capacitate de 35 wati, kilowati, ma rog, e la fel pt mine");
        entityManager.persist(powerUnit1);

        PowerUnitEntity powerUnit2 = new PowerUnitEntity();
        powerUnit2.setDescription("aceasta unitate are o capacitate maxima de 45 wati/kW");
        powerUnit2.setStationEntity(savedStation1);
        powerUnit2.setAvailable(true);
        powerUnit2.setFastCharge(true);
        powerUnit2.setPower(45);
        entityManager.persist(powerUnit2);

        PowerUnitEntity powerUnit3 = new PowerUnitEntity();
        powerUnit3.setPower(25);
        powerUnit3.setDescription("aceasta statie are putere maxima de 25 de wati/KW");
        powerUnit3.setFastCharge(false);
        powerUnit3.setAvailable(true);
        powerUnit3.setStationEntity(savedStation3);
        entityManager.persist(powerUnit3);

        PowerUnitEntity powerUnit4 = new PowerUnitEntity();
        powerUnit4.setDescription("aceasta unitate are 30 maxim");
        powerUnit4.setStationEntity(savedStation2);
        powerUnit4.setFastCharge(true);
        powerUnit4.setAvailable(true);
        powerUnit4.setPower(30);
        entityManager.persist(powerUnit4);

        PowerUnitEntity powerUnit5 = new PowerUnitEntity();
        powerUnit5.setDescription("asta are 35 maxim");
        powerUnit5.setPower(35);
        powerUnit5.setFastCharge(true);
        powerUnit5.setAvailable(true);
        powerUnit5.setStationEntity(savedStation3);
        entityManager.persist(powerUnit5);

        ContactFormCategoriesEntity category1 = new ContactFormCategoriesEntity();
        category1.setTypeOfCategory("complaints");
        entityManager.persist(category1);
        ContactFormCategoriesEntity savedCategory1 = entityManager.find(ContactFormCategoriesEntity.class,1L);

        ContactFormCategoriesEntity category2 = new ContactFormCategoriesEntity();
        category2.setTypeOfCategory("adding stations in db");
        entityManager.persist(category2);
        ContactFormCategoriesEntity savedCategory2 = entityManager.find(ContactFormCategoriesEntity.class,2L);

        ContactFormMessagesEntity message1 = new ContactFormMessagesEntity();
        message1.setContactFormCategoriesEntity(savedCategory1);
        message1.setMessageOfContactForm("i was insulted by the owner station of the station 1 in db, from Eroilor ");

        ContactFormMessagesEntity message2 = new ContactFormMessagesEntity();
        message2.setContactFormCategoriesEntity(savedCategory2);
        message2.setMessageOfContactForm(" i want to add my station to your db. i have 50 wats maximum power, " +
                "is named Dorobantilor, and the address is:Dorobantilor, nr.69");
        entityManager.persist(message2);

        ChargerequestsEntity request = new ChargerequestsEntity();
        request.setUsersEntity1(user3);
        request.setMessage("i'm  stuck near Porsche's centre near Fsega");
        entityManager.persist(request);

    }
}
