package com.yvonne.zoom.solr.test;

import com.yvonne.zoom.solr.manager.SolrManager;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Yvonne Wang
 * @date 2021/4/1920:41
 */
public class SolrTest {
    private SolrManager solrManager;

    @Before
    public void init(){
        solrManager = new SolrManager();
    }

    /**
     * 测试新增字段到索引库
     */
    @Test
    public void test01() throws IOException, SolrServerException {
        solrManager.addIndex();
    }


}
