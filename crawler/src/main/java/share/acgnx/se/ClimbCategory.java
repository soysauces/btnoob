package share.acgnx.se;

import hello.GithubRepoPageProcessor;
import share.acgnx.se.domain.AcgnxCategory;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.example.GithubRepo;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by zz on 2017/8/6.
 */
public class ClimbCategory implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setUserAgent(
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        System.out.println(page.getHtml().xpath("//*[@id=\"listTable\"]").links().regex(".*/show").all().size());
        int size = page.getHtml().xpath("//*[@id=\"listTable\"]").links().regex(".*/show").all().size();
        int total=Integer.valueOf(page.getHtml().xpath("//*[@id=\"btm\"]/div[8]/div[2]/a[5]/text()").toString());
        System.out.println(total);
        for (int i=1;i<=size;i++){
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[1]/text()"));
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[2]/a/text()"));
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a").links());
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[3]/a/text()"));
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[4]/text()"));
            System.out.println(page.getHtml().xpath("//*[@id=\"data_list\"]/tr["+i+"]/td[8]/a/text()"));
        }






//        System.out.println(page.getHtml());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ClimbCategory()).addUrl("http://www.acgsou.com/1.html").run();
    }
}
