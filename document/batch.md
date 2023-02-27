
* 배치 feature 하나를 하나의 Job으로 정의한다.
* Job은 Step들로 구성이 되어 있다.

# STEP
* 배치 처리를 정의하고 제어하는 독립된 작업의 단위를 말한다.
* Tasklet기반의 Step과 Chunk 기반의 Step으로 나뉜다.

## Tasklet Step
* 간단한 단일 작업을 처리할 때 사용한다.
  * Ex) 오래된 데이터를 삭제한다. 정의되어 있는 알림을 전송한다. 등

![tasklet_step.png](..%2F..%2Ftasklet_step.png)


## Chunk Step 
* 아이템 기반으로 처리를 하기 때문에 ItemReader, ItemProcessor, ItemWriter로 구성된다. 
* ItemProcessor는 필수요소가 아니므로 필요한 경우에만 사용한다. 
* 한번에 하나씩 데이터를 읽고 chunk로 만든 후, chunk단위로 트랜젝션을 처리한다.

![chunk_step.png](..%2F..%2Fchunk_step.png)


# JOB
* 처음부터 끝까지 외부 의존성에 영향을 받지 않고 독립적으로 실행할 수 있고, 고유하며, 순서가 지정된 여러 step들의 모음

![jobpng.png](..%2F..%2Fjobpng.png)

## JobRepository
* RDB에 붙였을 때 기본적으로 생성되는 테이블
![jobRepository.png](..%2F..%2FjobRepository.png)

### BATCH_JOB_INSTANCE
* job을 처음 실행하면 단일 job 인스턴스 데이터가 등록되는 테이블

### BATCH_JOB_EXECUTION
* job의 실제 기록 테이블 
* job이 실행될 때마다 새로운 데이터가 생성된다.
  * 실행 중에도 값들이 업데이트가 된다.

#### BATCH_JOB_EXECUTION_CONTEXT
#### BATCH_STEP_EXECUTION
##### BATCH_STEP_EXECUTION_CONTEXT
#### BATCH_JOB_EXECUTION_PARAMS



