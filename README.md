# 인스타그램 클론 코딩 

### 미션 목표

---

그동안의 미션들로 여러분들은 Spring을 사용해 기본적인 API를 구현하는 능력을 기를 수 있었어요.

이제 인스타그램 클론코딩을 통해 실제 서비스를 개발하기 위해 문서 설계부터 배포까지 경험해보는 시간을 가질꺼에요. 단순히 API를 개발하는 것이 아닌 작성한 코드에 대해 테스트 코드를 작성하고 배포 파이프라인을 구축하는 등 많은 고민 포인트가 있을 거에요.

처음 마주하는 문제가 많을 것이고 모르는 내용이 많이 나올 것이라고 생각해요. 하지만 너무 많은 자료들이 이미 있습니다. 모르는 내용이 나왔을때 이를 해결하는 능력 또한 기대하고 있어요.

구현해야할 내용이 정말 많아요. 아마도 4주동안 꽤 많은 시간을 쏟지 않는다면 어려울 수 있어요. 디스코드 Q&A 게시판을 적극적으로 이용해주세요.

이번 미션의 성장 포인트는 다음과 같아요.

- Convetion을 지키며 개발할 수 있다.
- 가독성 있는 클린 코드를 작성할 수 있다.
- 요구사항을 파악하여 프로덕트 서버를 개발할 수 있다.
- 테스트 코드를 통해 구현한 API를 테스트할 수 있다.
- 인증 기능을 구현할 수 있다.
- S3를 사용해 이미지를 저장할 수 있다.
- CI/CD 파이프라인을 통해 자동 배포를 적용할 수 있다.
- AWS 등 클라우드를 이용하여 자신의 서버를 배포할 수 있다.

### 구현 범위

---

- 글 생성
    - 태그나 장소 같은 것은 선택적으로 하셔도 됩니다. 이미지와 텍스트만 api에 넣으셔도 괜찮아요.
    - 이미지를 올릴 수 있어야해요. 10장 제약조건은 많으니 한장만 올릴 수 있게해도 상관 없습니다.
- 글 수정
- 글 삭제
- 댓글 생성
- 댓글 수정
- 댓글 삭제
- 답글 생성
    - 답글의 Depth는 1이에요
- 답글 수정
- 답글 삭제
- 피드 조회
    - 피드에 조회되는 게시글들은 내가 팔로우 한 사람이에요
    - 좋아요 기능을 구현했다면 내가 좋아요 누른글은 따로 표시를 해주세요.
- 좋아요 누르기
    - 좋아요는 고려해야할 점이 많습니다. 이왕이면 마지막에 구현하시는것을 추천드려요.
    - 시간이 애매하다면 좋아요 기능을 구현하는 것보다는 테스트코드에 신경써보아요.
- 좋아요 취소
- 회원 가입
- 회원 탈퇴
- 프로필 조회
- 프로필 수정
    - 프로필 수정을 할때 프로필 사진을 등록할 수 있어야해요
- 팔로우
- 팔로우 취소

### ERD

---

![instagram_ERD](https://github.com/TaetaetaE01/Instagram_Clone/assets/68328998/89d69d1a-2152-4309-b0bb-bf093ae0b140)


### API

---

[Instagram_Clone](https://documenter.getpostman.com/view/18378325/2s9Yytgg46)


### 기술스택

---
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/spring data jpa-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
<img src="https://img.shields.io/badge/mysql 8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<img src="https://img.shields.io/badge/amazon aws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">
<img src="https://img.shields.io/badge/Amazon S3-E15343?style=for-the-badge&logo=Amazon S3&logoColor=white"/>


