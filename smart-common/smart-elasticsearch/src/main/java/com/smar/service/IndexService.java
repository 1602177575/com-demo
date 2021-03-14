package com.smar.service;

import com.alibaba.fastjson.JSON;
import com.smar.pojo.Content;
import com.smar.utils.HtmlUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class IndexService {

    @Resource
    RestHighLevelClient restHighLevelClient;

    /**
     * 外部爬虫获取数据  向ES中批量添加数据
     * @return
     */
    public Boolean addListener(String keyword) throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("10s");
        List<Content> jdHtml = HtmlUtils.getJDHtml(keyword);
        //将数据遍历存入
        for (int i = 0; i < jdHtml.size(); i++) {
            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(jdHtml.get(i)),XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulk.hasFailures();
    }

    /**
     * 从ES 中获取数据实现搜索功能
     * @param keyword 关键字
     * @param pageNo 页数
     * @param pageSize 数量
     * @return
     * @throws IOException
     */
    public List<Map<String ,Object>> searchPages(String keyword,int pageNo,int pageSize) throws IOException {
        if(pageNo<1){
            pageNo=1;
        }

        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //精准匹配 根据标题查询
        TermQueryBuilder title = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(title);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果 封装返回
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }


    /**
     * 搜索关键字实现高亮
     * @param keyword
     * @param pageNo
     * @param pageSize
     * @return
     * @throws IOException
     */
    public List<Map<String ,Object>> Highlight(String keyword,int pageNo,int pageSize) throws IOException{
        if(pageNo<1){
            pageNo=1;
        }

        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        //精准匹配 根据标题查询
        TermQueryBuilder title = QueryBuilders.termQuery("title", keyword);
        sourceBuilder.query(title);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        //分页
        sourceBuilder.from(pageNo);
        sourceBuilder.size(pageSize);

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //高亮的字段
        highlightBuilder.field("title");
        //多个高亮
        highlightBuilder.requireFieldMatch(false);
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");

        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //解析结果 封装返回
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : search.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
    /**
     * 查询
     * @throws IOException
     */
    void testSearchResult() throws IOException {
        SearchRequest searchRequest = new SearchRequest("test1");

        //构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //匹配查询条件
        //精确
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "张三");
        //匹配所有条件
        MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("name", "战术");

        //多条件
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(new MatchQueryBuilder("name","战术"));
        boolQueryBuilder.mustNot(new MatchQueryBuilder("name","队列"));

        sourceBuilder.query(boolQueryBuilder);

        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        //分页
//        sourceBuilder.size(5);
//        sourceBuilder.from(0);
        //放到请求中
        searchRequest.source(sourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        System.out.println(searchResponse.status()+"状态");
        System.out.println(JSON.toJSONString(searchResponse.getHits()));
        System.out.println("==========================");
        for (SearchHit documentFields:searchResponse.getHits()){
            //返回结果集
            System.out.println(JSON.toJSONString(documentFields.getSourceAsMap()));
            //获取匹配值
            System.out.println(documentFields.getScore());
        }

    }
}
