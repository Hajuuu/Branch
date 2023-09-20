# Queue
<https://coding-factory.tistory.com/602>

## Queue 란?
- FIFO(First In First Out): 먼저 들어온 데이터가 가장 먼저 나가는 자료구조

![이미지](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbhvAPe%2FbtqHlVqf0RY%2FY4oCoA4wUkEpvIkU80i43K%2Fimg.png)

- Enqueue: Queue 맨 뒤에 데이터 추가
- Dequeue: Queue 맨 앞쪽의 데이터 삭제

## Queue의 특징
- 먼저 들어간 자료가 먼저 나오는 구조
- 한 쪽 끝은 Front 로 정하여 삭제 연산*만* 수행함
- 다른 한 쪽 끝은 Rear 로 정하여 삽입 연산*만* 수행함
- BFS에서 사용
- 컴퓨터 버퍼에서 주로 사용. 입력이 되었으나 처리를 하지 못할 때, 버퍼(Queue)를 만들어 대기시킴.

## 자바에서의 Queue 사용법
- LinkedList를 활용하여 생성해야 한다. -> import Queue, LinkedList

```
import java.util.LinkedList; //import
import java.util.Queue; //import
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언, linkedlist 이용
Queue<String> queue = new LinkedList<>(); //String형 queue 선언, linkedlist 이용
```

## Queue 에 값 추가
```
Queue<Integer> stack = new LinkedList<>(); //int형 queue 선언
queue.add(1);     // queue에 값 1 추가
queue.add(2);     // queue에 값 2 추가
queue.offer(3);   // queue에 값 3 추가
```
- add(value)
  - Queue에 여유 공간이 없어 삽입에 실패 -> IllegalStateException throw
  - 별도의 예외 처리 메서드를 생성해야 함
- offer(value)
  - Queue에 여유 공간이 없어 삽입에 실패 -> false 반환
  - 별도의 예외 처리 메서드가 필요하지 않음
 
## Queue 에 값 삭제
```
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언
queue.offer(1);     // queue에 값 1 추가
queue.offer(2);     // queue에 값 2 추가
queue.offer(3);     // queue에 값 3 추가
queue.poll();       // queue에 첫번째 값을 반환하고 제거 비어있다면 null
queue.remove();     // queue에 첫번째 값 제거
queue.clear();      // queue 초기화
```
- poll()
  - Queue가 비어있을 시 -> null 반환
  - Exception throw가 없어서, 별도의 메서드 작성하지 않아도 됨
- pop()
  - Stack에서 사용하는거라 이런 메서드는 없습니다 ㅎㅎ!
- remove()
  - Queue 에서 요소를 제거할 때 사용
  - 특정 요소를 제거하려면, 해당 요소를 특정하여 지정해야 함
  - 지우고자 하는 요소가 없다면 NoSuchElementException throw 를 함
- clear()
  - Queue 인터페이스에는 이런 메서드가 정의되어 있지 않음
  - 그러니 Queue를 비우고자 할때는 다음과 같은 방법을 사용
    - Queue 객체를 다시 생성
    - 반복문을 사용하여 요소를 모두 제거 by poll()
    - 큐 객체를 null로 설정
   
## Queue에서 가장 먼저 들어간 값 출력
```
Queue<Integer> queue = new LinkedList<>(); //int형 queue 선언
queue.offer(1);     // queue에 값 1 추가
queue.offer(2);     // queue에 값 2 추가
queue.offer(3);     // queue에 값 3 추가
queue.peek();       // queue의 첫번째 값 참조
```
- peek()
  - 요소 삭제는 이루어지지 않음.
