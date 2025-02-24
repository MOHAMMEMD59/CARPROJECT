package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Request;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RequestService {


    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public Page<Request> getRequestsuser(long userId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return requestRepository.findByuserId(userId,pageable);
    }

    public Page<Request> getRequests( int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return requestRepository.findAll(pageable);
    }


    public Page<Request> getRequestsByStatus( int pageNumber, int pageSize,int status) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return requestRepository.findBytraiter(status,pageable);
    }


    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }


    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Request not found with id: " + id));
    }

    @Autowired
    private NotificationService notificationService;

    public void saveRequest(Request request) {

        requestRepository.save(request);
        notificationService.createNotification("🆕 Nouvelle demande offre est ajouter .");

    }

    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    public void updaterequeststatus(long request,int status) {



        Request currentrequest = requestRepository.findById(request)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request));

        currentrequest.settraiter(status);



        requestRepository.save(currentrequest);
    }
}

