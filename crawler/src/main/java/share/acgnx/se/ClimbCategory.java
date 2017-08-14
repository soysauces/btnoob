package share.acgnx.se;

import hello.GithubRepoPageProcessor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import share.acgnx.se.domain.AcgnxCategory;
import share.acgnx.se.mapper.CategoryMapper;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;

import javax.xml.ws.ServiceMode;
import java.io.UnsupportedEncodingException;
import java.net.CookiePolicy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by zz on 2017/8/6.
 */
public class ClimbCategory implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36").setSleepTime(600).setTimeOut(10000);

    @Override
    public void process(Page page) {
        int size = page.getHtml().xpath("//*[@id=\"listTable\"]").links().regex(".*/show").all().size();
            for (int j=1;j<=size;j++){
                String url = page.getHtml().xpath("//*[@id=\"data_list\"]/tr[" + j + "]/td[3]/a").links().toString();
                AcgnxCategory ac=new AcgnxCategory();
                ac.setCategory(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[2]/a/text()").toString());
                ac.setCompleteNo(0);
                ac.setCreateAt(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[1]/text()").toString());
                ac.setDownloadNo(0);
                ac.setCreator("ACG搜".equals(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[8]/a/text()").toString())?"bt菜鸟":page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[8]/a/text()").toString());
                ac.setFluxLingage("magnet:?xt=urn:btih:"+getHashCode(url));
                ac.setHash(getHashCode(url));
                ac.setSize(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[4]/text()").toString());
                ac.setSourceName(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[3]/a/text()").toString());
                ac.setSourceUrl(url);
                ac.setUploadNo(0);
                System.out.println(ac);
                try {
                    addCateGory(ac);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[1]/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[2]/a/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a").links());
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[4]/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[8]/a/text()"));
            }
        }

    public static void addCateGory(AcgnxCategory c) throws SQLException, UnsupportedEncodingException {
        // jdbc 完成数据源封装
        Connection con = null;
        PreparedStatement pst = null;
        try {
            // 1: 连接数据库 Connection 注册驱动 driverClass url username password
            con = JdbcUtils.getConnection();
            // 2: PreparedStatement sql 发送 db
            String sql = "insert into noob_category_list (create_at,category,source_name,source_url,size,flux_linkage,upload_no,download_no,complete_no,hash,creator) values(?,?,?,?,?,?,?,?,?,?,?)";// 预编译
            // sql
            pst = con.prepareStatement(sql);// sql 预先编译 防止sql
            // 注入
            // pst setXXX(问号位置,问号对应值);
            System.out.println(c.getSourceName());
            pst.setString(1, c.getCreateAt());
            pst.setString(2,c.getCategory());
            pst.setString(3, c.getSourceName());
            pst.setString(4, c.getSourceUrl());
            pst.setString(5, c.getSize());
            pst.setString(6, c.getFluxLingage());
            pst.setInt(7, c.getUploadNo());
            pst.setInt(8, c.getDownloadNo());
            pst.setInt(9, c.getCompleteNo());
            pst.setString(10, c.getHash());
            pst.setString(11, new String(c.getCreator().getBytes(), "UTF-8"));

            // 发送
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e + "插入员工失败");
        } finally {
            // 3: 关闭资源
            JdbcUtils.close(con, pst);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static  String getHashCode(String url){
        return url.substring(27,url.length()-5);
    }

    public static void main(String[] args) {

        Spider.create(new ClimbCategory()).addUrl("http://www.acgsou.com/1.html").run();
    }

    public void nextPage(int i){
        Spider.create(new ClimbCategory()).addUrl("http://www.acgsou.com/"+i+".html").run();
    }
}
