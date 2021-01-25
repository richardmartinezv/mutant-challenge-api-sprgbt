package com.mutant.challenge.api.controller.v1;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutant.challenge.api.exception.ArraySizeNotAllowedException;
import com.mutant.challenge.api.exception.LetterSequenceNotAllowedException;
import com.mutant.challenge.api.model.request.DnaRequest;
import com.mutant.challenge.api.service.IMutantService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class controller attends to the calls associated with the endpoint that
 * has the logic to detect if the received DNA sequence corresponds to a mutant
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1")
@Validated
public class MutantController {

	IMutantService mutantService;

	/**
	 * EndPoint that calls the service in charge of detecting if a human is mutant,
	 * based on the DNA sequence
	 * 
	 * @param requestBody: contains an array of Strings that represents each row of
	 *                     a table of (NxN) with the DNA sequence
	 * 
	 * @return isMutant true if it checks that the received DNA sequence corresponds
	 *         to a mutant, otherwise it returns isMutant false
	 * 
	 * @throws ArraySizeNotAllowedException      if the size of the array does not
	 *                                           correspond to NxN
	 * @throws LetterSequenceNotAllowedException if the array of strings that
	 *                                           represents the DNA sequence
	 *                                           includes letters not allowed
	 */
	@PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> isMutant(@Valid @RequestBody final DnaRequest requestBody)
			throws ArraySizeNotAllowedException, LetterSequenceNotAllowedException {

		log.info("requestBody: {}", requestBody);

		final boolean isMutant = mutantService.isMutant(requestBody.getDna());
		log.info("isMutant: {}", isMutant);

		return new ResponseEntity<>(isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN);

	}

}
