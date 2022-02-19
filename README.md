# springboot-aws-webservice
***
## 스프링부트와 AWS로 혼자 구현하는 웹서비스

<img src="https://image.yes24.com/goods/83849117/XL" width="100">
- 
## ISSUE 해결
### Junit4 사용을 위한 build.gradle에 코드 추가
```
testImplementation("org.junit.vintage:junit-vintage-engine") {
 exclude group: "org.hamcrest", module: "hamcrest-core"
}
```

### spring테이블 생성 안됨
```
  // initialize-schema: always를 추가
  session:
    store-type: jdbc
    jdbc:
      initialize-schema: always
```
