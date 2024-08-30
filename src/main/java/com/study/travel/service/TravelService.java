package com.study.travel.service;

import com.study.travel.controller.TravelRecommendController;
import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
//import jakarta.transaction.Transactional;
import com.study.travel.repository.MemoryStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

@RequiredArgsConstructor
@Slf4j
public class TravelService {
    @Autowired
    private final MemberService memberService;

    public Travel getRecommendedTravel(Member member) {
        // member의 정보를 바탕으로 추천 로직을 처리
        List<Travel> travels = MemoryStorage.findTravelsByUserId(member.getId());

        // 추가된 필드를 기반으로 더 나은 추천을 할 수 있음
        if (!travels.isEmpty()) {
            // 예: 여행 스타일에 따라 필터링 또는 정렬
            return travels.get(0);  // 간단히 첫 번째 여행 정보 반환
        }
        return null;
    }
}
