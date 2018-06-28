package com.gongdel.springbootjpa;

import com.gongdel.springbootjpa.domain.user.Gender;
import com.gongdel.springbootjpa.domain.user.User;
import com.gongdel.springbootjpa.domain.user.UserProfile;
import com.gongdel.springbootjpa.repository.user.UserProfileRepository;
import com.gongdel.springbootjpa.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Calendar;

/**
 * <JPA Auditing>
 * 	Note.class에서, 'createdAt'과 'updateAt' field에 @CreatedDate와 @LastModifiedDate annotation을 사용했다.
 *	우리가 원하는 것은, entity를 create하거나 update할 때마다 해당 field가 자동적으로 채워지는 것이다.
 *	이를 달성하기 위해서는 다음의 두 가지가 필요하다.
 *	1. Spring Data JPA의 'AuditingEntityListener을 domain model에 추가한다.
 *	2. JPA Auditing을 main application에 활성화한다(밑의 EnableJpaAuditing).
 */
@EnableJpaAuditing
@SpringBootApplication
public class SpringbootjpaApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserProfileRepository userProfileRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// database table 정리
		userProfileRepository.deleteAllInBatch();
		userRepository.deleteAll();

		User user = new User("hojin", "gong", "gongdel@gmail.com", "gongdel");
		Calendar dateOfBirth = Calendar.getInstance();
		dateOfBirth.set(1992, 2, 29);

		// Create a UserProfile instance
		UserProfile userProfile = new UserProfile("+91-8197882053", Gender.MALE, dateOfBirth.getTime(),
				"747", "2nd Cross", "Golf View Road, Kodihalli", "Bangalore",
				"Karnataka", "India", "560008");

		// parent entity(user)에 child reference(userProfile) 설정
		user.setUserProfile(userProfile);

		// 반대 설정
		userProfile.setUser(user);

		// parent reference 저장(child 또한)
		userRepository.save(user);
	}
}
/**
 	@SpringBootApplication은 다음의 spring annotation의 결합체이다.
 		- @Configuration : @Configuration annotation이 할당된 class는 Spring boot에 의해서 관리된다. 또한 다른 bean을 정의하는 source로 여기진다.
 		- @EnableAutoConfiguration : 이 annotation은 pom.xml 파일에 추가한 dependencies를 우리의 application에 자동적으로 설정한다.
			예를들면, 'spring-data-jap' or 'spring-jdbc'가 classpath에 있다면, 해당 dependency는 application.properties 파일로부터 dababase 속성을 읽음으로써 자동적으로 DataSource를 설정한다.
 		-@ComponentScan : 이는 Spring이 현재 package(com.gongdel.springbootjpa)dhk 다른 모든 sub-packages에 정의된 다른 component를 scan하거나 bootstrap하도록 한다.

 	main() method는 Spring Boot의 SpringApplication.run() method를 호출해서 application을 실행시킨다.
 **/