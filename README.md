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


