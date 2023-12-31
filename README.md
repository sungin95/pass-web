# pass-web
내가 만약 헬쓰장 사장이 된다면 `남은 이용권을 타인에게 양도 가능하게 하는게 어떻까` 라는 생각에서 시작한 프로젝트입니다. 

현재는 사용자가 보는 이용권 조회 페이지와 패키지를 추가(구매)하는 페이지만 있지만 

추후 관리자가 보는 이용권 관리 페이지, 통계 조회 페이지, 그리고 유저가 강사에게 PT시간을 예약 할 수 있는 
PT예약제 시스템을 개발할 계획입니다. 

## 만들게 된 계기
헬창 친구가 회사에서 바쁜 일이 생겨 헬쓰장을 못가게 되었습니다. 남은 기한이 6개월 넘게 있었고
이게 너무 아까워서 직접 판매할 사람을 찾아서 남은 기한을 양도하려고 했습니다. 
하지만 헬쓰장측에서 거절을 하였고 큰 불쾌함을 느낀 친구는 바쁜 시즌이 끝난 후에도 
집 앞 공원에 있는 운동기구를 통해 헬쓰를 하기 시작했습니다. 

헬쓰장이 큰 돈을 벌거면 당연하게도 회원 수가 많아야 합니다. 그리고 매일 헬쓰장을 갈 수 있는 보디빌더 같은 회원 보다는 
바쁜 회사 생활에서 시간을 쪼개어 운동을 하려는 직장인을 상대로 장사를 해야 합니다. 그런데 양도 기능을 막는 이런 서비스로는 직장인들에게 
큰 불쾌함과 불합리함을 느끼게 합니다. 

이러한 문제점을 해결하기 위해 사용하고 남은 이용권을 양도 가능한 이번 프로젝트를 기획하게 되었습니다. 

제작 기간: 2023/12/01 ~ (현재 진행중)

## Environments
- Intellij IDEA Ultimate
- Corretto 17.0.8 (AWS 환경에서 향상된 성능을 보여주는 OpenJDK)
- Gradle
- Spring Boot 2.7.17

## 기술 세부 스택
Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Oauth2
- Spring Batch
- Spring Boot DevTools
- Spring Configuration Processor
- Thymeleaf
- MySQL Driver
- Lombok

CI/CD
- docker
- GitHub Actions

배포
- aws ec2

그외
- Bootstrap

## 데모 페이지
http://13.209.12.93

## 기능
### CI/CD 환경 구축
이번 작업을 하면서 브랜치를 3단계로 나누어서 개발을 했습니다.(master, develop, feature/...)   
feature/... : 브랜치에서는 개발 이슈를 발행하여 이슈를 완성하면 develop에 머지를 합니다.  
develop : 목표를 완료하면 검증 후 새로운 버전으로 master에 머지를 합니다.  
master: 이 코드를 AWS EC2에 배포를 합니다. 

이 과정중에서 배포를 하게 되는 과정을 간소화 하고 싶었습니다. 
그래서 Docker와 GitHub Actions를 사용하여 CI/CD 환경을 구축하였습니다. 

### 카카오 로그인
해킹에 대한 우려 때문에 개인 정보 저장에 대한 부담이 있었습니다. 
그래서 회원가입 및 로그인 기능을 카카오 로그인을 통해서만 가능하도록 만들었습니다. 

다른 소셜로그인은 추가하지 않았습니다. 이유는
- 우리나라 국민을 상대로 하는 서비스이기 때문에 보통 카카오 아이디는 있을 거라고 생각했습니다. 
- 제가 이 사이트를 무슨 아이디로 만들었는지 다시 기억하기 힘들었던 적이 있습니다. 그래서 다다익선이 장점이라고 생각하지 않았습니다. 

### 이용권 양도 기능
현재는 간단하게 '내 이용권' 페이지에서 유저 아이디와 '남은 기한 보내기' 버튼을 클릭하면 
남은 헬쓰 기간과 PT횟수가 양도 되도록 만들었습니다. 

### 헬쓰 남은 기간 차감 기능
매일 밤 12시 batch의 Tasklet을 이용하여 남은 기한를 1씩 차감하도록 만들었습니다. 


