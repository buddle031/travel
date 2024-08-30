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
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destination;
    private String duration;
    private String travelType;
    private LocalDateTime dateTime;
    private LocalDateTime startDate;  // 여행 시작일 (사용자가 날짜를 선택할 경우)
    private LocalDateTime endDate;    // 여행 종료일 (사용자가 날짜를 선택할 경우)

    //@ManyToOne
    private Member member;       // 여행자 (외래 키)
}
