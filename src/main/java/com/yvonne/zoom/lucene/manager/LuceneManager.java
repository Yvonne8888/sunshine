package com.yvonne.zoom.lucene.manager;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 * @author 	Yvonne Wang
 * 	功能:索引库的操作
 */
public class LuceneManager {
	/**
	 * @param:获取IndexWriter对象
	 */
	private IndexWriter getIndexWriter() throws IOException {
		//1.1)：定义索引库的存放目录
		Directory directory = FSDirectory.open(new File("C:/java/workspace-idea/sunshine/src/main/java/com/yvonne/zoom/lucene/directory/myindex"));
		//1.3)构建分析器，StandarAnalyzer是标准分析器，官方建议使用
		Analyzer analyzer = new StandardAnalyzer();
		//1.2)：创建索引输出流的配置
		IndexWriterConfig config = new IndexWriterConfig(Version.LATEST,analyzer);

		//第一步：构造IndexWriter输出流对象
		IndexWriter writer = new IndexWriter(directory, config);
		return writer;
	}
	/**
	 * @param:获取indexSearcher对象
	 */
	private IndexSearcher getIndexSearcher() throws IOException {
		//1.2）获取索引库所在的目录对象
		Directory directory = FSDirectory.open(new File("C:/java/workspace-idea/sunshine/src/main/java/com/yvonne/zoom/lucene/directory/myindex"));
		//1.1)根据目录对象得到相应的输入流
		IndexReader reader = DirectoryReader.open(directory);
		//第一步：根据IndexSearcher输入流对象构建索引搜索器对象
		IndexSearcher searcher = new IndexSearcher(reader);
		return searcher;
	}
	/**
	 * @param:根据索引查询器及query对象打印查询到的内容
	 * @throws IOException
	 */
	private void printRS(Query query,IndexSearcher searcher) throws IOException {
		//第三步：根据搜索器对象及查询对象得到前10条记录（其中TopDocs中存放的是文档的id），这里的10代表只显示的记录条数
		TopDocs docs = searcher.search(query, 10);
		//测试查询的条数（总共有N条）
		long totalHits = docs.totalHits;
		System.out.println("满足条件的有：" + totalHits);
		//第四步：遍历前10条记录
		ScoreDoc[] docs2 = docs.scoreDocs;
		for (ScoreDoc scoreDoc : docs2) {
			//4.1)得到文档的id
			int docId = scoreDoc.doc;
			//4.2)利用搜索器，传递文档id，查询出对应的文档对象
			Document doc = searcher.doc(docId);
			//4.3)获取各种域
			//获取文件名域
			IndexableField fileNameField = doc.getField("fileNameField");
			//获取文件路径域
			IndexableField filePathField = doc.getField("filePathField");
			/*//获取文件内容域（内容比较多，在此省略）
			IndexableField fileContentField = doc.getField("fileContentField");*/
			//获取文件大小域
			IndexableField fileSizeField = doc.getField("fileSizeField");
			//4.4)输出内容
			System.out.println("文件名：" + fileNameField.stringValue());
			System.out.println("文件路径：" + filePathField);
			/*System.out.println("文件内容：" + fileContentField.stringValue);*/
			System.out.println("文件大小：" + fileSizeField.stringValue());
			System.out.println("-----------------------------------------");
		}
	}


	/**
	 * 	创建索引
	 * @throws IOException
	 */
	public void createIndex() throws IOException{
		//第一步：获取IndexWriter输出流对象
		IndexWriter writer = this.getIndexWriter();
		//第二步：读取D:\Lucene\searchdocument下得文件并遍历成File对象（原始文档）
		File searchDocs = new File("C:/java/workspace-idea/sunshine/src/main/java/com/yvonne/zoom/lucene/directory/searchdocument");
		//第三步：遍历得到原始文档中所有的文件对象
		File[] files = searchDocs.listFiles();
		for (File file : files) {
			//第四步：将文档对象存放到索引库中
			//4.1）得到文件相关的属性
			//得到文件名
			String fileName = file.getName();
			//得到文件绝对路径
			String filePath = file.getAbsolutePath();
			//读取文件内容
			String fileContent = FileUtils.readFileToString(file);
			//读取文件大小
			long fileSize = FileUtils.sizeOf(file);
			//4.2)根据文件相关的属性定义域对象
			//定义文件名域（分析、索引、存储）
			Field fileNameField = new TextField("fileNameField", fileName, Store.YES);
			//定义文件绝对路径域（不分析、不索引、必须存储）
			Field filePathField = new StoredField("filePathField", filePath);
			//定义文件内容域（分析、索引、存储）
			Field fileContentField = new TextField("fileContentField",fileContent,Store.YES);
			//定义文件大小域（分析、索引、存储）
			Field fileSizeField = new LongField("fileSizeField", fileSize, Store.YES);
			//4.3)将上面定义的各种域添加到文档中
			Document document = new Document();
			document.add(fileNameField);
			document.add(filePathField);
			document.add(fileContentField);
			document.add(fileSizeField);
			//第五步：利用lidexWriter输出流对象将文档对象输出到索引中
			writer.addDocument(document);
		}
		//第六步：关闭输出流
		writer.close();
	}



