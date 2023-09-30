package com.spotify.cmdlinespotify;

import java.util.Scanner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.spotify.cmdlinespotify.commands.CommandRegistry;


@SpringBootApplication(scanBasePackages = "com")
@EnableJpaRepositories(basePackages = "com")
@EntityScan("com.*")
public class CmdlinespotifyApplication implements CommandLineRunner {

	private Scanner scanner;
	private CommandRegistry commandRegistry;

	public CmdlinespotifyApplication(CommandRegistry commandRegistry) {
		scanner = new Scanner(System.in);
		this.commandRegistry = commandRegistry;
	}

	public static void main(String[] args) {
		SpringApplication.run(CmdlinespotifyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		while (true) {
			System.out.println("Tell me what to do:");
			String input = scanner.nextLine();
			commandRegistry.excecute(input);
		}
	}

}
