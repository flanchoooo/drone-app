package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DispatchDetailsDto;
import com.musala.droneapp.dto.DispatchRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.DispatchItem;
import com.musala.droneapp.model.Drone;
import com.musala.droneapp.model.Medication;
import com.musala.droneapp.repository.DispatchItemRepository;
import com.musala.droneapp.repository.DispatchRepository;
import com.musala.droneapp.repository.DroneRepository;
import com.musala.droneapp.repository.MedicationRepository;
import com.musala.droneapp.service.DispatchService;
import com.musala.droneapp.utils.DroneStateEnum;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class DispatchServiceImpl implements DispatchService {

    private final DroneRepository droneRepository;

    private final DispatchRepository dispatchRepository;

    private final DispatchItemRepository dispatchItemRepository;

    private final MedicationRepository medicationRepository;
    @Override
    @Transactional
    public Dispatch dispatch(DispatchRequestDto dispatchRequestDto) {
       var drone =  droneRepository.findBySerialNumber(dispatchRequestDto.getDroneSerial());
       if(drone==null){
           throw new NotFoundException("drone");
       }


       if(drone.getBatteryLevel() < 25){
           throw new InvalidTypeException("Please recharge drone up to 26% to dispatch it.");
       }

        if(drone.getState() != DroneStateEnum.IDLE){
            throw new InvalidTypeException("Drone not available for dispatch. It is currently " + drone.getState());
        }


        Double totalWeight = 0.0;
        for (int id : dispatchRequestDto.getMedicationIds()) {
            var weightMedication = medicationRepository.findById(id).orElseThrow(()-> new NotFoundException("medication with ID:" + id));
            totalWeight+=weightMedication.getWeight();
        }

        if(totalWeight > drone.getWeightLimit()) {
            throw new InvalidTypeException("Maximum weight exceeded, allowed weight is " + drone.getWeightLimit());
        }

        Dispatch dispatch = new Dispatch();
        dispatch.setDrone(drone);
        dispatch.setState("DELIVERING");
        dispatch.setDestination(dispatchRequestDto.getDestination());
        dispatchRepository.save(dispatch);

        for (int id : dispatchRequestDto.getMedicationIds()) {
            var weightMedication = medicationRepository.findById(id).orElseThrow(()-> new NotFoundException("medication with ID:" + id));
            DispatchItem dispatchItem = new DispatchItem();
            dispatchItem.setDispatch(dispatch);
            dispatchItem.setMedication(weightMedication);
            dispatchItemRepository.save(dispatchItem);
        }
        drone.setState(DroneStateEnum.valueOf("DELIVERING"));
        droneRepository.save(drone);
        return  dispatch;
    }

    @Override
    public DispatchDetailsDto droneCargo(String serialNumber){
        var drone =  droneRepository.findBySerialNumber(serialNumber);
        if(drone==null){
            throw new NotFoundException("drone");
        }
        var dispatch = dispatchRepository.findDroneDispatch(drone.getId());
        if(dispatch==null){
            throw new NotFoundException("dispatch profile");
        }

        var dispatchItems = dispatchItemRepository.findDispatchedItems(dispatch.getId());

        List<Medication> medicationList =new ArrayList<>();
        for(DispatchItem dispatchItem : dispatchItems){
           var medication =  medicationRepository.findById(dispatchItem.getMedication().getId()).orElseThrow(()-> new NotFoundException("medication"));
           medicationList.add(medication);
        }


        DispatchDetailsDto dispatchDetailsDto = new DispatchDetailsDto();
        dispatchDetailsDto.setDrone(drone);
        dispatchDetailsDto.setMedicationList(medicationList);
        return dispatchDetailsDto;
    }

    @Override
    public List<Drone> availableDrones() {
        return droneRepository.findDrones();
    }

    @Override
    public List<Drone> batteryLevel() {
        return droneRepository.batteryLevel();
    }

}
