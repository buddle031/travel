package com.study.travel.repository;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class MemoryStorage {

    private static Member savedMember;  // 단일 사용자 관리
    private static final Map<Long, Travel> travels = new HashMap<>();  // 여행 정보 저장소
    private static final AtomicLong travelIdCounter = new AtomicLong(1);

    // 사용자 저장 메서드 (단일 사용자만 저장)
    public static Member saveMember(Member member) {
        member.setId(1L);  // 항상 ID를 1로 설정하여 단일 사용자만 관리
        savedMember = member;  // 저장된 사용자 객체를 기억
        System.out.println("Saved member: " + member);  // 저장된 사용자 로그 출력
        return savedMember;
    }

    // 저장된 단일 사용자 가져오기
    public static Optional<Member> getSavedMember() {
        System.out.println("Fetching saved member: " + savedMember);  // 저장된 사용자 로그 출력
        return Optional.ofNullable(savedMember);
    }

    // 여행 정보 저장 메서드
    public static Travel saveTravel(Travel travel) {
        Long id = travelIdCounter.getAndIncrement();
        travel.setId(id);
        travels.put(id, travel);
        System.out.println("Saved travel: " + travel);  // 저장된 여행 로그 출력
        return travel;
    }

    // 사용자 ID로 여행 정보를 찾는 메서드
    public static List<Travel> findTravelsByUserId(Long userId) {
        List<Travel> result = new ArrayList<>();
        for (Travel travel : travels.values()) {
            if (travel.getMember() != null && travel.getMember().getId().equals(userId)) {
                result.add(travel);
            }
        }
        System.out.println("Travels found for userId " + userId + ": " + result);  // 조회된 여행 로그 출력
        return result;
    }
}
