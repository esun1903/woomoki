# 우목이 🌳 (우린 다 목표를 이룰거야 ) .

![logo](/uploads/2d3df481f6a0d3c3761deffb5034cb0c/logo.PNG)

 

### 🌱 팀원 소개

![팀원최종](/uploads/a20b541badf7d13b0171acbf9e4731a7/팀원최종.PNG)
  

### 🌳  프로젝트 개요

프로젝트 기간 : 2021.01.11 ~ 2021.02.19


       자신의 도전,목표를 즐겁고 끝까지 달성하고 싶은 사람
       같은 목표를  가진 사람들과 함께 외롭지 않게 이루고 싶은 사람
       다양한 사람들의 도전을 보며 자극을 받으며 나아가고 싶은 사람
       
       웹사이트 이름 : 우목이(우린 다 목표를 이룰거야)
       디자인 컨셉 : 도전,목표를 하나의 씨앗으로  시작하여 거대한 나무를 키우는 것처럼 목표를 이룸 


#### 🌱  Tech Log  &  Cooperation

 저희의 진행 상황은 스크럼과 회의록을 통해 진행하고 있습니다. 
 스크럼은  1일 1스크럼으로  노션에 올려 이슈들을 공유하고 있으며 
 회의를 진행할 때마다 회의록을 작성하여 일차별로 정리하고 있습니다.

[프로젝트 공유문서](https://www.notion.so/A303-1d48727b951b41a18886118e55d04fb8)
![스크럼](/uploads/23af022e3d6741638aa7bd1651bb2038/스크럼.PNG)



### 🌳 Tech Stack

#### 🌱 system architecture

![시스템_구조도](/uploads/521385d54611d84f99e3e0572a5edf9f/시스템_구조도.PNG)


#### 🌱 ERD
![erd이미지_최종](/uploads/93dd266aae0b4d6e79298ebca9fecbe8/erd이미지_최종.PNG)
#### 🌱실행 방법 

프론트엔드 부분

Vue.js [Vue.js 설치](https://kr.vuejs.org/v2/guide/index.html)를 통해 설치할 수 있습니다. 

npm install 

npm run serve 를 통해 실행할 수 있습니다. 

localhost:8080으로 실행할 수 있습니다.


백엔드 부분 

Java(openjdk version "1.8.0_282") 설치합니다.
gradle 설치

DB 테이블 생성


appication.properties 파일 생성  후
아래 내용에 맞게 입력
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.url=(내용 입력)
spring.datasource.username=(내용 입력)
spring.datasource.password=(내용 입력)

/backend/src/main/resource/ 아래 위치 파일을 넣습니다. 


/backend/ 안에   ->   $ ./gradlew build 빌드 후
/backend/build/lib 안에 
$ java -jar ssafy_pjt2-0.0.1-SNAPSHOT.war --server.servlet.context-path=(실행할 서버주소)

살행 





###  🌳 개발목표


#### 🌱문제인식  

새해 목표를 꾸준히 지민 성인남녀 28% 달성률이 낮은 것을 볼 수 있습니다. 
![문제인식수정](/uploads/f8d864ea1f3edbd73fd505d954099331/문제인식수정.png)
![해결방안](/uploads/049afd0d4caba31a6632b44215ae26c6/해결방안.PNG)

#### 🌱주요 서비스 기능

![서비스_기능](/uploads/17b64fcc5f11b79c63f2acc7dcd5bb03/서비스_기능.PNG)


## 🌱기능

- 소셜 로그인 및 회원가입 가능

- 목표관리 등록, 수정, 삭제 기능

- 인증제를 통한 목표관리 기능

- SNS 형식으로 팔로우, 팔로잉 기능

- 관심 카테고리의 챌린지가 메인에 보이는 기능

- 내 정보 관리기능

- 알림 기능 


### 🌳 페이지 소개 

#### 회원가입 , 로그인 , 비밀번호 찾기 페이지

##### main 페이지

##### 챌린지 리스트 

##### SNS 계정 페이지  

##### 프로필 변경 페이지




