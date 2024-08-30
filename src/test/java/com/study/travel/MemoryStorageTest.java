package com.study.travel;

import com.study.travel.entity.Member;
import com.study.travel.entity.Travel;
import com.study.travel.repository.MemoryStorage;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemoryStorageTest {

    @Test
    void testSaveAndFindMember() {
        Member member = new Member();
        member.setName("Anne");
        member.setAge(21);
        member.setBodyCondition("Normal");
        member.setHealthStatus("Good");
        member.setEmergencyContact("1234567890");

        // 저장 테스트
        MemoryStorage.saveMember(member);
        Optional<Member> foundMember = MemoryStorage.findUserById(member.getId());

        assertTrue(foundMember.isPresent());
        assertEquals("Anne", foundMember.get().getName());
    }

    @Test
    void testSaveAndFindTravel() {
        Member member = new Member();
        member.setName("Anne");
        member.setAge(21);

        MemoryStorage.saveMember(member);

        Travel travel = new Travel();
        travel.setDestination("Paris");
        travel.setDuration("7 days");
        travel.setTravelType("Leisure");
        travel.setMember(member);

        // 저장 테스트
        MemoryStorage.saveTravel(travel);
        List<Travel> travels = MemoryStorage.findTravelsByUserId(member.getId());

        assertFalse(travels.isEmpty());
        assertEquals("Paris", travels.get(0).getDestination());
    }
}
