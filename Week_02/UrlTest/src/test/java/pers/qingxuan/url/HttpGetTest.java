/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pers.qingxuan.url;

import org.junit.jupiter.api.Test;

public class HttpGetTest {
    @Test
    public void testCall() throws Exception {
        String url = "http://localhost:8801";
        new HttpGet(url).call();
    }
}
