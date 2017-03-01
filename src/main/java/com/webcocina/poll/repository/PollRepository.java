package com.webcocina.poll.repository;

import com.webcocina.poll.domain.PollImpl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by joaquinguerra on 01/03/2017.
 */
public interface PollRepository extends JpaRepository<PollImpl, Integer> {
}
