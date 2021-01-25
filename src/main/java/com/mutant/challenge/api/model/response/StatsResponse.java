package com.mutant.challenge.api.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO used to return the response in relation to the statistics of the DNA
 * sequence checks performed
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatsResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private long countMutantDna;
	private long countHumanDna;
	private double ratio;

}
