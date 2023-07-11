해당 프로젝트는 **점프 투 스프링부트 온라인 자료**를 바탕으로 진행되었습니다.

출처 : https://wikidocs.net/book/7601

## Build.gradle
### 1. Implementation(ex. JPA)
**build.gradle 파일의 implementation은 해당 라이브러리 설치를 위해 일반적으로 사용하는 설정이다.** implementation은 해당 라이브러리가 변경되더라도 이 라이브러리와 연관된 모든 모듈들을 컴파일하지 않고 직접 관련이 있는 모듈들만 컴파일하기 때문에 rebuild 속도가 빠르다.
### 2. runtimeOnly(ex. H2 DB)
build.gradle 파일의 runtimeOnly는 **해당 라이브러리가 런타임(Runtime)시에만 필요한 경우에 사용한다.** 컴파일(Compile)시에만 필요한 경우에는 runtimeOnly 대신 compileOnly를 사용한다.
### 3. compileOnly(ex. Lombok)
build.gradle 파일의 compileOnly는 **해당 라이브러리가 컴파일 단계에서만 필요한 경우에 사용한다.**
### 4. annotationProcessor(ex. Lombok)
**컴파일 단계에서 애너테이션을 분석하고 처리하기 위해 사용한다.**
### 5. developmentOnly(ex. SpringBoot Devtools)
**Gradle의 developmentOnly는 개발환경에만 적용되는 설정이다. 즉, 운영환경에 배포되는 jar, war 파일에는 developmentOnly로 설치된 라이브러리가 제외된다.**

## JPA 엔티티 클래스 작성
엔티티란, 실제 DB의 테이블과 매핑되는 자바 진영 객체이다. 이는 실제 DB 데이터와
연관된 클래스이기 때문에 직접 접근하는 것을 지양하고 있다. 자바의 평범한 객체를
하나의 엔티티로 인식할 수 있도록 애노테이션을 붙여야 한다.
### @Entity
`@Entity`를 사용하면, JPA가 해당 클래스를 엔티티로 인식한다.
### @Id
엔티티는 고유의 속성 값이 필요하다. 이를 DB 쪽에서는 PK(Primary Key)라고 하는데,
엔티티에 PK를 지정하기 위해서는 해당 필드 위에 `@Id`를 붙이면 된다. 이는 각 데이터들을 구분하는 고유 값이기 때문에
중복되면 안된다.
### @GeneratedValue
`@GeneratedValue`를 사용하면 따로 설정하지 않아도 자동으로 해당 필드 값이 자동으로 1씩 증가한다.
주로, PK에 해당하는 필드에 같이 사용한다. 그러면 값이 생성될 때마다 알아서 PK 값이 자동으로 부여된다.
따라서 `@Id`가 중복된 값을 갖지 않게 된다.
#### GenerationType.IDENTITY
해당 컬럼만의 독립적인 시퀀스를 생성하여 번호를 증가시킬 때 사용한다. 즉, 값을 부여하는 기준이 해당 컬럼인 것이다.
예를 들어, 해당 컬럼 값이 2라면 그 다음 생성된 데이터의 컬럼 값은 3인 것이다.
### @Column
보통 엔티티에 정의한 필드들은 자동으로 테이블의 컬럼으로 인식된다. 하지만 특정 컬럼에 세부설정을 해야 할 경우
`@Column`을 사용한다. `length`는 컬럼의 길이를 설정할때 사용하고 `columnDefinition`은 컬럼의 속성을 정의할 때 사용한다. 
`columnDefinition = "TEXT"`은 "내용"처럼 글자 수를 제한할 수 없는 경우에 사용한다.
#### 테이블 컬럼명
엔티티에서 작성한 이름은 카멜 케이스이다. (ex. createDate) 이는 실제 테이블에서는
스네이크 케이스로 변경된다. (ex. createDate(엔티티) -> create_date(실제 테이블 컬럼))

