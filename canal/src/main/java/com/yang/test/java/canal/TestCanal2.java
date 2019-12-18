package com.yang.test.java.canal;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;

public class TestCanal2 {

	public static void main(String args[]) {
		CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress("192.168.194.65", 11111), "example", "", "");
		int batchSize = 1000;

		connector.connect();
		connector.subscribe("lyzhhw4.test"); //instance.properties
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

				connector.ack(batchId); // 提交确认
			} catch (Exception e) {
				connector.rollback(batchId); // 处理失败, 回滚数据
			}
		}

		//connector.disconnect();
	}

	private static void printEntry(List<Entry> entrys, long batchId) {
		for (Entry entry : entrys) {
			if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
				continue;
			}

			RowChange rowChage = null;
			try {
				rowChage = RowChange.parseFrom(entry.getStoreValue());
			} catch (Exception e) {
				throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(), e);
			}

			EventType eventType = rowChage.getEventType();
			System.out.println(String.format("batchId["+batchId+"]，binlog[%s:%s]，name[%s,%s]，eventType : %s，数据量：" + rowChage.getRowDatasList().size(), entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader().getSchemaName(), entry.getHeader().getTableName(), eventType));

			if(entry.getHeader().getTableName().equals("h_recycle_record")) {
				for (RowData rowData : rowChage.getRowDatasList()) {
					if (eventType == EventType.DELETE) {
						System.out.println("--------------------------> DELETE");
						printColumn(rowData.getBeforeColumnsList());
					} else if (eventType == EventType.INSERT) {
						System.out.println("--------------------------> INSERT");
						printColumn(rowData.getAfterColumnsList());
					} else if (eventType == EventType.CREATE) {
						System.out.println("--------------------------> CREATE");
					} else {
						System.out.println("--------------------------> BEFORE");
						printColumn(rowData.getBeforeColumnsList());
						System.out.println("--------------------------> AFTER");
						printColumn(rowData.getAfterColumnsList());
					}
				}
			}
		}
	}

	private static void printColumn(List<Column> columns) {
		for (Column column : columns) {
			System.out.println(column.getName() + " : " + column.getValue() + "    update=" + column.getUpdated());
		}
		System.out.println();
	}
}