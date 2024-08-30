package com.study.travel.service;

import com.study.travel.entity.Member;
import com.study.travel.repository.MemberRepository;
//import jakarta.transaction.Transactional;
import com.study.travel.repository.MemoryStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    public Member saveMember(Member member) {
        return MemoryStorage.saveMember(member);  // 저장된 Member 객체를 반환
    }

}
