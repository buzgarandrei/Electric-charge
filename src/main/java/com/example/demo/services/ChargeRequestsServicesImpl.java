package com.example.demo.services;

import com.example.demo.entities.ChargerequestsEntity;
import com.example.demo.repositories.ChargerequestRepository;
import com.example.demo.request.ChargerequestRequest;
import com.example.demo.request.specialRequests.RequestWithIdOnly;
import com.example.demo.response.ChargerequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChargeRequestsServicesImpl implements ChargeRequestsServices {

    @Autowired
    ChargerequestRepository chargerequestRepository;

    @Override
    public List<ChargerequestResponse> findAll() {

        List<ChargerequestResponse> chargerequestResponseList = new ArrayList<>();
        List<ChargerequestsEntity> chargerequestsEntityList =  chargerequestRepository.findAll();

        for ( ChargerequestsEntity entity : chargerequestsEntityList) {

            ChargerequestResponse chargerequestResponse = new ChargerequestResponse();
            chargerequestResponse.setMessage(entity.getMessage());
            chargerequestResponse.setReadOrNot(entity.getReadOrNot());
            try {
                chargerequestResponse.setIdUserEntity1(entity.getUsersEntity1().getId());

                if(entity.getUserEntity2()!= null)
                    chargerequestResponse.setIdUserEntity2(entity.getUserEntity2().getId());

                chargerequestResponse.setId(entity.getId());
            }
            catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Bad id's");
            }
            chargerequestResponseList.add(chargerequestResponse);
        }

        return chargerequestResponseList;
    }

    @Override
    public void addChargerequest(ChargerequestRequest chargerequestRequest) throws Exception {

        ChargerequestsEntity chargerequestsEntity = new ChargerequestsEntity();
        chargerequestsEntity.setMessage(chargerequestRequest.getMessage());
        chargerequestsEntity.setReadOrNot(chargerequestRequest.getReadOrNot());

        chargerequestRepository.addChargerequest(chargerequestRequest);
    }

    @Override
    public void updateChargerequest(ChargerequestRequest chargerequestRequest) throws Exception {

        chargerequestRepository.updateChargerequest(chargerequestRequest);
    }

    @Override
    public void deleteChargerequest(RequestWithIdOnly id) throws Exception {

        chargerequestRepository.deleteChargerequest(id);
    }

    @Override
    public ChargerequestResponse getRequestById(RequestWithIdOnly request) {

        return chargerequestRepository.getRequestById(request);
    }
}
