package com.otomasyon.otomasyonDemo.repository;

import com.otomasyon.otomasyonDemo.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByRolTuru(Rol.RolTuru rolTuru);
}
