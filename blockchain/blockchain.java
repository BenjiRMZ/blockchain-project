package blockchain;

import com.google.gson.GsonBuilder;
import java.util.ArrayList;


public class blockchain {

    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static void main(String[] args){

        blockchain.add(new Block("Firstblock", "0"));
        blockchain.add(new Block("Secondblock", blockchain.get(blockchain.size() - 1).getHash_()));
        blockchain.add(new Block("Thirdblock", blockchain.get(blockchain.size() - 1).getHash_()));
        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("Is Blockchain valid? " + checkChain());
        System.out.println(blockchainJson);
    }

    public static boolean checkChain(){
        for(int i = 1; i < blockchain.size(); i++){
            Block currentblock = blockchain.get(i);
            Block previousblock = blockchain.get(i-1);
            if(!currentblock.getHash_().equals(currentblock.computeHash())){
                System.out.println("Hash is not equal");
                return false;
            }

            if(!previousblock.getHash_().equals(currentblock.getPrevHash_())){
                System.out.println("Previous Hash not equal");
                return false;
            }
        }
        return true;
    }
}
