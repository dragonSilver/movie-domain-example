package net.dg.domain.movie;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShowingRepository extends JpaRepository<Showing, Long>, JpaSpecificationExecutor<Showing>{
}
