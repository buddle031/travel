package com.study.travel.repository;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryStorage {

    private static final Logger logger = LoggerFactory.getLogger(MemoryStorage.class);

    private static final Map<Long, Member> users = new HashMap<>();
    private static final Map<Long, Travel> travels = new HashMap<>();
    private static final AtomicLong userIdCounter = new AtomicLong(1);
    private static final AtomicLong travelIdCounter = new AtomicLong(1);

    public static Member saveMember(Member member) {
        Long id = userIdCounter.getAndIncrement();
        member.setId(id);
        users.put(id, member);
        logger.info("Saved id: {}", id);
        logger.info("Saved member: {}", member);  // 저장된 사용자 로그 출력
        return member;
    }

    public static Travel saveTravel(Travel travel) {
        Long id = travelIdCounter.getAndIncrement();
        travel.setId(id);
        travels.put(id, travel);
        logger.info("Saved travel: {}", travel);  // 저장된 여행 로그 출력
        return travel;
    }

    public static List<Travel> findTravelsByUserId(Long userId) {
        List<Travel> result = new ArrayList<>();
        for (Travel travel : travels.values()) {
            if (travel.getMember() != null && travel.getMember().getId().equals(userId)) {
                result.add(travel);
            }
        }
        logger.info("Travels found for userId {}: {}", userId, result);  // 조회된 여행 로그 출력
        return result;
    }

    public static Optional<Member> findUserById(Long userId) {
        Member member = users.get(userId);
        logger.info("User found for userId {}: {}", userId, member);  // 조회된 사용자 로그 출력
        return Optional.ofNullable(member);
    }
}
