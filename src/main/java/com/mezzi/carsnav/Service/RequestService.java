package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Request;
import com.mezzi.carsnav.Repository.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Request not found with id: " + id));
    }

    public void saveRequest(Request request) {
         requestRepository.save(request);
    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }
}

