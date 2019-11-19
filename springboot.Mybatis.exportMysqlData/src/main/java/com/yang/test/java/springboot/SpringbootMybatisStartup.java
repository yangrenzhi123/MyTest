package com.yang.test.java.springboot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yang.test.java.springboot.controller.TestDingding;
import com.yang.test.java.springboot.dao.UserDao;
import com.yang.test.java.springboot.entity.RecycleRecord;

@SpringBootApplication
@MapperScan("com.yang.test.java.springboot.dao")
public class SpringbootMybatisStartup {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisStartup.class, args);
	}

	@Resource
	private UserDao userDao;

	@Bean
	public Integer init() throws FileNotFoundException {

		RandomAccessFile f = new RandomAccessFile("C:/h_recycle_record_1_10000", "rw");
		FileChannel fileChannel = f.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(1024);
		
		new Thread(new Runnable() {
			public void run() {
				long start = 0;
				int i = 0;
				while (true) {
					try {
						long a = System.currentTimeMillis();
						List<RecycleRecord> l = userDao.limitRecycleRecord(start);
						long aa = System.currentTimeMillis() - a;

						if(l.size() == 0) {
							break;
						}

						for(RecycleRecord item : l) {
							String s = 
							item.getRecyclerecordid() + "\t" + 
							item.getTenantid() + "\t" + 
							item.getProductid() + "\t" + 
							item.getOrderid() + "\t" + 
							item.getRegionid() + "\t" + 
							item.getCommunityid() + "\t" + 
							item.getYzqyid() + "\t" + 
							item.getYxxqid() + "\t" + 
							item.getCjlx() + "\t" + 
							item.getSbbh() + "\t" + 
							item.getEquipmentid() + "\t" + 
							item.getTfbz() + "\t" + 
							item.getYhlx() + "\t" + 
							item.getTenantgroupid() + "\t" + 
							item.getOperateuserid() + "\t" + 
							item.getLjlx() + "\t" + 
							item.getHsms() + "\t" + 
							item.getTdqzl() + "\t" + 
							item.getTdhzl() + "\t" + 
							item.getTdzl() + "\t" + 
							item.getYszl() + "\t" + 
							item.getTddj() + "\t" + 
							item.getHdjf() + "\t" + 
							item.getSdjf() + "\t" + 
							item.getTffs() + "\t" + 
							item.getJlly() + "\t" + 
							item.getSjly() + "\t" + 
							item.getDxfssl() + "\t" + 
							item.getTfsj() + "\t" + 
							item.getGrowth_value() + "\t" + 
							item.getGrowth_value_singleprice() + "\t" + 
							item.getCreateuser() + "\t" + 
							item.getCreatetime() + "\t" + 
							item.getActive() + "\t" + 
							item.getRecyclerecordzzid() + "\t" + 
							item.getQy_projectid() + "\t" + 
							item.getEtl() + "\t" + 
							item.getGdspid() + "\r\n";

							buf.clear();
							buf.put(s.getBytes());
							buf.flip();
							while (buf.hasRemaining()) {
								fileChannel.write(buf);
							}
						}
						
						
						RecycleRecord last = l.get(l.size() - 1);
						System.out.println("查询耗时：" + aa + "，数据条数：" + l.size() + "，最后一条记录ID：" + last.getRecyclerecordzzid());
						start = last.getRecyclerecordzzid();
					}catch(Exception e) {
						e.printStackTrace();
						if(i++ > 5) TestDingding.test("h_recycle_record，数据导出线程，异常");
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					}
					
					i = 0;
				}
				
				try {
					f.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "h_recycle_record，数据导出线程").start();
		return 1;
	}
}