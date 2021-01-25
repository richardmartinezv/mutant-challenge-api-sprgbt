package com.mutant.challenge.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Slf4j
@RestController
public class HealthCheckController {

	@GetMapping(value = "/healtcheck")
	public ResponseEntity<Object> healthCheck() {

		log.info("comment: Mutant Challenge API");
		log.info("version: 1.0.0");
		log.info("path: /healtcheck");

		return new ResponseEntity<>("HEALTCHECK", HttpStatus.OK);
	}

}
