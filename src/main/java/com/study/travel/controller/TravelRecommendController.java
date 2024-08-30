package com.study.travel.controller;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import com.study.travel.repository.MemoryStorage;
import com.study.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.python.util.PythonInterpreter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import org.python.core.*;
import org.python.util.PythonInterpreter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
@Controller
@RequiredArgsConstructor
@Slf4j
public class TravelRecommendController {
    // 사용자 맞춤 여행지 추천 결과를 출력하는 컨트롤러

    private final TravelService travelService; // 여행 추천을 처리하는 서비스

    @GetMapping("/recommendation")
    public ResponseEntity<?> getTravelRecommendation(@RequestParam("userId") Long userId) {  // "userId" 이름 명시
        try {
            // 단일 사용자 존재 여부 확인
            Optional<Member> user = MemoryStorage.getSavedMember();
            System.out.println("Checking saved member: " + user.orElse(null));  // Optional 안의 값이 있는지 확인

            if (user.isEmpty()) {
                // 사용자 없음 로그 출력
                System.out.println("User not found for userId: " + userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            // 사용자 확인 후 여행 정보 조회
            List<Travel> travels = MemoryStorage.findTravelsByUserId(userId);
            if (!travels.isEmpty()) {
                Travel recommendation = travels.get(0);  // 간단한 예시로 첫 번째 여행 정보를 반환
                System.out.println("Travel recommendation found for userId " + userId + ": " + recommendation);

                // 사용자 ID가 1인 경우, Python 코드 호출
                if (userId == 1L) {
                    return callPythonCodeWithJython(recommendation);
                }

                return ResponseEntity.ok(recommendation);
            } else {
                System.out.println("No travels found for userId: " + userId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No travels found for the user");
            }
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            e.printStackTrace();  // 콘솔에 예외 스택 트레이스 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

    private ResponseEntity<?> callPythonCodeWithJython(Travel travel) {
        try (PythonInterpreter pyInterp = new PythonInterpreter()) {
            // Python 코드 작성
            String pythonCode =
                    "def generate_recommendation(location, duration, style, age, walking_ability):\n" +
                            "    # Simple travel plan generation logic\n" +
                            "    travel_plan = u'Generated travel plan for {} for {} with style {}\\n'.format(location, duration, style)\n" +
                            "    # Example transport and location details\n" +
                            "    travel_plan += u'Day 1: Visit Museum of Art, Transport: Bus, Time: 20 minutes\\n'\n" +
                            "    travel_plan += u'Day 2: Go to Central Park, Transport: Walk, Time: 15 minutes\\n'\n" +
                            "    travel_plan += u'Day 3: Explore Downtown, Transport: Metro, Time: 30 minutes\\n'\n" +
                            "    return travel_plan\n" +  // UTF-8로 인코딩 없이 반환
                            "\n" +
                            "result = generate_recommendation(location, duration, style, age, walking_ability)\n";

            // 파라미터 설정
            pyInterp.set("location", new PyUnicode(travel.getDestination()));
            pyInterp.set("duration", new PyUnicode(travel.getDuration()));
            pyInterp.set("style", new PyUnicode(travel.getTravelType()));
            pyInterp.set("age", new PyInteger(travel.getMember().getAge()));
            pyInterp.set("walking_ability", new PyUnicode(travel.getMember().getBodyCondition()));

            // 파이썬 코드 실행
            pyInterp.exec(pythonCode);

            // 결과 가져오기
            PyObject result = pyInterp.get("result");
            String recommendation = result.toString();  // UTF-8로 변환 없이 직접 변환

            return ResponseEntity.ok(recommendation);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while executing Python code: " + e.getMessage());
        }
    }


}
