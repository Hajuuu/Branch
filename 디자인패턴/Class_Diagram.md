<p style="line-height: 10px">
### 클래스 다이어그램

- 특징
	- 구조 다이어그램
	- 클래스 내부(멤버변수, 메소드)와 클래스 상호간의 관계 도식
	- 구현순서 : 개념 → 명세 → 구현
	1. 개념 : 클래스만 도출하기
	2. 명세 : 코드에 넣을 멤버변수, 메서드 등 정하기
	3. 구현 : 실제 코드 기반으로 그리기

- 구성요소
	- 클래스
		- 그냥 클래스(멤버변수, 메소드)
		- 추상 클래스(<i>Classname</i>, {abstract}, <i>&lt;&lt;abstract&gt;&gt;</i>)
		- 인터페이스(<i>&lt;&lt;interface&gt;&gt;</i>)
	- 관계(화살표)
		<img width="350" alt="다운로드" src="https://github.com/lynne921/Ssabalja/assets/119817396/138a3cdc-3cff-4656-b974-087afbafbe65">
		- Inheritance : 상속(부모 - 자식관계)
			- 실선 + 흰 화살표
			- 자식 : 부모의 복제품
			- 부모를 가리킴
		- Realization(Implementation) : 실체화(구현)
			- 추상클래스 / 인터페이스의 추상화된 메서드를 구현하는 관계
			- 인터페이스를 가리킴
		- Assoiciation : 연관
			- 클래스 간 참조관계(깊은 참조)
				- 멤버 변수로 다른 객체의 참조를 가지고 있을 때
			- 실선 + 빈 화살표
			- 참조 = reference
			- 방향이 있을 때 : 한쪽이 반대쪽 참조
			- 서로 가리킬때 : 서로 참조
			- A라는 객체가 B라는 객체를 참조하면 : B객체를 통해 A객체 수정 가능
				- 배열 깊은 복사 / 얕은 복사 생각하면 편함
![화면 캡처 2023-09-19 113019](https://github.com/lynne921/Ssabalja/assets/119817396/9df3beb2-8f4c-41c9-a8d4-36526464bfb2)
		- Dependency : 의존
			- 클래스 간 참조관계(얕은 참조)
				- 로컬변수나 메소드의 파라미터-반환에 사용되는 관계
				- 연관 관계와의 차이점 : 지속성
					- 연관 관계는 한번 호출되면 클래스 내에서 계속되고, 의존 관계는 호출된 스코프와 함께 종료되는 관계
					- 의존관계 vs 연관관계?? → 설계의 중요한 측면 : '결합도와 응집도'
			- 점선 + 빈 화살표
			- 양방향, 단방향 가능(연관과 동일)
			```java
			public class Phone { 
				public void call() {
					System.out.println("통화 시작");
					System.out.println("통화 종료");
				}
			}
			// 연관 관계
			public class Person { 
				// 멤버 변수로 정의
				private Phone phone;
				
				public void call() { 
					phone.call();
				} 
			}
			// 의존 관계
			public class Person { 
				//지역 변수(파라미터 변수)로 정의
				public void call(Phone phone) { 
					phone.call();
				} 
			}
			```
		- Aggregation : 집합
			- 사실상 연관관계의 한 모습
			- 집합 : 부분이 전체의 일부가 될때
			- 실선 + 빈 마름모

		- Composition : 구성(합성)
			- 부분이 전체에 완전히 종속적 + 라이프사이클 관리
				- 집합과의 차이점 : 라이프사이클
				- 전체가 사라지면 부분도 같이 사라져야 함 
			- 실선 + 꽉찬 마름모
			```java
			// 구성 관계
			public class Person { 
				// 보통 private 접근 제한자와 사용
				private Head head; 
				private Body body; 
				private List<Leg> legs; 
				public Person() { 
					super(); 
					this.head = new Head(); 
					this.body = new Body(); 
					this.legs = Arrays.asList(new Leg(), new Leg()); } 
				public void growUp() { 
					head.growUp(1); 
					body.growUp(2); 
					legs.stream().forEach(leg -> leg.growUp(5)); 
				} 
				public void checkLengths() { 
					System.out.println(head); 
					System.out.println(body); 
					legs.stream().forEach(System.out::println); 
					} 
				}
			// 집합 관계
			public class Person { 
				private Head head; 
				private Body body;
				// private 접근 제한자 사용 x 
				List<MechanicalLeg> MechanicalLegs;
				   = Arrays.asList(new MechaLeg(), new MechaLeg()); 
				public Person() { 
					super(); 
					this.head = new Head(); 
					this.body = new Body(); 
					this.MechanicalLegs = Arrays.asList(new MechaLeg(), new MechaLeg()); 
				public void die(){
					System.out.println(this.MechanicalLegs + "을 얻었다");
				}
			```
< 다음 시간에 계속>
</p>
