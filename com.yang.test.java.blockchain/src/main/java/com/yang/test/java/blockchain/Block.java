package com.yang.test.java.blockchain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

public class Block {

	public static void main(String[] args) throws NoSuchAlgorithmException {
//		Block genesisBlock = new Block("Hi im the first block", "0");
//		System.out.println("Hash for block 1 : " + genesisBlock.hash);
//
//		Block secondBlock = new Block("Yo im the second block", genesisBlock.hash);
//		System.out.println("Hash for block 2 : " + secondBlock.hash);
//
//		Block thirdBlock = new Block("Hey im the third block", secondBlock.hash);
//		System.out.println("Hash for block 3 : " + thirdBlock.hash);
		
		
		int difficulty = 6;

		

		List<Block> blockchain = new ArrayList<Block>();
		blockchain.add(new Block("Hi im the first block", "0"));
		long a = System.currentTimeMillis();
		blockchain.get(0).mineBlock(difficulty);
		System.out.println("第一次耗时："+(System.currentTimeMillis() - a)/1000+"秒");

		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		long b = System.currentTimeMillis();
		blockchain.get(1).mineBlock(difficulty);
		System.out.println("第二次耗时："+(System.currentTimeMillis() - b)/1000+"秒");

		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
		long c = System.currentTimeMillis();
		blockchain.get(2).mineBlock(difficulty);
		System.out.println("第三次耗时："+(System.currentTimeMillis() - c)/1000+"秒");

 

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);      
        
        System.out.println(blockchainJson);
        //System.out.println(isChainValid(blockchain));
	}
	
	public String hash;
	public String previousHash;
	private String data; // our data will be a simple message.
	private long timeStamp; // as number of milliseconds since 1/1/1970.
	private int nonce;


	public Block(String data, String previousHash) throws NoSuchAlgorithmException {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = System.currentTimeMillis();
		this.hash = calculateHash(); // Making sure we do this after we set the other values.
	}

    public void mineBlock(int difficulty) throws NoSuchAlgorithmException {
        String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
        while(!hash.substring(0, difficulty).equals(target)) {
        	nonce ++;
            hash = calculateHash();
        }
    }
	
	public static Boolean isChainValid(List<Block> blockchain) throws NoSuchAlgorithmException {
	    Block currentBlock; 
	    Block previousBlock;

	    //loop through blockchain to check hashes:
	    for(int i=1; i < blockchain.size(); i++) {
	        currentBlock = blockchain.get(i);
	        previousBlock = blockchain.get(i-1);
	        //compare registered hash and calculated hash:
	        if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
	            System.out.println("Current Hashes not equal");         
	            return false;
	        }
	        //compare previous hash and registered previous hash
	        if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
	            System.out.println("Previous Hashes not equal");
	            return false;
	        }
	    }
	    return true;
	}
	
	public String calculateHash() throws NoSuchAlgorithmException {
		String calculatedhash = sha256(previousHash + Long.toString(timeStamp) + nonce + data);
		return calculatedhash;
	}

	public static String sha256(String content) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(content.getBytes());
		String hash = new BigInteger(1, md.digest()).toString(16); // 16表示16进制
		StringBuilder sb = new StringBuilder(hash);
		int i = 64 - hash.length();
		while (i > 0) {
			sb.insert(0, "0");
			i--;
		}
		return sb.toString();
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
	public int getNonce() {
		return nonce;
	}
	public void setNonce(int nonce) {
		this.nonce = nonce;
	}
}