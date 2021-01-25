package com.mutant.challenge.api.datastore.entity;

import java.io.Serializable;

import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Field;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class represents the persistence unit for the DNA sequences in the
 * DataStore
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "sequences")
public class Sequence implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Field(name = "id")
	private String idSequence;

	/*
	 * represents the result of the detection algorithm where 1-isMutant ó 0-isHuman
	 */
	@Field(name = "mutant")
	private int mutant;

}
