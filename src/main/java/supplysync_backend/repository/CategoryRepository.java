package supplysync_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import supplysync_backend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}