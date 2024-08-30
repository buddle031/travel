package com.study.travel.controller;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import com.study.travel.repository.MemoryStorage;
import com.study.travel.repository.TravelRepository;
import com.study.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class TravelRecommendController {
    //사용자 맞춤 여행지 추천 결과를 출력하는 컨트롤러

    private final TravelService travelService; // 여행 추천을 처리하는 서비스

    @GetMapping("/recommendation")
    public ResponseEntity<?> getTravelRecommendation(@RequestParam Long userId) {
        // 사용자 존재 여부를 먼저 확인
        Optional<Member> user = MemoryStorage.findUserById(userId);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        List<Travel> travels = MemoryStorage.findTravelsByUserId(userId);

        if (!travels.isEmpty()) {
            Travel recommendation = travels.get(0); // 간단한 예시로 첫 번째 여행 정보를 반환
            return ResponseEntity.ok(recommendation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No travels found for the user");
        }
    }


  /*  @PostMapping("/recommendation/alternative")
    public ResponseEntity<Travel> getAlternativeRecommendation(@RequestParam Long userId, @RequestParam int attempt) {
        List<Travel> travels = MemoryStorage.findTravelsByUserId(userId);

        if (attempt > 3) {
            // 3회 이상 추천 요청 시 결제나 광고 시청 요구 로직
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // 예시로 금지 응답 반환
        } else if (!travels.isEmpty()) {
            Travel alternativeRecommendation = travels.get(0); // 간단한 예시로 첫 번째 여행 정보를 반환
            return ResponseEntity.ok(alternativeRecommendation);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }*/
}
