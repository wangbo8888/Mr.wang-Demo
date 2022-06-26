package com.example.demo.elasticsearch.repository;


import com.example.demo.elasticsearch.entity.EsDemo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @BelongsProject: test-es
 * @BelongsPackage: com.example.testes
 * @Author: Mr.wang
 * @Date: 2022/6/25 23:08
 * @Description:
 */
@Repository
public interface DemoRepository extends ElasticsearchRepository<EsDemo,Long> {
}
