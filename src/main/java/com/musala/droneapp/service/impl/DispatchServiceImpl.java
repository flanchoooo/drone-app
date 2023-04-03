package com.musala.droneapp.service.impl;

import com.musala.droneapp.dto.DispatchRequestDto;
import com.musala.droneapp.exceptions.InvalidTypeException;
import com.musala.droneapp.exceptions.NotFoundException;
import com.musala.droneapp.model.Dispatch;
import com.musala.droneapp.model.DispatchItem;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class DispatchServiceImpl implements DispatchService {

    private final DroneRepository droneRepository;

    private final DispatchRepository dispatchRepository;

    private final DispatchItemRepository dispatchItemRepository;

    private final MedicationRepository medicationRepository;
    @Override
    //@Transactional
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
        List<Medication> medicationList=null;
        for (int id : dispatchRequestDto.getMedicationIds()) {
            var weightMedication = medicationRepository.findById(id).orElseThrow(()-> new NotFoundException("medication with ID:" + id));
            totalWeight+=weightMedication.getWeight();
        }

        if(totalWeight > drone.getWeightLimit()) {
            throw new InvalidTypeException("Maximum weight exceeded, allowed weight is " + drone.getWeightLimit());
        }

        Dispatch dispatch = new Dispatch();
        dispatch.setDrone(drone);
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




////        // Double weight=medicationRepository.calculateWeight(dispatchRequestDto.getMedicationIds());
////        //System.out.println("ids-------------->" + dispatchRequestDto.getMedicationIds());
////
////        DispatchItem dispatchItem = new DispatchItem();
////        dispatchItem.setDispatch(dispatch);
////        dispatchItem.setMedication(weightMedication);
////        dispatchItemRepository.save(dispatchItem);
//
//
//
//      //  return null;
//
//
//        List<Integer> idList=dispatchRequestDto.getDispatchItemList().stream()
//                .map(item->{return item.getId();}).collect(Collectors.toList());
//
//        System.out.println("dfddfd" + idList.get(0));
//        Double weight=medicationRepository.calculateWeight(idList);
//
//        System.out.println("dfddfd" + weight);
//
//        if(weight>drone.getWeightLimit().doubleValue()){
//            throw new InvalidTypeException("Maximum weight exceeded, allowed weight is " + drone.getWeightLimit());
//        }
////
////        Dispatch dispatch = new Dispatch();
////        dispatch.setDestination("Harare");
////        dispatch.setDrone(drone);
////        dispatch.setDispatchItems(dispatchRequestDto.getDispatchItemList());
////        dispatch.getDispatchItems().forEach(item->dispatch.addDispatchItem(item));
////
////
////        dispatchRepository.save(dispatch);
////        return dispatch;
//        return null;

    }

}
