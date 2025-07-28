package com.myproject.mediaservice.repository;

import com.myproject.mediaservice.model.Media;
import com.myproject.mediaservice.view_model.NoFileMediaVM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 2:04 PM Thu 7/24/2025
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

	@Query(value = "SELECT new com.myproject.mediaservice.view_model.NoFileMediaVM(m.id, m.caption, m.fileName, m.mediaType) "
		+ "FROM Media m where m.id = ?1")
	NoFileMediaVM findByIdWithoutFileInReturn(Long id);
}
