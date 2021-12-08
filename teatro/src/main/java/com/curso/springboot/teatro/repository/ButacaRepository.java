package com.curso.springboot.teatro.repository;

import java.util.List;
import java.util.Optional;

import com.curso.springboot.teatro.model.Butaca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Integer> {
    List<Butaca> findByTeatroId(int id);
    Optional<Butaca> findByButaca(String butaca);
}
