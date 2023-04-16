## API

### User
**유저 회원가입**
- POST /api/auth/signup
```text
성공적으로 회원가입 하였습니다.
```
**유저 로그인**
- POST /api/auth/login
```text
{토큰값}
```


## Novel
**소설 목록 조회**

- GET /api/novel?novelId

```json
{
    "id": 1,
    "episodes": [
        {
            "id": 1,
            "title": "title",
            "sequence": 1,
            "hit": 0,
            "price": 0,
            "createDate": "2023-04-16T13:51:46Z"
        }
    ],
    "title": "테스트1",
    "author": "a",
    "description": "a",
    "freeType": "FREE",
    "genre": "FANTANSY"
}
```

**소설 에피소드 조회**
- GET /api/novel/episode?novelId=&episodeId=
```JSON
{
  "novel": {
    "id": 2,
    "title": "테스트2",
    "author": "a",
    "description": "a",
    "freeType": "FREE",
    "genre": "FANTANSY"
  },
  "episodeTitle": "3의 오아시스",
  "sequence": 17,
  "hit": 0,
  "price": 0,
  "createDate": "2023-04-16T08:07:01.053Z",
  "isFavorite": false,
  "contents": [
    {
      "id": 1,
      "page": 0,
      "content": "1페이지의 내용입니다."
    },
    {
      "id": 2,
      "page": 1,
      "content": "2페이지의 내용입니다."
    }
  ]
}
```

**포인트 충전**
- POST /api/point
```agsl
성공적으로 구매하였습니다.
```

**소설 구매**
- POST /api/pay
```agsl
성공적으로 구매하였습니다.
```

400 Error
```
포인트가 부족합니다. 
```

