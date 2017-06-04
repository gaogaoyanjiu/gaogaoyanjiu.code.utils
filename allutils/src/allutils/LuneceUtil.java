package allutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import icoal.core.exceptions.BusinessException;



/**
 * 建立索引与搜索
 * @author zhaojf
 * 2015-05-22
 *
 */
public class LuneceUtil {
	private static Analyzer analyzer = null;
	private static Directory directory = null;
	private static IndexWriter indexWriter = null;
	
	
	
	 public static  synchronized  void  createIndex(String upLoadPath,String IndexPath){
		 StringBuffer content=new StringBuffer();
		  
		    File file=new File(upLoadPath);
	        String type = file.getName().substring(file.getName().lastIndexOf(".")+1);
	          
	         if("doc".equalsIgnoreCase(type)){
	            
	              content.append(doc2String(file));
	            
	          }else if("docx".equalsIgnoreCase(type)){
	                
//	            	content.append( docx2String(upLoadPath+"\\"+file.getName()));
	            	content.append( docx2String(upLoadPath));
	            
	            }
	            
	        analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);
	        try {
				directory = FSDirectory.open(new File(IndexPath));
			} catch (IOException e) {
				throw new BusinessException("open index file error;");
			}
	        File indexFile = new File(IndexPath);
            if (!indexFile.exists()) {
                indexFile.mkdirs();
            }
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_CURRENT, analyzer);
           
            try {
				indexWriter = new IndexWriter(directory, config);
	            Document document = new Document();
	            document.add(new TextField("filename", file.getName(), Store.YES));
	            document.add(new TextField("content", content.toString(), Store.YES));
	            document.add(new TextField("path", file.getPath(), Store.YES));
			    indexWriter.addDocument(document);
	            indexWriter.commit();
	            
	            if (indexWriter != null) {
	                indexWriter.close();
	            }
	            } catch (IOException e) {
					// TODO Auto-generated catch block
	            	throw new BusinessException(" indexWriter create error;");
				}
	       }
	                
	                
	      private static String doc2String(File file){
        	String text2003=""; 
        	try{
           
        		InputStream in= new FileInputStream(file);
        		WordExtractor word=new WordExtractor(in);
    		    text2003 = word.getText();
        	}catch (Exception e) {
    			e.printStackTrace();
    		}
            return text2003;
        }
        
        private static String docx2String(String path) {
        	String text2007="";
        	try{
        	OPCPackage opcPackage = POIXMLDocument.openPackage(path);
    		POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
    		text2007 = extractor.getText();
        	}catch (Exception e) {
    			e.printStackTrace();
    		}
    	
    		  return text2007;
    	}
        
        public static synchronized Map<String,String> searchIndex(String IndexPath,String key){
        	Map map=new HashMap<String, String>();
//        	String index = "D:\\luceneIndex";
            String field = "content";
            IndexReader reader;
			try {
				reader = DirectoryReader.open(FSDirectory.open(new File(IndexPath)));
			
            IndexSearcher searcher = new IndexSearcher(reader);
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_CURRENT);

            QueryParser parser = new QueryParser(Version.LUCENE_CURRENT, field, analyzer);
            parser.setDefaultOperator(QueryParser.Operator.AND) ;
            
             Query query = parser.parse(key);
             
             TopDocs results = searcher.search(query, 1000);
             ScoreDoc[] hits = results.scoreDocs;
              for(int i=0;i<hits.length;i++){
            	  Document doc = searcher.doc(hits[i].doc); 
            	 // System.out.println("   Title: " + doc.get("filename"));
                  //System.out.println("--------------");
                  //System.out.println(doc.get("content"));
                  map.put(doc.get("filename"),doc.get("path"));
              }
             
            reader.close();
			} catch (Exception e) {
				//System.out.println(e.getStackTrace());
				new BusinessException("search index error");
				
				
				
			}
			return map;
        }
        
        public static void main(String[] args) {
        	
        	String upLoadPath="D:\\luceneData\\附件8：2014年大路电厂应急救援预案.doc";
        	String IndexPath="D:\\luceneIndex";
        	String keyIndex="应急预案";
        	//createIndex(upLoadPath,IndexPath);
        	Map <String,String>map=searchIndex(IndexPath,keyIndex);
        	
        	for (String key:map.keySet()) {
        		   System.out.println("key= "+ key + " and value= " + map.get(key));
        		  }
        	
		}
	                
	                

}
