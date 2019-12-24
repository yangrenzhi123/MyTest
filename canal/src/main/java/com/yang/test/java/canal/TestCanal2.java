package com.yang.test.java.canal;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import com.google.protobuf.InvalidProtocolBufferException;

public class TestCanal2 {

	public static void main(String args[]) {
		int batchSize = 1;

		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.30.121", 11111), "example", "", "");
		connector.connect();
		connector.subscribe("lyzhhw4.test,lyzhhw4.h_inspect_record"); //instance.properties
		while (true) {
			long batchId = 0;
			Message message = null;
			try {
				message = connector.getWithoutAck(batchSize);
				batchId = message.getId();
			}catch(CanalClientException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
				}
				continue;
			}
			try {
				int size = message.getEntries().size();
				if (batchId == -1 || size == 0) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				} else {
					printEntry(message.getEntries(), batchId);
				}

				connector.ack(batchId);
			} catch (Exception e) {
				connector.rollback(batchId);
			}
		}
	}

	private static void printEntry(List<Entry> entrys, long batchId) throws InvalidProtocolBufferException {
		for (Entry entry : entrys) {
			RowChange rowChage = RowChange.parseFrom(entry.getStoreValue());
			EventType eventType = rowChage.getEventType();
			if(entry.getHeader().getTableName().equals("test")) {
				System.out.println();
			}
			
			System.out.println(String.format("batchId[" + batchId + "]，binlog[%s:%s]，name[%s,%s]，eventType : %s，数据量：" + rowChage.getRowDatasList().size(), entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));
		}
	}
}