	/**
	 * 使用Lucene的Query子类TermQuery查询索引库
	 * @throws IOException
	 */
	public void searchIndex() throws IOException{
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：构建一个查询对象
		Query query = new TermQuery(new Term("fileContentField","java"));
		//打印输出
		printRS(query,searcher);
		//第五步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:添加索引库域
	 * @throws IOException
	 */
	public void addField() throws IOException{
		IndexWriter writer = getIndexWriter();
		//第二步:添加域到文档中
		Document document = new Document();
		document.add(new TextField("fnField", "新添加的文件名域", Store.YES));
		document.add(new TextField("fcField","新添加的文件内容域",Store.YES));
		//第三步：将文档存在索引库中
		writer.addDocument(document);
		//第四步：关闭流
		writer.close();
	}

	/**
	 * @param:修改索引库（先删除再添加）
	 * @throws IOException
	 */
	public void updateField() throws IOException{
		//第一步：获取IndexWriter输出流对象
		IndexWriter writer = this.getIndexWriter();
		//第二步：构造一个需要删除的term
		Term term = new Term("fileNameField","apache");
		//第三步：构造一个需要添加的文档对象
		Document document = new Document();
		document.add(new TextField("AA_field", "修改文档的AA域的内容", Store.YES));
		document.add(new TextField("BB_field", "修改文档的BB域的内容",Store.YES));
		//第四步：对索引库中的文档进行修改操作
		writer.updateDocument(term, document);
		//第五步：关闭流
		writer.close();
	}

	/**
	 * @param:删除索引库
	 * @throws IOException
	 */
	public void deleteField() throws IOException{
		//第一步：获取IndexWriter输出流对象
		IndexWriter writer = this.getIndexWriter();
		//第二步：构造一个需要删除的term
		Term term = new Term("fileContentField","mybatis");
		//第三步：删除指定的文档
		writer.deleteDocuments(term);
		//第四步：关闭流
		writer.close();
	}

	/**
	 * @param:删除索引库
	 */
	public void deleteFieldAll() throws IOException{
		//第一步：获取IndexWriter输出流对象
		IndexWriter indexWriter = this.getIndexWriter();
		//第二步：删除所有
		indexWriter.deleteAll();
		//第三步：关闭流
		indexWriter.close();
	}

	/**
	 * @param:使用Lucene的Query子类MatchAllDocsQuery查询索引库
	 */
	public void searcherIndexField() throws IOException{
		//第一步：获取IndexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：获取查询的Query对象
		Query matchAllDocsQuery = new MatchAllDocsQuery();
		//第三步：调用打印输出方法输出查询到的结果
		this.printRS(matchAllDocsQuery, searcher);
		//第四步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:使用Lucene的Query子类NumericRangeQuery查询索引库
	 */
	public void searcherIndexField1() throws IOException{
		//第一步：获取indexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：获取查询的Query对象
		NumericRangeQuery<Long> query = NumericRangeQuery.newLongRange("fileSizeField", 0L, 1000L, true, false);
		//第三步：调用打印输出方法输出查询到结果
		this.printRS(query, searcher);
		//第四步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:使用Lucene的Query子类BooleanQuery查询索引库
	 */
	public void searcherIndexField2() throws IOException{
		//第一步：获取indexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：构造用于BooleanQuery的基本查询
		Query query = new TermQuery(new Term("fileNameField","lucene"));
		Query query2 = new TermQuery(new Term("fileContentField","lucene"));
		//第三步：构造BooleanQuery对象
		BooleanQuery booleanQuery = new BooleanQuery();
		//Occur.MUST：必须满足此条件，相当于and
		//Occur.SHOULD：应该满足，但是不满足也可以，相当于or
		//Occur.MUST_NOT：必须不满足。相当于not
		booleanQuery.add(query, Occur.SHOULD);
		booleanQuery.add(query2, Occur.MUST);
		//第四步：调用打印输出方法输出查询到结果
		this.printRS(booleanQuery, searcher);
		//第五步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:通过QueryParser分析查询
	 */
	public void searcherFieldByQueryParser() throws IOException, ParseException{
		//第一步：获取indexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：得到QueryParser对象
		QueryParser queryParser = new QueryParser("fileContentField", new StandardAnalyzer());
		//第三步：通过QuertParser对象得到Query对象
		Query query = queryParser.parse("lucene is java development");
		//第四步:调用打印输出的方法输出查询到的结果
		this.printRS(query, searcher);
		//第五步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:通过QueryParser+简易语法进行分析查询
	 */
	public void searcherFieldByQueryParser1() throws IOException, ParseException{
		//第一步：获取indexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//第二步：得到QueryParser对象
		QueryParser queryParser = new QueryParser("fileSizeField", new StandardAnalyzer());
		//第三步：通过QueryParser对象得到Query对象
		Query query = queryParser.parse("+fileNameField:create +fileContentField:create");
		//第四步：调用打印输出的方法输出查询到的结果
		this.printRS(query, searcher);
		//第五步：关闭流
		searcher.getIndexReader().close();
	}

	/**
	 * @param:通过MultiFieldQueryParser进行多域分析查询
	 * @throws ParseException
	 */
	public void searcherFieldByQueryParser2() throws IOException, ParseException{
		//第一步：获取indexSearcher对象
		IndexSearcher searcher = this.getIndexSearcher();
		//2.1)定义要查询的多个域
		String[] fields = {"fileNameField","fileContentField"};
		//第二步：得到MultiFieldQuertParser对象
		MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
		//第三步：通过MultiFieldQueryParser对象得到Query对象
		Query query = multiFieldQueryParser.parse("lucene is java");
		//第四步：调用打印输出的方法输出查询到的结果
		this.printRS(query, searcher);
		//第五步：关闭流
		searcher.getIndexReader().close();
	}
}
