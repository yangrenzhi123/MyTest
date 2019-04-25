package com.yang.test.java.lucene;

import java.io.IOException;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class App {

	public static void main(String[] args) throws IOException, ParseException {
		Analyzer analyzer = new StandardAnalyzer();
		
		Directory directory = FSDirectory.open(Paths.get("/tmp/testindex"));
		
		// 三、创建索引写入器
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		IndexWriter iwriter = new IndexWriter(directory, config);
		
		// 四、将内容存储到索引
		Document doc = new Document();
		String text = "This is the text to be indexed.";
		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		iwriter.addDocument(doc);
		doc = new Document();
		text = "This is the text to be fasdfafdasdfadf2.";
		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		iwriter.addDocument(doc);
		text = "This is the text to be fasdfafdasdfadf.";
		doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
		iwriter.addDocument(doc);
		iwriter.close();
		
		
		// ============ 关键字查询 =============
		// 一、创建索引存储目录读取器
		// Now search the index:
		DirectoryReader ireader = DirectoryReader.open(directory);

		// 二、创建索引搜索器
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// 三、解析查询
		// Parse a simple query that searches for "text":
		QueryParser parser = new QueryParser("fieldname", analyzer);
		Query query = parser.parse("This is");

		// 四、获取结果
		ScoreDoc[] hits = isearcher.search(query, 2).scoreDocs;
		System.out.println(hits.length);
		// Iterate through the results:
		for (int i = 0; i < hits.length; i++) {
		  Document hitDoc = isearcher.doc(hits[i].doc);
		  System.out.println(hitDoc.get("fieldname"));
		  //assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
		}
		ireader.close();
		directory.close();
	}
}