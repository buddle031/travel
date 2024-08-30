package com.study.travel.controller;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import com.study.travel.repository.MemoryStorage;
import com.study.travel.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberInputController {

    private final MemberService memberService;  // 서비스 클래스 사용

    @PostMapping("/input")
    public ResponseEntity<String> inputUserData(@RequestBody Map<String, Object> request) {
        // 사용자 엔티티 생성 및 저장
        Member member = new Member();
        member.setName((String) request.get("name"));
        member.setAge(Integer.valueOf(request.get("age").toString()));
        member.setBodyCondition((String) request.get("bodyCondition"));
        member.setHealthStatus((String) request.get("healthStatus"));
        member.setEmergencyContact((String) request.get("emergencyContact"));

        // 서비스 클래스를 통해 멤버 저장하고 반환된 객체 사용
        Member savedMember = memberService.saveMember(member);

        // 여행 엔티티 생성 및 저장
        Travel travel = new Travel();
        travel.setDestination((String) request.get("destination"));
        travel.setDuration((String) request.get("duration"));
        travel.setTravelType((String) request.get("travelType"));

        // 날짜 파싱 및 설정
        // travel.setStartDate(LocalDateTime.parse((String) request.get("startDate")));
        // travel.setEndDate(LocalDateTime.parse((String) request.get("endDate")));

        // 저장된 멤버를 여행 객체에 설정
        travel.setMember(savedMember);
        MemoryStorage.saveTravel(travel);  // MemoryStorage를 직접 사용하여 저장

        return ResponseEntity.ok("User and travel data received successfully");
    }

}
