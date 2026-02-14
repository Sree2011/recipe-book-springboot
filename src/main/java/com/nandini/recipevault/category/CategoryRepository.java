
package com.nandini.recipevault.category;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository interface for {@link Category} entities.
 *
 * <p>Provides CRUD operations and query methods
 * for interacting with the database.
 * ------
 * Example:
 * <pre>
 * categoryRepository.save(new Category("Desserts"));
 * </pre>
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
