package com.study.travel.service;

import com.study.travel.controller.TravelRecommendController;
import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
//import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

@RequiredArgsConstructor
@Slf4j
public class TravelService {
    @Autowired
    private final MemberService memberService;

 /*   public Travel save(Travel travel){
        Travel build = Travel.builder()
                .destination(travel.getDestination())
                .duration(travel.getDuration())
                .travelType(travel.getTravelType())
                .dateTime(travel.getDateTime())
                .startDate(travel.getStartDate())
                .endDate(travel.getEndDate())

        Travel save = TravelRecommendController.save(build);
        return save;
    }*/
}
