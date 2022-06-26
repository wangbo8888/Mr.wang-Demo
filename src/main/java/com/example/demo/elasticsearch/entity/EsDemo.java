package com.example.demo.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: test-es
 * @BelongsPackage: com.example.testes
 * @Author: Mr.wang
 * @Date: 2022/6/25 23:06
 * @Description:
 */
@Data
@AllArgsConstructor
@Document(indexName = "demo")
@NoArgsConstructor
public class EsDemo {
    @Id
    private Long id;

    private List<Long> allowList;

    private List<Long> prohibitList;

    private List<Long> watchList;

    @Field(type = FieldType.Text)
    private String name;
    private Integer type;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

}
