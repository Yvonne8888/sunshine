package com.yvonne.zoom.lucene.test;

import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.util.Attribute;
import org.junit.Test;

/**
 * @author 	Yvonne Wang
 * 功能:分析器的分析效果源码
 */
public class TokenStreamTest {

	/**
	 * @param:查看标准分析器的分词效果
	 * @throws IOException
	 */
	@Test
	public void testTokenStream() throws IOException{
		//第一步：创建一个标准的分析器对象
		Analyzer standardAnalyzer = new StandardAnalyzer();
		//第二步：获得tokenStream标记流对象
		//第一个参数：域名，可以随便给一个
		//第二个参数：要分析的文本内容
		TokenStream tokenStream = standardAnalyzer.tokenStream("test",
				"The Spring Framework provides a comprehensive programming and configuration model.");
		//第三步：添加一个引用，可以获取每个关键词属性
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		//第四步：添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		//第五步：将指针调整到列表的头部(reset重置)
		tokenStream.reset();
		//第六步：遍历关键词列表，通过incrementToken方法判断列表是否结束
		while (tokenStream.incrementToken()) {
			//关键词的起始位置
			System.out.println("start->" + offsetAttribute.startOffset());
			//取关键词
			System.out.println(charTermAttribute);
			//关键词的结束位置
			System.out.println("stop->" + offsetAttribute.endOffset());
			System.out.println("-----------------------------------------");
		}
		//第七步：关闭流
		tokenStream.close();

	}
}
