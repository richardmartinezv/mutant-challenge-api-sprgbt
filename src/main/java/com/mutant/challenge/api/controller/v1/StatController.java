package com.mutant.challenge.api.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mutant.challenge.api.model.response.StatsResponse;
import com.mutant.challenge.api.service.IStatService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * This class controller attends to the calls associated with the endpoint that
 * has the logic to get the statistics of the DNA sequence checks performed
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/v1")
public class StatController {

	IStatService statService;

	/**
	 * EndPoint that calls the service in charge of Get the statistics of the DNA
	 * sequence checks performed
	 * 
	 * @return statistics related to the information of the detected DNA sequences
	 */
	@GetMapping(value = "/stats", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatsResponse> stats() {

		log.debug("Getting stats");
		return new ResponseEntity<>(statService.getVerificationStatistic(), HttpStatus.OK);

	}
}
