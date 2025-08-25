package blockchain;

import com.google.gson.Gson;
import java.time.Instant;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private final String data_;
    private final String prevHash_;
    private final String hash_;
    private final String timestamp_;

    public Block(String data, String prevHash){
        this.data_ = data;
        this.prevHash_ = prevHash;
        this.timestamp_ = Instant.now().toString();
        this.hash_ = computeHash();
    }

    public String computeHash() {
        String payload = new Gson().toJson(new Object() {
            final String data = Block.this.data_;
            final String prevHash = Block.this.prevHash_;
            final String timestamp = Block.this.timestamp_;
        });
        return Sha256(payload);
    }


    public static String Sha256(String input){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuffer hexString = new StringBuffer();
            for(int i = 0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
/*
    public String computeHash(){
        return Sha256(prevHash_ + data_ + timestamp_);
    }

 */

    @Override
    public String toString() {
        return "Block{ts=" + timestamp_ +
                ", data=\"" + data_ + "\"" +
                ", prev=" + prevHash_ +
                ", hash=" + hash_;
    }

    public String getHash_(){return hash_;}
    public String getPrevHash_(){return prevHash_;}
}

