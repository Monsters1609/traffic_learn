package frontend_traffic.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.multiple_account_roles_entity;

public interface multiple_account_role_repo extends JpaRepository<multiple_account_roles_entity, UUID> {

}
