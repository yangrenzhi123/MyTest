package com.yang.test.springboot.mongo;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.annotation.PostConstruct;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yang.test.springboot.mongo.entity.RecycleRecordDTO;

/** 注意Controller包在同包或者子包下 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
public class SpringbootMongo {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(SpringbootMongo.class, args);
	}
}

@RestController
class HelloController {

	@Autowired
	@Qualifier(value = "one_MongoTemplate")
	private MongoTemplate oneMongoTemplate;
	@Autowired
	@Qualifier(value = "two_MongoTemplate")
	private MongoTemplate twoMongoTemplate;
	@Autowired
	@Qualifier(value = "three_MongoTemplate")
	private MongoTemplate threeMongoTemplate;

	@PostConstruct
	public void init2() {
		Set<String> collectionNames = oneMongoTemplate.getCollectionNames();
		for(String collectionName : collectionNames) {
			ObjectId objectId = ObjectId.createFromLegacyFormat(0, 0, 0);
			
			int i = 0;
			while (true) {
				Criteria criteria = Criteria.where("_id").gt(objectId);
				Query query = new Query();
				query.addCriteria(criteria);
				query.limit(100000);
				query.with(new Sort(Direction.ASC, "_id"));
				List<RecycleRecordDTO> l = oneMongoTemplate.find(query, RecycleRecordDTO.class, collectionName);
				if (l.size() > 0) {
					RecycleRecordDTO last = l.get(l.size() - 1);
					objectId = last.get_id();
					System.out.println(last.get_id());
				}else {
					break;
				}
				i++;
			}
			
			System.out.println(collectionName + "，已结束，量级：" + i*100000);
		}
	}

    //@PostConstruct
    public void init() {
    	Set<String> collectionNames = oneMongoTemplate.getCollectionNames();
    	
    	long a = System.currentTimeMillis();System.out.println("开始执行...");long max = 0;
    	for(String collectionName : collectionNames) {
    		if(!collectionName.startsWith("ffasdfafdasdfadfadfas")) continue;
    		
        	long b = System.currentTimeMillis();System.out.println("开始执行" + collectionName);
    		try {
        		oneMongoTemplate.indexOps(collectionName).dropIndex("p_c_zhhh_zhnc_sjhm");
        		oneMongoTemplate.indexOps(collectionName).dropIndex("p_c_zhnc_sjhm");
        		oneMongoTemplate.indexOps(collectionName).dropIndex("p_c_sjhm");
    		}catch(Exception e) {
    			System.out.println();
    		}
    		
            Index index1 = new Index()
                    .on("communityid", Sort.Direction.ASC)
                    .on("zhhh", Sort.Direction.ASC)
                    .on("zhnc", Sort.Direction.ASC)
                    .on("sjhm", Sort.Direction.ASC).named("p_c_zhhh_zhnc_sjhm");
            Index index2 = new Index()
                    .on("communityid", Sort.Direction.ASC)
                    .on("zhnc", Sort.Direction.ASC)
                    .on("sjhm", Sort.Direction.ASC).named("p_c_zhnc_sjhm");
            Index index3 = new Index()
                    .on("communityid", Sort.Direction.ASC)
                    .on("sjhm", Sort.Direction.ASC).named("p_c_sjhm");
            
            oneMongoTemplate.indexOps(collectionName).ensureIndex(index1);
            oneMongoTemplate.indexOps(collectionName).ensureIndex(index2);
            oneMongoTemplate.indexOps(collectionName).ensureIndex(index3);
            long distance = System.currentTimeMillis() - b;
            if(distance > max) {
            	max = distance;
            }
        	System.out.println("执行完毕，耗时：" + (distance));
    	}
    	System.out.println("执行完毕，总耗时：" + (System.currentTimeMillis() - a) + "，单次最大耗时：" + max);
    }

	@GetMapping("/query")
	public void query() throws ParseException {
		List<String> communityidList = Arrays.asList("86-4cd4-a30f-605f15862762", "1fc4f9e6-6053-4483-bad1-7f0ec114b7e1", "21cca99e-7a5c-4cf4-93ae-b300b377ce57", "22b05d19-0202-4560-b191-b64018bf7a05", "23c13df3-b50a-4d29-b8bd-b7610fa47723", "29f6dd22-c3c4-495f-b09c-f68343e7080f", "2a9e6795-b15c-43a6-b19c-34727f2a971f", "2f83ecc1-6b50-4470-b9f2-7045d9891a8e", "36d7324e-bb26-4573-8604-2881ef988470", "3c819939-c6c2-4b78-ba60-b3a41589e170", "3dab7779-019f-4b15-9e69-227c3c6a5538", "3e362022-b3c7-44ea-aa9b-e8ddac6784c1", "408b0fdb-fe78-4c1f-bb1a-016cf46b19b6", "40d14157-65ce-4910-ad88-e56c63d9fe43", "44aecc73-0623-4547-a815-f762e77d7b2f", "455942bd-2fe6-4216-b0d3-8ffb35107818", "4805f7a2-9e41-40ab-9f4d-8c00a16d54a5", "4be9a3c9-e056-4baa-b3b1-f9f7e19326b6", "5287bc2a-3d9a-4117-8b51-dc32ee90cf88", "54458379-6cf7-4551-a60b-95ae0ee9eaf6", "54af66d9-e1a5-4e96-a18f-114f22b22611", "607924b4-b160-4116-b9f0-33c01f3fdde0", "65bc4572-f70d-4003-ab7b-d69b44e6e60f", "6707e9de-6870-450c-93d2-a133fb13e91b", "6c812864-3f04-4f8a-8811-f2bbaec55960", "6c83470f-d3df-4e60-9376-d18396a16781", "76261057-730a-4e0b-a6a8-8c7f9d8b4341", "7ad64681-04f8-4e56-8aff-4b5133b7e807", "7d12d0f3-3364-410b-b6ad-e8dee8488b7d", "7f3f547b-0e59-4cbb-8cba-44f8685d114b", "812d670f-c176-4a9b-8a69-4b10834eb3df", "839207fc-76df-45b2-b851-065176bb4d75", "8488edc9-8608-4e03-9db2-5163f835a67c", "85bfdce3-1cd6-4427-ba12-51ba18b49ca9", "8933c3e8-abf3-4bc5-b316-fecc499ac02f", "8bc33cf6-8c4c-4ab0-b1ea-7858c53b4c04", "8e8bbb03-9a31-41e0-a362-9f11ed860e42", "902b7554-5380-47a5-9913-9c43d72bbe25", "911ee810-c66f-4a26-811a-eed8dcf765d0", "91386b26-5352-4b39-a87c-3ac2bb7dcd67", "9337923c-0104-44dc-a457-29757fd6e94c", "96dc6149-7ccc-4c9a-adc8-6bc9398c4e73", "993a6831-8c0f-4b0a-b10d-ea98741efd9c", "9a47682e-c54d-4b54-8e9b-92480fe54153", "9dab74cc-0e52-46d8-8e41-4a5dcd501fb5", "a1b0377a-1d98-4463-9c70-9708bd640e0c", "a2e5f703-4246-40b1-8d9f-8be1d93de41a", "a3e19a39-ae19-4f17-babb-6be2ce51d9d2", "a4168668-a896-4c03-af5f-ffb8330fecdf", "a5dfe600-7259-47e7-b36e-db66bc6f11ba", "a6e6a6ae-67c9-4e49-ba32-efa99dcf1d89", "ad5d78f3-4903-4c25-99cb-85c19415d809", "ad8097aa-790d-4cc0-b379-e4ea49c990a9", "adb14d7b-1387-4cf4-a97d-07ec9235e608", "af8f81f7-b0d5-482b-819f-87d2a5aa6c26", "b3a80cf3-bb7d-4040-8dfa-9188c84704c4", "b50c4f19-4120-4576-a84c-e21d388d914e", "bcadf39e-cf9d-4a6b-9793-cd42f36b16af", "ca413c88-1abf-444b-a3c5-8a1072b47f6c", "cb5e2fd9-527d-40ab-8263-93915b2fb5dd", "cba4a90c-b78a-455e-bfa5-fb4dfbf49c81", "d302a57f-651c-47b7-9915-59e9d961daa8", "d33b7b0d-6669-4495-85b7-dd44fb292d2e", "d45abf46-86f2-4a8f-a605-c30c7ff7d984", "d69b331e-3fda-4b56-8b65-2a3465622985", "db9f8005-77f4-4558-a948-5da0edee79d9", "e077c2a6-34ac-4f97-8c54-09cb821ce20c", "e15ea132-b506-4789-897f-28f5bf66b3c5", "e57299b8-746f-4768-b55a-f0b24fe153e9", "e67349f9-4bec-4153-8258-845461ff233c", "e8662040-cea9-4a68-8e8b-ebfd3e09ce77", "e9324b22-558b-44b1-aa73-593c436513ad", "e9557cff-bb74-43eb-9d0c-664cc2f8eba2", "e9c084c5-faf4-49f3-a6ba-de838588048c", "ea3d8bf3-67f0-4b38-ac62-2c94a3c73c1f", "f20e05cc-d22d-4fa2-b76d-ade64cbc34bd", "f76b0b68-bc2b-4084-8b95-8a99134235b2", "fa667578-ed71-4f9b-83e5-52be6020249a", "fe022fff-61a8-4c95-96d1-4325f53d5017", "fe26eed8-6b57-417e-b9c9-cd1da7beee0d", "ffe1c76e-2b4d-4b2d-a3cb-c3bd5e843466");

		DateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");

		for(String communityid : communityidList) {
			final Criteria criteria = Criteria
					.where("regionid").is("c846a27a-087c-4669-9b47-f833d07a88e2")
					.and("communityid").is(communityid)
					.and("jlly").in(Arrays.asList(1, 2, 3))
					.and("cjlx").in(Arrays.asList(0, 1, 2, 3, -1))
					.and("ljlx").in(Arrays.asList("0205", "1602", "0305", "0A02", "0209", "14", "0103", "DJ02", "0409", "JD36", "0413", "JD30", "0E01", "0J01", "JD04", "0210", "DJ48", "0F1F", "JD44", "0A10", "JD14", "JD21", "0411", "JD03", "JD31", "JD43", "JD08", "0FOF", "0E05", "0J02", "0408", "040410", "0A06", "0419", "JD09", "JD42", "0399", "0E02", "JD06", "JD16", "0k", "0334", "JD23", "JD15", "09DD", "0L46", "0216", "1503", "1501", "JD39", "0417", "JD17", "0A04", "JD38", "JD34", "0J", "JD05", "09DE", "FF", "0416", "16", "JD37", "JD28", "0J03", "25", "JD45", "0A07", "0L", "1603", "01", "0101", "02", "0201", "0202", "0203", "0204", "03", "0301", "0302", "0303", "0304", "04", "0401", "0402", "0403", "0404", "0405", "0406", "07", "0B", "0E", "JD32", "0A09", "0A08", "0412", "JD48", "JD07", "JD47", "DJ03", "JD29", "1601", "JD18", "0k01", "0M44", "JD10", "JD50", "21", "0A05", "0207", "JD35", "JD02", "0410", "JD46", "15", "FFFW", "0407", "0M", "0316", "0208", "JD49", "JD19", "JD25", "JD20", "JD26", "JD24", "JD11", "JD27", "0217", "JD", "1401", "JD12", "0102", "0B47", "DJ01", "0E03", "020210", "JD13", "1502", "JD01", "4901", "0k02", "0E04", "010104", "0F", "10", "13", "JD33", "0414", "JD41", "JD22", "0L45", "0415", "0A03"))
					.and("tfsj").gte(yyyyMMdd.parseObject("20200501"))
					.and("tfsj").lt(yyyyMMdd.parseObject("20200601"));
			final Query query = new Query();
			query.addCriteria(criteria);
			//query.withHint("");
			query.limit(10);

			final List<Object> l = oneMongoTemplate.find(query, Object.class);
			for(Object o : l) {
				System.out.println(o);
			}
		}
	}

	@GetMapping("/count")
	public void q() {
		Criteria criteria = Criteria.where("regionid")
				.in(Arrays.asList("000de5db-aa78-479d-8d70-7b3267811568", "04388528-8eb4-41a8-9589-8cf68a7299d8",
						"06c7372e-6c09-475c-9f74-cacb2a050ab6", "07a62a5c-7ae5-4305-a414-fba3ea1e4af5",
						"0c728efd-391a-4496-939d-7777622f8be7", "237c5130-be38-4611-b94c-75a84f19be5f",
						"251c01a7-6847-4f48-978b-e61b6c00bee9", "2a831d64-a317-4a37-8055-47804781a2c2",
						"313a6fb9-cb47-415d-86cd-ca1f02f9d608", "3a61a2b9-3a4f-473a-ac79-a576aacab5d4",
						"3eca810e-1a28-4b8b-8695-b9c6a4e0ae5d", "451f6a64-4e1f-4823-a9a5-9191a022fa8b",
						"4e52da86-8566-4b8a-8f46-2c00ab6c9251", "55b50a03-5927-4868-afe4-2c834effcb21",
						"6a648f42-15ae-4678-8fe1-87530b21f4b7", "6a8d5caf-405e-4bfe-925d-61f279e1c2c8",
						"8a5d5a1a-7018-4920-a974-5a720ba91f37", "9dd297d8-b756-4b39-851c-134bdb330824",
						"b889ecfa-597e-44f8-8036-88fbcb2c8d49", "d9765bf6-91c2-4339-bd5e-f70e54e89cae"))
				.and("jlly").in(Arrays.asList(1, 2, 3)).and("productid").is("58fd4d48-cbe5-430c-8cca-8b5b39469bd5")
				.and("tenantid").is("a926e726-9424-4248-9493-34859f21fc84").and("cjlx")
				.in(Arrays.asList(0, 1, 2, 3, -1)).and("ljlx")
				.in(Arrays.asList("05", "06", "09", "0A", "0C", "0D", "11"));
		final Query query = new Query();
		query.addCriteria(criteria);

		Calendar start = Calendar.getInstance();
		start.set(2019, 0, 1);
		
		List<Thread> l = new ArrayList<>();
		

		int num = 350;
		final CountDownLatch latch = new CountDownLatch(num);
		
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		for (int i = 0; i < num; i++) {
			final String yyyyMMdd = df.format(start.getTime());
			final String collectionName = "h_recycle_record_day_" + yyyyMMdd;
			l.add(new Thread(new Runnable() {
				public void run() {
					long a = System.currentTimeMillis();
					if(yyyyMMdd.startsWith("201901") || yyyyMMdd.startsWith("201904") || yyyyMMdd.startsWith("201907") || yyyyMMdd.startsWith("201910")) {
						oneMongoTemplate.count(query, collectionName);
					}else if(yyyyMMdd.startsWith("201902") || yyyyMMdd.startsWith("201905") || yyyyMMdd.startsWith("201908") || yyyyMMdd.startsWith("201911")) {
						twoMongoTemplate.count(query, collectionName);
					}else {
						threeMongoTemplate.count(query, collectionName);
					}
					System.out.println(collectionName + "，耗时：" + (System.currentTimeMillis() - a));
					latch.countDown();
				}
			}));
			
			start.add(Calendar.DAY_OF_YEAR, 1);
		}
		

		long b = System.currentTimeMillis();
		for(Thread t : l) {
			t.start();
		}
		
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("总耗时：" + (System.currentTimeMillis() - b));
	}
}

class Max {
	private int max;
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
}