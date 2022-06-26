package com.example.demo.elasticsearch;

import com.example.demo.elasticsearch.entity.EsDemo;
import com.example.demo.elasticsearch.repository.DemoRepository;
import com.example.demo.util.IdUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.*;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: test-es
 * @BelongsPackage: com.example.testes
 * @Author: Mr.wang
 * @Date: 2022/6/25 23:25
 * @Description:
 */
@RestController
@RequestMapping("es")
public class ElasticSearchController {
    @Resource
    private DemoRepository demoRepository;
    @Resource
    private ElasticsearchRestTemplate elasticsearchTemplate;


    @PostMapping("/testes")
    void test() {
        //type : 0 全部能看，1允许看，2禁止看
        /**
         * 活动1 用户1.2能看，用户1关注
         * 活动2 用户1 禁止，
         * 活动3 用户1能看，未关注
         * 活动4 用户1能看，未关注
         * 活动5 用户1能看，关注
         * 活动6 全员看
         *
         * */


        //活动1 用户1.2能看，用户1关注
        List<Long> allowList = new ArrayList<>();
        allowList.add(1L);
        allowList.add(2L);
        List<Long> watchList = new ArrayList<>();
        watchList.add(1L);
//        List<Long>  notList = new ArrayList<>();
//        notList.add(4L);
        EsDemo demo = new EsDemo();
        demo.setId(1L);
        demo.setType(1);
        demo.setAllowList(allowList);
        demo.setCreateDate(LocalDateTime.now());
//        demo.setProhibitList(notList);
        demo.setWatchList(watchList);
        demo.setName("活动1");

        //活动2 用户1 禁止，
        List<Long> notList = new ArrayList<>();
        notList.add(1L);
        EsDemo demo1 = new EsDemo();
        demo1.setId(2L);
        demo1.setType(2);
//        demo.setAllowList(allowList);
        demo1.setCreateDate(LocalDateTime.now());
        demo1.setProhibitList(notList);
        demo1.setName("活动2");
        //活动3 用户1能看，未关注
        List<Long> allowList3 = new ArrayList<>();
        allowList3.add(1L);
        EsDemo demo3 = new EsDemo();
        demo3.setId(3L);
        demo3.setType(1);
        demo3.setAllowList(allowList3);
        demo3.setCreateDate(LocalDateTime.now());
        demo3.setName("活动3");
        //活动4 用户1能看，未关注
        List<Long> allowList4 = new ArrayList<>();
        allowList4.add(1L);
        EsDemo demo4 = new EsDemo();
        demo4.setId(4L);
        demo4.setType(1);
        demo4.setAllowList(allowList4);
        demo4.setCreateDate(LocalDateTime.now());
        demo4.setName("活动4");
        //活动5 用户1能看，用户1关注
        List<Long> allowList5 = new ArrayList<>();
        allowList5.add(1L);
        List<Long> watchList5 = new ArrayList<>();
        watchList5.add(1L);
        EsDemo demo5 = new EsDemo();
        demo5.setId(5L);
        demo5.setType(1);
        demo5.setAllowList(allowList5);
        demo5.setCreateDate(LocalDateTime.now());
        demo5.setWatchList(watchList5);
        demo5.setName("活动5");
        //活动6  全员看
        EsDemo demo6 = new EsDemo();
        demo6.setId(6L);
        demo6.setType(0);
        demo6.setCreateDate(LocalDateTime.now().plusDays(1));
        demo6.setName("活动6");


        List<EsDemo> demos = new ArrayList<>();
        demos.add(demo);
        demos.add(demo1);
        demos.add(demo3);
        demos.add(demo4);
        demos.add(demo5);
        demos.add(demo6);
        elasticsearchTemplate.save(demos);
    }

    @PostMapping("/select")
    List<EsDemo> select() {
        /**
         * 活动1 用户1.2能看，用户1关注
         * 活动2 用户1 禁止，
         * 活动3 用户1能看，未关注
         * 活动4 用户1能看，未关注
         * 活动5 用户1能看，关注
         * 活动6 全员看
         * //type : 0 全部能看，1允许看，2禁止看
         * */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        BoolQueryBuilder typeQuery = QueryBuilders.boolQuery();
        typeQuery.should(QueryBuilders.termQuery("type", 0));
        typeQuery.should(getProhibitQuery());
        typeQuery.should(getAllowedQuery());
        boolQueryBuilder.must(typeQuery);
        SortBuilder sortBuilder = SortBuilders.fieldSort("createDate").order(SortOrder.DESC);
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(boolQueryBuilder)
                .withAggregations(AggregationBuilders.terms("date").field("createDate"))
                .withPageable(PageRequest.of(0, 10))
                .withSorts(sortBuilder)
                .build();
        SearchHits<EsDemo> search = elasticsearchTemplate.search(nativeSearchQuery, EsDemo.class);
        List<EsDemo> collect = search.getSearchHits().stream().map(x -> x.getContent()).collect(Collectors.toList());
//        AggregationsContainer<?> aggregations = search.getAggregations();
//        aggregations
//        Aggregation date = aggregations.get("date");
//        Map<String, Object> metadata = date.getMetadata();
        Long id = IdUtils.getId();
        return collect;

    }

    private BoolQueryBuilder getAllowedQuery() {
        BoolQueryBuilder allowedQuery = QueryBuilders.boolQuery();
        allowedQuery.must(QueryBuilders.termQuery("type", 1));
//        BoolQueryBuilder in = QueryBuilders.boolQuery();
        allowedQuery.must(QueryBuilders.termQuery("allowList", 1));
//        allowedQuery.must(in);
        return allowedQuery;
    }

    private BoolQueryBuilder getProhibitQuery() {
        BoolQueryBuilder prohibitQuery = QueryBuilders.boolQuery();
        prohibitQuery.must(QueryBuilders.termQuery("type", 2));
//        BoolQueryBuilder in = QueryBuilders.boolQuery();
        prohibitQuery.mustNot(QueryBuilders.termQuery("prohibitList", 1));
//        prohibitQuery.must(in);
        return prohibitQuery;
    }
    
}
