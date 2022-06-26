//package com.example.demo.elasticsearch;
//
//import org.apache.lucene.search.join.ScoreMode;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.index.query.QueryStringQueryBuilder;
//import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
//import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
//import org.elasticsearch.indices.TermsLookup;
//import org.elasticsearch.script.Script;
//import org.elasticsearch.script.ScriptType;
//import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author yuhh
// * @date 2020-03-16 13:40
// **/
//public class QueryBuilderTest {
//
//    ElasticsearchRestTemplate baseElasticsearchService;
//
//    /**
//     * 查询所有文档
//     * @author yuhh
//     * @date 2020/3/16 13:52
//     */
//    public void matchAllQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.matchAllQuery();
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es：
//     *  GET index/_search
//     *  {
//     *      "query": {
//     *          "match": {
//     *              "待查询的字段": "待查询的值，多个值用" "隔开"，
//     *              "operator": "or"
//     *           }
//     *      }
//     * }
//     * @author yuhh
//     * @date 2020/3/16 13:52
//     */
//    public void matchQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.matchQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "common": {
//     *       "message": {
//     *         "待查询的字段": "待查询的值，多个值用" "隔开"，
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/16 13:52
//     */
//    public void commonTermsQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.commonTermsQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段列表的值的分词查询分词中包含给定的值的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "multi_match": {
//     *      "query": "待查询的字段，多个好像无法正确查询出结果",
//     *      "fields": [待查询的字段array]
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/16 18:11
//     */
//    public void multiMatchQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("待查询的值", "待查询的字段array");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值的文档，匹配的字段分词所在位置必须和待查询的值的分词位置一致
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "match_phrase": {
//     *      "待查询的字段": "待查询的值，多个值用" "隔开"
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 15:27
//     */
//    public void matchPhraseQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值的文档，匹配的字段分词所在位置必须和待查询的值的分词位置一致，
//     * 并且会将待查询的值的最后一个词作为前缀去进行查询
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "match_phrase_prefix": {
//     *      "待查询的字段": "待查询的值，多个值用" "隔开"
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 15:43
//     */
//    public void matchPhrasePrefix(){
//        QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 对子查询的结果做去重合并，score沿用子查询score的最大值
//     * 待查询的字段类型为text会分词
//     * 待查询的值会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "dis_max": {
//     *      "queries": [
//     *          查询条件array
//     *      ]
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 15:52
//     */
//    public void disMaxQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.disMaxQuery()
//                .add(QueryBuilders.matchPhrasePrefixQuery("待查询的字段", "待查询的值"))
//                .add(QueryBuilders.matchPhraseQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的idArray查询文档
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "ids": {"values": [idArray]}
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 16:02
//     */
//    public void idsQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds("idArray");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "term": {
//     *      "待查询的字段": {
//     *        "value": "待查询的值"
//     *      }
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 16:24
//     */
//    public void termQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.termQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值在纠正指定次数（默认是2）后的文档
//     * 待查询的字段不会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "fuzzy": {
//     *      "待查询的字段": {
//     *        "value": "待查询的值，会被纠正",
//     *        "fuzziness": 纠正次数
//     *
//     *      }
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 16:34
//     */
//    public void fuzzyQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.fuzzyQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含前缀为给定的值的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "prefix": {
//     *       "待查询的字段": {
//     *         "value": "待查询的值"
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 16:51
//     */
//    public void prefixQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.prefixQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中在指定范围内的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "range": {
//     *       "待查询的字段": {
//     *         "gte": "下限",
//     *         "lte": "上限"
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 17:48
//     */
//    public void rangeQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.rangeQuery("待查询的字段").gte("下限").lte("上限");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含待查询的值（包含通配符，*：任意字符；?：任意一个字符）的文档
//     * 注意：尽量别用*或?开头
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "wildcard": {
//     *       "待查询的字段": {
//     *         "value": "待查询的值（包含通配符，*：任意字符；?：任意一个字符）"
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/17 18:02
//     */
//    public void wildcardQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.wildcardQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中符合正则表达式的文档
//     * 注意：最好在使用正则前，加上匹配的前缀
//     * 待查询的字段类型为text会分词
//     * 待查询的正则表达式不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "regexp": {
//     *       "待查询的字段": "待查询的正则表达式"
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 10:48
//     */
//    public void regexpQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.regexpQuery("待查询的字段", "待查询的正则表达式");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中符合查询字符串的文档
//     * 待查询的字段类型为text会分词
//     * 查询字符串会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "query_string": {
//     *       "fields": [待查询字段array], 或 "default_field": 待查询的字段,
//     *       "query": "查询字符串（支持的通配符。支持通过AND OR NOT ！进行布尔运算。+：代表必须含有  -：代表不能含有）"
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 11:04
//     */
//    public void queryStringQuery(){
//        Map<String, Float> fields = new HashMap<>();
//        fields.put("待查询的字段q", QueryStringQueryBuilder.DEFAULT_BOOST);
//        fields.put("待查询的字段w", QueryStringQueryBuilder.DEFAULT_BOOST);
////        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("查询字符串").defaultField("待查询的字段");
//        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("查询字符串").fields(fields);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中符合查询字符串的文档
//     * 待查询的字段类型为text会分词
//     * 查询字符串会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "simple_query_string": {
//     *     "query_string": {
//     *       "fields": [待查询字段array], 或 "default_field": 待查询的字段,
//     *       "query": "查询字符串（支持的通配符。不支持通过AND OR NOT ！进行布尔运算。+：代表必须含有  -：代表不能含有）"
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 11:04
//     */
//    public void simpleQueryStringQuery(){
//        Map<String, Float> fields = new HashMap<>();
//        fields.put("待查询的字段q", QueryStringQueryBuilder.DEFAULT_BOOST);
//        fields.put("待查询的字段w", QueryStringQueryBuilder.DEFAULT_BOOST);
////        QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("查询字符串").simpleQueryStringQuery("待查询的字段");
//        QueryBuilder queryBuilder = QueryBuilders.simpleQueryStringQuery("查询字符串").fields(fields);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 返回positive query的查询结果，如果positive query的查询结果也满足negative query，则改变其_source的值
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "boosting": {
//     *      "positive": {
//     *        指定用于查询的 query，最后返回结果必须满足 positive 对应的条件
//     *      },
//     *      "negative": {
//     *        指定影响相关性算分的 query，
//     *        如果positive query查询出来的文档同时满足 negative query，
//     *        那么最终得分 = positive query 得分 * negative_boost
//     *      },
//     *      "negative_boost": 范围是 0 到 1.0
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 14:26
//     */
//    public void boostingQuery(){
//        QueryBuilder positiveQuery = QueryBuilders.wildcardQuery("待查询的字段", "待查询的值");
//        QueryBuilder negativeQuery = QueryBuilders.wildcardQuery("待查询的字段", "待查询的值");
//        QueryBuilder queryBuilder = QueryBuilders.boostingQuery(positiveQuery, negativeQuery).negativeBoost(0.1f);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 返回满足bool下所有query的结果
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "bool": {
//     *       "should": [
//     *          query array
//     *          返回的文档可能满足should子句的条件。
//     *          在一个Bool查询中，如果没有must或者filter，有一个或者多个should子句，那么只要满足一个就可以返回。
//     *          minimum_should_match参数定义了至少满足几个子句。
//     *       ],
//     *       "must": [
//     *          query array
//     *          返回的文档必须满足must子句的条件，并且参与计算分值
//     *       ],
//     *       "must_not": [
//     *         query array
//     *         返回的文档必须不满足must_not定义的条件。
//     *       ],
//     *       "filter": {query 返回的文档必须满足filter子句的条件。但是不会像Must一样，参与计算分值}
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 15:14
//     */
//    public void boolQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
//                .must(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"))
//                .must(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"))
//                .should(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"))
//                .mustNot(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"))
//                .filter(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 等同于 term query ，但与其他Span查询一起使用
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_term": {
//     *       "待查询的字段": {
//     *         "value": "待查询的值"
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 15:33
//     */
//    public void spanTermQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanTermQuery("待查询的字段", "待查询的值");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查询出待查询的值在待查询的字段的值的分词中前end个位置的文档
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_first": {
//     *       "match": {
//     *         "span_term": {
//     *           "待查询的字段": "待查询的值"
//     *         }
//     *       },
//     *       "end": 最大位置值
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 15:51
//     */
//    public void spanFirstQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanFirstQuery(
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                3);
//        baseElasticsearchService.search(queryBuilder);
//
//    }
//
//    /**
//     * 几个span query匹配的值的跨度必须在0-slop范围内，匹配的值的顺序必须和span query顺序一样
//     * 注意：所有span query的待查询的字段必须为同一个，不然会报异常
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "span_near": {
//     *      "clauses": [
//     *          span query array
//     *      ],
//     *      "slop": 最大的跨度,
//     *      "in_order": false
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 16:15
//     */
//    public void spanNearQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanNearQuery(
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                12).addClause(QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 把include query查询结果中符合exclude query的文档排除后返回结果
//     * 注意：include query和exclude query的待查询的字段必须为同一个，不然会报异常
//     * 注意：include query和exclude query的span query都为span_term好像不会被拦截
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_not": {
//     *       "include": {
//     *         "span_term": {
//     *           "待查询的字段": {
//     *             "value": "待查询的值"
//     *           }
//     *         }
//     *       },
//     *       "exclude": {
//     *         "span_term": {
//     *           "待查询的字段": {
//     *             "value": "待查询的值"
//     *           }
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 16:28
//     */
//    public void spanNotQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanNotQuery(
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 返回与任何span query匹配的文档
//     * 注意：所有span query的待查询的字段必须为同一个，不然会报异常
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_or": {
//     *       "clauses": [
//     *         span query array
//     *       ]
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 17:12
//     */
//    public void spanOrQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanOrQuery(QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"))
//                .addClause(QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查找符合big query条件且包含little query的文档
//     * 注意：所有span query的待查询的字段必须为同一个，不然会报异常
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_within": {
//     *       "little": {
//     *         "span_term": {
//     *           "待查询的字段": {
//     *             "value": "待查询的值"
//     *           }
//     *         }
//     *       },
//     *       "big": {
//     *         "span_near": {
//     *           "clauses": [
//     *             {
//     *               "span_term": {
//     *                 "待查询的字段": {
//     *                   "value": "待查询的值"
//     *                 }
//     *               }
//     *             },
//     *             {
//     *               "span_term": {
//     *                 "待查询的字段": {
//     *                   "value": "待查询的值"
//     *                 }
//     *               }
//     *             }
//     *           ],
//     *           "slop": 1,
//     *           "in_order": false
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 17:27
//     */
//    public void spanWithinQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanWithinQuery(
//                QueryBuilders.spanNearQuery(
//                        QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                        12).addClause(QueryBuilders.spanTermQuery("待查询的字段", "待查询的值")),
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查找符合big query条件的文档，之后筛选出包含little query的文档
//     * 注意：所有span query的待查询的字段必须为同一个，不然会报异常
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_containing": {
//     *       "little": {
//     *         "待查询的字段": {
//     *           "message": {
//     *             "value": "待查询的值"
//     *           }
//     *         }
//     *       },
//     *       "big": {
//     *         "span_near": {
//     *           "clauses": [
//     *             {
//     *               "待查询的字段": {
//     *                 "message": {
//     *                   "value": "待查询的值"
//     *                 }
//     *               }
//     *             },
//     *             {
//     *               "待查询的字段": {
//     *                 "message": {
//     *                   "value": "待查询的值"
//     *                 }
//     *               }
//     *             }
//     *           ],
//     *           "slop": 1,
//     *           "in_order": false
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 17:40
//     */
//
//    public void spanContainingQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.spanWithinQuery(
//                QueryBuilders.spanNearQuery(
//                        QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                        12).addClause(QueryBuilders.spanTermQuery("待查询的字段", "待查询的值")),
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查找满足条件的文档
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_multi": {
//     *       "match": {
//     *         可以是term, range, prefix, wildcard, regexp 或者 fuzzy 查询
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 17:54
//     */
//    public void spanMultiTermQueryBuilder(){
//        QueryBuilder queryBuilder = QueryBuilders.spanMultiTermQueryBuilder(QueryBuilders.wildcardQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 具体不清楚是干嘛的，好像只能用于span query下，猜测是从已查询到的文档中过滤出符合该query的文档
//     * 注意：所有span query的待查询的字段必须为同一个，不然会报异常
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "span_near": {
//     *       "clauses": [
//     *         {
//     *           "span_term": {
//     *             "待查询的字段": {
//     *               "value": "待查询的值"
//     *             }
//     *           }
//     *         },
//     *         {
//     *           "field_masking_span": {
//     *             "query": {
//     *               "span_term": {
//     *                 "待查询的字段": {
//     *                   "value": "待查询的值"
//     *                 }
//     *               }
//     *             },
//     *             "field": "待查询的字段"
//     *           }
//     *         }
//     *       ],
//     *       "slop": 12,
//     *       "in_order": false
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 18:05
//     */
//    public void fieldMaskingSpanQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.fieldMaskingSpanQuery(
//                QueryBuilders.spanTermQuery("待查询的字段", "待查询的值"),
//                "待查询的字段");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查询满足条件的文档并返回指定的_source
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "constant_score": {
//     *       "filter": {
//     *         query
//     *       },
//     *       "boost": 1.2
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/18 18:19
//     */
//    public void constantScoreQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.constantScoreQuery(
//                QueryBuilders.termQuery("待查询的字段", "待查询的值")).boost(1.2f);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查询满足条件的文档但不对_source进行计算
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "function_score": {
//     *      "query": {
//     *        query
//     *      }
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/19 14:09
//     */
//    public void functionScoreQueryNoFunction(){
//        QueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
//                QueryBuilders.termQuery("待查询的字段", "待查询的值"));
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查询满足条件的文档并对_source进行计算
//     * 这个太过复杂，详情请看https://www.jianshu.com/p/f164f127bf33
//     * @author yuhh
//     * @date 2020/3/19 15:02
//     */
//    public void functionScoreQuery(){
//        FunctionScoreQueryBuilder.FilterFunctionBuilder[] filterFunctionBuilders =
//                new FunctionScoreQueryBuilder.FilterFunctionBuilder[2];
//        filterFunctionBuilders[0] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//                QueryBuilders.termQuery("待查询的字段", "待查询的值"),
//                ScoreFunctionBuilders.fieldValueFactorFunction("待查询的字段"));
//        filterFunctionBuilders[1] = new FunctionScoreQueryBuilder.FilterFunctionBuilder(
//                ScoreFunctionBuilders.randomFunction().seed(1).setField("待查询的字段"));
//        QueryBuilder queryBuilder = QueryBuilders.functionScoreQuery(
//                QueryBuilders.termQuery("待查询的字段", "待查询的值"),filterFunctionBuilders);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据待查询字段array查询包含待查询的值array的文档
//     * 相关参数解释地址：https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-mlt-query.html
//     * es7.6测试查不到数据，不知道是什么原因
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "more_like_this": {
//     *       "fields": [
//     *         待查询字段array，es7.6必须为非空，所用的springboot-data-elasticsearch不兼容es7.6
//     *       ],
//     *       "like": ["待查询的值array"],
//     *       "min_term_freq": 1,
//     *       "max_query_terms": 12
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/19 18:00
//     */
//    public void moreLikeThisQuery(){
//        String[] likes = new String[1];
//        likes[0] = "待查询的值";
//        QueryBuilder queryBuilder = QueryBuilders.moreLikeThisQuery(likes);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 用于nested嵌套类型的查询
//     * nested嵌套类型详细说明请看：https://blog.csdn.net/laoyang360/article/details/82950393
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "nested": {
//     *       "path": "类型为nested的字段名",
//     *       "query": {
//     *          query，例：
//     *              "match": {
//     *                  "类型为nested的字段名.类型为nested的字段里的字段名": "待查询的值"
//     *              }
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/27 17:32
//     */
//    public void nestedQuery(){
//        QueryBuilder query = QueryBuilders.matchQuery("类型为nested的字段名.类型为nested的字段里的字段名", "待查询的值");
//        QueryBuilder queryBuilder = QueryBuilders.nestedQuery("类型为nested的字段名", query, ScoreMode.None);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含给定的值array的文档
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *    "terms": {
//     *      "待查询的字段": [
//     *        待查询的值array
//     *      ]
//     *    }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/27 17:42
//     */
//    public void termsQuery(){
//        String[] values = new String[2];
//        values[0] = "待查询的值1";
//        values[1] = "待查询的值2";
//        QueryBuilder queryBuilder = QueryBuilders.termsQuery("待查询的字段", values);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据查询语句的base编码格式进行查询，例如：
//     * eyJ0ZXJtIiA6IHsgIuW+heafpeivoueahOWtl+autSI6ICLlvoXmn6Xor6LnmoTlgLwiIH19 对应 {"term" : { "待查询的字段": "待查询的值" }}
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *      "wrapper": {
//     *          "query": "base64编码格式的查询语句"
//     *      }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/27 17:55
//     */
//    public void wrapperQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.wrapperQuery("eyJ0ZXJtIiA6IHsgIuW+heafpeivoueahOWtl+autSI6ICLlvoXmn6Xor6LnmoTlgLwiIH19");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据文档类型查询，在7.0中已经被弃用
//     * es:
//     * GET /_search
//     * {
//     *     "query": {
//     *         "type" : {
//     *             "value" : "待查询类型"
//     *         }
//     *     }
//     * }
//     * @author yuhh
//     * @date 2020/3/27 18:01
//     */
//    public void typeQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.typeQuery("待查询类型");
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 根据给定的字段的值的分词查询分词中包含指定索引下指定id的文档的指定字段的值
//     * 待查询的字段类型为text会分词
//     * 待查询的值不会分词
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "terms": {
//     *       "待查询的字段": {
//     *         "index": "作为查询条件的索引",
//     *         "id": "作为查询条件的文档的id",
//     *         "path": "作为查询条件的字段"
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/30 10:48
//     */
//    public void termsLookupQuery(){
//        TermsLookup termsLookup = new TermsLookup("作为查询条件的索引", "作为查询条件的文档类型", "作为查询条件的文档的id", "作为查询条件的字段");
//        QueryBuilder queryBuilder = QueryBuilders.termsLookupQuery("待查询的字段", termsLookup);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     *
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "script": {
//     *       "script": {
//     *         作为查询运行的脚本，具体使用方法见：
//     *         https://www.elastic.co/guide/en/elasticsearch/reference/current/modules-scripting-using.html
//     *         例子，查询指定字段的值大于指定值的数据：
//     *         "source": "doc['自定字段'].value > params.param1",
//     *         "lang": "painless",
//     *         "params": {
//     *           "param1": 2
//     *         }
//     *       }
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/30 11:22
//     */
//    public void scriptQuery(){
//        Map<String, Object> params = new HashMap<>();
//        params.put("param1", 2);
//        Script script = new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG,
//                "doc['自定字段'].value > params.param1", params);
//        QueryBuilder queryBuilder = QueryBuilders.scriptQuery(script);
//        baseElasticsearchService.search(queryBuilder);
//    }
//
//    /**
//     * 查询文档中包含待查询字段的数据
//     * es:
//     * GET index/_search
//     * {
//     *   "query": {
//     *     "exists": {
//     *       "field": "待查询字段"
//     *     }
//     *   }
//     * }
//     * @author yuhh
//     * @date 2020/3/30 16:17
//     */
//    public void existsQuery(){
//        QueryBuilder queryBuilder = QueryBuilders.existsQuery("待查询字段");
//        baseElasticsearchService.search(queryBuilder);
//    }
//}