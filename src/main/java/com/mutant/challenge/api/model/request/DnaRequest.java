package com.mutant.challenge.api.model.request;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * DTO used to receive the information related to the DNA sequences to be
 * checked
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
public class DnaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "the value of the dna field cannot be null")
	@NotEmpty(message = "the value of the dna field cannot be empty")
	@Valid
	private String[] dna;

}
