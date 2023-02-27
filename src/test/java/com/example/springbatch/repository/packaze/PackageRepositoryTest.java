package com.example.springbatch.repository.packaze;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class PackageRepositoryTest {

    @Autowired
    private PackageRepository packageRepository;

    @Test
    public void test_save(){
        // given
        PackageEntity entity = PackageEntity.builder()
                .packageName("바디 챌린지 PT 8주")
                .period(32)
                .build();

        // when
        packageRepository.save(entity);

        // then
        assertNotNull(entity.getPackageSeq());
    }

    @Test
    public void test_findByCreatedAtAfter() {
        // given
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(1);

        PackageEntity entity1 = PackageEntity.builder()
                .packageName("학생 전용 3개월")
                .period(90)
                .build();
        packageRepository.save(entity1);

        PackageEntity entity2 = PackageEntity.builder()
                .packageName("학생 전용 6개월")
                .period(180)
                .build();
        packageRepository.save(entity2);

        // when
        final List<PackageEntity> entities =
                packageRepository.findByCreatedAtAfter(dateTime, PageRequest.of(0, 1, Sort.by("packageSeq").descending()));

        // then
        assertEquals(1, entities.size());
        assertEquals(entity2.getPackageSeq(), entities.get(0).getPackageSeq());
    }

    @Test
    public void test_updateCountAndPeriod() {
        // given
        PackageEntity entity = PackageEntity.builder()
                .packageName("바디프로필 4개월 이벤트")
                .count(100)
                .period(90)
                .build();
        PackageEntity savedEntity = packageRepository.save(entity);

        // when
        Integer updateCount = 30;
        Integer updatePeriod = 120;

        int updatedCount = packageRepository.updateCountAndPeriod(savedEntity.getPackageSeq(), updateCount, updatePeriod);

        final PackageEntity updatedEntity = packageRepository.findById(savedEntity.getPackageSeq()).get();

        // then
        assertEquals(1, updatedCount);
        assertEquals(30, updatedEntity.getCount());
        assertEquals(120, updatedEntity.getPeriod());
    }


    @Test
    public void test_deleteById() {
        // given
        PackageEntity entity = PackageEntity.builder()
                .packageName("테스트 이용권")
                .period(1)
                .build();
        PackageEntity savedEntity = packageRepository.save(entity);
        Long packageSeq = savedEntity.getPackageSeq();

        // when
        packageRepository.deleteById(packageSeq);

        // then
        assertTrue(packageRepository.findById(packageSeq).isEmpty());

    }

}