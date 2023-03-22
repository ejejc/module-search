### Redis 라이브러리 사용 
### - Docker 실행
```shell
 docker run -d -p 6379:6379 redis
```
### - Jar File 다운로드
* https://drive.google.com/file/d/1N_iY3n3Jc3nrcWi5ukxjLbHpD5eMGTGE/view?usp=sharing

### - 어플리케이션 실행
* search-app-api-0.0.1-SNAPSHOT.jar

### - 사용 이유
* 트래픽이 많은 기능에서 사용자가 검색 액션이 발생할때 마다 DB I/O 작업이 일어나는 것은 DB 부하 증가 가능성이 높아 이를 해결하기 위해 사용
* 동시성 이슈를 해결하기 위해 사용

### - 모듈 구조
* module-redis
  * about redis db 모듈
* module-domain
  * 공통 api domain 모듈
* module-internal-core
  * 비즈니스 공통 모듈 (ex. Exception class)
* clients
  * 서버간 통신 모듈
* search-app-api
  * Application 실행 모듈

### - API 명세서

### Swagger 주소 
* http://localhost:8080/swagger-ui/index.html

### [ 블로그 검색 API ]
* URL : [GET] http://localhost:8080/api/search/blog
* Request Param
  * query - 필수
    * 검색어
  * sort
    * ACCURACY - 정확도순 / RECENCY - 최신순
  * page
    * 결과 페이지 번호
  * size
    * 한 페이지에 보여질 문서수 

### [ 인기 검색어 조회 API ]
* URL : [GET] http://localhost:8080/api/search/popular/query
