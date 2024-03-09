package com.tanmoy.springboottest.repository;

import com.tanmoy.springboottest.entity.UserEventLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventLogsRepository extends JpaRepository<UserEventLogs, Long> {
}
