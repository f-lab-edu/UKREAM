# UKREAM

한정판 거래 플랫폼 "**KREAM**"을 벤치마킹한 프로젝트 입니다.

**UKREAM**은 **KREAM**의 "KICKS RULE EVERYTHING AROUND ME"에서 "UNIQUE"를 앞에 붙여 만든 이름으로, "평범하지 않은 신발이 주변의 모든 것을 지배하거나 영향을 미친다"를 나타냅니다.

## 기술 스택

![기술스택 drawio (4)](https://github.com/f-lab-edu/UKREAM/assets/141301678/6ceb1ddf-acc7-4e52-b813-db51d3bfde0a)



## 코드 컨벤션

코드는 Google Code Style을 준수합니다.

## 기술적 이슈 및 해결 과정

[중복되는 로그인 체크 로직 AOP 분리](https://northern-catshark-6f5.notion.site/AOP-a9baff5c12d7490c83e49f36b89210d2)

[Elasticsearch를 이용한 검색 기능](https://northern-catshark-6f5.notion.site/Elasticsearch-f136ca2d556542d9a791b3a0fb8af27b)

[Logstash를 이용한 데이터 동기화](https://northern-catshark-6f5.notion.site/Logstash-47dd5f12859146f194ee374b6f782d09)

[kafka 이용한 실시간 데이터 동기화](https://northern-catshark-6f5.notion.site/Kafka-fffc8fa6764f48329252db8eea30c9ee)



## 브랜치 관리 전략

![48032310-63842400-e114-11e8-8db0-06dc0504dcb5](https://github.com/f-lab-edu/UKREAM/assets/141301678/0f4133aa-cb03-43b7-8dfe-98c7475995f5)


### 기능 개발

1. `main` 브랜치에서 새로운 기능 개발을 위해 새로운 브랜치 생성: `git checkout -b feature/feature-name`
2. 기능 구현 및 변경 사항 커밋: `git commit -m "msg"`
3. 변경 사항을 원격 저장소에 푸시: `git push origin feature/feature-name`
4. GitHub에서 새로운 Pull Request 생성

### Pull Request 및 Merge

1. 코드 리뷰 및 변경 사항이 필요한 경우, 해당 사항을 해결합니다.
2. 리뷰어가 코드를 승인하면 `main` 브랜치로 머지합니다.



## 프로토타입


### 로그인,회원가입
<img width="361" alt="스크린샷 2023-10-21 오후 3 00 58" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/867453ce-7d50-44c0-b93f-cda67d120200">
<img width="363" alt="스크린샷 2023-10-21 오후 3 03 22" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/8b4d2401-a16b-4ac0-ba30-6e4e56d4b3cb">

### HOME
<img width="364" alt="스크린샷 2023-10-21 오후 3 04 32" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/d190c048-1e78-4114-821b-1fc5a4f07f85">
<img width="363" alt="스크린샷 2023-10-21 오후 3 05 18" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/32011ef4-5c9a-40cd-afaa-339a5d55d534">
<img width="363" alt="스크린샷 2023-10-21 오후 3 07 42" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/470a617e-5b48-49fa-aeed-bf0aeaa8cf62">

### 검색
<img width="363" alt="스크린샷 2023-10-21 오후 3 09 17" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/55ad6344-ff6c-4f89-ba4c-6d5792eaf80f">
<img width="363" alt="스크린샷 2023-10-21 오후 3 10 03" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/9d1c5d84-3fa1-41c8-ab00-090c83b5fecd">

### SHOP , 상품상세
<img width="363" alt="스크린샷 2023-10-21 오후 3 09 17" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/55ad6344-ff6c-4f89-ba4c-6d5792eaf80f">
<img width="363" alt="스크린샷 2023-10-21 오후 3 12 18" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/263042a3-fd38-4da1-9c91-56718ca4b0ba">


### 구매하기
<img width="363" alt="스크린샷 2023-10-21 오후 3 13 13" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/4a791622-a927-4e9c-a557-fbfb0a3d133e">
<img width="363" alt="스크린샷 2023-10-21 오후 3 13 46" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/eeb0fec8-22ff-498f-b8a7-c88489f48dc8">
<img width="363" alt="스크린샷 2023-10-21 오후 3 14 14" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/067452ba-b79b-4bdd-85a5-6de68ae51950">

### 판매하기
<img width="363" alt="스크린샷 2023-10-21 오후 3 15 29" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/36d7820d-9024-488f-8923-9bd9d16dbc87">
<img width="363" alt="스크린샷 2023-10-21 오후 3 16 13" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/9c5f10f6-05ee-4d14-a000-ffa39ee6c14d">
<img width="363" alt="스크린샷 2023-10-21 오후 3 18 13" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/4903395a-93c2-455e-aae2-0a9944f42546">


### MY
<img width="362" alt="스크린샷 2023-10-21 오후 3 30 29" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/54ff3e3b-e536-4a34-a926-41e16e48c869">
<img width="363" alt="스크린샷 2023-10-21 오후 3 32 21" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/33df56e3-dd29-458d-9f52-72dd5ef5df69">
<img width="363" alt="스크린샷 2023-10-21 오후 3 33 24" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/0391d9bc-a2f1-4ed5-adaa-43efc546c7b2">
<img width="364" alt="스크린샷 2023-10-21 오후 3 34 28" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/e9643918-7c50-4ee8-89ff-1363f436c9ea">
<img width="363" alt="스크린샷 2023-10-21 오후 3 35 23" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/0aa12b13-3d85-4d3b-bc7d-bf0df1dfc234">
<img width="364" alt="스크린샷 2023-10-21 오후 3 36 24" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/7bd6609d-b2e6-4ed2-94fe-f44e2e09cda3">
<img width="363" alt="스크린샷 2023-10-21 오후 3 36 55" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/a041a248-4ab8-4c8c-9d89-dacc1b66391f">
<img width="364" alt="스크린샷 2023-10-21 오후 3 37 20" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/24d24e58-c79c-41ab-ba77-2b92ffe7f00e">


## ERD

<img width="1002" alt="스크린샷 2023-10-23 오후 9 15 36" src="https://github.com/f-lab-edu/UKREAM/assets/141301678/736355fd-384d-4a00-8c58-13572f23d50f">






