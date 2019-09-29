package com.rudy.spider;

import com.rudy.spider.downloader.NewHttpClientDownloader;
import us.codecraft.webmagic.Spider;

/**
 * <p>ClassName: SpiderCommon</p>
 * <p>Description: </p>
 * <p>Company: 爱用科技有限公司</p>
 *
 * @author zhikang.du
 * @version v1.0
 * @date: 2019/9/28
 * @since JDK 1.8
 */
public class SpiderCommon {

    public void githubspider() {
        Spider.create(new GithubPageProcessor())
                .setDownloader(new NewHttpClientDownloader())
                .addUrl("https://github.com/code4craft").thread(5).run();
    }
}
