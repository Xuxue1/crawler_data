package com.xuxue.crawler;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 主要用来抓取 网贷观察网的数据
 * 因为这个程序 只是用来抓取少量的数据 说以写的比较简陋  各种异常没有处理
 * Created by Xuxue on 2017/3/21.
 */
public class Test {
    static Logger LOG= LoggerFactory.getLogger(Test.class);

    static CloseableHttpClient client = HttpClients.createDefault();

    public static void main(String[] args)throws Exception{
        //String province = args[0]; //运行程序第一个参数代表 爬取得省份
        String province = "beijing";
        LOG.info("will crawler {}",province);
        int pageNum = getPageNum(province);
        LOG.info("{}: page num {}",province,pageNum);
        List<Model> list = new ArrayList<>();
        for(int i=1;i<=pageNum;i++){
            Document doc = getContent("http://dangan.wangdaiguancha.com/"+province+"/list_"+i+".html");
            Elements e= doc.select(".list_ul");
            for(Element element:e){
                String url = element.select("li").get(0).select("a").get(0).absUrl("href");
                Document data_doc = getContent(url);
                String name = data_doc.select(".le_three").select(".con_ul").select("li").get(0).text().replace("平台名称：","");
                String faren = data_doc.select(".le_three").select(".con_ul").select("li").get(2).text().replace("创始人/法人：","");
                String onLineTime = data_doc.select(".le_three").select(".con_ul").select("li").get(4).text().replace("上线时间：","");

                String money = data_doc.select(".le_three").select(".con_ul").select("li").get(3).text().replace("注册资本：","");
                String company = data_doc.select(".le_three").select(".con_ul").select("li").get(7).text().replace("所属企业：","");
                String phone = data_doc.select(".le_four").select(".con_ul").select("li").get(0).text().replace("联系电话：","");
                String address = data_doc.select(".le_four").select(".con_ul").select("li").get(4).text().replace("联系电话：","");

                Model model = new Model();
                model.setPlateformName(name);
                model.setFaren(faren);
                model.setOnLineTime(onLineTime);
                model.setMoney(money);
                model.setCompany(company);
                model.setPhone(phone);
                model.setAddress(address);
                list.add(model);
                LOG.info("get a model {} from web ",model);
                Thread.sleep(2000);
            }
        }
        PrintWriter writer = new PrintWriter(province);
        for(Model m :list){
            writer.println(m.getPlateformName()+"\t"+m.getCompany()+"\t"+m.getMoney()+"\t"+m.getOnLineTime()+"\t"+m.getFaren()+"\t"+m.getAddress()+"\t"+m.getPhone());
        }
        writer.flush();
        writer.close();
    }




    public static Document getContent(String url){
        HttpGet get = new HttpGet(url);
        try(CloseableHttpResponse response = client.execute(get)){
            return Jsoup.parse(EntityUtils.toString(response.getEntity(),"gb2312"),url);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            System.out.println(url+"   haha");
        }
    }

    private static int getPageNum(String province)throws Exception{
        String url = "http://dangan.wangdaiguancha.com/"+province+"/";
        Document doc = getContent(url);
        Element element = doc.select(".li_six").select("span").select("strong").get(0);
        return Integer.parseInt(element.text());
    }

}
