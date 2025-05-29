package com.example.DevopsProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DevopsProjectApplication {
	private static final Logger logger = LoggerFactory.getLogger(DevopsProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DevopsProjectApplication.class, args);
	}

	@Bean
	MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
		return registry -> registry.config().commonTags("application", "test-backend");
	}

	@RestController
	public static class DemoController {
		@GetMapping("/")
		public String root() {
			return "root";
		}

		@GetMapping("/status/{statusCode}")
		public ResponseEntity<Map<String, String>> returnStatus(
				@PathVariable int statusCode,
				@RequestParam(required = false) Integer secondsSleep
		) throws InterruptedException {
			logger.info("Hello from Spring! statusCode={}, secondsSleep={}", statusCode, secondsSleep);

			if (secondsSleep != null) {
				TimeUnit.SECONDS.sleep(secondsSleep);
			}

			if (statusCode != 200) {
				logger.error("Shit happens");
				throw new ResponseStatusException(HttpStatus.valueOf(statusCode), "an error occurred");
			}

			return ResponseEntity.ok(Map.of("data", "Hello"));
		}
	}
}
