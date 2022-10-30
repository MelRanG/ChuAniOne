# 츄애니원 (애니메이션 추천 서비스)

[TOC]

📆 **프로젝트 진행기간 : 2022/08/22 - 2022/10/07** 

🎈 **팀원소개**

| 이름  | 역할                 |
| --- | ------------------ |
| 금동운 | BigData & Frontend |
| 박유주 | Backend            |
| 안세영 | Backend & Server   |
| 이소영 | BigData & Backend  |
| 이승현 | Frontend           |
| 장창완 | Frontend           |

📽 [**UCC 보러가기**](https://youtu.be/oGQ1oaCvIBA)

![logo2](README.assets/logo2.png)

---

## 01 서비스 소개

> #### 애니메이션 빅데이터 추천 서비스
> 
> "츄애니원"은 유저들의 선호도를 기반으로 잠재요인의 값을 구하여 
> 
> 각각의 유저에게 애니메이션을 맞춤형으로 제공하는 서비스이다.

---

## 02 주요 기능

✔ 애니메이션 추천

✔ 애니메이션에 대한 정보와 리뷰, 톡톡을 통한 소통공간

✔ 유저가 본 애니메이션을 분석하여 만든 일본어 단어장

✔ 취향이 비슷한 사람들과 소통의 공간, 오픈채팅

---

## 03 담당 기능

✔ 회원가입시 메일 인증 구현

✔ Spring Security + JWT를 활용한 회원 인증 및 관리

✔ Jenkins를 활용한 CI, CD

✔ Docker와 Docker-compose로 컨테이너화

---

## 04 "츄애니원" 서비스 화면

#### **회원가입 및 로그인**

✨ **회원가입**

![signup](README.assets/signup.gif)

![인증](README.assets/인증.gif)

✨ **로그인**

![login](README.assets/login.gif)

✨ **비밀번호 찾기**

![findpwd](README.assets/findpwd.gif)

---

#### 마이페이지

![mypageTap](README.assets/mypageTap.gif)

✨ **애니메이션**

![myani](README.assets/myani.gif)

✨ **리뷰**

![myreview](README.assets/myreview.gif)

---

#### 메인페이지

![메인](README.assets/메인.gif)

---


## 05 개발환경

📌 **Backend**
- spring-boot 2.7.3
- spring-security 2.7.3
- docker 20.10.18
- docker-compose 2.1.0
- mysql 5.7
- nginx 1.23.1

📌 **CI/CD**

---

## 06 서비스 아키텍처

![image-20221006111907348](README.assets/image-20221006111907348.png)

---


## 07 협업 툴

- **Gitlab**

![image-20221006112132474](README.assets/image-20221006112132474.png)

- **Jira**

![image-20221006112243398](README.assets/image-20221006112243398.png)

- **Notion**

![image-20221006112354591](README.assets/image-20221006112354591.png)

---

## 08 서비스 설계

- **ERD**
  
  ![img](https://cdn.discordapp.com/attachments/1012637429376753677/1027439856579776543/unknown.png)

- **기능 명세서**

![image-20221006112549162](README.assets/image-20221006112549162.png)

- **Figma**

![image-20221006112716610](README.assets/image-20221006112716610.png)

- **컴포넌트 구조 설계**

![image-20221006112759543](README.assets/image-20221006112759543.png)

- **Rest API**

![image-20221006112833140](README.assets/image-20221006112833140.png)
