package com.riwi.test_view.model;

// 2. El DTO (Data Transfer Object) usando Java Records
// Esto es lo único que la vista recibe. Son datos inmutables y puros.
public record TaskDTO(String id, String description, boolean completed) {}
