# UKREAM

한정판 거래 플랫폼 "**KREAM**"을 벤치마킹한 프로젝트 입니다.

**UKREAM**은 **KREAM**의 "KICKS RULE EVERYTHING AROUND ME"에서 "UNIQUE"를 앞에 붙여 만든 이름으로, "평범하지 않은 신발이 주변의 모든 것을 지배하거나 영향을 미친다"를 나타냅니다.

## 기술 스택

![기술스택 drawio (4)](https://github.com/f-lab-edu/UKREAM/assets/141301678/6ceb1ddf-acc7-4e52-b813-db51d3bfde0a)



## 코드 컨벤션

코드는 Google Code Style을 준수합니다.

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


### 기술적 이슈 및 해결 과정

[중복되는 로그인 체크 로직 AOP 분리](https://northern-catshark-6f5.notion.site/AOP-a9baff5c12d7490c83e49f36b89210d2)

[Elasticsearch를 이용한 검색 기능](https://northern-catshark-6f5.notion.site/Elasticsearch-f136ca2d556542d9a791b3a0fb8af27b)

[Logstash를 이용한 데이터 동기화](https://northern-catshark-6f5.notion.site/Logstash-47dd5f12859146f194ee374b6f782d09)

[kafka 이용한 실시간 데이터 동기화](https://northern-catshark-6f5.notion.site/Kafka-fffc8fa6764f48329252db8eea30c9ee)




