# 유저 생성 및 로그인

## 유저 생성
`POST /api/auth/signup`
### | Request
RequestBody
```JSON
{
  "email" : "abc@example.com",
  "password" : "password",
  "nickname" : "닉네임"
}
```

### | Response
200 OK
```JSON
{
  "message" : "성공적으로 회원가입 하였습니다.",
  "userId" : 1
}
```
400 Bad Request
```JSON
{
  "errorCode" : 400,
  "errorMessage" : "이미 존재하는 유저 이메일입니다.",
  "errorPath" : "/api/auth/signup"
}
```

## 유저 로그인
`POST /api/auth/login`
### | Request
RequestBody
```JSON
{
  "email" : "abc@example.com",
  "password" : "password"
}
```

### | Response
200 OK
```text
{토큰값}
```

400 Bad Request
```JSON
{
	"errorCode" : 400,
  "errorMessage" : "이메일이 틀렸습니다",
  "errorPath" : "/api/auth/login"
}
```

400 Bad Request
```JSON
{
	"errorCode" : 400,
  "errorMessage" : "비밀번호가 틀렸습니다.",
  "errorPath" : "/api/auth/login"
}
```

# 소설 조회 및 생성
## 소설 조회
`GET /api/novel?novelId`
### | Request
Parameter
```
/api/novel?novelId={소설ID}
```

### | Response
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
## 에피소드 조회
`GET /api/novel/episode`
### | Request 
Parameter
```
/api/novel/episode?novelId={novelId}&episodeId={episodeId}
```

### | Response

200 OK
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

400 Bad Request
```JSON
{
  "errorCode" : 400,
  "errorMessage" : "해당 소설 에피소드를 찾을 수 없습니다.",
  "errorPath" : "/api/novel/episode	"
}
```

400 Bad Request - 유료 결제 필요한 소설 조회 시
```JSON
{
  "errorCode" : 400,
  "errorMessage" : "결제 후에 열람할 수 있습니다.",
  "errorPath" : "/api/novel/episode	"
}
```

## 소설 생성
`POST /api/novel`
### | Request
Header
```
Authorization : {token}
```
RequestBody
```JSON
{
	"title" : "제목",
  "description" : "설명",
  "genre" : "FANTASY",
  "freeType" : "NON-FREE"
}
```
### | Response
200 OK
```JSON
{
	"title" : "제목",
  "description" : "설명",
  "genre" : "FANTASY",
  "freeType" : "NON-FREE"
}
```

## 에피소드 생성
`POST /api/novel/episode`
### | Request
Header
```
Authorization : {token}
```
RequestBody
```JSON
{
	"novelId" : 1,
  "title" : "에피소드 제목",
  "price" : 500,
  "contents" : [
    {
      "content" : "1페이지 내용" 
    },
    {
      "content" : "2페이지 내용" 
    }
  ]
}
```
### | Response
200 OK
```JSON
{
	"novelId" : 1,
  "title" : "에피소드 제목",
  "price" : 500,
  "contents" : [
    {
      "content" : "1페이지 내용" 
    },
    {
      "content" : "2페이지 내용" 
    }
  ]
}
```

400 Bad Request
```JSON
{
  "errorCode" : 400,
  "errorMessage" : "해당 소설을 찾을 수 없습니다.",
  "errorPath" : "/api/novel/episode	"
}
```

400 Bad Request
```JSON
{
  "errorCode" : 400,
  "errorMessage" : "무료 소설은 가격을 등록할 수 없습니다.",
  "errorPath" : "/api/novel/episode	"
}
```

# 선호작 등록
## 선호작 등록
`POST /api/novel/favorite`
### | Request
Header
```
Authorization : {token}
```
Parameter
```
novelId={novelId}
```

### | Response
200 OK
```JSON
{
  "id" : 1,
  "title" : "제목",
  "author" : "작성자",
  "description" : "설명",
  "freeType" : "NON-FREE",
  "genre" : "FANTASY"
}
```

401 Unauthorized
```JSON
{
  "errorCode" : 401,
  "errorMessage" : "로그인이 필요한 접근입니다.",
  "errorPath" : "/api/novel/favorite	"
}
```

## 선호작 마지막 읽은 페이지 등록
`POST /novel/lastReadedPage`
### | Request
Header
```
Authorization : {token}
```
RequestBody
```JSON
{
	"novelId" : 1,
  "episodeId" : 1,
  "page" : 1
}
```

### | Response
200 OK
```JSON
{
 	"novelId" : 1,
  "episodeId" : 1,
  "page" : 1
}
```

401 Unauthorized
```JSON
{
  "errorCode" : 401,
  "errorMessage" : "로그인이 필요한 접근입니다.",
  "errorPath" : "/api/novel/favorite	"
}
```

## 선호작 조회
`GET /novel/lastReadedPage`
### | Request
Header
```
Authorization : {token}
```

### | Response
200 OK
```JSON
{
  {
  "novel" : {
    "id" : 1,
      "title" : "제목",
      "author" : "작성자",
      "description" : "설명",
      "freeType" : "NON-FREE",
      "genre" : "FANTASY"
	},
  	"episode" : 12,
  	"page" : 14
	},
	...
}
```


# 포인트 충전

## 포인트 충전
- `POST /api/point`
### | Request
Header
```
Authorization : {token}
```
RequestBody
```
{
	"chargePoint" : 100
}
```



### | Response
200 OK
```JSON
{
  "chargePoint" : 100
}
```

400 BadRequest - 동시 요청에 대한 예외처리
``` JSON
{
  "errorCode" : 400,
  "errorMessage" : "이미 충전이 진행중입니다.",
  "errorPath" : "/api/point"
}
```
# 소설 구매
## 소설 구매 
- `POST /api/pay`
### | Request
Header
```
Authorization : {token}
```

RequestBody
```JSON
{
	"novelId" : 1,
  "episodeId" : 1
}
```

### | Response
200 OK
```JSON
{
	"novelId" : 1,
  "episodeId" : 1
}
```

400 Error
``` JSON
{
  "errorCode" : 400,
  "errorMessage" : "포인트가 부족합니다.",
  "errorPath" : "/api/pay"
}
```

400 Error - 동시 요청에 대한 예외처리
``` JSON
{
  "errorCode" : 400,
  "errorMessage" : "이미 구매가 진행중입니다.",
  "errorPath" : "/api/pay"
}
```


# 랭킹
## 전체 랭킹 조회
`GET /api/ranking/all`
### | Request
### | Response
200 OK
```JSON
{
  {
    "id" : 1,
    "title" : "소설 제목 2",
    "author" : "작성자",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 100
	},
	{
    "id" : 2,
    "title" : "소설 제목 2",
    "author" : "작성자 2",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 90
	},
	...
}
```

## 무료 소설 랭킹 조회
`GET /api/ranking/free`
### | Request
### | Response
200 OK
```JSON
{
  {
    "id" : 1,
    "title" : "소설 제목 2",
    "author" : "작성자",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 100
	},
	{
    "id" : 2,
    "title" : "소설 제목 2",
    "author" : "작성자 2",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 90
	},
	...
}
```

## 유료 소설 랭킹 조회
`GET /api/ranking/non-free`
### | Request
### | Response
200 OK
```JSON
{
  {
    "id" : 1,
    "title" : "소설 제목 2",
    "author" : "작성자",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 100
	},
	{
    "id" : 2,
    "title" : "소설 제목 2",
    "author" : "작성자 2",
    "description" : "설명",
    "genre" : "FANTANSY",
    "totalHits" : 90
	},
	...
}
```

