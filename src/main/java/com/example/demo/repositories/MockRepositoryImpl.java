package com.example.demo.repositories;

import com.example.demo.entities.*;
import com.example.demo.entities.enums.RoleEnum;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MockRepositoryImpl implements MockRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void populate() {

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
        company1.setName("Audi");
        entityManager.persist(company1);

        CompaniesEntity company2 = new CompaniesEntity();
        company2.setName("BMW");
        entityManager.persist(company2);

        CompaniesEntity company3 = new CompaniesEntity();
        company3.setName("Bollore");
        entityManager.persist(company3);

        CompaniesEntity company4 = new CompaniesEntity();
        company4.setName("BYD");
        entityManager.persist(company4);

        CompaniesEntity company5 = new CompaniesEntity();
        company5.setName("Chery");
        entityManager.persist(company5);

        CompaniesEntity company6 = new CompaniesEntity();
        company6.setName("Chevrolet");
        entityManager.persist(company6);

        CompaniesEntity company7 = new CompaniesEntity();
        company7.setName("Citroen");
        entityManager.persist(company7);

        CompaniesEntity company8 = new CompaniesEntity();
        company8.setName("Courb");
        entityManager.persist(company8);

        CompaniesEntity company9 = new CompaniesEntity();
        company9.setName("ElectraMeccanica");
        entityManager.persist(company9);

        CompaniesEntity company10 = new CompaniesEntity();
        company10.setName("Fiat");
        entityManager.persist(company10);

        CompaniesEntity company11 = new CompaniesEntity();
        company11.setName("Ford");
        entityManager.persist(company11);

        CompaniesEntity company12 = new CompaniesEntity();
        company12.setName("Honda");
        entityManager.persist(company12);

        CompaniesEntity company13 = new CompaniesEntity();
        company13.setName("Hyundai");
        entityManager.persist(company13);

        CompaniesEntity company14 = new CompaniesEntity();
        company14.setName("Jaguar");
        entityManager.persist(company14);

        CompaniesEntity company15 = new CompaniesEntity();
        company15.setName("Kewet");
        entityManager.persist(company15);

        CompaniesEntity company16 = new CompaniesEntity();
        company16.setName("Kia");
        entityManager.persist(company16);

        CompaniesEntity company17 = new CompaniesEntity();
        company17.setName("Mahindra");
        entityManager.persist(company17);

        CompaniesEntity company18 = new CompaniesEntity();
        company18.setName("Mercedez-Benz");
        entityManager.persist(company18);

        CompaniesEntity company19 = new CompaniesEntity();
        company19.setName("Micro-Mobility Systems");
        entityManager.persist(company19);

        CompaniesEntity company20 = new CompaniesEntity();
        company20.setName("Mitsubishi");
        entityManager.persist(company20);

        CompaniesEntity company21 = new CompaniesEntity();
        company21.setName("MW Motors");
        entityManager.persist(company21);

        CompaniesEntity company22 = new CompaniesEntity();
        company22.setName("NIO");
        entityManager.persist(company22);

        CompaniesEntity company23 = new CompaniesEntity();
        company23.setName("Nissan");
        entityManager.persist(company23);

        CompaniesEntity company24 = new CompaniesEntity();
        company24.setName("Peugeot");
        entityManager.persist(company24);

        CompaniesEntity company25 = new CompaniesEntity();
        company25.setName("Renault");
        entityManager.persist(company25);

        CompaniesEntity company26 = new CompaniesEntity();
        company26.setName("Seat");
        entityManager.persist(company26);

        CompaniesEntity company27 = new CompaniesEntity();
        company27.setName("Skoda");
        entityManager.persist(company27);

        CompaniesEntity company28 = new CompaniesEntity();
        company28.setName("Smart");
        entityManager.persist(company28);

        CompaniesEntity company29 = new CompaniesEntity();
        company29.setName("Sono Motors");
        entityManager.persist(company29);

        CompaniesEntity company30 = new CompaniesEntity();
        company30.setName("Stevens");
        entityManager.persist(company30);

        CompaniesEntity company31 = new CompaniesEntity();
        company31.setName("Tesla");
        entityManager.persist(company31);

        CompaniesEntity company32 = new CompaniesEntity();
        company32.setName("Volkswagen");
        entityManager.persist(company32);


        CarsEntity car1 = new CarsEntity();
        car1.setModel("Audi e-tron 50");
        car1.setIdCompany(company1);
        car1.setChargingTime("8.5h AC / 30min DC");
        car1.setAutonomy("241km");
        entityManager.persist(car1);

        CarsEntity car2 = new CarsEntity();
        car2.setModel("Audi e-tron 55");
        car2.setIdCompany(company1);
        car2.setAutonomy("328km");
        car2.setChargingTime("8.5h AC / 30min DC");
        entityManager.persist(car2);

        CarsEntity car3 = new CarsEntity();
        car3.setModel("i3 (BEV 60 Ah)");
        car3.setIdCompany(company2);
        car3.setChargingTime("4h-6h 240V  / 30-40min DC");
        car3.setAutonomy("130km");
        entityManager.persist(car3);

        CarsEntity car4 = new CarsEntity();
        car4.setModel("i3 (BEV 94 Ah)");
        car4.setIdCompany(company2);
        car4.setChargingTime("4h-6h 240V / 30-40min DC");
        car4.setAutonomy("183km");
        entityManager.persist(car4);

        CarsEntity car5 = new CarsEntity();
        car5.setModel("i3 (BEV 120 Ah)");
        car5.setIdCompany(company2);
        car5.setAutonomy("246km");
        car5.setChargingTime("4h-6h 240V / 30-40min DC");
        entityManager.persist(car5);

        CarsEntity car6 = new CarsEntity();
        car6.setModel("Zinoro 1E");
        car6.setIdCompany(company2);
        car6.setAutonomy("150km");
        car6.setChargingTime("7.5h AC / 30-40 min DC");
        entityManager.persist(car6);

        CarsEntity car7 = new CarsEntity();
        car7.setModel("i8");
        car7.setIdCompany(company2);
        car7.setAutonomy("370km");
        car7.setChargingTime("2h-3h AC");
        entityManager.persist(car7);

        CarsEntity car8 = new CarsEntity();
        car8.setModel("Bluecar");
        car8.setIdCompany(company3);
        car8.setAutonomy("250km");
        car8.setChargingTime("~15h (regular AC charge)");
        entityManager.persist(car8);

        CarsEntity car9 = new CarsEntity();
        car9.setModel("e6");
        car9.setIdCompany(company4);
        car9.setAutonomy("300km");
        car9.setChargingTime("8h-9h AC");
        entityManager.persist(car9);

        CarsEntity car10 = new CarsEntity();
        car10.setModel("QQ3 EV");
        car10.setIdCompany(company5);
        car10.setAutonomy("100km");
        car10.setChargingTime("8h-10h AC");
        entityManager.persist(car10);

        CarsEntity car11 = new CarsEntity();
        car11.setModel("Bolt EV");
        car11.setIdCompany(company6);
        car11.setAutonomy("383km");
        car11.setChargingTime("10h-12h AC / 30min DC");
        entityManager.persist(car11);

        CarsEntity car12 = new CarsEntity();
        car12.setModel("Spark EV");
        car12.setIdCompany(company6);
        car12.setAutonomy("130km");
        car12.setChargingTime("8h AC / 30min DC");
        entityManager.persist(car12);

        CarsEntity car13 = new CarsEntity();
        car13.setModel("C-Zero");
        car13.setIdCompany(company7);
        car13.setAutonomy("150km");
        car13.setChargingTime("7h AC / 30min DC");
        entityManager.persist(car13);

        CarsEntity car14 = new CarsEntity();
        car14.setModel("C-ZEN");
        car14.setIdCompany(company8);
        car14.setAutonomy("130km");
        car14.setChargingTime("5h-7h AC");
        entityManager.persist(car14);

        CarsEntity car15 = new CarsEntity();
        car15.setModel("Solo");
        car15.setIdCompany(company9);
        car15.setAutonomy("161km");
        car15.setChargingTime("3h-6h AC");
        entityManager.persist(car15);

        CarsEntity car16 = new CarsEntity();
        car16.setModel("500e");
        car16.setIdCompany(company10);
        car16.setAutonomy("140km");
        car16.setChargingTime("~4h AC / 20-30min DC");
        entityManager.persist(car16);

        CarsEntity car17 = new CarsEntity();
        car17.setModel("Focus Electric");
        car17.setIdCompany(company11);
        car17.setAutonomy("185km");
        car17.setChargingTime("5.5h AC / 30min DC");
        entityManager.persist(car17);

        CarsEntity car18 = new CarsEntity();
        car18.setModel("Fit EV");
        car18.setIdCompany(company12);
        car18.setAutonomy("132km");
        car18.setChargingTime("~3h AC");
        entityManager.persist(car18);

        CarsEntity car19 = new CarsEntity();
        car19.setModel("Clarity Electric");
        car19.setIdCompany(company12);
        car19.setAutonomy("143km");
        car19.setChargingTime("~3.5h AC");
        entityManager.persist(car19);

        CarsEntity car20 = new CarsEntity();
        car20.setModel("Ioniq Electric");
        car20.setIdCompany(company13);
        car20.setAutonomy("250km");
        car20.setChargingTime("4h AC / 30min DC");
        entityManager.persist(car20);

        CarsEntity car21 = new CarsEntity();
        car21.setModel("Kona Electric");
        car21.setIdCompany(company13);
        car21.setAutonomy("470km");
        car21.setChargingTime("10.5h AC / 44 min DC");
        entityManager.persist(car21);

        CarsEntity car22 = new CarsEntity();
        car22.setModel("Jaguar I-Pace");
        car22.setIdCompany(company14);
        car22.setAutonomy("350km");
        car22.setChargingTime("13h AC / 40min-1h DC");
        entityManager.persist(car22);

        CarsEntity car23 = new CarsEntity();
        car23.setModel("Buddy");
        car23.setIdCompany(company15);
        car23.setAutonomy("80km");
        car23.setChargingTime("6h-8h AC");
        entityManager.persist(car23);

        CarsEntity car24 = new CarsEntity();
        car24.setModel("Soul EV");
        car24.setIdCompany(company16);
        car24.setAutonomy("150km");
        car24.setChargingTime("4h-5h AC / 30min DC");
        entityManager.persist(car24);

        StationsEntity station1 = new StationsEntity();
        station1.setPhotos("photo 1");
        station1.setPrice(20);
        station1.setName("Type1");
        station1.setAddress("Memo, nr. 14");
        station1.setCity("Cluj-Napoca");
        station1.setLng(23.5972907);
        station1.setLat(46.7703884);
        station1.setAccuracy(2);
        station1.setCompany(company3);
        entityManager.persist(station1);
        StationsEntity savedStation1 = entityManager.find(StationsEntity.class,1L);

        StationsEntity station2 = new StationsEntity();
        station2.setCity("Cluj-Napoca");
        station2.setName("Type2");
        station2.setAddress("Eroilor, nr.44");
        station2.setPrice(35);
        station2.setPhotos("photo 2");
        station2.setAccuracy(3);
        station2.setLng(23.6038855);
        station2.setLat(46.7714372);
        station2.setCompany(company2);
        entityManager.persist(station2);
        StationsEntity savedStation2 = entityManager.find(StationsEntity.class,2L);


        StationsEntity station3 = new StationsEntity();
        station3.setName("Type3");
        station3.setAddress("Dristor, nr.21");
        station3.setPhotos("photo 3");
        station3.setPrice(45);
        station3.setCity("Bucuresti");
        station3.setLat(44.4256856);
        station3.setLng(26.0931378);
        station3.setAccuracy(2.5);
        station3.setCompany(company4);
        entityManager.persist(station3);
        StationsEntity savedStation3 = entityManager.find(StationsEntity.class,3L);

        StationsEntity station4 = new StationsEntity();
        station4.setName("CCS");
        station4.setAddress("Pastorului, nr.21");
        station4.setPhotos("photo 7");
        station4.setPrice(40);
        station4.setCity("Cluj-Napoca");
        station4.setLat(46.7610211);
        station4.setLng(23.5764720);
        station4.setAccuracy(2.5);
        station4.setCompany(company6);
        entityManager.persist(station4);
        StationsEntity savedStation4 = entityManager.find(StationsEntity.class,4L);

        StationsEntity station5 = new StationsEntity();
        station5.setName("CHAdeMO");
        station5.setAddress("Piata Marasti");
        station5.setPhotos("photo 10");
        station5.setPrice(43);
        station5.setCity("Cluj-Napoca");
        station5.setLat(46.7789443);
        station5.setLng(23.6104581);
        station5.setAccuracy(2.5);
        station5.setCompany(company8);
        entityManager.persist(station5);
        StationsEntity savedStation5 = entityManager.find(StationsEntity.class,5L);

        StationsEntity station6 = new StationsEntity();
        station6.setName("Tesla Wall Connector");
        station6.setAddress("Aurel Vlaicu, nr.140");
        station6.setPhotos("photo 11");
        station6.setPrice(35);
        station6.setCity("Cluj-Napoca");
        station6.setLat(46.7764171);
        station6.setLng(23.6168306);
        station6.setAccuracy(2.5);
        station6.setCompany(company8);
        entityManager.persist(station6);
        StationsEntity savedStation6 = entityManager.find(StationsEntity.class,6L);


        StationsEntity station7 = new StationsEntity();
        station7.setName("Tesla Supercharger");
        station7.setAddress("Aurel Vlaicu, nr.140");
        station7.setPhotos("photo 11");
        station7.setPrice(35);
        station7.setCity("Cluj-Napoca");
        station7.setLat(46.7764171);
        station7.setLng(23.6168306);
        station7.setAccuracy(2.5);
        station7.setCompany(company8);
        entityManager.persist(station7);
        StationsEntity savedStation7 = entityManager.find(StationsEntity.class,6L);


        StationsEntity station8 = new StationsEntity();
        station8.setName("Household German Type");
        station8.setAddress("Aurel Vlaicu, nr.140");
        station8.setPhotos("photo 11");
        station8.setPrice(35);
        station8.setCity("Cluj-Napoca");
        station8.setLat(46.7764171);
        station8.setLng(23.6168306);
        station8.setAccuracy(2.5);
        station8.setCompany(company8);
        entityManager.persist(station8);
        StationsEntity savedStation8 = entityManager.find(StationsEntity.class,6L);

        PowerUnitEntity powerUnit1 = new PowerUnitEntity();
        powerUnit1.setDescription("7.5 kW");
        powerUnit1.setPower(35);
        powerUnit1.setFastCharge(false);
        powerUnit1.setAvailable(true);
        powerUnit1.setStationEntity(savedStation1);
        entityManager.persist(powerUnit1);

        PowerUnitEntity powerUnit2 = new PowerUnitEntity();
        powerUnit2.setDescription("22-43 kW");
        powerUnit2.setPower(35);
        powerUnit2.setFastCharge(false);
        powerUnit2.setAvailable(true);
        powerUnit2.setStationEntity(savedStation2);
        entityManager.persist(powerUnit2);

        PowerUnitEntity powerUnit3 = new PowerUnitEntity();
        powerUnit3.setDescription("22-45 kW");
        powerUnit3.setPower(35);
        powerUnit3.setFastCharge(false);
        powerUnit3.setAvailable(true);
        powerUnit3.setStationEntity(savedStation3);
        entityManager.persist(powerUnit3);

        PowerUnitEntity powerUnit4 = new PowerUnitEntity();
        powerUnit4.setDescription("50-170 kW");
        powerUnit4.setPower(35);
        powerUnit4.setFastCharge(true);
        powerUnit4.setAvailable(true);
        powerUnit4.setStationEntity(savedStation4);
        entityManager.persist(powerUnit4);

        PowerUnitEntity powerUnit5 = new PowerUnitEntity();
        powerUnit5.setDescription("50 kW");
        powerUnit5.setPower(35);
        powerUnit5.setFastCharge(false);
        powerUnit5.setAvailable(true);
        powerUnit5.setStationEntity(savedStation5);
        entityManager.persist(powerUnit5);

        PowerUnitEntity powerUnit6 = new PowerUnitEntity();
        powerUnit6.setDescription("11-16.5 kW");
        powerUnit6.setPower(35);
        powerUnit6.setFastCharge(false);
        powerUnit6.setAvailable(true);
        powerUnit6.setStationEntity(savedStation6);
        entityManager.persist(powerUnit6);

        PowerUnitEntity powerUnit7 = new PowerUnitEntity();
        powerUnit7.setDescription("150 kW");
        powerUnit7.setPower(35);
        powerUnit7.setFastCharge(true);
        powerUnit7.setAvailable(true);
        powerUnit7.setStationEntity(savedStation7);
        entityManager.persist(powerUnit7);

        PowerUnitEntity powerUnit8 = new PowerUnitEntity();
        powerUnit8.setDescription("3.7 kW");
        powerUnit8.setPower(35);
        powerUnit8.setFastCharge(false);
        powerUnit8.setAvailable(true);
        powerUnit8.setStationEntity(savedStation8);
        entityManager.persist(powerUnit8);


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
