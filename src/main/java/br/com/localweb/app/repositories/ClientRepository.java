package br.com.localweb.app.repositories;

import br.com.localweb.app.domain.client.Client;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ClientRepository extends JpaRepository <Client, UUID> {

    @Query("""
            select c.active
            from Client c
            where
            c.id = :id
            """)
    public Boolean findActiveById(@NotNull UUID id);
}
