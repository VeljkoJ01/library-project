package com.example.Library_Management_System.service;
import com.example.Library_Management_System.entity.Publisher;
import com.example.Library_Management_System.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public Publisher getPublisherById(Long id) {
        return publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
    }

}
