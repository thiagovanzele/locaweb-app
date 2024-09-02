package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository <Client, UUID> {
}
