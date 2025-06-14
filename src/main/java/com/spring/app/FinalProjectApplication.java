package com.spring.app;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication

@MapperScan(
  basePackages    = "com.spring.app",                // 팀 프로젝트 전역을 봐도 되고
  annotationClass = org.apache.ibatis.annotations.Mapper.class  // @Mapper 가 붙은 것만!
)

@EnableScheduling
// 여기에 매퍼가 위치한 패키지를 지정

public class FinalProjectApplication {
  public static void main(String[] args) {
    SpringApplication.run(FinalProjectApplication.class, args);
  }
}