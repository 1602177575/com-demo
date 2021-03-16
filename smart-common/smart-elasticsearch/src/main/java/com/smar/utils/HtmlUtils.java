package com.smar.utils;


import com.smar.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 从网页爬取 商品数据
 */
public class HtmlUtils {
    public static void main(String[] args) throws IOException {
        getJDHtml("java",2).forEach(System.out::println);
    }

    /**
     * 获取京东 搜索数据
     * @param keyword
     * @return
     * @throws IOException
     */
    public static List<Content> getJDHtml(String keyword,int pageNo) throws IOException {
        String url="https://search.jd.com/Search?keyword="+keyword+"&page="+pageNo;
        ArrayList<Content> list = new ArrayList<>();
        Document document = Jsoup.parse(new URL(url),30000);
        //J_goodsList 包含所需原始的大的标签ID
        Element element = document.getElementById("J_goodsList");
        //查看一波
        //System.out.println(element.html());
        //获取 li 标签中的数据
        Elements elements_li = element.getElementsByTag("li");
        for (Element el:elements_li){
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            list.add(new Content(title,price,img));
        }
        return list;
    }


}
