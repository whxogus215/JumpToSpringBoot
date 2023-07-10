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