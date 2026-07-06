package co.istad.matra.ecommerce.features.file;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<FileUpload, Integer> {


    Optional<FileUpload> findByName(String name);
}
