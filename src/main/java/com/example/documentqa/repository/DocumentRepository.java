package com.example.documentqa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.documentqa.entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByContentContainingIgnoreCase(String keyword);
    List<Document> findByAuthorContainingIgnoreCase(String author);
}

