package com.codewithsameer.blog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codewithsameer.blog.repositories.UserRepo;

@SpringBootTest
class BlogAppApisApplicationTests {
	@Autowired
	private UserRepo userRepo;
	
	Calculator c = new Calculator();
	@Test
	void contextLoads() {
			}
	
	@Test
	@Disabled
	public void repoTest()
	{		
		String className = this.userRepo.getClass().getName();
		String packName=this.userRepo.getClass().getPackageName( ) ;
		System.out.println(className) ;
		System.out.println(packName) ;		
	}
	
	@Test
	@Disabled

	void testSum() {
	//expected
	int expectedResult=36;
	//actual
	int actualResult = c.doSum(12, 12, 12);
	assertThat(actualResult).isEqualTo(expectedResult);
}
	
	@Disabled
	@Test
	void testProduct() {
	//expected
	int expectedResult = 6;
	//actual
	int actualResult = c.doProduct(3,2);
	assertThat (actualResult) . isEqualTo(expectedResult) ;
	}
	
	@Test
	void testCompareTwoNumbers()
	{
		boolean actualResult=c.compareTwoNumbers(3, 3);
		boolean expectedResult =true;
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}
	
	
	}
