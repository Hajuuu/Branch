# 목차
- OOP
- SOLID
- 
# OOP is A PIE
## 객체
## A : Abstraction; 추상화
- 공통의 속성이나 기능을 묶어 이름을 붙이는 것
- 현실 객체 → (추상화) → 클래스 → (추상화) → 추상클래스 / 인터페이스
- 공통적인 기능 : 추상화
- 세부적인(다른) 기능 : 구체화
- 장점 : 개념의 복잡성 감소, 코드 재사용성 증가
- 단점 : 설계의 복잡성 증가, 과도한 일반화
- 다음에 나오는 다형성과 연계되어 동작

```JAVA
// 베인 : A(평타), Q(구르기), W(은화살), E(선고), R(결전의 시간)
public Vayne {

    private final static int 사거리 = 550;
    private static int HP, MP, AD, AS, AP, level;

    public Vayne(int HP, int MP, int AD, int AS, int AP){
        this.HP = HP;
        this.MP = MP;
        this.AD = AD;
        this.AS = AS;
        this.AP = AP;
        this.level = 1;
    }

    public boolean attack(String 대상, int 거리) {
        if (거리 < 사거리) {
            System.out.println(대상 + "에게 화살을 쏜다.");
        }
        return true;
    }

    public void Q(int skill_level) {
        System.out.println("구른다.");
        if (attack) this.AD + 계수/스킬레벨*skill_level;
    }
    ...
}

// 케이틀린 : A(평타), Q(필트오버 피스메이커), W(요들잡이 덫), E(90구경 투망), R(비장의 한발)
public Caitlyn {

    private final static int 사거리 = 650;
    private static int HP, MP, AD, AS, AP, level;

    public Vayne(int HP, int MP, int AD, int AS, int AP){
        this.HP = HP;
        this.MP = MP;
        this.AD = AD;
        this.AS = AS;
        this.AP = AP;
        this.level = 1;
    }

    public boolean attack(String 대상, int 거리) {
        if (거리 < 사거리) {
            System.out.println(대상 + "에게 총을 쏜다.");
        }
        return true;
    }

    public void Q(int skill_level) {
        System.out.println("소총을 장전해서 쏜다.");
    }
    ...
}
```
## P : Polymorphism; 다형성
- 다형성 : 어떤 객체의 속성이나 기능이 상황에 따라 여러 형태를 가질 수 있는 성질
- 자바에서 하나의 객체가 여러가지 타입을 가질 수 있음을 의미
- 부모 클래스 → 자식 클래스 : 참조 가능
- 자식 클래스 → 부모 클래스 : 참조 불가능
```JAVA
// 베인 : A(평타), Q(구르기), W(은화살), E(선고), R(결전의 시간)
public Vayne {

    private final static int 사거리 = 550;
    private static int HP, MP, AD, AS, AP, level;

    public Vayne(int HP, int MP, int AD, int AS, int AP){
        this.HP = HP;
        this.MP = MP;
        this.AD = AD;
        this.AS = AS;
        this.AP = AP;
        this.level = 1;
    }

    public boolean attack(String 대상, int 거리) {
        if (거리 < 사거리) {
            System.out.println(대상 + "에게 화살을 쏜다.");
        }
        return true;
    }

    public void Q(int skill_level) {
        System.out.println("구른다.");
        if (attack) this.AD + 계수/스킬레벨*skill_level;
    }
    ...
}

// 케이틀린 : A(평타), Q(필트오버 피스메이커), W(요들잡이 덫), E(90구경 투망), R(비장의 한발)
public Caitlyn {

    private final static int 사거리 = 650;
    private static int HP, MP, AD, AS, AP, level;

    public Vayne(int HP, int MP, int AD, int AS, int AP){
        this.HP = HP;
        this.MP = MP;
        this.AD = AD;
        this.AS = AS;
        this.AP = AP;
        this.level = 1;
    }

    public boolean attack(String 대상, int 거리) {
        if (거리 < 사거리) {
            System.out.println(대상 + "에게 총을 쏜다.");
        }
        return true;
    }

    public void Q(int skill_level) {
        System.out.println("소총을 장전해서 쏜다.");
    }
    ...
}
```
## I : Inheritance; 상속
## E : Encapsulation; 캡슐화

# SOLID
