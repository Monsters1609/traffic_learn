package frontend_traffic.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.role_entity;

public interface role_repository extends JpaRepository<role_entity, UUID> {

}
