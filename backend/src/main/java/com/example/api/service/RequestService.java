package com.example.api.service;


import com.example.api.model.Request;
import com.example.api.model.User;
import com.example.api.repository.RequestRepo;
import com.example.api.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestService {

    private final RequestRepo requestRepo;
    private UserRepo userRepo;

    public RequestService(RequestRepo requestRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
    }

    public void sendRequest(Request request, User student){
        User hod = userRepo.findByEmail("hamed@najah.edu").get();
        request.setStudentUniversityNumber(student.getUniversityNumber());
        request.setName(student.getFullName());
        request.setEmail(student.getEmail());
        hod.addRequest(request);
        requestRepo.save(request);
    }

    public Request getRequest(Long Id) {
        return requestRepo.getById(Id);
    }

}
