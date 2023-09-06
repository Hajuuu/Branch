
# LCA : 최소 공통 조상

  

## 목차

- 개요 

- LCA 정의

- LCA 구조(세그먼트 트리, DP)

- LCA 구현(JAVA)

  

## 개요

- (이진) 트리 구조에서 특정 자식들이 공통의 부모를 갖고 있는지 확인하려면?
![image](https://github.com/lynne921/Ssabalja/assets/119817396/2999e006-84ac-431c-88d2-c2dbbacdacae)
  

## LCA 정의

- Least Common Ancester : 최소 공통조상

- 두 정점(u, v)의 가장 가까운 공통조상을 찾는 알고리즘

- 트리를 효율적으로 탐색할 수 있는 알고리즘 고민하기

  

## LCA 구조(세그먼트 트리, DP)

### 세그먼트 트리
![image-1](https://github.com/lynne921/Ssabalja/assets/119817396/180ee9fe-c7d0-4a9e-9aa0-f17eaec10b19)

#### 아이디어

- 위 트리를 전위탐색(DFS)의 순서배열로 나타내보면,

> 1, 2, 3, 2, 4, 5, 4, 2, 1, 6, 1, 7, 8, 7, 1
<pre>    </pre>와 같다.

- 2와 7이 [(처음) / (마지막)]으로 등장하는 구간을 나타내보면,

> 1, [(2), 3, 2, 4, 5, 4, (2)], 1, 6, 1, [(7), 8, (7)], 1

<pre>    </pre>와 같다. 이는 해당 숫자를 root로 하는 서브트리가 된다.

- 특정 두 숫자(ex : [u, v] = [5, 8])가 처음 나오는 시점을 시작과 끝으로 하는 서브트리는

> 1, 2, 3, 2, 4, [(5), 4, 2, 1, 6, 1, 7, (8)], 7, 1

<pre>    </pre>와 같다. 이 서브트리의 root는 두 숫자의 LCA가 된다.<br>

<pre>    </pre>∴ u를 포함하는 서브트리에서 v를 포함하는 서브트리로 넘어가려면 LCA를 반드시 건너가야 하므로
<br><br>
※ 두 정점의 거리(촌수)를 구하려면, 노드의 깊이(D : depth)를 저장해서 다음과 같이 구할 수 있다.

> (D[u] - D[LCA]) + (D[v] - D[LCA]) = D[u] + D[v] - 2 * D[LCA]

  

#### 구현

- 트리를 해당 범위 내 가장 작은값을 찾는 세그먼트 트리로 변형한다

1. 트리 전순위 탐색 → 탐색 순으로 리프노드에 [탐색순서, 노드번호, 트리 높이] 갱신

2. 해당 탐색순서를 통해 세그먼트 트리 구현(먼저 탐색한 노드가 먼저 나오도록 하는 트리)

3. 세그먼트 트리를 통해 발견된 순서에 따른 쿼리 가능

![image-2](https://github.com/lynne921/Ssabalja/assets/119817396/f25df506-0fd9-44d9-b7bd-bea5e36af6f7)

### DP

![image-1](https://github.com/lynne921/Ssabalja/assets/119817396/180ee9fe-c7d0-4a9e-9aa0-f17eaec10b19)

#### 아이디어

1. 각 자식의 부모를 올라가면서 확인 <br>

- case1) u : 5, v : 8 <br>

> [5] : 4 - 2 - 1 <br>

> [8] : 7 - 1 <br>

- case2) u : 3, v : 5 <br>

> [3] : 2 - 1 <br>

> [5] : 4 - 2 - 1 <br>

- LCA를 만난 이후, 부모의 값이 같아진다!

2. 부모의 배열을 만들어서 상호 비교, 가장 먼저 부모가 같아지는 지점 확인 <br>

#### 구현

- 2차원 배열(DP[i][N])을 정의하고 2<sup>i</sup>번째 부모들을 비트마스킹한다.

> 2^i번째 부모들을 확인하는 이유 : 탐색 시간복잡도를 O(logN)으로 만들기 위해

1. 트리를 탐색하면서 각 노드의 높이를 기록한다.

2. 상향식으로 DP를 갱신한다.

>점화식 :  DP[ i + 1 ][ N ] = DP[ i ][ DP[ i ][ N ] ]

3. DP를 통해 LCA를 구한다.
![image-3](https://github.com/lynne921/Ssabalja/assets/119817396/9c11e129-d3e4-4e7f-9291-1aa8e5b371c1)

> 노드 높이 맞추기 : 깊이가 같아질때까지 부모로 변경하기(~> find)
```
for (i = h-1 ~ 0) if(2^i <= d[a] - [b]) a = parent[a][i];
```
> LCA 구하기 : 같은 부모가 나올때까지 타고 올라가기(~> union)
```
for (i = h-1 ~ 0) if(parent[a][i] != parent[b][i]) a = parent[a][i]; b = parent[b][i]
```
## LCA 구현(JAVA)
- 세그먼트 트리 구현
```java
// 트리의 노드 개수로부터 최대높이 구하기
int N, list[] = new int[N]; 							// N : 노드 개수 / list : 인접리스트

(입력받기)

// 전위 순회를 통한 방문순서 확인하기
trip = new ArrayList<>(); 							// 방문순서 저장하는 list(전역변수)
firstVisitedCnt = new int[N + 1]; 						// 처음 방문할때의 노드번호 저장(전역변수)
dfs(1, 0, 0);

// 세그먼트 트리 만들기
int h = Math.ceil(Math.log(N) / Math.log(2)) + 1; 				// 트리 높이 구하기
int size = 1 << (h + 1); 							// 트리 크기 구하기
segMinTree = new int[size];							// 세그먼트 트리 정의
init(1, 0, visitedRoute.size() - 1);						// 세그먼트 트리 초기화

// 최솟값(LCA) 출력하기
int start = firstVisitedCnt[a];							// a가 처음나오는 idx 구하기
int end = firstVisitedCnt[b];							// b가 처음나오는 idx 구하기
if (start > end) { int tmp = start; start = end; end = tmp; }			// 시작이 항상 끝보다 작게 만들기
System.out.println(getMin(1, 0, visitRoute.size()-1, , ));
```

```java
// 전위 순회(dfs)를 통해 방문순서를 저장하는 메서드
static void dfs(int node, int parents, int d) { 
	if(firstVisitCnt[node] == 0) firstVisitCnt[node] = N; 			// 루트노드 크기
	visitRoute.add(node); 							// 현재노드 방문순서 추가

// 현재 -> 자식 -> 부모 순으로 순회하므로
	for(int child : list[node]) if(child != parents) dfs(child, node, d+1); // 자식노드 순회
	if(parents != 0) visitRoute.add(parents);  				// 부모노드 방문순서 추가
}
```

```java
// 세그먼트 트리 만드는 메서드
static int init(int node, int start, int end) {
	if(start == end) return segMinTree[node] = visitRoute.get(start);	// 단말노드 : 방문순서 추가

	int mid = (start+end)/2; 						// 중간점 확인해서 자식노드 좌우로 2개 만들기
										// 자식노드(중간값 기준 좌우측 재귀 호출)
	return segMinTree[node] = Math.min(init(node*2, start, mid), init(node*2+1, mid+1, end)); 	
}
```
```java
// 세그먼트 트리 최솟값 호출하는 메서드
    static int getMin(int node, int start, int end, int left, int right) {
        if(end < left || start > right) return Integer.MAX_VALUE; 		// 해당 노드의 시작 / 끝 범위가 세그먼트 트리 범위를 초과하면 공통부모 없음(다른 트리에 존재)			
        
        if(left <= start && end <= right) return segMinTree[node]; 		// 찾는 범위 내에 세그먼트 트리가 있으면 현재노드(==현재 트리의 최솟값) 출력			
        
int mid = (start + end) / 2;							// 범위 내로 들어올때까지 세그먼트 트리 최솟값 찾아가기
        return Math.min(getMin(node*2, start, mid, left, right), getMin(node*2+1, mid+1, end, left, right));
    }
}
```

- DP 구현
```java
int N, list[] = new int[N];

(입력받기)


int h = Math.ceil(Math.log(n) / Math.log(2)) + 1;				// 트리의 노드 개수로부터 최대높이 구하기

int[] depth = new int[N];							// 노드의 깊이 저장할 depth 배열, 부모 저장할 dp배열 만들기
int[][] parent = new int[N][h];							// 부모 노드의 깊이는 log(h)로 초기화(^2씩 찾아갈예정)

dfs(1, 0, 0);

```

```java
// 전위 순회(dfs)를 통한 부모 위치 저장하기
static void dfs(int current, int h, int parent) { 
	depth[current] = h; 							// 현재 높이 depth에 저장
	for(int next : list[current]) 
		if(next != parent) {
			dfs(next, h+1, current); 				// 자식 노드 순회			
			parent[next][0] = current; 				// next의 부모 = current			
		} 
}
```
```java
// 다음 조상들의 위치 2차원(parents)으로 저장하기
static void fillParents() { 
	// 2차원 parent 배열 돌면서 조상 저장하기(dp) 
	for(int i = 1; i < h; i++) {  						// i : log(트리의 깊이)			
		for(int j = 1; j < N + 1; j++) 					// j : 모든 노드 순회			
			parent[j][i] = parent[parent[j][i-1]][i-1]; 
	} // 점화식 : DP[ i + 1 ][ N ] = DP[ i ][ DP[ i ][ N ] ]
```
```java
// DP(parents) 배열로부터 LCA 찾기
static int LCA(int a, int b) { 
	int ah = depth[a]; 
	int bh = depth[b]; 
	
	if(ah < bh) { int tmp = a; a = b; b = tmp; } 				// ah > bh로 세팅(높이 맞추기전에 트리에서 더 깊은 값이 먼저 나오도록 swap)
	
	// 1. 높이 맞추기 
	for (int i = h - 1; i >= 0; i--) { 					// 아래에서부터 찾아나가기
		if(Math.pow(2, i) <= depth[a] - depth[b]) a = parent[a][i]; 
	} if( a == b ) return a; 						// 높이 맞췄는데 같은값이면 바로 return
	
	// 2. LCA찾기 (부모가 다르면 같을때까지 찾아가기)
	for(int i = h - 1; i >= 0; i--) { 					// 아래에서부터 찾아나가기
		if(parent[a][i] != parent[b][i]) a = parent[a][i]; b = parent[b][i];
	} return parent[a][0]; 							// 찾은 첫번째 부모 return
}
```
---
