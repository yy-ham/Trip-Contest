<p align="center">
  <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/bca2943a-fd50-40d9-b868-f1dae67182e0" width="300">
</p>

# 🗺 전국 여행자랑
여행지 정보를 소개하고, 여행 계획을 작성할 수 있는 웹 페이지
<br>
<br>

<h2>프로젝트 소개</h2>
<ul>
  <li>프로젝트명: 전국 여행자랑</li>
  <li>개발 기간: 2023.02.15 ~ 2023.03.07</li>
  <li>개발 인원: 6명</li>
  <li>개발 환경</li>
  <ul>
    <li>Back-end
      <ul>
        <li>Java 17</li>
        <li>Framework: SpringBoot 3.0</li>
        <li>DB: Oracle 21c</li>
        <li>ORM: MyBatis, JPA</li>
      </ul>
    </li>
    <li>Front-end
      <ul>
        <li>JavaScript, JQuery</li>
        <li>Framework: Bootstrap</li>
        <li>View: HTML5, CSS3, Thymeleaf</li>
      </ul>
    </li>
  </ul>
  <li>주요 기능</li>
    <ul>
      <li>여행지 정보 및 여행 계획 조회, 검색</li>
      <li>여행 계획 작성</li>
      <li>좋아요 목록 조회</li>
      <li>관리자 페이지, 여행지 정보 임시 저장</li>
    </ul>
</ul>
<br>
<br>

<h2>서비스 흐름도</h2>
<p>
  <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/35973d15-69cf-4577-97b9-31eb1ddbbee9" width="800">
</p>
<br>
<br>

<h2>DB 설계</h2>
<p>
  <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/7e365f35-d943-420b-a481-0c4d1577a867">
</p>
<br>
<br>

<h2>담당 기능</h2>
▶를 누르면 상세 내용을 볼 수 있습니다.
<br>
<br>
<details>
  <summary>여행계획 CRUD 기능</summary>
  <br>
  <b>1. 여행 계획 목록 페이지</b>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/cac5286a-6e07-469e-a15b-08ba31851c5b">
  </p>
  <br>
  <br>
  
  <b>2. 여행 계획 상세 페이지</b>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/1df9d5d1-8f58-4a65-87ab-3d9f96d595ed">
  </p>
  <br>
  <br>
  
  <b>3. 여행 계획 작성 페이지</b>
  <ul>
    <li>데이터베이스에 있는 여행지 목록을 전국 또는 지역별로 조회</li>
    <li>여행지를 선택하면 화면 좌측 날짜 탭에 여행지 정보 추가</li>
    <li>여행일수 최대 4일까지 선택 가능</li>
    <li>여행 계획 작성 시 여행지별, 날짜별 또는 전체 초기화 가능</li>
  </ul>
  <br>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/deeccd8f-999c-46ed-9221-a91f9585eea4">
  </p>
  <br>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/28d56563-7a0f-4365-93af-16a049c86437"><br>
    ▲ 여행 계획 작성
  </p>
  <br>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/8586cf13-aec2-494a-b824-58e8cbe31fe0"><br>
    ▲ 여행일수 선택
  </p>
  <br>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/6569cf19-e4ec-4ea1-b1c4-c6e05058e1cf"><br>
    ▲ 여행지별, 날짜별 또는 전체 초기화
  </p>
  <br>
  <br>
  
  <b>4. 여행 계획 수정 페이지</b>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/8a38a09b-ff34-46bb-93ed-e1783afaab5e">
  </p>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/03e1e17f-a2b1-463d-81a3-8a5e3d3d885d"><br>
    ▲ 여행 계획 수정
  </p>
  <br>
</details>
<details>
  <summary>댓글 CRUD 기능</summary>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/387ac490-db7c-402e-bf54-9e25910ec7d3"><br>
    ▲ 댓글 작성, 수정 및 삭제
  </p>
</details>
<details>
  <summary>상세 페이지 좋아요 CRUD 기능</summary>
  <p>
    <img src="https://github.com/yy-ham/Trip-Contest/assets/118264054/8f5cbe69-6910-4201-a6ef-56ddec1b47b2"><br>
    ▲ 좋아요 및 좋아요 취소
  </p>
</details>
<br>
<br>

