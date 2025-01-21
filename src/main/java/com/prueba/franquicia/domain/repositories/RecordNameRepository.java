package com.prueba.franquicia.domain.repositories;

public interface RecordNameRepository<T> {
    T getRecordByName(String name);
    T getRecordByNameOfDifferentId(Long id, String name);
}
