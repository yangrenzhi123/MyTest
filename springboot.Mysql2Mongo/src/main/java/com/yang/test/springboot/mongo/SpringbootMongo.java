package com.yang.test.springboot.mongo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.bson.Document;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateManyModel;
import com.mongodb.client.model.UpdateOptions;
import com.yang.test.springboot.dao.RecycleRecordDAO;
import com.yang.test.springboot.mongo.entity.RecycleRecordDTO;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMongo {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringbootMongo.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
    RecycleRecordDAO recycleRecordDAO;
	
	@Autowired
	@Qualifier(value = "one_MongoTemplate")
	MongoTemplate oneMongoTemplate;
	
	@Autowired
	@Qualifier(value = "two_MongoTemplate")
	MongoTemplate twoMongoTemplate;
	
	@Autowired
	@Qualifier(value = "three_MongoTemplate")
	MongoTemplate threeMongoTemplate;

	private  static  int limit = 10000;//查询mysql一次获取的数据条数
    private  static  int step = 10;//单次迁移任务的循环次数次数    limit*step 单次任务处理的总数据量
	
    public void doIt() throws InterruptedException {
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        long maxId = 0;

        // 休息10秒
        Thread.sleep(10000);

		while (true) {
			try {
	            // 获取开始游标
	            long startId = 0; // >
	            if (startId == maxId) {
	                System.out.println("startId 与 maxId一致，提前结束。");
	                break;
	            }


	            long a = System.currentTimeMillis();
	            long last = startId;
	            Map<String, List<UpdateManyModel<Document>>> groupByDay = new HashMap<>(); //按日分组的数据
	            for (int i = 0; i < step; i++) {
	                // 每次拉取1万条数据，最大id为maxId
					//last  起始自增id
					//maxId 最大自增id
					//limit 单次取的数据条数
	                List<RecycleRecordDTO> l = recycleRecordDAO.limitRecycleRecord(last, maxId ,limit);

	                if (l.size() == 0) {
	                    break;
	                }else{
	                	//过滤数据
	                	l = l.stream().filter(one->(one.getActive()==1)).collect(Collectors.toList());
					}

	                // 将数据遍历，分配到每日组
	                for (RecycleRecordDTO rr : l) {
	                    String day = df.format(rr.getTfsj());


	                    Document update = new Document();
	                    update.put("_id", rr.getRecyclerecordzzid());
	                    Document updated = new Document();
	                    updated.put("recyclerecordid", rr.getRecyclerecordid());
	                    updated.put("tenantid", rr.getTenantid());
	                    updated.put("regionid", rr.getRegionid());
	                    updated.put("tfsj", rr.getTfsj());
	                    updated.put("recyclerecordzzid", rr.getRecyclerecordzzid());
	                    updated.put("jlly", rr.getJlly());
	                    updated.put("operateuserid", rr.getOperateuserid());
	                    updated.put("tenantgroupid", rr.getTenantgroupid());
	                    updated.put("tfbz", rr.getTfbz());
	                    updated.put("sbbh", rr.getSbbh());
	                    UpdateManyModel<Document> updateOrInsert = new UpdateManyModel<Document>(update, new Document("$set", updated), new UpdateOptions().upsert(true));

	                    List<UpdateManyModel<Document>> templ = groupByDay.get(day);
	                    if (templ == null) {
	                        templ = new ArrayList<>();
	                        groupByDay.put(day, templ);
	                    }
	                    templ.add(updateOrInsert);
	                }

	                // 临时存储最后一条数据ID
	                last = l.get(l.size() - 1).getRecyclerecordzzid();
	            }
	            long b = System.currentTimeMillis();
	            // 将这个10组数据进行保存到mongodb
	            Iterator<Entry<String, List<UpdateManyModel<Document>>>> iter = groupByDay.entrySet().iterator();
	            Integer count = 0;
	            while (iter.hasNext()) {
	                Map.Entry<String, List<UpdateManyModel<Document>>> entry = (Map.Entry<String, List<UpdateManyModel<Document>>>) iter.next();
	                String key = entry.getKey();
	                List<UpdateManyModel<Document>> val = (List<UpdateManyModel<Document>>) entry.getValue();
	                count =count+val.size();

	                MongoCollection<Document> collection =  oneMongoTemplate.getCollection("h_recycle_record_rhb_test" + key);
	                collection.bulkWrite(val);
	            }
	            System.out.println();
	            System.out.println("数据条数::" + count+"::Mysql数据抽取耗时：" + (System.currentTimeMillis() - a)+"::批存耗时::" + (System.currentTimeMillis() - b));
	            System.out.println();


	            // 更新开始游标
	            startId = last;
			} catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(10000);
			}
        }
    }
}