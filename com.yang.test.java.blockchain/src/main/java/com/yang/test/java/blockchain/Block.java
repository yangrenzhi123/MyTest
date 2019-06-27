package com.yang.test.java.blockchain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

public class Block {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		Block genesisBlock = new Block("Hi im the first block", "0");
		System.out.println("Hash for block 1 : " + genesisBlock.hash);

		Block secondBlock = new Block("Yo im the second block", genesisBlock.hash);
		System.out.println("Hash for block 2 : " + secondBlock.hash);

		Block thirdBlock = new Block("Hey im the third block", secondBlock.hash);
		System.out.println("Hash for block 3 : " + thirdBlock.hash);
		
		
		
		

		ArrayList<Block> blockchain = new ArrayList<Block>();
		blockchain.add(new Block("Hi im the first block", "0"));
		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));

 

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);      
        System.out.println(blockchainJson);
	}
	
	public String hash;
	public String previousHash;
	private String data; // our data will be a simple message.
	private long timeStamp; // as number of milliseconds since 1/1/1970.

	public Block(String data, String previousHash) throws NoSuchAlgorithmException {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = System.currentTimeMillis();
		this.hash = calculateHash(); // Making sure we do this after we set the other values.
	}

	public String calculateHash() throws NoSuchAlgorithmException {
		String calculatedhash = sha256(previousHash + Long.toString(timeStamp) + data);
		return calculatedhash;
	}

	public static String sha256(String content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes());
		return new BigInteger(1, md.digest()).toString(16);
	}

	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPreviousHash() {
		return previousHash;
	}
	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
}