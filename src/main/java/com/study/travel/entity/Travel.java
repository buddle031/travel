package com.study.travel.entity;

//import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Travel {
    private Long id;
    private String destination;
    private String duration;
    private String travelType;  // 여행 스타일 (예: "Leisure", "Adventure")
    private String transportation;  // 추가 가능: 교통수단 (예: "Bus", "Car", "Train")
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Member member;  // 연관된 사용자 정보

    // 추가 가능한 필드 예시
    private String additionalNotes;  // 추가적인 설명 또는 메모answ
}
