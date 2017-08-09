package share.acgnx.se;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by zz on 2017/8/9.
 */
public class BeginCrawft implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);
    private int total;

    @Override
    public void process(Page page) {
        int total=Integer.valueOf(page.getHtml().xpath("//*[@id=\"btm\"]/div[8]/div[2]/a[5]/text()").toString());
        System.out.println(total);
        for (int i=2;i<=3;i++){
            new ClimbCategory().nextPage(i);
            System.out.println(i);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new BeginCrawft()).addUrl("http://www.acgsou.com/1.html").run();

    }
}
