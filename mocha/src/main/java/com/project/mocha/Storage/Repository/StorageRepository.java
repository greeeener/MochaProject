package com.project.mocha.Storage.Repository;

import com.project.mocha.Storage.Entity.Storage;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StorageRepository extends CrudRepository<Storage, Integer> {
    List<Storage> findByStorageIdUserId(int userId) throws DataAccessException;
    void deleteByStorageIdUserIdAndStorageIdCreationId(
            int userId, int creationId) throws DataAccessException;
}
