package com.yvonne.zoom.lucene.test;

import java.io.IOException;

import com.yvonne.zoom.lucene.manager.LuceneManager;
import org.apache.lucene.queryparser.classic.ParseException;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Yvonne Wang
 * 	功能:测试索引库的操作
 */

public class LuceneManagerTest {

	/**
	 * @Fields	luceneManager:对索引库操作类
	 */
	private LuceneManager luceneManager;

	/**
	 * @param:new一个新的LuceneManager对象
	 */
	@Before
	public void init(){
		luceneManager = new LuceneManager();
	}

	/**
	 * @param:创建索引库
	 */
	@Test
	public void testcreateIndex() throws IOException{
		luceneManager.createIndex();
		System.out.println("成功创建索引库！");
	}

	/**
	 * @param:使用Lucene的Query子类TermQuery查询索引库
	 * @throws IOException
	 */
	@Test
	public void testsearchIndex() throws IOException{
		luceneManager.searchIndex();
	}

	/**
	 * @param:添加索引库域
	 * @throws IOException
	 */
	@Test
	public  void testAddField() throws IOException{
		luceneManager.addField();
		System.out.println("成功添加索引库域！");
	}

	/**
	 * @param:修改索引库
	 */
	@Test
	public void testUpdateField() throws IOException{
		luceneManager.updateField();
		System.out.println("成功修改索引库！");
	}

	/**
	 * @param:删除索引库
	 */
	@Test
	public void testDeleteField() throws IOException{
		luceneManager.deleteField();
		System.out.println("成功删除索引库文档！");
	}

	/**
	 * @param:删除索引库所有
	 */
	@Test
	public void testDeleteFieldAll() throws IOException{
		luceneManager.deleteFieldAll();
		System.out.println("成功删除所有！");
	}

	/**
	 * @param:使用Lucene的Query子类MatchAllDocsQuery查询索引库
	 */
	@Test
	public void testSearcherField() throws IOException{
		luceneManager.searcherIndexField();
	}

	/**
	 * @param:使用Lucene的Query子类NumericRangeQuery查询索引库
	 */
	@Test
	public void testSearcherIndexField1() throws IOException{
		luceneManager.searcherIndexField1();
	}

	/**
	 * @param:使用Lucene的Query子类BooleanQuery查询索引库
	 */
	@Test
	public void testSearcherField2() throws IOException{
		luceneManager.searcherIndexField2();
	}

	/**
	 * @param:通过QueryParser分析查询
	 */
	@Test
	public void testSearcherFieldByQueryParser() throws IOException, ParseException{
		luceneManager.searcherFieldByQueryParser();
	}

	/**
	 * @param:通过QueryParser+简易语法进行分析查询
	 */
	@Test
	public void testSearcherFieldByQueryParser1() throws IOException, ParseException{
		luceneManager.searcherFieldByQueryParser1();
	}

	/**
	 * @param:通过MultiFieldQueryParser进行多域分析查询
	 * @throws ParseException
	 * @throws IOException
	 */
	@Test
	public void testSearchFieldQueryParser2() throws IOException, ParseException{
		luceneManager.searcherFieldByQueryParser2();
	}
}
