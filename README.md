# 웹소설 클론 코딩 챌린지
## 1. 기술 스택
|Java|Spring Data JPA|Spring Security|MySQL|Gradle|Redis|
|---|---|---|---|---|---|

## 2. API 문서
[API 상세](https://github.com/jindaram-stu/Numble_Novel_Clone/blob/master/API.md)

## 3. 요구사항
[요구사항 문서](https://www.notion.so/jinsb/78a2aef8230c42448831353ba5e5be4d)

## 4. ERD
![img](https://velog.velcdn.com/images/jindaram-stu/post/fa0b46d1-179e-44c9-989a-c8b5e61f105c/image.png)

## 5. 추가한 비즈니스 정책
- 소설 등록 시 20,000자 이내이어야 합니다.
    - 많은 이용자의 무분별한 긴 양의 소설 등록을 제한하도록 20,000자로 제한
    - 추후 작품에 대한 인기가 많아진다면, 해당 소설의 작자에 한하여 글자 수 제한 해제.
- 홈 화면에는 최신순, 조회순, 인기순으로 나열
- 무료 소설은 비로그인, 로그인 사용자 모두 열람할 수 있다.
- 유료 소설은 로그인 사용자 중 결제 이력이 있는 사용자만 열람할 수 있다.
## 5. 고민과 해결
1. [캐시 성능 개선기](https://jinsb.notion.site/a40bd22c30b94970b8355eb0b2009263)
2. [어떻게 여러 요청이 들어오면 한 가지 요청만 승인할까?](https://jinsb.notion.site/2ff1b00a58b340458c693e98e5c9e9f7)
3. [소설 내용은 어떻게 관리해야 할까?](https://www.notion.so/jinsb/21aa346d88334751a1724d6ac8c805fb)
4. [조회수를 매번 올려야 할까?](https://www.notion.so/jinsb/228cdcfbe9824aab96f1ac42370e3dba)
5. [선호작 기능을 효율적으로 구현하는 방법](https://www.notion.so/jinsb/78a2aef8230c42448831353ba5e5be4d)

## 6. 회고글
[회고글 velog](https://velog.io/@jindaram-stu/%EB%84%98%EB%B8%94-%ED%9A%8C%EA%B3%A0-%EC%9B%B9%EC%86%8C%EC%84%A4-%ED%81%B4%EB%A1%A0%EC%BD%94%EB%94%A9-%EC%B1%8C%EB%A6%B0%EC%A7%80)

## 7. 아쉬웠던 점
- 대용량 트래픽이 오가는 환경에서 발생할 수 있는 여러 부하 및 장애에 대해 대처할 수 있는 기술들을 많이 알게되었지만, 실제로 적용해보지 못해서 아쉽다. 😥
- nGrinder와 같은 부하 테스트 도구를 적극적으로 사용해보고 싶었다.
- 테스트 코드에 대한 신경을 쓰지 못했다. 동시성이 중요했던 프로젝트이니 만큼, 해당 부분은 테스트 코드를 작성하고 싶었다.
- 프론트엔드를 구성해보고 싶었지만, 시간이 부족했다.
등 나머지 내용은 회고글에 작성하였습니다.
