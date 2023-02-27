package com.example.springbatch.repository.packaze;

import com.example.springbatch.repository.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageSeq;

    private String packageName;

    private Integer count;

    private Integer period;

    public void modifyPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void modifyCount(int count) {
        this.count = count;
    }

    public void modifyPeriod(int period) {
        this.period = period;
    }

}
