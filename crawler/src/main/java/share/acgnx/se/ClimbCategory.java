package share.acgnx.se;

import hello.GithubRepoPageProcessor;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.client.DefaultHttpClient;
import share.acgnx.se.domain.AcgnxCategory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.net.CookiePolicy;
import java.util.Date;
import java.util.List;

/**
 * Created by zz on 2017/8/6.
 */
public class ClimbCategory implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        int size = page.getHtml().xpath("//*[@id=\"listTable\"]").links().regex(".*/show").all().size();
            for (int j=1;j<=size;j++){
                String url = page.getHtml().xpath("//*[@id=\"data_list\"]/tr[" + j + "]/td[3]/a").links().toString();
                AcgnxCategory ac=new AcgnxCategory();
                ac.setCategory(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[2]/a/text()").toString());
                ac.setCompleteNo(0);
                ac.setCreateAt(new Date());
                ac.setDownloadNo(0);
                ac.setCreator("ACG搜".equals(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[8]/a/text()").toString())?"bt菜鸟":"bt菜鸟");
                ac.setFluxLingage("magnet:?xt=urn:btih:"+getHashCode(url));
                ac.setHash(getHashCode(url));
                ac.setSize(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[4]/text()").toString());
                ac.setSourceName(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+j+"]/td[3]/a/text()").toString());
                ac.setSourceUrl(url);
                ac.setUploadNo(0);
                System.out.println(ac);
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[1]/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[2]/a/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a").links());
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[4]/text()"));
//            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[8]/a/text()"));
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
