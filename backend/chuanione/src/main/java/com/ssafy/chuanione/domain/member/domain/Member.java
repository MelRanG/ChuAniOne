package com.ssafy.chuanione.domain.member.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer id;
    private String email;
    private String password;
    @Column(unique = true)
    private String nickname;
    private String profile;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String birthday;
    private String introduction;
    @Enumerated(EnumType.STRING)
    private Role role;

    private String token;
//    public Member toEntity(){
//        return Member.builder()
//                .email(email)
//                .password(password)
//                .nickname(nickname)
//                .profile(profile)
//                .gender(gender)
//                .birthday(birthday)
//                .introduction(introduction)
//                .build();
//    }
    public void saveToken(String token){
        this.token = token;
    }

    public void patch(Member member, PasswordEncoder encoder){
        if(member.getPassword() != null){
            this.password = encoder.encode(member.getPassword());
        }
        if(member.getProfile() != null){
            this.profile = member.getProfile();
        }
        if(member.getIntroduction() != null){
            this.introduction = member.getIntroduction();
        }
        if(member.getNickname() != null){
            this.nickname = member.getNickname();
        }
    }
}
