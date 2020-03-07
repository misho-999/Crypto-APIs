package com.taskcrypto.data.repositories;

import com.taskcrypto.data.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, String> {
}
