package com.ssafy.chuanione.domain.member.service;

import com.ssafy.chuanione.domain.animation.dao.AnimationRepository;
import com.ssafy.chuanione.domain.animation.dao.AnimationTypeRepository;
import com.ssafy.chuanione.domain.animation.domain.Animation;
import com.ssafy.chuanione.domain.animation.domain.AnimationType;
import com.ssafy.chuanione.domain.animation.dto.AnimationResponseDto;
import com.ssafy.chuanione.domain.member.dao.MemberRepository;
import com.ssafy.chuanione.domain.member.domain.Member;
import com.ssafy.chuanione.domain.member.dto.MyPageResponseDto;
import com.ssafy.chuanione.domain.member.exception.MemberNotFoundException;
import com.ssafy.chuanione.domain.review.dao.ReviewRepository;
import com.ssafy.chuanione.domain.review.domain.Review;
import com.ssafy.chuanione.domain.review.dto.ReviewResponseDto;
import com.ssafy.chuanione.domain.voca.dao.MemorizeVocaRepository;
import com.ssafy.chuanione.domain.voca.dto.MemorizeResponseDto;
import com.ssafy.chuanione.domain.voca.dto.MyVocaJoinInterface;
import com.ssafy.chuanione.global.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageServiceImpl implements MyPageService {

    private final MemberRepository memberRepository;
    private final AnimationTypeRepository aniTypeRepository;
    private final AnimationRepository animationRepository;
    private final ReviewRepository reviewRepository;
    private final MemorizeVocaRepository memorizeVocaRepository;


    // 회원 정보
    public MyPageResponseDto getMyInfo() {
        System.out.println("토큰 " + SecurityUtil.getCurrentUsername());
        Member member = SecurityUtil.getCurrentUsername().flatMap(memberRepository::findByEmail).orElseThrow(MemberNotFoundException::new);
        return MyPageResponseDto.from(member);
    }

    // 경험치
    // 상위 6개 장르

    // 애니 내역 - 메인
    public Map<String, Object> getMyAni(int memberId){
        System.out.println("[Service] getMyAni");
        // 좋아요한 애니 아이디
        List<AnimationType> likeIds = aniTypeRepository.findAllTop8ByMemberId_IdAndTypeOrderByIdDesc(memberId, 1);
        // 찜한 애니 아이디
        List<AnimationType> wishIds = aniTypeRepository.findAllTop8ByMemberId_IdAndTypeOrderByIdDesc(memberId, 3);
        // 시청한 애니 아이디
        List<AnimationType> watchIds = aniTypeRepository.findAllTop8ByMemberId_IdAndTypeOrderByIdDesc(memberId, 4);
        System.out.println(likeIds.get(0));
        // 각 리스트로 받아옴
        List<Animation> likePage = animationRepository.findAllByQuery(getAnimationId(likeIds));
        List<Animation> wishPage = animationRepository.findAllByQuery(getAnimationId(wishIds));
        List<Animation> watchPage = animationRepository.findAllByQuery(getAnimationId(watchIds));
        System.out.println(likePage.size());
        Map<String, Object> result = new HashMap<>();
        result.put("like", likePage.stream().map(AnimationResponseDto::from).collect(Collectors.toList()));
        result.put("wish", wishPage.stream().map(AnimationResponseDto::from).collect(Collectors.toList()));
        result.put("watch", watchPage.stream().map(AnimationResponseDto::from).collect(Collectors.toList()));

        return result;
    }

    // 시청한 애니 더보기
    public Map<String, Object> getWatchAni(int memberId){
        // 시청한 애니 아이디
        List<AnimationType> watchIds = aniTypeRepository.findAllByMemberId_IdAndTypeOrderByIdDesc(memberId, 4);
        // 각 리스트로 받아옴
        return getAniTypeList("watch", watchIds);
    }

    // 좋아요한 애니 더보기
    public Map<String, Object> getLikeAni(int memberId){
        // 시청한 애니 아이디
        List<AnimationType> likeIds = aniTypeRepository.findAllByMemberId_IdAndTypeOrderByIdDesc(memberId, 1);
        // 각 리스트로 받아옴
        return getAniTypeList("like", likeIds);
    }

    // 찜한 애니 더보기
    public Map<String, Object> getWishAni(int memberId){
        // 찜한 애니 아이디
        List<AnimationType> wishIds = aniTypeRepository.findAllByMemberId_IdAndTypeOrderByIdDesc(memberId, 3);
        // 각 리스트로 받아옴
        return getAniTypeList("wish", wishIds);
    }

    // 리뷰
    public List<ReviewResponseDto> getMyReview(int memberId){
        Member member = SecurityUtil.getCurrentUsername().flatMap(memberRepository::findByEmail).orElseThrow(MemberNotFoundException::new);
        List<Review> review = reviewRepository.findAllByMemberId(member);
        return review.stream().map(ReviewResponseDto::from).collect(Collectors.toList());
    }

    // 보카 내역
    public Map<String, Object> getMyVoca(int memberId){
        List<MyVocaJoinInterface> myVoca = memorizeVocaRepository.findAllByMemberId(memberId);
        List<MemorizeResponseDto> vocaList = myVoca.stream().map(MemorizeResponseDto::from).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("myVoca", vocaList);
        result.put("totalCnt", vocaList.size());
        return result;
    }

    // 경험치 history

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // 중복 코드 묶음(애니메이션 목록, 총 갯수 반환)
    public Map<String, Object> getAniTypeList(String type, List<AnimationType> ids){
        // 애니메이션 아이디로 정보 목록 가져오기
        List<Animation> aniList = animationRepository.findAllByQuery(getAnimationId(ids));
        Map<String, Object> result = new HashMap<>();
        result.put(type, aniList.stream().map(AnimationResponseDto::from).collect(Collectors.toList()));
        // 반환된 애니메이션 수
        result.put("totalCnt", aniList.size());
        return result;
    }

    // 타입별 애니메이션 아이디 얻기
    public int[] getAnimationId(List<AnimationType> myAniList){
        List<Integer> temp = new ArrayList<>();
        for(AnimationType myAni : myAniList){
            temp.add(myAni.getAnimationId());
        }
        return temp.stream().mapToInt(Integer::intValue).toArray();
    }
}
