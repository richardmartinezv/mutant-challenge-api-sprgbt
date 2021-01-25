package com.mutant.challenge.api.datastore.repository;

import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;

import com.mutant.challenge.api.datastore.entity.Sequence;

/**
 * This class repository provides Datastore-specific functionality.
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */
public interface IDatastoreRepository extends DatastoreRepository<Sequence, String> {

}
