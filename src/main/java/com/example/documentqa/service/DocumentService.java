package com.example.documentqa.service;

import com.example.documentqa.entity.Document;
import com.example.documentqa.repository.DocumentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document save(Document doc) {
        doc.setUploadedAt(LocalDate.now());
        return documentRepository.save(doc);
    }

    public List<Document> search(String keyword) {
        return documentRepository.findByContentContainingIgnoreCase(keyword);
    }

    public List<Document> filterDocuments(String author, String date, String type) {
        // Assuming type could be added as a field in Document
        return documentRepository.findByAuthorContainingIgnoreCase(author);
    }
}
