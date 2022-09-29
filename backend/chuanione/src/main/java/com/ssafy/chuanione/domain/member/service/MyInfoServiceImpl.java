package com.ssafy.chuanione.domain.member.service;

import com.ssafy.chuanione.domain.animation.dao.AnimationTypeRepository;
import com.ssafy.chuanione.domain.animation.dto.AnimationResponseDto;
import com.ssafy.chuanione.domain.member.dao.MemberRepository;
import com.ssafy.chuanione.domain.member.dto.MemberResponseDto;
import com.ssafy.chuanione.domain.member.exception.MemberNotFoundException;
import com.ssafy.chuanione.domain.voca.dao.MemorizeVocaRepository;
import com.ssafy.chuanione.domain.voca.dto.BigVocaResponseDto;
import com.ssafy.chuanione.domain.voca.dto.MyVocaJoinInterface;
import com.ssafy.chuanione.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyInfoServiceImpl {

    private final MemberRepository memberRepository;
    private final AnimationTypeRepository aniTypeRepository;
    private final MemorizeVocaRepository memorizeVocaRepository;

    // 회원 정보
    public MemberResponseDto getMyInfo() {
        System.out.println("토큰 " + SecurityUtil.getCurrentUsername());
        return MemberResponseDto.from(SecurityUtil.getCurrentUsername().flatMap(memberRepository::findByEmail).orElseThrow(MemberNotFoundException::new));
    }

    // 경험치
    // 상위 6개 장르
//    private GenresResponseDto genres;

    // 애니 내역
    public List<AnimationResponseDto> getMyAni(int memberId, int type){
//        List<AnimationResponseDto> myaniList = aniTypeRepository.findTop8ByMemberIdAndType(memberId, type);
//        return myaniList.stream().map(AnimationResponseDto::from).collect(Collectors.toList());
        return null;
    }
    // 리뷰

    // 보카 내역
    public List<BigVocaResponseDto> getMyVoca(int memberId, Pageable pageable){
        Page<MyVocaJoinInterface> myVoca = memorizeVocaRepository.findAllByMemberId(memberId, pageable);
        List<BigVocaResponseDto> result = myVoca.stream().map(BigVocaResponseDto::from).collect(Collectors.toList());
        return result;
    }

    // 경험치 history

}
