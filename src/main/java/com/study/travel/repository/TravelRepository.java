package com.study.travel.repository;

import com.study.travel.entity.Member;

public interface TravelRepository {
    Member findByUserId(Long id);
}
