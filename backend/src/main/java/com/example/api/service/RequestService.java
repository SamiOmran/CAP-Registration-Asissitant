package com.example.api.service;


import com.example.api.model.Request;
import com.example.api.model.User;
import com.example.api.repository.RequestRepo;
import com.example.api.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final RequestRepo requestRepo;
    private UserRepo userRepo;

    public RequestService(RequestRepo requestRepo, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
    }

    public void sendRequest(Request request, User student){
        request.setStudentUniversityNumber(student.getUniversityNumber());
        student.addRequest(request);
        requestRepo.save(request);
    }

}
