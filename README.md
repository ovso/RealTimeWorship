# Deprecated

이 프로젝트는 더이상 진행하지 않는다. 

이 프로젝트는 Youtube 사이트를 스크래핑한다. 스크래핑 결과 값 중 Json 형태의 String을 분석 후, videoId 추출하여 영생을 재생한다. 그러나 많은 영상들에서, 썸네일은 나오지만 `알 수 없는 이유`로 영상 재생이 되지 않는 문제점이 발견되었다. 해결책을 찾지 못했다. 때문에 개발을 중지하기로 했다.ㅈ

# RealTimeWorship

## Architecture

- MVVM
- RxJava3
- Room + LiveData
- Repository pattern
- fragment-ktx
- Activity-ktx
- Custom Service locator(ViewModel)
- Koin