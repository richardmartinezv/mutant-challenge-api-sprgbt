package com.mutant.challenge.api.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Mutant Challenge properties
 * 
 * @author Richard Martinez Valderrama, richardmartinezv@gmail.com
 * @since 1.0
 */

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "challenge.api")
public class MutantChallengeProperties {

	private String lettersAllowedRegex = "^[ACGT]*$";
	private int minimumNumberSequencesFind = 2;
	private int lengthLetterSequence = 4;

}
