package com.example.documentqa.controller;

import com.example.documentqa.entity.Document;
import com.example.documentqa.service.DocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/upload")
    public ResponseEntity<Document> upload(@RequestBody Document doc) {
        return ResponseEntity.ok(documentService.save(doc));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam String keyword) {
        return ResponseEntity.ok(documentService.search(keyword));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Document>> filter(
            @RequestParam String author,
            @RequestParam String date,
            @RequestParam String type) {
        return ResponseEntity.ok(documentService.filterDocuments(author, date, type));
    }
}
