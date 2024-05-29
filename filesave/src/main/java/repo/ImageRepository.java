package repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}