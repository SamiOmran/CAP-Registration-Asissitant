package com.example.api.service;


import com.example.api.model.Request;
import com.example.api.model.User;
import com.example.api.repository.RequestRepo;
import com.example.api.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RequestService {

    private final RequestRepo requestRepo;
    private UserRepo userRepo;
    private final MailService mailService;

    public RequestService(RequestRepo requestRepo, UserRepo userRepo, MailService mailService) {
        this.userRepo = userRepo;
        this.requestRepo = requestRepo;
        this.mailService = mailService;
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

    public Set<Request> removeRequest(Long requestId, User hod) {
        Request request = requestRepo.findById(requestId).get();
        hod.removeRequest(request);
        return hod.getRequests();
    }

    public void forwardRequest(Long requestId) {
        Request currentRequest = requestRepo.getById(requestId);
        User student = userRepo.findByEmail(currentRequest.getEmail()).get();
        User dean = userRepo.findByEmail("samer@najah.edu").get();
        mailService.sendRequestEmail(dean, student, currentRequest);
    }
}
