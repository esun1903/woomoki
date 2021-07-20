## 🌳 우목이 - 우린 목표를 이룰 거야 <br>


<img src = "https://user-images.githubusercontent.com/38427646/126250991-a5e359ca-0938-42cb-b074-7c7856dbdc11.png" width="400px">
<br>

## 🌳  프로젝트 개요

<b>프로젝트 기간 : 2021.01.08 ~ 2021.02.19</b>

**자신의 목표 설정을 넘어 다함께 목표를 설정하고 및 인증하는 웹 서비스**

- 자신의 도전,목표를 즐겁고 끝까지 달성하고 싶은 사람
- 같은 목표를  가진 사람들과 함께 외롭지 않게 이루고 싶은 사람
- 다양한 사람들의 도전을 보며 자극을 받으며 나아가고 싶은 사람을 위한 서비스

**디자인 컨셉** : 도전,목표를 하나의 씨앗으로 시작하여 거대한 나무를 키우는 것처럼 목표를 이루는 컨셉 </b>

<br>

## 👩🏻‍💻👨🏻‍💻 우목이를 만든 사람들

| 팀원   | 역할    |
| ------ | ------- |
| 최은선 | BE      |
| 표기동 | BE      |
| 김효진 | FE      |
| 김용민 | FE    |
| 홍지희 | FE,팀장 |

<br>

### ❓문제인식  

**새해 목표를 꾸준히 지킨 성인남녀가 28%로 달성률이 현저히 낮은 것**을 볼 수 있습니다. 
<br>

<img src = "https://user-images.githubusercontent.com/38427646/126270492-64caf202-1155-45d1-a86d-96c574a1ca21.png" width="800px">

### 👨🏻‍💼 페르소나 설정
**목표 달성이 쉽지 않은 32세 직장인 김경민씨**
<br>
<img src = "https://user-images.githubusercontent.com/38427646/126270878-41bec00d-65a9-416d-b8f3-8b79c3747615.png" width="800px">

### 💁🏻‍♀️ 해결방안 
<img src = "https://user-images.githubusercontent.com/38427646/126271519-1c1e07ab-b6ea-44ac-b423-cb1a69f7fe3c.png" width="800px">

<br>

### ✔️주요 서비스 기능
1. 소셜 로그인 및 회원가입 가능

2. 목표관리 등록, 수정, 삭제 기능

3. 인증제를 통한 목표관리 기능

4. SNS 형식으로 팔로우, 팔로잉 기능

5. 관심 카테고리의 챌린지를 주로 보여주는 기능

6. 내 정보 관리기능

7. 알림 기능

8. 챌린지에 맞는 인증이 되었는지 확인하고 이야기할 수 있는 기능
<br>

<img src = "https://user-images.githubusercontent.com/38427646/126271817-d7786a5b-c3d0-4532-89be-20f4285b66c3.png" width="800px">



### 🌱 Tech Log  &  Cooperation

 진행 상황은 **스크럼과 회의록**을 통해 진행하고 있습니다. 

 **1일 1스크럼**으로 노션에 올려 이슈들을 공유하고 있으며

 **회의를 진행 시 회의록을 작성**하여 일차별로 정리하고 있습니다.

[프로젝트 공유문서](https://www.notion.so/A303-1d48727b951b41a18886118e55d04fb8)

<br>

#### [ 🌳 상세페이지 이미지 ](https://www.notion.so/7156847a4022484da2f3002e27f20941)
#### [ 🌳 시연영상 ](https://www.notion.so/No-04c62b36acc64a9c9cbcb02ec6668851)
#### [ 🌳 실제 홈페이지 ](http://i4a303.p.ssafy.io/)

#### [🌱 컨벤션](https://www.notion.so/332cddb89bff4354b3aee8bc1d2746a8)
#### [🌱 BE-Api](https://www.notion.so/a4d59235ba6f4795b0c7677414e0d766)

<br>

### 🌱 Tech Stack
#### - System architecture

<img src = "https://user-images.githubusercontent.com/38427646/126262711-ae506989-798f-4143-978a-882827bdd6ef.png" width="800px">

<br>

<details>
### <summary><b>✔️ERD</b></summary>
<img src = "https://user-images.githubusercontent.com/38427646/126273089-c15b39f8-c1eb-4cff-a8c9-f6d9caf6aee0.png" width="800px">
</details>

<br>

### 💻 실행 방법

#### ☑️ 프론트엔드 부분

1. Vue.js [Vue.js 설치](https://kr.vuejs.org/v2/guide/index.html)를 통해 설치할 수 있습니다. 

  ``` $npm install ```

   ```npm run serve``` 를 통해 실행할 수 있습니다. 

    localhost:8080으로 실행할 수 있습니다.

<br>

#### ☑️ 백엔드 부분

1. Java(openjdk version "1.8.0_282") 설치합니다.

2. gradle 설치

3. DB 테이블 생성

4. ```appication.properties``` 파일 생성  후

아래 내용에 맞게 입력

```spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

   spring.datasource.url=(내용 입력)

   spring.datasource.username=(내용 입력)

   spring.datasource.password=(내용 입력)

```

5. /backend/src/main/resource/ 아래 위치 파일을 넣습니다. 

6. /backend/ 안에   ->   $ ./gradlew build 빌드 후

7. /backend/build/lib 안에 

```$ java -jar ssafy_pjt2-0.0.1-SNAPSHOT.war --server.servlet.context-path=(실행할 서버주소)```
8. 실행



