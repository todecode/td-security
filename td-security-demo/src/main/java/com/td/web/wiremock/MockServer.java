package com.td.web.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * @className: MockServer
 * @description:
 * @author: cyd
 * @date: 2019/12/2 下午10:17
 **/
public class MockServer {

    // TODO: 2019/12/4  解决方案。引入httpclient包即可使用
    public static void main(String[] args) throws IOException {
        WireMock.configureFor(8062);
        WireMock.removeAllMappings();


        mock("/order/1","01");
        mock("/order/2","01");
    }

    private static void mock(String url, String file) throws IOException {
        // 读取出来的文件连接成字符串
        ClassPathResource resource = new ClassPathResource("/Users/chenyd/my/td-security/td-security-demo/src/main/resources/mock/response/"+file+".txt");
        String content = StringUtils.join(FileUtils.readLines(resource.getFile(),"UTF-8").toArray(),"\n");
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
                .willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
