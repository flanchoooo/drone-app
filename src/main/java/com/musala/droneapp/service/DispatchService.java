package com.musala.droneapp.service;

import com.musala.droneapp.dto.DispatchRequestDto;
import com.musala.droneapp.model.Dispatch;

public interface DispatchService {

    Dispatch dispatch(DispatchRequestDto dispatchRequestDto);
}
