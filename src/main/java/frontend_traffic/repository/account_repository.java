package frontend_traffic.repository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import frontend_traffic.models.account_entity;
public interface account_repository extends JpaRepository<account_entity, UUID> {

}
