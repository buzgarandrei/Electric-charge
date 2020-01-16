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

       /* int i;

        List<ContactFormCategoriesEntity> categoriesEntityList = new ArrayList<>();
        List<Region> regionList = new ArrayList<>();
        List<CompaniesEntity> companiesEntityList = new ArrayList<>();
        List<RoleEnum> roleEnumList = new ArrayList<>();
        roleEnumList.add(RoleEnum.ADMIN);
        roleEnumList.add(RoleEnum.BASIC_USER);
        roleEnumList.add(RoleEnum.PREMIUM_USER);
        roleEnumList.add(RoleEnum.STATION_OWNER);

        for (i = 0; i <= 6; i++) {

            ContactFormCategoriesEntity entity = new ContactFormCategoriesEntity();
            entity.setTypeOfCategory(RandomStringUtils.randomAlphabetic(13));
            entityManager.persist(entity);

            Region region = new Region();
            region.setCountry(RandomStringUtils.randomAlphabetic(12));
            region.setCity(RandomStringUtils.randomAlphabetic(11));
            entityManager.persist(region);

            CompaniesEntity company = new CompaniesEntity();
            company.setName(RandomStringUtils.randomAlphabetic(7));
            entityManager.persist(company);

            categoriesEntityList.add(entity);
            regionList.add(region);
            companiesEntityList.add(company);

            i++;
        }

        List<CarsEntity> carsEntityList = new ArrayList<>();
        List<StationsEntity> stationsEntityList = new ArrayList<>();
        List<UsersEntity> usersEntityList = new ArrayList<>();

        for (i = 0; i <= 100; i++) {

            Random random = new Random();


            CarsEntity carsEntity = new CarsEntity();
            carsEntity.setMinutesForFastCharge((float) Math.random());
            carsEntity.setMinutesForNormalCharge((float) Math.random());
            carsEntity.setModel(RandomStringUtils.randomAlphabetic(10));
            carsEntity.setIdCompany(companiesEntityList.get(random.nextInt(companiesEntityList.size())));
            carsEntityList.add(carsEntity);
            entityManager.persist(carsEntity);

            StationsEntity stationsEntity = new StationsEntity();
            stationsEntity.setRegion(regionList.get(random.nextInt(regionList.size())));
            stationsEntity.setAddress(RandomStringUtils.randomAlphabetic(8));
            stationsEntity.setName(RandomStringUtils.randomAlphabetic(5));
            stationsEntity.setPrice((float) Math.random());
            stationsEntity.setPhotos(RandomStringUtils.randomAlphabetic(9));
            stationsEntityList.add(stationsEntity);
            entityManager.persist(stationsEntity);

            PowerUnitEntity powerUnitEntity = new PowerUnitEntity();
            powerUnitEntity.setFastCharge(random.nextBoolean());
            powerUnitEntity.setStationEntity(stationsEntity);
            powerUnitEntity.setDescription(RandomStringUtils.randomAlphabetic(7));
            powerUnitEntity.setPower((float) Math.random());
            entityManager.persist(powerUnitEntity);

            ContactFormMessagesEntity contactFormMessagesEntity = new ContactFormMessagesEntity();
            contactFormMessagesEntity.setMessageOfContactForm(RandomStringUtils.randomAlphabetic(234));
            contactFormMessagesEntity.setState(random.nextInt());
            entityManager.persist(contactFormMessagesEntity);

            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setRegion(regionList.get(random.nextInt(regionList.size())));
            usersEntity.setRole(roleEnumList.get(random.nextInt(roleEnumList.size())));
            usersEntity.setName(RandomStringUtils.randomAlphabetic(18));
            usersEntity.setPassword(RandomStringUtils.randomAlphabetic(8));
            usersEntity.setUsername(RandomStringUtils.randomAlphabetic(12));

            List<CarsEntity> carsForUser = usersEntity.getCarsEntityList();
            for (int j = 0; j < random.nextInt(carsEntityList.size()); j++) {
                carsForUser.add(carsEntityList.get(random.nextInt(carsEntityList.size())));
            }

            List<StationsEntity> favourites = usersEntity.getFavourites();
            for (int j = 0; j < random.nextInt(stationsEntityList.size()); j++) {
                favourites.add(stationsEntityList.get(random.nextInt(stationsEntityList.size())));
            }
            usersEntityList.add(usersEntity);
            entityManager.persist(usersEntity);
        }

        List<ReadEnum> readEnumList = new ArrayList<>();
        readEnumList.add(ReadEnum.RESPONDED);
        readEnumList.add(ReadEnum.NOT_RESPONDED);

        Random random = new Random();

        for (i = 0; i < 1000; i++) {
            ChargerequestsEntity chargerequestsEntity = new ChargerequestsEntity();
            chargerequestsEntity.setMessage(RandomStringUtils.randomAlphabetic(200));
            chargerequestsEntity.setReadOrNot(readEnumList.get(random.nextInt(readEnumList.size())));
            chargerequestsEntity.setUsersEntity1(usersEntityList.get(random.nextInt(usersEntityList.size())));
            chargerequestsEntity.setUserEntity2(usersEntityList.get(random.nextInt(usersEntityList.size())));
            entityManager.persist(chargerequestsEntity);
        }

        System.out.println("I'm a fucking bad-ass :D");

    }*/

        Region region1 = new Region();
        region1.setCity("Cluj-Napoca");
        region1.setCountry("Romania");
        entityManager.persist(region1);

        Region region2 = new Region();
        region2.setCountry("Romania");
        region2.setCity("Bucharest");
        entityManager.persist(region2);

        Region region3 = new Region();
        region3.setCountry("Hungary");
        region3.setCity("Budapest");
        entityManager.persist(region3);

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
